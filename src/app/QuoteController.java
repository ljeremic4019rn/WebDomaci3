package app;

import http.Quote;
import http.Request;
import http.response.HtmlResponse;
import http.response.RedirectResponse;
import http.response.Response;
import http.serverMain.HttpRequest;
import http.serverMain.Server;

import java.io.*;
import java.net.Socket;

public class QuoteController extends Controller {


    PrintWriter out = null;
    BufferedReader in = null;
    String qotd = "";
    String listElements = "";


    public QuoteController(Request request) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpRequest httpRequest = new HttpRequest("");
        out.println(httpRequest.getRequestString());



        try {
            String requestLine = in.readLine();
            int cLength = 0;
            do {
                if(requestLine.contains("Content-Length")) cLength = Integer.parseInt(requestLine.split(":")[1].trim());
                requestLine = in.readLine();
            } while (!requestLine.trim().equals(""));

            char[] buffer = new char[cLength];
            in.read(buffer);

            String a = new String(buffer);
            a = a.replace("{", "");
            a = a.replace("}", "");
            String author = a.split(",")[0].split(":")[1];
            String quote = a.split(",")[1].split(":")[1];

            qotd = quote + " - " + author;
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
//                "<p>" + qotd + "<p>" +
                "<p> saved quotes: </p>" +
                "<ul>" + listElements + "</ul>";


        String content = "<html><head><title>Odgovor servera</title></head>\n";
        content += "<body>" + htmlBody + "</body></html>";
        return new HtmlResponse(content);
    }

    @Override
    public Response doPost() {
        System.out.println("sacuvan quote");
        return new RedirectResponse("/quote");
    }
}
