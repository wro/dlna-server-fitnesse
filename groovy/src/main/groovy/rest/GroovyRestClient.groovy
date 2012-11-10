package rest

import groovyx.net.http.RESTClient

class GroovyRestClient implements RestClient {

    RESTClient restClient

    @Override
    RestClient.Response put(Map request) {
        new ResponseWrapper(delegate: restClient.put(path: request.path, body: request.body, requestContentType: request.requestContentType))
    }

    @Override
    RestClient.Response delete(Map request) {
        new ResponseWrapper(delegate: restClient.delete(path: request.path))
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
