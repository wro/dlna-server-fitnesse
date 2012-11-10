package media.source

import rest.RestClient
import spock.lang.Ignore
import spock.lang.Specification

@Ignore
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
    }

    def "if status is not 201 return failure"() {
        given:
        fixture.client = client

        when:
        fixture.execute()

        then:
        fixture.result == 'failure'
    }

    class FakeResponse implements RestClient.Response {
        int status
        String data
    }

}
