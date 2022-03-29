package appSecond;

import http.HttpMethod;
import http.Request;
import http.response.Response;

public class RequestHandlerS {
    public Response handle(Request request) throws Exception {
        if (request.getPath().equals("/qod") && request.getHttpMethod().equals(HttpMethod.GET)) {
            return (new QodController(request)).doGet();
        }

        throw new Exception("Page: " + request.getPath() + ". Method: " + request.getHttpMethod() + " not found!");
    }
}
