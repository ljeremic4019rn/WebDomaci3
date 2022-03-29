package http.serverSecond;

import http.response.Response;

public class HtmlResponseBack extends Response {

    private final String json;

    public HtmlResponseBack(String json) {
        this.json = json;
    }

    @Override
    public String getResponseString() {
        String response = "HTTP/1.1 200 OK\r\nContent-Type: text/json\r\nContent-Length: "+json.length()+"\r\n\r\n";
        response += json;

        return response;
    }
}