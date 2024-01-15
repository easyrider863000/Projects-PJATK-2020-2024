package prog3;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ServerUDP222 {

    public static void log(String messag) {
        System.out.println("[S]: " + messag);
    }

    private static final String myIp = "172.23.130.46";
    private static final int myPort = 32421;

    public static void main(String[] args) {
        try {
            log("Starting");
            log("Server socket opening");
            InetAddress myIpAdress = InetAddress.getByName(myIp);

            DatagramSocket socketServer = new DatagramSocket(myPort,myIpAdress);



            while (true){
                if (socketServer.getBroadcast()){
                    ServerService ss = new ServerService(socketServer);
                    new Thread(ss).start();
                }
            }


        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }
}