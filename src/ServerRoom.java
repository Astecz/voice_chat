

import java.net.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;



public class ServerRoom{
	
	int port;
	String nome;
	private Collection <ServerUser> conectados = new ArrayList<ServerUser>();
	int num = 0;
	BufferCenter buff;
	
	public ServerRoom(ServerUser user, int port, String nome, BufferCenter b){
		this.nome = nome;
		this.port = port;
		buff = b;
		for(int i = 0; i < buff.isEmptyFlags.length; i++){
			buff.isEmptyFlags[i] =  true;
		}
		insereUser(user);
		
	}
//	public int getNum(){
//		return num;
//	}
		
	public int getPorta(){
		return port;
	}
	
	public BufferCenter getBufferCenter(){
		return buff;
	}
	
	public String getNome(){
		return nome;
	}
        
    public synchronized int getNum(){
        return num;
    }
	
	public synchronized void insereUser(ServerUser user){
		conectados.add(user);
		num++;
		if(buff == null) System.out.println("Fudeu 2");
		buff.setNumUsers(num);
		user.setSala(this);
		user.start();
	}
	
	public synchronized void removeUser(ServerUser user){
		conectados.remove(user);
		num--;
		buff.setNumUsers(num);
	}
	
	public synchronized Collection <ServerUser> getUsers(){
		return conectados;
	}
	
}
