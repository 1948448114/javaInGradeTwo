package clientThread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPsend extends Thread{
	String send;
	DatagramSocket client;
	DatagramPacket sendPacket;
	String address;
	int port;
	public UDPsend(DatagramSocket client,String send,String address,int port) {
		this.address=address;
		this.port=port;
		this.send = send;
		this.client=client;
			start();
	}
	public void run(){
		try {
		//client=new DatagramSocket(8887);
		InetAddress addr = InetAddress.getByName(address);
		byte[] sendBuf;
		sendBuf = send.getBytes();
		sendPacket = new DatagramPacket(sendBuf,
				sendBuf.length,addr,port);
		client.send(sendPacket);
		System.out.println("¿Í»§¶Ë·¢ËÍ:"+send);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error1");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error2");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//client.close();
			
	}
}
