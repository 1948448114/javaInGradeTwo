package Main;

import Message.UserMessage;
import Serverthread.Server;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2014/12/18.
 */
public class Main {
    public static UserMessage userMessage=new UserMessage();
    public  static void main(String[] args){
        new Thread(new Runnable() {
            @Override
            public void run() {
               new Server(8888);
            }
        }).start();

//        UserMessage message=new UserMessage();
//        message.addUser("127.0.0.1:1",1);
//        message.addUser("127.0.0.1:2",2);
//        System.out.println(message.AllUser());
//
//        Map<String,Integer> user=new HashMap<String,Integer>();
//        user.put("Tom",1);
//        user.put("Jack",2);
//        int i=user.get("Tom");
//        System.out.println(i);

    }
}
