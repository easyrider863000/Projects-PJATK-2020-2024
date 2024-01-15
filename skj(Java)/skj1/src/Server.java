import java.io.*;
import java.net.ServerSocket;

public class Server {
    public static void log(String message){
        System.out.println("[S]: "+message);
    }
    public static final int PORT = 50000;
    public static void main(String[] args) throws IOException {
        log("Starting");
        log("Server socket opening");
        ServerSocket welcomeSocket = new ServerSocket(PORT);
        while (true) {
            Service s = new Service(welcomeSocket.accept());
            new Thread(s).start();
        }





    }
}
