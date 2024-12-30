
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class HoneypotService {
    public static void main(String [] args) throws Exception{
        
        //Simulate a vulnerable FTP service
        simulateVulnerableFtpService();
    }
    
    private static void simulateVulnerableFtpService() throws Exception{
        
        ServerSocket serverSocket= new ServerSocket(21);
        System.out.println("Honeypot FTP service listesting on port 21...");
        
        while(true){
            Socket socket= serverSocket.accept();
            System.out.println("Incoming FTP connection from" + socket.getInetAddress());
            
            //Hndle incoming FTP connection
            handleFtpConnection(socket);
        }
    }
    
    private static void handleFtpConnection(Socket socket) throws Exception{
        BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line;
        
        while((line=reader.readLine()) != null){
            System.out.println("Received FTP command" + line);
        }
        
        //send a fake FTP welcome message
       PrinterWriter writer=new PrinterWriter(socket.getOutputStream(),true);
       writer.println("220 Welcome to the honey FTP service");
    }
}
