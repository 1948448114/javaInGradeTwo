package clientThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class sendFriendList extends Thread{
	DataOutputStream out=null;
	public sendFriendList(OutputStream out1){
		out=new DataOutputStream(out1);
		start();
		
	}
	public void  run(){
		String typename="FriendList";
		try {
			
			while(true){
				out.writeUTF(typename);
				out.flush();
				sleep(10000);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
