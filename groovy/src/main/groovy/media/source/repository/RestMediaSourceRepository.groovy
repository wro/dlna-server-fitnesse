package media.source.repository

import media.source.MediaSource
import rest.RestClient

class RestMediaSourceRepository implements MediaSourceRepository {

    RestClient client

    @Override
    void create(MediaSource mediaSource) {
        def rest = new RestMediaSource(delegate: mediaSource)
        rest.check()
        client.put(path: "rest/media-source/$rest.name", body: """{"location":"$rest.location","description":"$rest.description"}""", requestContentType: 'application/json')
    }

    @Override
    Set<MediaSource> getMediaSources() {

    }

    @Override
    void remove(String name) {

    }

    class RestMediaSource implements MediaSource {
        @Delegate MediaSource delegate

        void check() {
            if (!name?.trim()) throw new MediaSource.NameRequired()
        }
    }
}
