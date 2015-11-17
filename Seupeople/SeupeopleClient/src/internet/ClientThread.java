package internet;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Info.Info;

public class ClientThread extends Thread{
	DataInputStream bReader;  
	DataOutputStream out;
    Socket socket; 
    Info info=null;
    String sendString=null;
    public ClientThread(Socket socket,String string,Info info) {
    	try {
    		sendString=string;
    		this.info=info;
    		this.socket=socket;
    		out=new DataOutputStream(socket.getOutputStream());
			bReader = new DataInputStream(this.socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
            start();  
		// TODO Auto-generated constructor stub
	}
    public void run()  
        {  
		String str = "";
		// while (true)//一直等待着服务器的消息
		// {
		try {
			System.out.println("地址"+socket.getLocalAddress());
			System.out.println("端口"+socket.getLocalPort());
			out.writeUTF(sendString);
			System.out.println("客户端发送:"+sendString);
			out.flush();
			str = bReader.readUTF();
			info.set(str);
			System.out.println("服务端返回:"+str);
			out.close();
			bReader.close();
			socket.close();
			Thread.sleep(100);
			// System.out.println("客户端收到"+str);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// }
                   
        }  
}
