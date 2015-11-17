package Main;

import internet.Client;
import internet.ClientThread;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import sun.awt.windows.ThemeReader;
import Frame.LoginFrame;
import Info.Info;

public class Main {
	public static String host="223.3.31.20";
	public static int port=8888;
	
	public static String getResultFromServer(String string){
	  Client client=new Client(host, port);
	  client.sendRequest(string);
	  String resultString=client.getResponse();
	  client.close();
	  return resultString;
	}
	public static void main(String[] args) {
		 EventQueue.invokeLater(new Runnable() {
		 public void run() {
		 try {
		 LoginFrame window = new LoginFrame();
		 window.frame.setVisible(true);
		 } catch (Exception e) {
		 e.printStackTrace();
		 }
		 }
		 });
	}
		
		
//		String querryPwsString="QPSW";
//		String querryRegisterString="QREG";
//		String querryFriendString="QFRI";
//		String querryContent="QCON";
//		String querryRemarkString="QREM";
//		
//		
//		String updatePasswordString="UPSW";
//		String deleteFriendString="DFRI";
//		String deleteContentString="DCON";
//		String updateNameString="UNAM";
//		String updateFriendShipString="UFRS";
//		String insertContentString="ICON";
//		String insertRemarkString="IREM";
//		
//		String registerString="REG";
//		
//		new Thread(new Runnable() {
//			
//			public void run() {
//				String send="IFRI|"+"3|4";
//				Info info=new Info();
//				Client client = null;
//				client = new Client("127.0.0.1", 8888);
//				 //while(true){
//				System.out.println("----------------------------------");
//				// client.sendRequest("hello word" + "\r\n");
//				// client.getResponse();
//				// new sendThread(client.clientSocket);
//				new ClientThread(client.clientSocket,send,info);
//				System.out.println(info.get());
//				// client.close();
//				// }
//
//			}
//		}).start();
//	}

}
