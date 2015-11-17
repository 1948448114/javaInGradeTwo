package internet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public Server(int port){
		//String host="127.0.0.1";
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("���ڼ���"+port+"�˿�");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("�����˿�" + port + "ʧ��");
			//System.exit(0);
		}
		// TODO Auto-generated method stub
		try {
			while (true) {
				clientSocket = serverSocket.accept();
				try {
					new ServerThread(clientSocket);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
					System.out.println("��ȡʧ��");
					//System.exit(0);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("���տͻ�������ʧ��");
			//System.exit(0);
		}
		finally{
			try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}

