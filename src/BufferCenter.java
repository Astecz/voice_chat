
public class BufferCenter {
	
	byte buffer[][] = new byte[5][1500];
	private boolean isEmptyFlags[] = new boolean[5];
	int cont[] = new int[5];
	int numUsers;
	boolean userReady[] = new boolean[5];
	
	boolean user1 = false;
	
	public BufferCenter(){
		user1 = false;
		for(int i = 0; i < isEmptyFlags.length; i++){
			isEmptyFlags[i] = false;
			userReady[i] = false;
		}
		
		
		
	}
	//////////////////////////////
	
	public synchronized void setUser1(boolean value){
		user1 = value;
	}
	
	public synchronized boolean getUser1(){
		return user1;
	}
	
	////////////////////
	
	
	public synchronized void setNumUsers(int num){
		numUsers = num;
	}
	
	public synchronized int getNumUsers(){
		return numUsers;
	}
	
	public synchronized void setIsEmptyFlag(int id, boolean value){
		isEmptyFlags[id] = value;
		//System.out.println("FLAG DE :"+id+" "+getIsEmptyFlag(id));
	}
	
	public synchronized boolean getIsEmptyFlag(int id){
		return isEmptyFlags[id];
	}
	
	
	///////////////////
	public synchronized void setUserReady(int id, boolean value){
		userReady[id] = value;
		//System.out.println("FLAG DE :"+id+" "+getIsEmptyFlag(id));
	}
	
	public synchronized boolean getUserReady(int id){
		return userReady[id];
	}
	//////////////////////
	
	
	public synchronized void incCont(int id){
		cont[id]++;
	}
	
	public synchronized void setCont(int id, int value){
		cont[id] = value;
	}
	
	public synchronized int getCont(int id){
		return cont[id];
	}
	
	public byte[] getBuffer(int id){
		byte[] temp = null; 
		//System.out.println("Getbuffer em " +id+ " Flag "+isEmptyFlags[id] +" numUsers "+ numUsers);
		
		
		if((getCont(id) < (getNumUsers()-1)) && (!getIsEmptyFlag(id))){ //Confere se todos já leram o buffer e se o buffer está cheio
			incCont(id);
			temp = buffer[id].clone();
			//System.out.println("Fudeu 1");
		}
		
		if(getCont(id) == (getNumUsers()-1)){
			setIsEmptyFlag(id, true);
			setCont(id,0);
			//System.out.println("Fudeu 2");
		}
		
		return temp; //Se o buffer esteja vazio, retorna null
	}
	
	//public static synchronized byte[][]
	
//	public static synchronized void writeBuffer(byte[] b, int id){
//		buffer[id] = b.clone();
//	}
	

}
