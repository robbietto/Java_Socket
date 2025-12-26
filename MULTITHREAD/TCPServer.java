/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author Carello
 */

import java.io.*;
import java.net.*;

class ServerMT extends Thread{
    private Socket s;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;
    private String idclient;
    
    public ServerMT(Socket s)throws IOException{
        this.s=s;
        inFromClient=new BufferedReader(new InputStreamReader(s.getInputStream()));
        outToClient=new DataOutputStream(s.getOutputStream());
        this.start();
    }
    
    @Override
    public void run(){
        try {
            idclient=inFromClient.readLine();
            System.out.println("Benvenuto, client "+idclient);
            while(true){
                String msg=inFromClient.readLine();
                
                if(msg.equals("end")) break;
                
                outToClient.writeBytes(msg.toUpperCase()+"\n");
            }
            System.out.println("Chiusura connessione con client "+idclient+ " ...");
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

public class TCPServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket=new ServerSocket(7777);
            System.out.println("Server multithread in ascolto sulla porta 7777...");
            
            while(true){
                Socket s=serverSocket.accept();
                new ServerMT(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
