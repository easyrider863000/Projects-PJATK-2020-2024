package ServerApp;

import java.io.IOException;
import java.net.ServerSocket;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket welcomeSocket = new ServerSocket(50000);
        while (true) {
            Service s = new Service(welcomeSocket.accept());
            new Thread(s).start();
        }


    }
}
