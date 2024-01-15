import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class prog2 {
    public static final String ServerName = "172.21.48.9";
    public static final int SERVER_PORT = 14168;

    public static void log(String message) {
        System.out.println("[C]: " + message);
    }


    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }


    public static void main(String[] args) throws IOException {
        InetAddress serverAddress = InetAddress.getByName(ServerName);
        Socket clientSocket = new Socket(serverAddress, SERVER_PORT);
        log("connected to server: " + clientSocket.toString());


        InputStream is = clientSocket.getInputStream();
        OutputStream os = clientSocket.getOutputStream();
        InputStreamReader isr = new InputStreamReader(is);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);

        log("Sending flag 1 to server");
        String req = "143781";
        bw.write(req);
        bw.newLine();
        bw.flush();
        log("Flag 1 was sent to server");

        log("getting 5 natural numbers");
        int num1 = Integer.parseInt(br.readLine());
        int num2 = Integer.parseInt(br.readLine());
        int num3 = Integer.parseInt(br.readLine());
        int num4 = Integer.parseInt(br.readLine());
        int num5 = Integer.parseInt(br.readLine());
        log("5 natural numbers were got\n" + num1 + "\n" + num2 + "\n" + num3 + "\n" + num4 + "\n" + num5 + "\n");


        log("finding greatest common divisor");
        int gcdSSS = gcd(gcd(gcd(gcd(num1, num2), num3), num4), num5);
        log("greatest common divisor was finded = " + gcdSSS);


        log("Sending second result to server");
        bw.write(gcdSSS + "");
        bw.newLine();
        bw.flush();
        log("second result was sent to server");


        log("getting text from the server");
        String str = br.readLine();
        log("text from the server was got: " + str);
        String req2 = str + str + str;
        log("req to the server sending: " + req2);
        bw.write(req2);
        bw.newLine();
        bw.flush();
        log("req to the server was sent");

        log("response from server: "+br.readLine());


        log("socket closing");
        clientSocket.close();
        log("socket closed");
    }
}
