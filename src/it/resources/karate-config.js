function fn() {
    var port = java.lang.System.getProperty('karate.server.port', '8080');

    return {
        baseUrl: 'http://localhost:' + port
    };
}