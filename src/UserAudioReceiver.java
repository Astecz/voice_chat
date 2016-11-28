

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

    int id;
    InetAddress IP;
    int port;
    DatagramSocket sIn;
    DatagramPacket pIn;
    int numUsers;
    ServerRoom sala;
    int cont;
    BufferCenter bufferCenter;
    ServerUser user;

    
    public UserAudioReceiver (int id, InetAddress IP,int port, ServerRoom sala, ServerUser user){
    	this.user = user;
    	this.id = id;
        this.IP = IP;
        this.port = port;
        try {
        this.sIn = new DatagramSocket(port);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        bufferCenter = sala.getBufferCenter();
        bufferCenter.setIsEmptyFlag(id, true);
        this.pIn = new DatagramPacket(bufferCenter.buffer[id],bufferCenter.buffer[0].length);
        this.sala = sala;
        cont = 0;
    }
    
    public synchronized void setUsersNumber(int num){
        numUsers = num;
    } 
    


    public void run(){
    	try {
		    while(true){
		        if(bufferCenter.getIsEmptyFlag(id) || sala.getNum() == 1){
		            	sIn.receive(pIn); //encher o buffer
		            	bufferCenter.setIsEmptyFlag(id,false);
		        }
		    }
    	} catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}