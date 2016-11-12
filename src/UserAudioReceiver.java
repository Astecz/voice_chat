

import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;

public class UserAudioReceiver extends Thread{
   // byte buffer[] = new byte[512];

    int id;
    InetAddress IP;
    int port;
    DatagramSocket sIn;
    DatagramPacket pIn;
    boolean isEmpty;
    int numUsers;
    ServerRoom sala;
    int cont;
    BufferCenter bufferCenter;
    
    public UserAudioReceiver (int id, InetAddress IP,int port, ServerRoom sala){
        this.id = id;
        this.IP = IP;
        this.port = port;
        try {
        this.sIn = new DatagramSocket(port);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        bufferCenter = sala.getBufferCenter();
        this.pIn = new DatagramPacket(bufferCenter.buffer[id],bufferCenter.buffer[0].length);
        isEmpty = true;
        this.sala = sala;
        cont = 0;
        
    }
    
    public synchronized void setUsersNumber(int num){
        numUsers = num;
    } 
    
//    private synchronized void read() throws IOException{
//        sIn.receive(pIn); //encher o buffer
//    }
    
//    public synchronized byte[] getBuffer(){
//        cont++;
//        setEmpty();
//        return buffer;
//    }
//    
//    public synchronized void setEmpty(){
//        if(cont == sala.getNum()){
//            isEmpty = true;
//            cont = 0;
//        }else{
//            isEmpty = false;
//        }
//        
//    }

    public void run(){
    	try {
			sIn.receive(pIn); //encher o buffer
			bufferCenter.setIsEmptyFlag(id,false);
			
        while(true){
            if(bufferCenter.isEmptyFlags[id]){
                	sIn.receive(pIn); //encher o buffer
                	bufferCenter.setIsEmptyFlag(id,false);
                	System.out.println("---------->Leu no "+id);
                
            }
        }
    	} catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}