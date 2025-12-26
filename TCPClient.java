/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author Carello
 */
import java.io.*;
import java.net.*;
public class TCPClient {

    public static void main(String[] args) {
        try {
            Socket s=new Socket("localhost",7777);
            BufferedReader tastiera=new BufferedReader(new InputStreamReader(System.in));
            BufferedReader inFromServer=new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream outToServer=new DataOutputStream(s.getOutputStream());
            
            System.out.print("Inserisci una parola: ");
            String sentence=tastiera.readLine();
            outToServer.writeBytes(sentence+"\n");
            
            System.out.println("Risposta dal server: "+inFromServer.readLine());
            System.out.println("Chiusura connessione...");
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}