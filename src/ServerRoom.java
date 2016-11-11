

import java.net.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;



public class ServerRoom{
	
	int port;
	String nome;
	private Collection <ServerUser> conectados = new ArrayList<ServerUser>();
	int num = 0;
	
	public ServerRoom(ServerUser user, int port, String nome){
		this.nome = nome;
		this.port = port;
		insereUser(user);
	}
//	public int getNum(){
//		return num;
//	}
		
	public int getPorta(){
		return port;
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
		user.setSala(this);
		user.start();
	}
	
	public synchronized Collection <ServerUser> getUsers(){
		return conectados;
	}
	
}
