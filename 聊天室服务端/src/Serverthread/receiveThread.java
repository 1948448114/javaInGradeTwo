package Serverthread;

import Main.Main;
import Message.ConnectMessage;
import Message.TcpMessage;

import java.io.*;

/**
 * Created by Administrator on 2014/12/18.
 */
//������Ƶ�����水��Alt+Insert�����Զ�������ʽ�룬��ʱʹ�á�Form main()����������ʾ��GUI����main()��
//Alt+�س� �����,�Զ�����
//Ctrl+N   ������
//        Ctrl+Shift+N �����ļ�
//        Ctrl+Alt+L  ��ʽ������
//        Ctrl+Alt+O �Ż��������Ͱ�
//        Alt+Insert ���ɴ���(��get,set����,���캯����)
//        Ctrl+E����Alt+Shift+C  ������ĵĴ���
//        Ctrl+R �滻�ı�
//        Ctrl+F �����ı�
//        Ctrl+Shift+Space �Զ���ȫ����
//        Ctrl+�ո� ������ʾ
//        Ctrl+Alt+Space ������ӿ�����ʾ
//        Ctrl+P ����������ʾ
//        Ctrl+Shift+Alt+N �������еķ��������
//        Alt+Shift+C �Ա�����޸ĵĴ���
//
//        Shift+F6  �ع�-������
//        Ctrl+Shift+���ϼ�
//        Ctrl+X ɾ����
//        Ctrl+D ������
//        Ctrl+/ �� Ctrl+Shift+/  ע�ͣ�// ����/*...*/ ��
//        Ctrl+J  �Զ�����
//        Ctrl+E ����򿪵��ļ�
//        Ctrl+H ��ʾ��ṹͼ
//        Ctrl+Q ��ʾע���ĵ�
//        Alt+F1 ���Ҵ�������λ��
//        Alt+1 ���ٴ򿪻����ع������
//        Ctrl+Alt+ left/right �������ϴ������λ��
//        Alt+ left/right �л�������ͼ
//        Alt+ Up/Down �ڷ���������ƶ���λ
//        Ctrl+Shift+Up/Down ��������/���ƶ���
//        F2 ��Shift+F2 ��������򾯸���ٶ�λ
//
//        �����ǩ������ɺ󣬰�Tab�����ɴ��롣
//        ѡ���ı�����Ctrl+Shift+F7 ��������ʾ���и��ı�����Esc������ʧ��
//        Ctrl+W ѡ�д��룬��������������Ч��
//        ѡ���ı�����Alt+F3 ��������²�����ͬ�ı�����������ʾ��
//        Ctrl+Up/Down �����ת����һ�л����һ����
//        Ctrl+B ���ٴ򿪹�괦����򷽷�

public class receiveThread extends Thread {
    InputStream inputStream = null;
    // DataInputStream in = null;
    ObjectInputStream input = null;
    ConnectMessage Connectmessage = null;

    String username=null;

    String FromName = null;
    String ToName=null;
    String TypeName=null;
    String ContentString=null;

    //��Ϣ����
    String InitString="INIT";
    String TalkString="Talk";


    public receiveThread(InputStream ina, ConnectMessage message) {
        // inputStream = ina;
        //in = new DataInputStream(inputStream);
            inputStream=ina;
        try {
            input = new ObjectInputStream(new BufferedInputStream(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Connectmessage = message;
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

    public void HanderMesage(TcpMessage receiveMessage){
        TypeName=receiveMessage.getType();
        //����ǳ�ʼ�������ʾ��һ�����ӣ�������Ӧ��Ϣ������˭���ĺͷ���˭
        if(TypeName.equals(InitString)){
            FromName = receiveMessage.getFromname();
            ToName=receiveMessage.getToName();
            setUserName(FromName);
            setToName(ToName);
            //��Ӵ������û�
            Main.userMessage.addUser(FromName,(int)getId());
            Main.userMessage.PrintAll();
        }
        //�����Ľ��պͷ�����Ϣ
        else if(TypeName.equals(TalkString)){
            ContentString=receiveMessage.getContent();
            Connectmessage.setRequest(ContentString);
           // int ThreadId=Main.userMessage.getThreadId(getToName());
            Connectmessage.setFromName(getUserName());
            Connectmessage.setToName(getToName());
        }
        else {

        }


    }
    @Override
    public void run() {
        try {
            int count=0;

            while (true) {

                TcpMessage receive = (TcpMessage) input.readObject();
                System.out.println(count);
                count++;
                if (receive!= null) {

                    // String request = in.readUTF();
                    System.out.println("�����̺߳�:" + getId());
                    System.out.println("�ͻ��˷��ͣ�" + receive.getContent());
                    System.out.println("From:"+receive.getFromname());
                    System.out.println("To:"+receive.getToName());
                    System.out.println(receive.getType());
                    //HanderMesage(receive);
                    Connectmessage.setRequest(receive.getContent());
                    System.out.println("------------");

                }

                else
                    System.out.println("�������");
                //temp=null;

                sleep(100);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("�ͻ����˳�����");
            //ɾ���������û�
            Main.userMessage.deleteUser(getUserName());
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(e);
        } catch (ClassNotFoundException e) {
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
