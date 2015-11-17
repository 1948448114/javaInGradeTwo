package internet;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;

import SQLoperation.mysqlOpeartion;

public class ServerThread extends Thread{
	Socket socket;
	//BufferedReader in;
	DataInputStream in;
	DataOutputStream out;
	//ObjectOutputStream out;
	public ServerThread(Socket socket) throws IOException{
		this.socket=socket;
		//in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		in=new DataInputStream(socket.getInputStream());
		out=new DataOutputStream(socket.getOutputStream());
		//out=new ObjectOutputStream(socket.getOutputStream());
		start();
	}
	public void run(){
		String typeString=null;
		String requestString=null;
		try {
			System.out.println("-----------------");
			System.out.println("�ͻ�������");
			System.out.println("�ͻ��˵�ַ:"+socket.getInetAddress());
			System.out.println("�ͻ��˶˿�"+socket.getPort());
			String string=in.readUTF();
			System.out.println("�ͻ��˷���"+string);
			String string2[]=string.split("\\|");
			typeString=string2[0];
			requestString=string2[1];
			for(int i=2;i<string2.length;i++)
			requestString=requestString+"|"+string2[i];
			//typeString=in.readLine();
			//typeString=typeString.trim();
			//typeString="PSW";
			//requestString=in.readLine();
			//requestString=requestString.trim();//ȥ�����з�
			//requestString=in.readUTF();
			System.out.println("�ͻ��˷���:"+typeString);
			System.out.println("�ͻ��˷���:"+requestString);
			mysqlOpeartion opeartion=new mysqlOpeartion();
			opeartion.Connect();
//			
			String resultString=opeartion.Operation(typeString,requestString);
//			if(resultString==null)
//				requestString="error";
			//out.writeObject(resultString);
			out.writeUTF(resultString);
			out.flush();
			System.out.println("����˻�Ӧ"+resultString);
			System.out.println("---------------------");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			System.out.println("��ȡ�ͻ������ݴ���");
			//System.exit(0);
		}
		
		
		finally {
			try {
				in.close();
				out.close();
			    socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} 
		
	}

}
