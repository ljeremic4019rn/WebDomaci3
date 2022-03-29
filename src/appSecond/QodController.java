package appSecond;

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
        Quote qotd = ServerS.getQod();
        return new HtmlResponseBack(qotd.toString());
    }

    @Override
    public Response doPost() {
        // TODO: obradi POST zahtev
        return null;
    }
}
