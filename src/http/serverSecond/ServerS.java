package http.serverSecond;

import http.Quote;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class ServerS {

    public static final int TCP_PORT_SEC = 8114;
    public static final ArrayList<Quote> qods = new ArrayList<>();


        public static void main(String[] args) {
            qods.add(new Quote("Tachanka", "LMG Mounted & Ready!"));
            qods.add(new Quote("Tachanka", "LMG Mounted & Ready!"));
            qods.add(new Quote("Mute", "WOLL SECOORED"));
            qods.add(new Quote("Blitz", "I see you've got a few new holes in you, let's get them plugged up ja?"));
            qods.add(new Quote("Jager", "I am an engineer, not a medic!"));
            qods.add(new Quote("Smoke", "Nest of beauty's in position."));
            qods.add(new Quote("Rook", "Time for some serious protection!"));
            qods.add(new Quote("Thatcher", "Fookin' Laser Sights"));
            qods.add(new Quote("Kapkan", "EDD primed, let them come."));
            qods.add(new Quote("IQ", "If it runs on Batteries, I'll see it!"));
            qods.add(new Quote("Thermite", "A really big fucking hole coming right up!"));

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

    public static Quote getQod() {
        return qods.get(new Random().nextInt(qods.size()));
    }
}
