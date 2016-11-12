

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
	boolean voice = true; // pretendo usar isso pra mutar um dos usuarios
	ServerRoom sala;
	int TAM = 1024;
	IServidor server;
    int ID;
    BufferCenter buff;
		
	// comunicação
	byte []buffer = new byte[TAM];

	public ServerUser(IServidor s,String nickname, InetAddress IP, int port){
		this.server = s;
		this.nickname = nickname;
		this.port = port;
		this.IP = IP;
		
	}
        
    public ServerUser(IServidor s,String nickname, InetAddress IP, int port, int ID, BufferCenter buff){
		this.server = s;
		this.nickname = nickname;
		this.port = port;
		this.IP = IP;
	    this.ID = ID;
	    this.buff = buff;
	}
    
    public ServerUser(IServidor s,String nickname, InetAddress IP, int port, int ID){
		this.server = s;
		this.nickname = nickname;
		this.port = port;
		this.IP = IP;
	    this.ID = ID;
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
	
	public void run(){
		
		new UserAudioReceiver (ID,IP,port,sala).start();
		
		new Mixer(ID, IP, port, buff).start();
		
		new ServerCheckConection(server, this).start();
		
		
//		try{
//			DatagramSocket  s;
//			DatagramPacket  p;
//			
//			s = new DatagramSocket(port);
//			p = new DatagramPacket(buffer, buffer.length);
//			
//			DatagramSocket  sout = new DatagramSocket();
//			DatagramPacket  pout;
//			
//			
//
//			while(true){
//				
//				s.receive(p); //Pega os pacotes do usu�rio
//                                
//                                pout = new DatagramPacket(buffer, buffer.length,s.getInetAddress(), 4000+ID);//Manda os pacotes desse usu�rio para o Center
//                                sout.send(pout);
//                                
////				Iterator<ServerUser> usersIT = sala.getUsers().iterator();
////				
////				while(usersIT.hasNext()){
////					ServerUser atual = usersIT.next();
////					int i = sala.getUsers().size();
////					if(this != atual){
////						pout = new DatagramPacket(buffer, buffer.length, atual.getIP(), atual.getPorta()+1);
////						sout.send(pout);
////					}
////				}
//				
//			}
//		
//		}catch(SocketException e){
//			JOptionPane.showMessageDialog(null, "Problemas na conexão 1 - ServerUser");
//		}catch(IOException e){
//			JOptionPane.showMessageDialog(null, "Problemas na conexão 2 - ServerUser");
//		}

	}
}

