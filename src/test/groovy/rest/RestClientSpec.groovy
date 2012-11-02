package rest

import groovyx.net.http.RESTClient
import spock.lang.Specification

class RestClientSpec extends Specification {

    def client = new GroovyRestClient('baseUrl')

    def capturedRequest = [:]

    def setup() {
        RESTClient.metaClass.put {Map m->
            capturedRequest = m
            [status: 201, data: 'data']
        }
    }

    def cleanup() {
        GroovySystem.metaClassRegistry.removeMetaClass RESTClient
    }

    def "put request"() {
        given:
        def request = new Request(path: 'path', body: 'body', requestContentType: 'contentType')

        when:
        def response = client.put(request)

        then:
        client.restClient in RESTClient
        client.restClient.defaultURI.path == 'baseUrl'
        capturedRequest.path == 'path'
        capturedRequest.body == 'body'
        capturedRequest.requestContentType == 'contentType'
        response.status == 201
        response.data == 'data'
    }

    class Request implements RestClient.Request {
        String path
        String body
        String requestContentType
    }

}
