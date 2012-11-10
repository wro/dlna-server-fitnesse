package rest

import spock.lang.Specification

abstract class RestClientSpec extends Specification {

    def request = [:]

    def "put request"() {
        request.path = 'path'
        request.body = 'body'
        request.requestContentType = 'contentType'
        withStatusToReturn(201)
        withDataToReturn('data')

        when:
        def response = client.put(request)

        then:
        capturedRequest.path == 'path'
        capturedRequest.body == 'body'
        capturedRequest.requestContentType == 'contentType'
        response.status == 201
        response.data == 'data'
    }

    abstract RestClient getClient()

    abstract Map getCapturedRequest()

    abstract void withStatusToReturn(int status)

    abstract void withDataToReturn(String data)

    def "delete request"() {
        given:
        request.path = 'path'
        withStatusToReturn(204)
        withDataToReturn('data')

        when:
        def response = client.delete(request)

        then:
        capturedRequest.path == 'path'
        response.status == 204
        response.data == 'data'
    }
}
