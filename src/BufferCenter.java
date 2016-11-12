
public class BufferCenter {
	
	byte buffer[][] = new byte[5][512];
	boolean isEmptyFlags[] = new boolean[5];
	int cont[] = new int[5];
	int numUsers;
	
	public BufferCenter(){
		for(int i = 0; i < isEmptyFlags.length; i++){
			isEmptyFlags[i] =  true;
		}
	}
	
	public synchronized void setNumUsers(int num){
		numUsers = num;
	}
	
	public synchronized int getNumUsers(){
		return numUsers;
	}
	
	public synchronized void setIsEmptyFlag(int id, boolean value){
		isEmptyFlags[id] = value;
		System.out.println("FLAGA DE :"+id+" "+getIsEmptyFlag(id));
	}
	
	public synchronized boolean getIsEmptyFlag(int id){
		return isEmptyFlags[id];
	}
	
	public synchronized byte[] getBuffer(int id){
		byte[] temp = null; 
		System.out.println("Getbuffer em " +id+ " Flag "+isEmptyFlags[id] +" numUsers "+ numUsers);
		
		
		if(cont[id] < (numUsers-1) && !isEmptyFlags[id]){ //Confere se todos já leram o buffer e se o buffer está cheio
			cont[id]++;
			temp = buffer[id].clone();
			System.out.println("Fudeu 1");
		}
		
		if(cont[id] == (numUsers-1)){
			setIsEmptyFlag(id, true);
			cont[id] = 0;
			System.out.println("Fudeu 2");
		}
		
		return temp; //Se o buffer esteja vazio, retorna null
	}
	
	//public static synchronized byte[][]
	
//	public static synchronized void writeBuffer(byte[] b, int id){
//		buffer[id] = b.clone();
//	}
	

}
