
import java.io.IOException;
import java.net.*;

import javax.swing.JOptionPane;

public class ClienteCheckConection extends Thread{
	InetAddress IP;
	int port;
	byte[] buffer = new byte[1];
	boolean conect = true;
	byte []inbuff = new byte[1];
	ICliente IC;
	Cliente C;
	
	public ClienteCheckConection(ICliente IC, Cliente C, InetAddress IP, int port){
		this.C = C;
		this.IC = IC;
		this.IP = IP;
		this.port = port;
	}
	
	public void disconect(){
		conect = false;
	}
	
	public void run(){
		try {
			buffer[0] = 0;
			DatagramSocket s = new DatagramSocket();
			DatagramPacket p = new DatagramPacket(buffer, buffer.length, IP, port);
			DatagramSocket s2 = new DatagramSocket(port + 40);
			DatagramPacket p2 = new DatagramPacket(inbuff, inbuff.length);
			suport2 rhasta = new suport2();
			s.send(p);
			rhasta.start();
			
			while(true){
				buffer[0] = (byte) ((buffer[0] % 42 ) + 1);
				s2.receive(p2);
				rhasta.refreshBuffer(inbuff[0]);
				s.send(p);
				
			}
			
		} catch (IOException e) {
			
		}
	}
	
	class suport2 extends Thread{
		byte buffer;
		byte lastbuffer;
		
		
		public suport2(){
			
		}
		
		public synchronized void refreshBuffer(byte novo){
			lastbuffer = buffer;
			buffer = novo;
		}
		
		
		public void run(){
			
			
			buffer = 127;
			lastbuffer = 127;
			
			
			try {
				
				Thread.sleep(4000);
			
				while(true){
					if(buffer == lastbuffer){
						break;
					}else{
						
						lastbuffer = buffer;
						
						Thread.sleep(4000);
						
					}	
				}
				JOptionPane.showMessageDialog(null, "Conexão com o servidor expirou!");
				C.interrupt();
				IC.reset();
				
				
			} catch (InterruptedException e) {
				System.out.println("erro");
				System.exit(1);
			}
			
		}
		
		
		
	}
	
}
