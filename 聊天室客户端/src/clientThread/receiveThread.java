package clientThread;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import Frame.Dialog;
import Frame.MainFrame;
import Message.TcpMessage;

public class receiveThread extends Thread{
	//TcpMessage receive=null;
	//ObjectInputStream input=null;
	MainFrame mainFrame;
	String noUserString="No User";
	String FriendList="FriendList";
	DataInputStream input=null;
	public receiveThread(MainFrame main,InputStream ina){
		//inputStream=ina;
		this.mainFrame=main;
	
			input=new DataInputStream(ina);
		
		start();
	}
	public void run(){
		String receive=null;
		try {
			while(true){
			receive=input.readUTF();
			if(receive!=null){
				
			String temp[]=receive.split("\\|");
			if(temp[0].equals(FriendList)){
				mainFrame.deleteList();
				for(int i=1;i<temp.length;i++){
					mainFrame.addList(temp[i]);
				}
			}
			else if(temp[1].equals(noUserString))
				new Dialog("Ta 不在线哦");
			else{
			//MainFrame.message.setResponse(receive.getContent());
			mainFrame.allMessageText.append(temp[0]+":\n");
			mainFrame.allMessageText.append(temp[1]+"  "+"\n");
			System.out.println("服务端返回:"+temp[1]);
			}
			receive=null;
			}
			sleep(100);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			System.out.println("服务端断开连接");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		} 	}

}
