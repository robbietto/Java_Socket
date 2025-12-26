/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author Carello
 */
import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IpServer = InetAddress.getByName("localhost");
            final int port = 6666;

            byte[] sendData= new byte[1024];
            byte[] receiveData = new byte[1024];

            while (true) {
                System.out.print("Inserisci una parola ('end' per terminare): ");
                String msg = tastiera.readLine();

                sendData = msg.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IpServer, port);
                clientSocket.send(sendPacket);

                if (msg.equals("end")) break;

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);

                String risposta = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Risposta del server: " + risposta);
            }

            System.out.println("Chiusura connessione...");
            clientSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
