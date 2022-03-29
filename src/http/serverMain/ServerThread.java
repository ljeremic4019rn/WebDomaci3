package http.serverMain;

import app.RequestHandler;
import http.HttpMethod;
import http.Quote;
import http.Request;
import http.response.Response;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import static http.serverMain.Server.quotes;

public class ServerThread implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;

    public ServerThread(Socket sock) {
        this.client = sock;

        try {
            //inicijalizacija ulaznog toka
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            //inicijalizacija izlaznog sistema
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            // uzimamo samo prvu liniju zahteva, iz koje dobijamo HTTP method i putanju
            String requestLine = in.readLine();

            StringTokenizer stringTokenizer = new StringTokenizer(requestLine);

            String method = stringTokenizer.nextToken();
            String path = stringTokenizer.nextToken();

            System.out.println("\nHTTP ZAHTEV KLIJENTA:\n");
            int contentLength = 0;

            do {
                System.out.println(requestLine);
                if(requestLine.contains("Content-Length")) {
                    contentLength = Integer.parseInt(requestLine.split(":")[1].trim());
                }
                requestLine = in.readLine();
            } while (!requestLine.trim().equals(""));

            if (method.equals(HttpMethod.POST.toString())) {
                char[] buffer = new char[contentLength];
                in.read(buffer);

                String a = new String(buffer);

                String [] split = a.trim().split("&"); //0 author 1 quote
                String [] split2 = split[0].split("=");
                String [] split3 = split[1].split("=");
//                System.err.println(split2[1] + " + " + split3[1]);
                quotes.add(new Quote(split2[1],split3[1]));
            }

            Request request = new Request(HttpMethod.valueOf(method), path);

            RequestHandler requestHandler = new RequestHandler();
            Response response = requestHandler.handle(request);

            System.out.println("\nHTTP odgovor:\n");
            System.out.println(response.getResponseString());

            out.println(response.getResponseString());

            in.close();
            out.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
