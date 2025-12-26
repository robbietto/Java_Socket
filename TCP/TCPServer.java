/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author Carello
 */

import java.io.*;
import java.net.*;
public class TCPServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket=new ServerSocket(7777);
            System.out.println("Server in ascolto sulla porta 7777...");
            
            while(true){
                Socket s=serverSocket.accept();
                
                BufferedReader inFromClient=new BufferedReader(new InputStreamReader(s.getInputStream()));
                DataOutputStream outToClient=new DataOutputStream(s.getOutputStream());
                
                String sentence=inFromClient.readLine();
                System.out.println(sentence);
                outToClient.writeBytes(sentence.toUpperCase());
                System.out.println("Chiusura connessione...");
                s.close();
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
