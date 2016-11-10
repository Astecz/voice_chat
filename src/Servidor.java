
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.*;


public class Servidor extends Thread{
	
	IServidor a;
	
	public Servidor(IServidor a){
		this.a = a;
	}
	public void run(){
		try{
			
			int port = 8001;
			
			Socket s;
				
			ServerSocket serverSocket = new ServerSocket(8000);
			DataInputStream dataIN;
			DataOutputStream dataOUT;
			Collection <ServerRoom> salas = new ArrayList<ServerRoom>();
			String entrada;
			ServerUser user;
			while(true)
			{
				s = serverSocket.accept();
				dataIN = new DataInputStream(s.getInputStream());
				dataOUT = new DataOutputStream(s.getOutputStream());
        	
				entrada = dataIN.readUTF();
        	
					
        	
					String []dados = entrada.split(";"); //[nick;nome_da_sala]
					String Nick = dados[0];
					String sala = dados[1];
        	       	
        	
					Iterator<ServerRoom> salasIT = salas.iterator();
					ServerRoom x = null;
        	
					while(salasIT.hasNext()){
						ServerRoom temporario = salasIT.next();
						if(temporario.getNome().equals(sala)){
							x = temporario;
        			
							break;
						}
					}
					if(x == null){
						user = new ServerUser(a, Nick, s.getInetAddress(), port+1,1);
						x = new ServerRoom(user, port, sala);
						salas.add(x);
						dataOUT.writeUTF(Integer.toString(port+1));
						port += 1000;
						
						
					}else{
						user = new ServerUser(a, Nick, s.getInetAddress(), x.getPorta() + (x.getNum()*3)+1
                                                ,x.getNum()+1);
						dataOUT.writeUTF(Integer.toString(x.getPorta() + (x.getNum()*3)+1));
						x.insereUser(user);
					}
					
					
					a.printT("\"" + user.nickname + "\" se conectou à sala: " + x.nome);
        	
					s.close();
					            
			}
		}catch(IOException e){
			
		}
		
	}
	
	
}






































