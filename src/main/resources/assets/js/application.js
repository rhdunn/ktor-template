document.addEventListener('htmx:configRequest', evt => {
    // Workaround for https://github.com/bigskysoftware/htmx/issues/1229#issuecomment-2211895516
    evt.detail.headers['Accept'] = 'text/html'
})
