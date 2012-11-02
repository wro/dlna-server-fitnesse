package rest

public interface RestClient {


    Response put(Request request)

    interface Request {
        String getPath()
        String getBody()
        String getRequestContentType()
        Map asMap()
    }

    interface Response {
        int getStatus()
        String getData()
    }

}