package internet;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;



public class Client {

    DataOutputStream out;
    DataInputStream in;
    InetAddress addr;
    public Socket clientSocket=new Socket();
    public Client(String servername,int port) {
        try {
            addr=InetAddress.getByName(servername);
            clientSocket.connect(new InetSocketAddress(addr,port),3000);
            out= new DataOutputStream(clientSocket.getOutputStream());
            in= new DataInputStream(clientSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.out.println("地址错误");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("客户端连接服务端失败");
            System.exit(0);
        }
    }


    public void sendRequest(String response){

        try{
            out.writeUTF(response);
            System.out.println("client 发送请求:" + response);
           out.flush();
        }
        catch(Exception e){
            System.out.println("发送失败");
            System.out.println(e.getMessage());
        }
    }

    public String getResponse(){
        String string=null;
            try {
                string=in.readUTF();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
           // string=string.trim();
        System.out.println("Client收到server返回:"+string);
        return string;
    }

    public void close(){

        try {
            if(out!=null)
            out.close();
            if(in!=null)
            in.close();
            clientSocket.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
//    public static void main(String[] args) {
//        Client client = null;
//        client = new Client("127.0.0.1", 8888);
//        System.out.println("----------------------------------");
//        client.sendRequest("hello word" + "\r\n");
//        client.getResponse();
//        client.close();
//
//
//
//        client = new Client("127.0.0.1", 8888);
//        System.out.println("----------------------------------");
//        client.sendRequest("hello word" + "\r\n");
//        client.getResponse();
////        
//    }
}
