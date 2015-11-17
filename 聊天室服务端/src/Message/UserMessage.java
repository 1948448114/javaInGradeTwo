package Message;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2014/12/18.
 */
public class UserMessage {
    Map<String,Integer> user=new HashMap<String,Integer>();
    public UserMessage(){

    }

    public void addUser(String name,int threadId){

        user.put(name,threadId);
    }
    public void deleteUser(String name){

        user.remove(name);
    }

    public int getThreadId(String name){
        if(user.containsKey(name)) {
            int id = user.get(name);
            return id;
        }
        else
            return -1;
    }
    public void PrintAll(){
        System.out.println(user.keySet());
    }
    //public boolean HasUser(String name)

    public String AllUser() {
        String temp = "";
        int flag = 0;
        Set<String> key = user.keySet();

        for (Iterator it = key.iterator(); it.hasNext(); ) {
            String s = (String) it.next();
            System.out.println(s);
            temp = temp+"|" + s;

            //System.out.println(user.get(s));
        }
        return temp;
    }

}
