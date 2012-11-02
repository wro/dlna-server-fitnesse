package rest

public interface RestClient {


    Response put(Request request)

    interface Request {
        String getPath()
        String getBody()
        String getRequestContentType()
    }

    interface Response {
        int getStatus()
        String getData()
    }

}