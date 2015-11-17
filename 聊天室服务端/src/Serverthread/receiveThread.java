package Serverthread;

import Main.Main;
import Message.ConnectMessage;
import Message.TcpMessage;

import java.io.*;

/**
 * Created by Administrator on 2014/12/18.
 */
//将光标移到最后面按〔Alt+Insert〕以自动产生程式码，此时使用【Form main()】来产生显示此GUI表单的main()。
//Alt+回车 导入包,自动修正
//Ctrl+N   查找类
//        Ctrl+Shift+N 查找文件
//        Ctrl+Alt+L  格式化代码
//        Ctrl+Alt+O 优化导入的类和包
//        Alt+Insert 生成代码(如get,set方法,构造函数等)
//        Ctrl+E或者Alt+Shift+C  最近更改的代码
//        Ctrl+R 替换文本
//        Ctrl+F 查找文本
//        Ctrl+Shift+Space 自动补全代码
//        Ctrl+空格 代码提示
//        Ctrl+Alt+Space 类名或接口名提示
//        Ctrl+P 方法参数提示
//        Ctrl+Shift+Alt+N 查找类中的方法或变量
//        Alt+Shift+C 对比最近修改的代码
//
//        Shift+F6  重构-重命名
//        Ctrl+Shift+先上键
//        Ctrl+X 删除行
//        Ctrl+D 复制行
//        Ctrl+/ 或 Ctrl+Shift+/  注释（// 或者/*...*/ ）
//        Ctrl+J  自动代码
//        Ctrl+E 最近打开的文件
//        Ctrl+H 显示类结构图
//        Ctrl+Q 显示注释文档
//        Alt+F1 查找代码所在位置
//        Alt+1 快速打开或隐藏工程面板
//        Ctrl+Alt+ left/right 返回至上次浏览的位置
//        Alt+ left/right 切换代码视图
//        Alt+ Up/Down 在方法间快速移动定位
//        Ctrl+Shift+Up/Down 代码向上/下移动。
//        F2 或Shift+F2 高亮错误或警告快速定位
//
//        代码标签输入完成后，按Tab，生成代码。
//        选中文本，按Ctrl+Shift+F7 ，高亮显示所有该文本，按Esc高亮消失。
//        Ctrl+W 选中代码，连续按会有其他效果
//        选中文本，按Alt+F3 ，逐个往下查找相同文本，并高亮显示。
//        Ctrl+Up/Down 光标跳转到第一行或最后一行下
//        Ctrl+B 快速打开光标处的类或方法

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

    //消息类型
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
        //如果是初始化，则表示第一次连接，保存相应信息，包括谁发的和发给谁
        if(TypeName.equals(InitString)){
            FromName = receiveMessage.getFromname();
            ToName=receiveMessage.getToName();
            setUserName(FromName);
            setToName(ToName);
            //添加此在线用户
            Main.userMessage.addUser(FromName,(int)getId());
            Main.userMessage.PrintAll();
        }
        //正常的接收和发送消息
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
                    System.out.println("接收线程号:" + getId());
                    System.out.println("客户端发送：" + receive.getContent());
                    System.out.println("From:"+receive.getFromname());
                    System.out.println("To:"+receive.getToName());
                    System.out.println(receive.getType());
                    //HanderMesage(receive);
                    Connectmessage.setRequest(receive.getContent());
                    System.out.println("------------");

                }

                else
                    System.out.println("传输错误");
                //temp=null;

                sleep(100);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端退出连接");
            //删除此在线用户
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
