// Copyright (C) 2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package io.github.rhdunn.exposed.dao

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.notInList
import org.jetbrains.exposed.sql.statements.InsertStatement
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface EntityRef<ID : Comparable<ID>> {
    val id: EntityID<ID>
}

interface InnerTableEntity<TID : Comparable<TID>, T : EntityRef<TID>> {
    val dependsOnTables: ColumnSet
    val table: Table

    fun wrapRow(row: ResultRow): T

    fun wrapRows(rows: SizedIterable<ResultRow>): SizedIterable<T> = rows mapLazy {
        wrapRow(it)
    }

    fun InsertStatement<*>.insert(value: T)

    fun doInsert(statement: InsertStatement<*>, value: T) = statement.insert(value)
}

@Suppress("unused")
class InnerTableLinkEx<SID : Comparable<SID>, Source : Entity<SID>, ID : Comparable<ID>, Target : EntityRef<ID>>(
    private val inner: InnerTableEntity<ID, Target>,
    private val sourceTable: IdTable<SID>,
    private val targetTable: IdTable<ID>,
) : ReadWriteProperty<Source, SizedIterable<Target>> {

    @Suppress("UNCHECKED_CAST")
    private val sourceColumn =
        inner.table.columns.singleOrNull { it.referee == sourceTable.id } as? Column<EntityID<SID>>
            ?: error("Table does not reference source")

    @Suppress("UNCHECKED_CAST")
    private val targetColumn =
        inner.table.columns.singleOrNull { it.referee == targetTable.id } as? Column<EntityID<ID>>
            ?: error("Table does not reference target")

    private var cachedValue: SizedIterable<Target>? = null

    override fun getValue(thisRef: Source, property: KProperty<*>): SizedIterable<Target> {
        if (cachedValue != null) return cachedValue!!
        val query = inner.dependsOnTables.selectAll().where { sourceTable.id eq thisRef.id }
        cachedValue = query.run { inner.wrapRows(this) }
        return cachedValue!!
    }

    override fun setValue(thisRef: Source, property: KProperty<*>, value: SizedIterable<Target>) {
        val existingIds = getValue(thisRef, property).map { it.id }
        val targetIds = value.map { it.id }
        inner.table.deleteWhere { (sourceColumn eq thisRef.id) and (targetColumn notInList targetIds) }
        inner.table.batchInsert(
            value.filter { !existingIds.contains(it.id) },
            shouldReturnGeneratedValues = false
        ) { target ->
            this[sourceColumn] = thisRef.id
            this[targetColumn] = target.id
            inner.doInsert(this, target)
        }
        cachedValue = null
    }
}
