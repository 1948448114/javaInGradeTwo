package clientThread;

import java.io.DataInputStream;
import java.io.InputStream;

import Frame.MainFrame;

public class receiveFriendList extends Thread{
	DataInputStream in=null;
	MainFrame mainFrame=null;
	public receiveFriendList(MainFrame mainFrame,InputStream in1){
		in=new DataInputStream(in1);
		this.mainFrame=mainFrame;
	}
	
	public void  run(){
		
	}
}
