package clientThread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

import Frame.Dialog;
import Frame.FileFrame;
public class UDPclient extends Thread{
	String address;
	int port;  
	DatagramSocket client;
	FileFrame fileFrame;
	public refreshProgressThread refresh;
	
	String FileString="File";
	String noFileString="No File";
	String ListString="List";
	int ThreadNumber;
	
	FileReceiveThread fileThread[];
	
  public UDPclient(FileFrame fileFrame,String address,int port){
	  this.fileFrame=fileFrame;
	  this.address=address;
	  this.port=port;
	  fileFrame.connectButton.setEnabled(false);
	  fileFrame.DownloadButton.setEnabled(true);
	  fileFrame.listButton.setEnabled(true);
	  
	  ThreadNumber=fileFrame.fileList.getThreadNumber();
	  fileThread=new FileReceiveThread[ThreadNumber];
      start();
  }
  public void run(){
	  try { 
		InetAddress addr=null;
		addr = InetAddress.getByName(address);
		//设置本地端口8887
		client=new DatagramSocket(8887);
		new Dialog("启动成功");
		while (true) {
				byte[] recvBuf = new byte[1024];
				DatagramPacket recvPacket = new DatagramPacket(recvBuf,
						recvBuf.length,addr,port);
				client.receive(recvPacket);
				String recvStr = new String(recvPacket.getData(), 0,
						recvPacket.getLength());
				System.out.println("从服务端收到:" + recvStr);
				DealMessage(recvStr);
				sleep(1000);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			client.close();
		}
		
  }
  //处理消息
  public void DealMessage(String message){
	  String temp[]=message.split("\\|");
	  if(temp[0].equals(ListString)){
		  fileFrame.refreshList(temp);
	  }
	  else if(temp[0].equals(FileString)){
		 // String length=temp[1];
		  String ThrheadPort[]=new String[ThreadNumber];
		 // long fileLength=Long.parseLong(length);
		 // System.out.println("File length:"+length); 
		 // fileFrame.fileList.getFileInfo(fileFrame.message.getFileName()).setFileLength(fileLength);
		  //fileFrame.message.setFileLength(fileLength);
		  refresh=new refreshProgressThread(fileFrame);
		  long startPosition[]=fileFrame.fileList.getFileInfo(fileFrame.message.getFileName()).getStartPosition();
		  long downloaded[]=fileFrame.fileList.getFileInfo(fileFrame.message.getFileName()).getDownloadLegth();
		  for(int k=1;k<1+ThreadNumber;k++){
			  //ThrheadPort[k-2]=temp[k];
			  long position=startPosition[k-1]+downloaded[k-1];
			  fileThread[k-1]=new FileReceiveThread(fileFrame, address,Integer.parseInt(temp[k]),k-1,position);
		  }
		 
		  
		 
		 
		 
		  
	  }
	  else if(temp[0].equals(noFileString)){
		  
	  }
	  
  }
  
  //发送线程
  public void send(String send){
	  new UDPsend(client, send,getAdress(),getPort());
  }
  public String getAdress(){
	  return address;
  }
  
  public int getPort(){
	  return port;
  }
  
  public void stopThread(){
	  for(int i=0;i<ThreadNumber;i++){
		  fileThread[i].stopThread();
	  }
	  refresh.setFlag();
  }
}
