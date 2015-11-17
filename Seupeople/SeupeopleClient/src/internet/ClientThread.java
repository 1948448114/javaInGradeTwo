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
		// while (true)//һֱ�ȴ��ŷ���������Ϣ
		// {
		try {
			System.out.println("��ַ"+socket.getLocalAddress());
			System.out.println("�˿�"+socket.getLocalPort());
			out.writeUTF(sendString);
			System.out.println("�ͻ��˷���:"+sendString);
			out.flush();
			str = bReader.readUTF();
			info.set(str);
			System.out.println("����˷���:"+str);
			out.close();
			bReader.close();
			socket.close();
			Thread.sleep(100);
			// System.out.println("�ͻ����յ�"+str);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// }
                   
        }  
}
