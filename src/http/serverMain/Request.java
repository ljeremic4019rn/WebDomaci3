package http.serverMain;

public class Request {

    public Request() {
    }

    public String getRequestString() {
        String response =
                "GET /qod HTTP/1.1\r\n";

        return response;
    }
}