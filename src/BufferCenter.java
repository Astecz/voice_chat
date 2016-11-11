
public class BufferCenter {
	
	static byte buffer[][] = new byte[5][512];
	static boolean isEmptyFlags[] = new boolean[5];
	static int cont[] = new int[5];
	static int numUsers;
	
	
	public static synchronized void setNumUsers(int num){
		numUsers = num;
	}
	
	public static synchronized byte[] getBuffer(int id){
		byte[] temp = null; 
		
		if(cont[id] < numUsers && !isEmptyFlags[id]){ //Confere se todos já leram o buffer e se o buffer está cheio
			cont[id]++;
			temp = buffer[id].clone();
			if(cont[id] == numUsers){
				isEmptyFlags[id] = true;
				cont[id] = 0;
			}
		}
		
		return temp;
	}
	
	//public static synchronized byte[][]
	
//	public static synchronized void writeBuffer(byte[] b, int id){
//		buffer[id] = b.clone();
//	}
	

}
