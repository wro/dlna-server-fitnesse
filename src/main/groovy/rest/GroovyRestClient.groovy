package rest

import groovyx.net.http.RESTClient

class GroovyRestClient implements RestClient {

    RESTClient restClient

    GroovyRestClient(String baseUrl) {
        restClient = new RESTClient(baseUrl)
    }

    @Override
    RestClient.Response put(RestClient.Request request) {
        new ResponseWrapper(delegate: restClient.put(request.asMap()))
    }

    class ResponseWrapper implements RestClient.Response {
        def delegate

        @Override
        int getStatus() {
            delegate.status
        }

        @Override
        String getData() {
            delegate.data
        }
    }
}
