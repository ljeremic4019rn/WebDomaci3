package http.serverSecond;

import http.Quote;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class ServerS {

    public static final int TCP_PORT_SEC = 8114;
    public static final ArrayList<Quote> Quotes = new ArrayList<>(){{
        add(new Quote("Aca", "It is what it is."));
        add(new Quote("Nikola", "Sorry I was fighting a monster."));
        add(new Quote("Luka", "Vidi ga sto srce."));
        add(new Quote("Mladen", "Ide gas."));
        add(new Quote("Helena", "Je li to neka skrivena poruka?"));
        add(new Quote("Ana", "3 crvena nemaju rivala."));
        add(new Quote("Gabi", "Ja nisam platina."));
        add(new Quote("Milos", "Ceka me devojka."));
    }} ;

    public static void main(String[] args) {

        try {
            ServerSocket ss = new ServerSocket(TCP_PORT_SEC);
            while (true) {
                Socket sock = ss.accept();
                new Thread(new ServerThread(sock)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Quote getQod(){
        Random x = new Random();
        return ServerS.Quotes.get( x.nextInt( ServerS.Quotes.size() ) );
    }

}
