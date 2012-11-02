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
        result = client.put(new Request()).status == 201 ? 'ok' : 'failure'
    }

    String result() {
        result
    }

    class Request implements RestClient.Request {

        @Override
        String getPath() {
            "rest/media-source/$name"
        }

        @Override
        String getBody() {
            """{"location":"$location","description":"$description"}"""
        }

        @Override
        String getRequestContentType() {
            'application/json'
        }

        @Override
        Map asMap() {
            [path: path, body: body, requestContentType: requestContentType]
        }
    }
}
