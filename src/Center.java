
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;

public class Center extends Thread{
    byte buffer[][] = new byte[5][512];
    public void run(){
        try {
            ServerSocket s[] = new ServerSocket[5];
            
            for(int i = 0; i < s.length; i++) s[i] = new ServerSocket(4001+i);
            
            DatagramSocket sIn[] = new DatagramSocket[5];
            DatagramPacket pIn[] = new DatagramPacket[5];
            
            DatagramSocket sOut[] = new DatagramSocket[5];
            DatagramPacket pOut[] = new DatagramPacket[5];
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}