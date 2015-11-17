package Serverthread;

import File.FileInfo;
import File.getFileList;
import Message.ConnectMessage;

import java.io.*;
import java.net.*;

/**
 * Created by Administrator on 2014/12/25.
 */
public class UDPserver extends Thread{
    DatagramSocket server=null;
    String FileString="File";
    String ListString="List";
    String logPath="log.txt";
    int port;
    InetAddress addr;
   public static DataOutputStream write;

    public static ConnectMessage message=new ConnectMessage();
    public  static File log;
    public UDPserver(String address,int port){
        try {
            log=new File(logPath);
            write=new DataOutputStream(new FileOutputStream(log,true));
            InetAddress addrServer = InetAddress.getByName(address);
            server=new DatagramSocket(port,addrServer);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        start();
    }

    public void run(){
        byte[] recvBuf = new byte[100];
        DatagramPacket recvPacket=new DatagramPacket(recvBuf, recvBuf.length);
        try {
            while(true) {
                server.receive(recvPacket);
                String recvStr = new String(recvPacket.getData(), 0, recvPacket.getLength());
                System.out.println("---------");
                System.out.println("�ͻ���IP��ַ" + recvPacket.getAddress());
                write.writeUTF("�ͻ���IP��ַ:" + recvPacket.getAddress()+"\r\n");

                System.out.println("�ͻ��˶˿�" + recvPacket.getPort());
                write.writeUTF("�ͻ��˶˿�" + recvPacket.getPort()+"\r\n");
                System.out.println("�ͻ��˷���" + recvStr);
                write.writeUTF("�ͻ��˷���" + recvStr+"\r\n");
                //��ö˿���Ҫ��handermessage��д�������һ����Щֵ���ǿյ�
                port = recvPacket.getPort();
                addr = recvPacket.getAddress();
                HanderMessage(recvStr);
                sleep(100);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            server.close();
        }
    }


    public void HanderMessage(String request){
        String temp[]=request.split("\\|");
        if(temp[0].equals(FileString)){
            String response=FileString;
            FileInfo fileInfo=new FileInfo(temp[1],Integer.parseInt(temp[2]));
            message.setFileName(temp[1]);

            int ThreadNumber=Integer.parseInt(temp[2]);
            //��ȡ���������ļ���ʼλ����Ϣ
            long position[]=new long[ThreadNumber];
            for(int i=3;i<3+ThreadNumber;i++){
                position[i-3]=Long.parseLong(temp[i]);
            }
            //���ļ���ʼλ��д��fileinfo����
            fileInfo.setStartPosition(position);
            //�������ļ��Ķ˿ڷ��͸��ͻ���
            for(int k=0;k<ThreadNumber;k++){
                int port= new FileSendThread(k,fileInfo).getPort();
                response=response+"|"+String.valueOf(port);
            }
            new UDPsend(server,response, addr, port);


        }
        else if (temp[0].equals(ListString)) {
            String list = new getFileList().getList();
            if(list==null)
                System.out.println("no file");
            else{
                new UDPsend(server,ListString+list,addr,port);
            }
        }
        else{

        }
    }
}
