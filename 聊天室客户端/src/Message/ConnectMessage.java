package Message;

public class ConnectMessage {
	    String request=null;
	    String response=null;
	    
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

}
