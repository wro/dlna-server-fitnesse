package media.source

import rest.RestClient
import spock.lang.Specification

class AddMediaSourceSpec extends Specification {

    def fixture = new AddMediaSource()

    def client = Mock(RestClient)

    def "add a media source and return ok"() {
        given:
        fixture.name = 'name'
        fixture.location = 'location'
        fixture.description = 'description'
        fixture.client = client

        when:
        fixture.execute()

        then:
        fixture.result() == 'ok'
        1 * client.put({RestClient.Request r->
            assert r.path == "rest/media-source/name"
            assert r.body == """{"location":"location","description":"description"}"""
            assert r.requestContentType == 'application/json'
            return true
        }) >> new FakeResponse(status: 201)
        0 * _
    }

    def "if status is not 201 return failure"() {
        given:
        fixture.client = client

        when:
        fixture.execute()

        then:
        fixture.result == 'failure'
        1 * client.put(_ as RestClient.Request) >> new FakeResponse(status: 412)
        0 * _
    }

    class FakeResponse implements RestClient.Response {
        int status
        String data
    }

}
