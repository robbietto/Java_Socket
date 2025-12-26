/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author Carello
 */
import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        byte[] receiveData = new byte[1024];
        byte[] sendData= new byte[1024];

        try {
            DatagramSocket serverSocket = new DatagramSocket(6666);
            System.out.println("Server in ascolto sulla porta 6666...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String msg = new String(receivePacket.getData(), 0, receivePacket.getLength());

                if (msg.equals("end")) {
                    System.out.println("Il client ha richiesto la chiusura.");
                    break;
                }

                InetAddress IPClient = receivePacket.getAddress();
                int port = receivePacket.getPort();

                String outToClient = msg.toUpperCase();
                sendData = outToClient.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPClient, port);
                serverSocket.send(sendPacket);
            }

            serverSocket.close();
            System.out.println("Server chiuso.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
