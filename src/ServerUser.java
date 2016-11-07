

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JOptionPane;


public class ServerUser extends Thread{
	
	String nickname;
	InetAddress IP;
	int port;
	boolean voice = true; // pretendo usar isso pra mutar um dos usuarios
	ServerRoom sala;
	int TAM = 1024;
	IServidor server;
		
	// comunicação
	byte []buffer = new byte[TAM];

	public ServerUser(IServidor s,String nickname, InetAddress IP, int port){
		this.server = s;
		this.nickname = nickname;
		this.port = port;
		this.IP = IP;
		
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
	
	public void run(){
		try{
			DatagramSocket  s;
			DatagramPacket  p;
			
			s = new DatagramSocket(port);
			p = new DatagramPacket(buffer, buffer.length);
			
			DatagramSocket  sout = new DatagramSocket();
			DatagramPacket  pout;
			
			new ServerCheckConection(server, this).start();
			
			while(true){
				
				s.receive(p);
				Iterator<ServerUser> usersIT = sala.getUsers().iterator();
				
				while(usersIT.hasNext()){
					ServerUser atual = usersIT.next();
					int i = sala.getUsers().size();
					if(this != atual){
						pout = new DatagramPacket(buffer, buffer.length, atual.getIP(), atual.getPorta()+1);
						sout.send(pout);
					}
				}
				
			}
		
		}catch(SocketException e){
			JOptionPane.showMessageDialog(null, "Problemas na conexão 1 - ServerUser");
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "Problemas na conexão 2 - ServerUser");
		}

	}
}
