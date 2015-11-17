package Serverthread;

import Main.Main;
import Message.ConnectMessage;
import Message.TcpMessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2014/12/20.
 */
public class send extends Thread{
    //OutputStream outputStream = null;
    DataOutputStream out = null;
    TcpMessage send =new TcpMessage();
    //ObjectOutputStream out = null;
    ConnectMessage connectMessage = null;
    ConnectMessage ThreadMessage=null;
    String username = null;

    String FromName = null;
    String ToName=null;
    String TypeName=null;
    String ContentString=null;


    String NoUserString="No User";
    String InitString="INIT";
    String FriendList="FriendList";

    public send(OutputStream outa,ConnectMessage message,ConnectMessage message1) {
        out=new DataOutputStream(outa);
        //out = new DataOutputStream(outputStream);
        connectMessage = message;//��ͬ���Ѽ���Ϣ
        ThreadMessage=message1;//ͬһ���˼������Ϣ
        start();
    }

    private void setUserName(String name) {
        username = name;
    }

    private String getUserName() {
        return username;
    }


//    public int HanderMessage(ConnectMessage message){
//        ToName=message.getToName();
//        int ThreadId= Main.userMessage.getThreadId(ToName);
//        if(ThreadId==-1){
//            int FromThreadId=Main.userMessage.getThreadId(message.getFromname());
//
//            message.setResponse(NoUserString);
//            return FromThreadId;
//        }
//        else{
//            message.setResponse(message.getRequest());
//            return ThreadId;
//        }
//    }
    @Override
    public void run() {
        //String result = "�յ�";
        try {
            while (true) {
                String string = ThreadMessage.getRequest();
                //����ֻ��Ҫ��һ���ͻ��䷵����Ϣ�����ͻ�����͸��º���
                if (!(string == null)) {

                    if (string.equals(InitString)) {
                        System.out.println(string);
                        Main.userMessage.addUser(ThreadMessage.getFromname(), (int) getId());
                        System.out.println("���������û�:");
                        Main.userMessage.PrintAll();
                        ThreadMessage.setRequest(null);
                    }
                    else if (string.equals(FriendList)) {
                        String result=FriendList+Main.userMessage.AllUser();
                        out.writeUTF(result);
                        out.flush();
                        // connectMessage.setResponse(result);
                        //connectMessage.setRequest(null);
                        connectMessage.Clear();
                        System.out.println("�����̺߳�:" + getId());
                        System.out.println("����˻ظ�:" + result);
                        System.out.println("--------");
                        ThreadMessage.setRequest(null);
                    }
                }
                //������Ϣ�Ĵ���
                if (connectMessage.getRequest() != null) {
                        int id = connectMessage.getThreadID();
                        if (getId() == id) {
                            String result = connectMessage.getFromname() + "|" + connectMessage.getResponse();
//                        send.setContent(connectMessage.getResponse());
//                        send.setFromName(connectMessage.getFromname());
                            // send.setContent(result);
                            //out.writeObject(send);
                            out.writeUTF(result);
                            out.flush();
                            // connectMessage.setResponse(result);
                            //connectMessage.setRequest(null);
                            connectMessage.Clear();
                            System.out.println("�����̺߳�:" + getId());
                            System.out.println("����˻ظ�:" + result);
                            System.out.println("--------");
                        }
                    }

                // }
                sleep(100);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("��ȡ�ͻ������ݴ���");

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
