package ServerApp;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        InetAddress serverAddress = InetAddress.getByName("localhost");
        Socket clientSocket = new Socket(serverAddress, 50000);

        Scanner in = new Scanner(System.in);

        InputStream is = clientSocket.getInputStream();
        OutputStream os = clientSocket.getOutputStream();
        InputStreamReader isr = new InputStreamReader(is);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);

        System.out.println("1.get list files -> \"list\"\n" +
                "2.get file -> \"get file.txt\"\n" +
                "3.remove file -> \"remove file.txt\"\n" +
                "4.for exit -> \"exit\"");
        while(true) {
            String req = in.next();
            if (req.equals("exit")){
                break;
            }
            bw.write(req);
            bw.newLine();
            bw.flush();

            System.out.println(br.readLine());

        }
    }
}