import java.io.IOException;
import java.net.*;

public class ClientUDP {

    public static void main(String[] args) throws IOException {
        byte[]doWyslania = {0x08, 0x54, 0x01, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x03, 0x77, 0x77, 0x77, 0x02, 0x77, 0x70, 0x02, 0x70, 0x6c, 0x00, 0x00, 0x01, 0x00, 0x01};
        String dnsName = "8.8.8.8";
        InetAddress dnsaddress = InetAddress.getByName(dnsName);
        int dnsPort = 53;

        DatagramPacket packetToSend = new DatagramPacket(
                doWyslania,
                doWyslania.length,
                dnsaddress,
                dnsPort
        );

        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.send(packetToSend);


        DatagramPacket packetToRecieve = new DatagramPacket(
                new byte[1400],1400
        );

        datagramSocket.receive(packetToRecieve);
        packetToRecieve.getData();
    }
}
