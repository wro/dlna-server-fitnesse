package media.source

import rest.GroovyRestClient
import rest.RestClient

class AddMediaSource {
    String name
    String location
    String description

    String result
    RestClient client = new GroovyRestClient("http://dlna-server.be:7070/")

    void execute() {
        result = client.put([path: '', body: '', requestContentType: '']).status == 201 ? 'ok' : 'failure'
    }

    String result() {
        result
    }
}
