

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
import java.util.Scanner;

import javax.sound.sampled.*;
import javax.swing.JOptionPane;

public class Cliente extends Thread {
	int TAM = 1024;
	String nick, IP, sala;    //meu ip "192.168.0.22";
	ICliente IC;
	
	public Cliente(ICliente IC, String nick, String ip, String sala){
		this.IC = IC;
		this.nick = nick;
		this.IP = ip;
		this.sala = sala;
	}
	
	
	public void run(){
		
		try{
			int TAM = 1024;
			
			
			// protocolo
			
			Socket s = new Socket(IP, 8000);
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			out.writeUTF(nick+";"+sala);
			int porta = Integer.parseInt(in.readUTF());
			s.close();
			//fim
					
			InetAddress ip = InetAddress.getByName(IP);
			
			DatagramPacket pacote;
			DatagramSocket socket;
			byte []bufferOUT = new byte[TAM];
		
			//input som
			AudioFormat af = new AudioFormat(8000.0f,8,1,true,false);
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, af);
			TargetDataLine microphone = (TargetDataLine)AudioSystem.getLine(info);
			microphone.open(af);
			microphone.start();
			//fim
			
			
		
			new ClienteCheckConection(IC, this,ip, porta + 2).start();
			
			//chama o speaker
			new ClienteSom(porta+1).start();
			//fim
			
			
			pacote = new DatagramPacket(bufferOUT, bufferOUT.length, ip, porta); // cria o pacote
			socket = new DatagramSocket(); // cria a conexão
		
			while(true){
				microphone.read(bufferOUT, 0, bufferOUT.length);
				
				if(pacote.getLength() >= TAM)
					socket.send(pacote);
			}
		}catch(UnknownHostException e){
			JOptionPane.showMessageDialog(null, "Impossível conectar ao servidor! - Cliente");
			System.exit(1);
		}catch(LineUnavailableException e){
			JOptionPane.showMessageDialog(null, "Problemas ao acessar periféricos! - Cliente");
			System.exit(1);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Caminho para o servidor não encontrado!");
			IC.reset();
		}
		
	}
	
}
