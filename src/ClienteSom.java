

import java.net.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.IOException;

import javax.sound.sampled.*;
import javax.swing.JOptionPane;

public class ClienteSom extends Thread{
	int porta;
	int TAM = 1500;
	
	public ClienteSom(int porta){
		this.porta = porta;
		
	}
	
	public void run(){
		try{
			
			DatagramSocket soc = new DatagramSocket(porta); // crio o socket pra aguardar dados na porta 8000
			
			byte []buffer = new byte[TAM]; // crio o buffer


			//output som
			AudioFormat af = new AudioFormat(8000.0f,8,1,true,false);
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);
			SourceDataLine inSpeaker = (SourceDataLine)AudioSystem.getLine(info);
			inSpeaker.open(af);
			inSpeaker.start();
			
			
			//fim 
			
			//long time;
					
			while(true){
				//time = System.currentTimeMillis();
				DatagramPacket pacote = new DatagramPacket(buffer, buffer.length); // criei o pacote que vai ficar recebendo o bagúi
				soc.receive(pacote); // recebe o pacote no buffer
				
				//if(buffer[100]==0) continue; 
				
				inSpeaker.write(buffer, 0, buffer.length);
				
				//if(porta < 8100) System.out.println(buffer[100]);
				
				//if(porta < 8100) System.out.println((System.currentTimeMillis() - time));
			}
		}catch(SocketException e){
			JOptionPane.showMessageDialog(null, "Problemas na criação do servidor! - ClienteSom");
		}catch(LineUnavailableException e){
			JOptionPane.showMessageDialog(null, "Problemas ao acessar periféricos! - ClienteSom");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Problemas na conexão! - ClienteSom");
		}
	}
	
}
