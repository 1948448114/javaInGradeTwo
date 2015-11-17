package Serverthread;

import Main.Main;
import Message.ConnectMessage;
import Message.TcpMessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Member;

/**
 * Created by Administrator on 2014/12/18.
 */
public class sendThread extends Thread {
    //OutputStream outputStream = null;
    //DataOutputStream out = null;
    TcpMessage send =new TcpMessage();
    ObjectOutputStream out = null;
    ConnectMessage connectMessage = null;
    String username = null;

    String FromName = null;
    String ToName=null;
    String TypeName=null;
    String ContentString=null;


    String NoUserString="No User";

    public sendThread(OutputStream outa, ConnectMessage message) {
        try {
            out = new ObjectOutputStream(outa);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        //out = new DataOutputStream(outputStream);
        connectMessage = message;
        start();
    }

    private void setUserName(String name) {
        username = name;
    }

    private String getUserName() {
        return username;
    }


    public int HanderMessage(ConnectMessage message){
        ToName=message.getToName();
        int ThreadId=Main.userMessage.getThreadId(ToName);
        if(ThreadId==-1){
            int FromThreadId=Main.userMessage.getThreadId(message.getFromname());

            message.setResponse(NoUserString);
            return FromThreadId;
        }
        else{
            message.setResponse(message.getRequest());
            return ThreadId;
        }
    }
    @Override
    public void run() {
        String result = "收到";
        try {
            while (true) {
                if (connectMessage.getRequest() != null) {
//                    int id=connectMessage.getThreadID();
//                    if(getId()==id) {
//                        send.setContent(connectMessage.getResponse());
//                        send.setFromName(connectMessage.getFromname());
                    send.setContent(result);
                    out.writeObject(send);
                    out.flush();
                    // connectMessage.setResponse(result);
                    //connectMessage.setRequest(null);
                    connectMessage.Clear();
                    System.out.println("发送线程号:" + getId());
                    System.out.println("服务端回复:" + result);
                    System.out.println("--------");
                }
                // }
                sleep(100);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("读取客户端数据错误");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
