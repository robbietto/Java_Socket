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
            
            System.out.print("Inserisci id cliente: ");
            String idcliente=tastiera.readLine();
            outToServer.writeBytes(idcliente+"\n");

            while(true){
                System.out.print("Inserisci una parola ('end' per terminare): ");
                String sentence=tastiera.readLine();
                
                if(sentence.equals("end")) break;
                
                outToServer.writeBytes(sentence+"\n");
                System.out.println("\nRisposta dal server: "+inFromServer.readLine());
            }
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
