package rest

class FakeRestClient implements RestClient {
    String capturedPath
    String capturedBody
    String capturedRequestContentType
    int statusToReturn
    String dataToReturn
    String capturedMethod

    @Override
    RestClient.Response put(Map map) {
        capturedMethod = 'PUT'
        captureRequest(map)
        return response
    }

    private FakeResponse getResponse() {
        new FakeResponse(status: statusToReturn, data: dataToReturn)
    }

    private void captureRequest(Map map) {
        capturedPath = map.path
        capturedBody = map.body
        capturedRequestContentType = map.requestContentType
    }

    @Override
    RestClient.Response delete(Map map) {
        capturedMethod = 'DELETE'
        captureRequest(map)
        return response
    }

    class FakeResponse implements RestClient.Response {
        int status
        String data
    }
}
