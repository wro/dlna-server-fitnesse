package rest

public interface RestClient {


    Response put(Map map)
    Response delete(Map map)

    interface Response {
        int getStatus()
        String getData()
    }

}