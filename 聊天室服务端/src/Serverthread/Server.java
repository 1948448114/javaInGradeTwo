package Serverthread;

import Message.ConnectMessage;
import sun.misc.Cleaner;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2014/12/18.
 */
public class Server {
    int port;
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    ConnectMessage message=new ConnectMessage();

    public Server(int port) {
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);

            System.out.println("服务端正在监听：" + port + "端口");
            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("-----------------");
                System.out.println("客户端连接");
                System.out.println("客户端地址:" + clientSocket.getInetAddress());
                System.out.println("客户端端口" + clientSocket.getPort());
                InputStream in = clientSocket.getInputStream();
                OutputStream out = clientSocket.getOutputStream();
                ConnectMessage message1=new ConnectMessage();
                Thread rec= new receive(in,message,message1);
                Thread sen= new send(out,message,message1);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
            System.out.println("服务端没法启动");
        } finally {
            try {

                serverSocket.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
