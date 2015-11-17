package Serverthread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by Administrator on 2014/12/25.
 */
public class UDPsend extends  Thread{
    InetAddress address;
    int port;
    String content;
    DatagramSocket server;
    public UDPsend(DatagramSocket server,String content,InetAddress address,int port){
        this.server=server;
        this.content=content;
        this.address=address;
        this.port=port;
        start();
    }

    public void run(){
        DatagramSocket sendSocket = null;
        try {
            sendSocket =new DatagramSocket();
            byte[] sendBuf;
            sendBuf = content.getBytes();
            DatagramPacket sendPacket
                    = new DatagramPacket(sendBuf, sendBuf.length, address, port);
            server.send(sendPacket);
            //sendSocket.send(sendPacket);
            UDPserver.write.writeUTF("·þÎñÆ÷»Ø¸´:"+content+"\r\n");
            System.out.println("response:"+content);
            System.out.println("----------");
            System.out.println();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            sendSocket.close();
        }

    }
}
