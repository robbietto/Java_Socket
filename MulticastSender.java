/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author Carello
 */
import java.io.*;
import java.net.*;
public class MulticastSender {

    public static void main(String[] args) {
        final int port=5000;
        final String gruppo="225.0.0.0";
        try {
            BufferedReader tastiera=new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket senderSocket=new DatagramSocket();
            System.out.println("Server avviato sulla porta "+port);
            
            InetAddress group=InetAddress.getByName(gruppo);
            byte[] sendData;
            
            while(true){
                System.out.print("\n>Inserire 'end' per terminare: ");
                String msg=tastiera.readLine();
                
                if(msg.equals("end"))break;
                
                msg=msg.toUpperCase();
                sendData=msg.getBytes();
                DatagramPacket sendPacket=new DatagramPacket(sendData, sendData.length,group,port);
                senderSocket.send(sendPacket);
            }
            System.out.println("\nChiusura della connessione... ");
            senderSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}