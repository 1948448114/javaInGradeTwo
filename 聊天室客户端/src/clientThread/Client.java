package clientThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import Frame.Dialog;
import Frame.MainFrame;

public class Client extends Thread{
	Socket clientSocket=null;
	InputStream inputStream=null;
	OutputStream outputStream=null;
	MainFrame mainFrame;
	String address=null;
	String port=null;

	public Client(MainFrame mainFrame,String address,int port){
		try {
			this.address=address;
			this.port=String.valueOf(port);
			this.mainFrame=mainFrame;
			clientSocket=new Socket(address,port);
			new Dialog("客户端连接成功");
			mainFrame.connectButton.setEnabled(false);
			
			mainFrame.NameText.setText(address+":"+2);
			mainFrame.userMessage.setUsername(address+":"+2);
			
			System.out.println("客户端连接成功");
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			System.out.println("地址错误");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("连接服务器失败");
		}
		start();
	}
	
	public String getIp(){
		return address;
	}
	
	public String getPort(){
		return port;
	}
	
	public void run(){
		try {
			
			inputStream=clientSocket.getInputStream();
			outputStream=clientSocket.getOutputStream();
			
			new sendThread(outputStream);
			new receiveThread(mainFrame,inputStream);
			new sendFriendList(outputStream);
			while(true){
			Thread.sleep(10000);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		 catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally{
			try {
				
				inputStream.close();
				outputStream.close();
				clientSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}
			
		}
	}

}
