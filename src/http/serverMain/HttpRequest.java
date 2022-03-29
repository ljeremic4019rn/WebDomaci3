package http.serverMain;

public class HttpRequest  {

    private final String http;

    public HttpRequest(String http) {
        this.http = http;
    }

    public String getRequestString() {
        String response =
                "GET /qod HTTP/1.1\r\n" +
                        "Host: localhost:8113\r\n" +
                        "Connection: keep-alive\r\n" +
                        "sec-ch-ua: \" Not A;Brand\";v=\"99\", \"Chromium\";v=\"99\", \"Google Chrome\";v=\"99\"\r\n" +
                        "sec-ch-ua-mobile: ?0\r\n" +
                        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.82 Safari/537.36\r\n" +
                        "sec-ch-ua-platform: \"Windows\"\r\n" +
                        "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n" +
                        "Sec-Fetch-Site: same-origin\r\n" +
                        "Sec-Fetch-Mode: no-cors\r\n" +
                        "Sec-Fetch-Dest: image\r\n" +
                        "Accept-Encoding: gzip, deflate, br\r\n" +
                        "Accept-Language: en-US,en;q=0.9,sr-RS;q=0.8,sr;q=0.7,hr;q=0.6,tr;q=0.5\r\n" +
                        "Referer: http://localhost:8113/quote\r\n\r\n";
        response += http;

        return response;
    }
}