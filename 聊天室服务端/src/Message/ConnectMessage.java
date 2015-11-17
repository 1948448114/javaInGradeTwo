package Message;

import Main.Main;

/**
 * Created by Administrator on 2014/12/18.
 */
public class ConnectMessage {
    String request=null;
    String response=null;
    String fromName=null;
    String toName=null;
    int ThreadID;

    String NoUserString="No User";
    public ConnectMessage(){

    }
    public void setRequest(String request){
        this.request=request;
    }
    public String getRequest(){
        return request;
    }
    public void setResponse(String response){
        this.response=response;
    }
    public String getResponse(){
        return response;
    }

    public void setFromName(String name){
        fromName=name;
    }

    public void setToName(String name){
        toName=name;
    }

    public String getFromname(){
        return fromName;
    }

    public String getToName()
    {
        return toName;
    }

    public void Clear(){
        setToName(null);
        setFromName(null);
        setRequest(null);
        ThreadID=-1;

    }

    public void setThreadId(){
        int ToId=Main.userMessage.getThreadId(getToName());
        if(ToId==-1){
            ThreadID=Main.userMessage.getThreadId(getFromname());
            setResponse(NoUserString);
        }
        else
        {ThreadID=ToId;
        setResponse(getRequest());
        }
    }

    public int getThreadID(){
        return ThreadID;
    }

}
