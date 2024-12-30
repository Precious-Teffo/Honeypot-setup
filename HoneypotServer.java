
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HC
 */
public class HoneypotServer {
    public static void main(String[] args) throws Exception{
       
        ServerSocket serverSocket=new ServerSocket(8080);
        System.out.println("Honeypot server listening on port 8080....");
        
        while(true){
            Socket socket= serverSocket.accept();
            System.out.println("Incoming connection from" +socket.getInetAddress());
            
            //Handle incoming connection
            handleConnection(socket);
        }
    }
    private static void handleConnection(Socket socket) throws Exception{
        
        BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line;
        
        while((line=reader.readLine()) != null){
            System.out.println("Received:" + line);
        }
        //Simulate a vulnerable service
        simulateVulnerableService(socket);
    }
    private static void simulateVulnerableService( Socket socket) throws Exception{
        
    PrintWriter writer =new PrintWriter(socket.getOutputStream(),true);
    writer.println("Login:");
    }
}
