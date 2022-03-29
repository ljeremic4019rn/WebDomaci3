package appSecond;

import com.google.gson.Gson;
import http.Quote;
import http.Request;
import http.response.Response;
import http.serverSecond.HtmlResponseBack;
import http.serverSecond.ServerS;


public class QodController extends Controller {

    public QodController(Request request) {
        super(request);
    }

    @Override
    public Response doGet() {
        Quote qod = ServerS.getQod();
        Gson gson = new Gson();
        String json = gson.toJson(qod.jsonString());
        return new HtmlResponseBack(json);
    }

    @Override
    public Response doPost() {
        // TODO: obradi POST zahtev
        return null;
    }
}
