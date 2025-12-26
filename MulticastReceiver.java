/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author Carello
 */
import java.net.*;
public class MulticastReceiver {
    public static void main(String[] args) {
        final String gruppo="225.0.0.0";
        final int port=5000;
        byte[] receiveData=new byte[1024];
        
        try {
            MulticastSocket receiverSocket=new MulticastSocket(port);
            InetAddress group=InetAddress.getByName(gruppo);
            
            receiverSocket.joinGroup(group);
            System.out.println("Receiver connesso al gruppo "+gruppo);
            
            while(true){
                DatagramPacket receivePacket=new DatagramPacket(receiveData, receiveData.length);
                receiverSocket.receive(receivePacket);
                
                String msg=new String(receivePacket.getData(),0,receivePacket.getLength());
                if(msg.equals("end"))break;
                
                System.out.println("Messaggio dal sender: "+msg);
            }
            System.out.println("Chiusura connessione dal sender... ");
            receiverSocket.leaveGroup(group);
            receiverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
