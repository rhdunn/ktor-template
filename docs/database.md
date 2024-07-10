# Database

1. [Configuration](#configuration)
2. [IntelliJ Ultimate](#intellij-ultimate)
    1. [Preparation](#preparation)
    2. [Creating the Database](#creating-the-database)
    3. [Managing the Database](#managing-the-database)
3. [Setup](#setup)

## Configuration

You can control the database settings via environment variables:

- *database user name* &ndash; the `DB_USERNAME` environment variable;
- *database user password* &ndash; the `DB_PASSWORD` environment variable;
- *database connection string* &ndash; the `DB_URL` environment variable.

## IntelliJ Ultimate

### Preparation

1. Open the Database tool window (View > Tool Windows > Database)
2. Install the PostgreSQL driver:
    1. `+` (New) > Driver
    2. Complete Support > PostgreSQL
    3. Driver Files > Download ver. ...
    4. OK

### Creating the Database

1. Add a postgres data source to run the database management commands against:
    1. `+` (New) > Data Source > PostgreSQL
    2. Host: set to the hostname/ip address of the database, e.g. `localhost`
    3. User: `postgres`
    4. Password: the postgres user password
    5. Database: `postgres`
    6. Use "Test Connection" to check that the settings are working
    7. OK
2. Open a query console on the `postgres` database/user to run the setup commands on:
    1. Jump to Query Console... Database toolbar icon
    2. Default Query Console
3. Run the [Setup](#setup) SQL commands in the console

### Managing the Database

1. Add a postgres data source for the database:
    1. `+` (New) > Data Source > PostgreSQL
    2. Host: set to the hostname/ip address of the database, e.g. `localhost`
    3. User: the *database user name*
    4. Password: the librarian user password
    5. Database: the name of the database in the *database connection string*
    6. Use "Test Connection" to check that the settings are working
    7. OK
2. Sync the database schemas (e.g. after running `flywayMigrate`):
    1. Select and expand the connection: e.g. `librarian@localhost` > *database name* > `public`
    2. Refresh Database tool icon

## Setup

Create the `username` user to manage the librarian database(s):

    CREATE USER username WITH ENCRYPTED PASSWORD 'password';

Create the database:

    CREATE DATABASE database OWNER username;
