import java.io.*;
import java.net.Socket;

public class Service implements Runnable {
    public static final int PORT = 64000;
    public static final String clientLogin = "Login ALA";
    public static final String clientPassword = "Password Kota123";
    public static void log(String message){
        System.out.println("[S]: "+message);
    }
    Socket clientSocket;
    public Service(Socket cs){
        clientSocket=cs;
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

    @Override
    public void run() {
        try {
            String clientIp = clientSocket.getInetAddress().toString();
            int clientPort = clientSocket.getPort();
            log("user " + clientPort + " " + clientIp);
            InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream();
            InputStreamReader isr = new InputStreamReader(is);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedReader br = new BufferedReader(isr);
            BufferedWriter bw = new BufferedWriter(osw);

            bw.write(br.readLine());
            bw.newLine();
            bw.flush();


            int num1 = Integer.parseInt(br.readLine());
            int num2 = Integer.parseInt(br.readLine());
            int num3 = Integer.parseInt(br.readLine());
            String resp = "";
            System.out.println(num1);
            System.out.println(num2);
            System.out.println(num3);

            bw.write(NWD(NWD(num1,num2),num3));
            bw.newLine();
            bw.flush();


            bw.write(clientSocket.getPort());
            bw.newLine();
            bw.flush();

            System.out.println(br.readLine());



            //if (req.equals(clientLogin)) {
            //    resp = "ACK";
            //    bw.write(resp);
            //    bw.newLine();
            //    bw.flush();
            //    req = br.readLine();
            //    if (req.equals(clientPassword)) {
            //        resp = "ACK";
            //        bw.write(resp);
            //        bw.newLine();
            //        bw.flush();
//
//
            //        req = br.readLine();
            //        System.out.println(req);
//
//
            //    } else {
            //        resp = "NAK";
            //        bw.write(resp);
            //        bw.newLine();
            //        bw.flush();
            //    }
            //} else {
            //    resp = "NAK";
            //    bw.write(resp);
            //    bw.newLine();
            //    bw.flush();
            //}
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
