

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Iterator;
import javax.sound.sampled.AudioInputStream;

import javax.swing.JOptionPane;


public class ServerUser extends Thread{
	
	String nickname;
	InetAddress IP;
	int port;
	//boolean voice = true; // pretendo usar isso pra mutar um dos usuarios
	ServerRoom sala;
	int TAM = 1024;
	IServidor server;
    int ID;
    BufferCenter buff;
    boolean isReady;
		
	byte []buffer = new byte[TAM];

        
    public ServerUser(IServidor s,String nickname, InetAddress IP, int port, int ID, BufferCenter buff){
		this.server = s;
		this.nickname = nickname;
		this.port = port;
		this.IP = IP;
	    this.ID = ID;
	    this.buff = buff;
	    this.isReady = false;
	}
    
    public ServerUser(IServidor s,String nickname, InetAddress IP, int port, int ID){
		this.server = s;
		this.nickname = nickname;
		this.port = port;
		this.IP = IP;
	    this.ID = ID;
	}
    
    public synchronized void setIsReady(boolean value){
    	isReady = value;
    }
    
    public synchronized boolean isReady(){
    	return isReady;
    }
	
	public InetAddress getIP(){
		return IP;
	}
	
	public int getPorta(){
		return port;
	}
	
	public void setSala(ServerRoom sala){
		this.sala = sala;
	}
	
	public void setBufferCenter(BufferCenter buff){
		this.buff = buff;
	}
	
	public int getID(){
		return ID;
	}
	
	public void run(){
		
		new UserAudioReceiver (ID,IP,port,sala,this).start();
		
		new Mixer(sala, this, IP, port, buff).start();
		
		new ServerCheckConection(server, this).start();
		
	}
}

