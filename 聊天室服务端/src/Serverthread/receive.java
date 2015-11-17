package Serverthread;

import Main.Main;
import Message.ConnectMessage;
import Message.TcpMessage;

import java.io.*;

/**
 * Created by Administrator on 2014/12/20.
 */
public class receive extends Thread{
    InputStream inputStream = null;
     DataInputStream input = null;
    //ObjectInputStream input = null;
    ConnectMessage Connectmessage = null;
    ConnectMessage Threadmessage=null;
    String username=null;

    String FromName = null;
    String ToName=null;
    String TypeName=null;
    String ContentString=null;

    //��Ϣ����
    String InitString="INIT";
    String TalkString="Talk";
    String FriendList="FriendList";


    public receive(InputStream ina, ConnectMessage message,ConnectMessage message1) {
        // inputStream = ina;
        //in = new DataInputStream(inputStream);
        inputStream=ina;
        input=new DataInputStream(ina);

        Connectmessage = message;
        Threadmessage=message1;
        start();
    }

    private void setUserName(String name) {
        username = name;
    }

    private String getUserName() {
        return username;
    }

    public void setToName(String name){
        ToName=name;
    }

    public String getToName(){
        return ToName;
    }

    public void HanderMesage(String receiveMessage){
        String result[]=receiveMessage.split("\\|");
        TypeName=result[0];
        //����ǳ�ʼ�������ʾ��һ�����ӣ�������Ӧ��Ϣ������˭���ĺͷ���˭
        if(TypeName.equals(InitString)){
            FromName = result[1];
            ToName=result[2];
            setUserName(FromName);
            setToName(ToName);
            Threadmessage.setFromName(FromName);
            Threadmessage.setRequest("INIT");
            Threadmessage.setToName(ToName);
            //��Ӵ������û�
//            Main.userMessage.addUser(FromName,(int)getId());
//            Main.userMessage.PrintAll();
        }
        //�����Ľ��պͷ�����Ϣ
        else if(TypeName.equals(TalkString)){
            ContentString=result[3];
            Connectmessage.setRequest(ContentString);
            // int ThreadId=Main.userMessage.getThreadId(getToName());
            Connectmessage.setFromName(getUserName());
            Connectmessage.setToName(getToName());
        }
        else if(TypeName.equals(FriendList)){
            Threadmessage.setRequest(FriendList);
        }
        else {

        }


    }
    @Override
    public void run() {
        try {
            int count=0;

            while (true) {
                String temp=input.readUTF();
                System.out.println(count);
                count++;
                if (temp!=null) {
                    HanderMesage(temp);
                    // String request = in.readUTF();
                    System.out.println(temp);

                    System.out.println("�����̺߳�:" + getId());
                    System.out.println("�ͻ��˷��ͣ�" + Threadmessage.getRequest());
                    System.out.println("From:"+FromName);
                    System.out.println("To:"+ToName);
                    System.out.println(TypeName);

                    Connectmessage.setRequest(ContentString);
                    Connectmessage.setThreadId();
                    System.out.println("------------");

                }
                temp=null;

                sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("�ͻ����˳�����");
            //ɾ���������û�
            Main.userMessage.deleteUser(getUserName());
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(e);
        }
//        finally {
//            try {
//               // input.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
}


