package media.source

import rest.RestClient
import spock.lang.Ignore
import spock.lang.Specification

@Ignore
class RemoveMediaSourceSpec extends Specification {

    def fixture = new RemoveMediaSource()
    def client = Mock(RestClient)

    def "test"() {
        given:
        fixture.name = 'name'
        fixture.location = 'location'
        fixture.client = client

        when:
        fixture.execute()

        then:
        fixture.result() == 'ok'

    }

}
