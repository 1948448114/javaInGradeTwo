package Message;

import java.io.Serializable;

public class UserMessage {	
	private String userName=null;
	private String password=null;
	private String ToUserName=null;
	String Ip=null;
	String port=null;
	public void setIP(String ip){
		Ip=ip;
	}
	
	public String getIP(){
		return Ip;
	}
	
	public void setPort(String port){
		this.port=port;
	}
	
	public String getPort(){
		return port;
	}
	
	public UserMessage(){
		
	}
	public UserMessage(String name){
		userName=name;
	}
	
	public void setUsername(String name){
		userName=name;
	}
	
	public String getUsername(){
		return userName;
	}
	
	public void setPassword(String p){
		password=password;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setToUserName(String name){
		ToUserName=name;
	}
	
	public String getToUsername(){
		return ToUserName;
	}

}
