

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class Mixer extends Thread{
	int idUser;
	InetAddress ipUser;
	int portUser;
	byte buffer[][];
	byte bufferOut[];
	BufferCenter bufferCenter;
	List<byte[]> lista;
	ServerRoom sala;
	ServerUser user;
    
    public Mixer(int id,InetAddress IP,int port,BufferCenter buff){
    	idUser = id;
    	ipUser = IP;
        portUser = port;
        buffer = new byte[5][1500];
        bufferOut = new byte[1500];
        bufferCenter = buff;
        lista = new ArrayList<byte[]>(Arrays.asList(buffer));
    }
    
    public Mixer(ServerRoom sala, ServerUser user,InetAddress IP,int port,BufferCenter buff){
    	ipUser = IP;
        portUser = port;
        buffer = new byte[5][1500];
        bufferOut = new byte[1500];
        bufferCenter = buff;
        lista = new ArrayList<byte[]>(Arrays.asList(buffer));
        this.sala = sala;
        this.user = user;
    }
    
    public void run(){
    	long time;    	
    	try {
			DatagramSocket sOut = new DatagramSocket();
			DatagramPacket pOut;
			
			
			while(true){
				time = System.currentTimeMillis();
				if(sala.getNum() > 1){
					
					Thread.sleep(180);
					
					Iterator<ServerUser> it = sala.getUsers().iterator();
					boolean pass = true; //variável para o controle do iterator
					ServerUser u = null;
					
					while(it.hasNext()){
						if(pass) u = it.next(); //vai para o próximo elemento se for true
						
						if(u != user){
							byte[] temp = bufferCenter.getBuffer(u.getID());
							if(temp == null){
								pass = false; //caso não consiga acessar o buffer, não vai para o próximo obj
								continue;
							}
							buffer[u.getID()] = temp.clone();
													}
						pass = true;
					}
					
					
					//Mixando
		    		for(int i = 0; i < buffer[0].length; i++){ 
		    			bufferOut[i] = (byte) (buffer[0][i] + buffer[1][i] + buffer[2][i] 
		    									+ buffer[3][i] + buffer[4][i]);
		    		}
		    		
		    		//Enviar pacote mixado
	    			
		    		pOut = new DatagramPacket(bufferOut, bufferOut.length, ipUser, portUser+1);
		    		sOut.send(pOut);
			    		
			    		
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    		
    		
    	
    	
    }
}