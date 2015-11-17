package clientThread;

import Frame.Dialog;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import Frame.MainFrame;
import Main.Main;
import Message.TcpMessage;


public class sendThread extends Thread{
	DataOutputStream out=null;
	//ObjectOutputStream out=null;
	TcpMessage send=new TcpMessage();
	int flag=0;
	public sendThread(OutputStream out1){
	
			//out=new ObjectOutputStream(out1);
		out=new DataOutputStream(out1);
		start();
	}
	
	public void run(){
		//String send="Hello word";
		try {
			String toName=MainFrame.userMessage.getToUsername();
//			send.setFromName(MainFrame.userMessage.getUsername());
//			send.setToName("Jack");
//			send.setFromName("LJ");
//			send.setToName("Jack");
//			send.setContent("hello~");
//			send.setType("TalkMessae");
			
			while (true) {
				String resultString=null;
				if(flag==0){
					//send.setType("INIT");
					resultString="INIT|"+MainFrame.userMessage.getUsername()+"|"
							+MainFrame.userMessage.getToUsername()+"|"+"INIT";
					flag=1;
					out.writeUTF(resultString);
					System.out.println("客户端发送 :" + MainFrame.message.getRequest());
					MainFrame.message.setRequest(null);
					out.flush();
				}
				else {
				if (MainFrame.message.getRequest()!=null) {
					if(MainFrame.userMessage.getToUsername()==null)
						new Dialog("请先选择好友");
					else{resultString="Talk|"+MainFrame.userMessage.getUsername()+"|"
							+MainFrame.userMessage.getToUsername()+"|"+MainFrame.message.getRequest();
					
					
					out.writeUTF(resultString);
					System.out.println(send.getType());
					System.out.println("客户端发送 :" + MainFrame.message.getRequest());
					
					out.flush();
					}
					MainFrame.message.setRequest(null);
				}
				}
		   
		    sleep(100);
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
