package media.source.repository

import rest.FakeRestClient

import static media.source.fixtures.MediaSourceFixtures.getValidMediaSource

class RestMediaSourceRepositorySpec extends MediaSourceRepositorySpec {

    def repository = new RestMediaSourceRepository()
    def client = new FakeRestClient()

    def setup() {
        repository.client = client
    }

    @Override
    MediaSourceRepository getRepository() {
        repository
    }

    def "create sends put request"() {
        when:
        repository.create(validMediaSource)

        then:
        client.capturedMethod == 'PUT'
        client.capturedPath == "rest/media-source/$validMediaSource.name"
        client.capturedBody == """{"location":"$validMediaSource.location","description":"$validMediaSource.description"}"""
        client.capturedRequestContentType == 'application/json'

    }

    boolean validatePutRequest(Map request) {
        assert request.path == "rest/media-source/$validMediaSource.name"
        assert request.body == """{"location":"$validMediaSource.location","description":"$validMediaSource.description"}"""
        assert request.requestContentType == 'application/json'
        return true
    }
}
