



import java.io.IOException;
import java.net.*;

import javax.swing.JOptionPane;

public class ServerCheckConection extends Thread {
	ServerUser user;
	byte[] inbuff = new byte[1];
	int i = 0;
	IServidor s;
	byte[] outbuff = new byte[1];
	
	public ServerCheckConection(IServidor s , ServerUser user){
		this.s = s;
		this.user = user;
	}
	
	public void run(){
		try {
			DatagramSocket s = new DatagramSocket(user.getPorta() + 2);
			DatagramPacket p = new DatagramPacket(inbuff, inbuff.length);
			DatagramSocket s2 = new DatagramSocket();
			DatagramPacket p2 = new DatagramPacket(outbuff, outbuff.length, user.getIP(), user.getPorta() + 42);
			outbuff[0] = 0;
			suport io = new suport(user);
			io.start();
			
			while(true){
					outbuff[0] = (byte) ((outbuff[0] % 42) + 1);
					s.receive(p);
					io.refreshBuffer(inbuff[0]);
					s2.send(p2);
					
			}
			
					
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Problemas na conexão 1 - ServerCheckConection");
		}
		
	}
	
	class suport extends Thread{
		byte buffer;
		byte lastbuffer;
		ServerUser user;
		
		public suport(ServerUser user){
			this.user = user;
		}
		
		public synchronized void refreshBuffer(byte novo){
			lastbuffer = buffer;
			buffer = novo;
		}
		
		
		public void run(){
			
			
			buffer = 127;
			lastbuffer = 127;
			
			
			try {
				
				Thread.sleep(5000);
			
				while(true){
					if(buffer == lastbuffer){
						break;
					}else{
						
						lastbuffer = buffer;
						
						Thread.sleep(5000);
						
					}	
				}
				s.printT("\""+ user.nickname + "\" desconectou-se da sala " + user.sala.nome);
				user.sala.removeUser(user);
				s.printT("A sala: \"" + user.sala.nome + "\" agora tem " + user.sala.getUsers().size() + " membros");
				
				
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(null, "Conexão expirada!");
				System.exit(1);
			}
			
		}
		
		
		
	}
}
