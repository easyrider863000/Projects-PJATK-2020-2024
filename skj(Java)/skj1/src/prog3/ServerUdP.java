package prog3;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLOutput;

import static ServerApp.Service.log;

public class ServerUdP {
    public static void main(String[] args) throws IOException {
        InetAddress serverAddress = InetAddress.getByName("172.21.48.25");
        Socket clientSocket = new Socket(serverAddress, 13154);


        InputStream is = clientSocket.getInputStream();
        OutputStream os = clientSocket.getOutputStream();
        InputStreamReader isr = new InputStreamReader(is);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);

        log("Sending flag 1 to server");
        String req = "189231";
        bw.write(req);
        bw.newLine();
        bw.flush();
        System.out.println("okk");

        String req2 = "172.23.130.46:32421";
        bw.write(req2);
        bw.newLine();
        bw.flush();

        System.out.println(br.readLine());
    }
}
