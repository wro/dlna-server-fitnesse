package rest

import groovyx.net.http.RESTClient

class GroovyRestClientSpec extends RestClientSpec {

    def restClient = new RESTClient('baseUrl')
    def client = new  GroovyRestClient(restClient: restClient)
    def capturedRequest = [:]
    def statusToReturn
    def dataToReturn

    def setup() {
        RESTClient.metaClass.put {Map m->
            capturedRequest = m
            [status: statusToReturn, data: dataToReturn]
        }
        RESTClient.metaClass.delete {Map m->
            capturedRequest = m
            [status: statusToReturn, data: dataToReturn]
        }
    }

    def cleanup() {
        GroovySystem.metaClassRegistry.removeMetaClass RESTClient
    }

    @Override
    RestClient getClient() {
        client
    }

    @Override
    Map getCapturedRequest() {
        capturedRequest
    }

    @Override
    void withStatusToReturn(int status) {
        statusToReturn = status
    }

    @Override
    void withDataToReturn(String data) {
        dataToReturn = data
    }
}
