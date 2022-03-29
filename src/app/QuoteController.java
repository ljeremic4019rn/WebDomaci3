package app;

import com.google.gson.Gson;
import http.Quote;
import http.response.HtmlResponse;
import http.response.RedirectResponse;
import http.response.Response;
import http.serverMain.Request;
import http.serverMain.Server;

import java.io.*;
import java.net.Socket;

public class QuoteController extends Controller {


    PrintWriter out = null;
    BufferedReader in = null;
    String qod = "";
    String listElements = "";


    public QuoteController(http.Request request) {
        super(request);
    }

    @Override
    public Response doGet() {

        for (Quote q: Server.quotes) {
            listElements = listElements + "<li>"+ q.getAuthor() + ": " + q.getQuote();
        }

        try {
            Socket socket = new Socket("localhost", 8114);
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            Request request = new Request();//saljemo request za qod
            out.println(request.getRequestString());


            String requestLine = in.readLine();
            int contentLength = 0;
            do {
                if(requestLine.contains("Content-Length")) {
                    contentLength = Integer.parseInt(requestLine.split(":")[1].trim());//parsiramo qod
                }
                requestLine = in.readLine();
            } while (!requestLine.trim().equals(""));

            char[] buffer = new char[contentLength];
            in.read(buffer);


            String quoteRaw = new String(buffer);
            System.out.println("raw");
            System.out.println(quoteRaw); //"{\"author\":Thermite,\"quote\":A really big fucking hole coming right up!}"

            quoteRaw = quoteRaw.replace("{", "");
            quoteRaw = quoteRaw.replace("}", "");


            String []tmp = quoteRaw.split(",");
            String []author = tmp[0].split(":");
            String []quote = tmp[1].split(":");

            qod = author[1]  + " - " + quote[1].replace("\"","");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String htmlBody = "" +
                "<form method=\"POST\" action=\"/save-quote\">" +
                "<label>Author: </label>" + "<p>" +"<input name=\"author\" type=\"text\"><br><br>" +
                "<label>Quote: </label>" + "<p>" + "<input name=\"quote\" type=\"text\"><br><br>" +
                "<button>Submit</button>" +
                "</form>" +
                "<p> Quote of the day: <p>" +
                "<p>" + qod + "<p>" +
                "<p> saved quotes: </p>" +
                "<ul>" + listElements + "</ul>";


        String content = "<html><head><title>Odgovor servera</title></head>\n";
        content += "<body>" + htmlBody + "</body></html>";
        return new HtmlResponse(content);
    }

    @Override
    public Response doPost() {
//        System.out.println("sacuvan quote");
        return new RedirectResponse("/quote");
    }
}
