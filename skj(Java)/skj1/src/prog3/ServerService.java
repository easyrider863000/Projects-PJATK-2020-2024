package prog3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ServerService implements Runnable{
    DatagramPacket dp = new DatagramPacket(new byte[1400], 1400);
    List<Integer> liczby = Collections.synchronizedList(new ArrayList<>());
    static List<Integer> liczbyAll = Collections.synchronizedList(new ArrayList<Integer>());
    static HashSet<String> clients = new HashSet<>();

    DatagramSocket socketServer;

    public ServerService(DatagramSocket socketServer) {
        this.socketServer = socketServer;
    }

    @Override
    public void run() {

        try {
            socketServer.receive(dp);
            String resp = (String.valueOf(dp.getData()));
            resp = resp+resp;
            socketServer.send(new DatagramPacket(resp.getBytes(),resp.getBytes().length));
            socketServer.receive(dp);
            System.out.println(String.valueOf(dp));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static int NWD(int pierwsza, int druga)
    {
        while (pierwsza != druga) // dopóki dwie liczby nie są sobie równe
        {
            if (pierwsza > druga)  // sprawdzamy, która z nich jest większa
            {
                pierwsza = pierwsza - druga; // odejmujemy mniejszą liczbę
            }                               // od większej
            else
            {
                druga = druga - pierwsza;
            }
        }
        return pierwsza;
    }
}
