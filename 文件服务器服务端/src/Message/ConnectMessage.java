package Message;

import Main.Main;

/**
 * Created by Administrator on 2014/12/18.
 */
public class ConnectMessage {
    String request=null;
    String response=null;
    String typeName=null;
    String FileName=null;
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

    public String getFileName() {
        return FileName;
    }

    public String getTypeName() {

        return typeName;
    }

    public void setFileName(String fileName) {

        FileName = fileName;
    }

    public void setTypeName(String typeName) {

        this.typeName = typeName;
    }

    public void Clear(){
        setRequest(null);
        setTypeName(null);
        setFileName(null);
    }


}
