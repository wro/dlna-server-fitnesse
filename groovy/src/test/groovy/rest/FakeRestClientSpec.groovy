package rest

class FakeRestClientSpec extends RestClientSpec {

    def client = new FakeRestClient()

    @Override
    RestClient getClient() {
        client
    }

    @Override
    Map getCapturedRequest() {
        [path: client.capturedPath, body: client.capturedBody, requestContentType: client.capturedRequestContentType]
    }

    @Override
    void withStatusToReturn(int status) {
        client.statusToReturn = status
    }

    @Override
    void withDataToReturn(String data) {
        client.dataToReturn = data
    }

    def "capture put request method"() {
        when:
        client.put([:])

        then:
        client.capturedMethod == 'PUT'
    }

    def "test"() {
        when:
        client.delete([:])

        then:
        client.capturedMethod == 'DELETE'
    }
}
