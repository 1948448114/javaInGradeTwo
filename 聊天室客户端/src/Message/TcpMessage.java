package Message;

import java.io.Serializable;

public class TcpMessage implements Serializable{
	String fromName=null;
	String toName=null;
	String type=null;
	String content=null;
	 private static final long serialVersionUID = 1L; 
	public TcpMessage(){
		
	}
	
	public TcpMessage(String name1,String name2){
		setFromName(name1);
		setToName(name2);
	}
	public void setFromName(String name){
		fromName=name;
	}
	
	public void setToName(String name){
		toName=name;
	}
	
	public void setType(String type){
		this.type=type;
	}
	
	public void setContent(String content){
		this.content=content;
	}
	
	public String getFromname(){
		return fromName;
	}
	
	public String getToName()
	{
		return toName;
	}
	
	public String getType(){
		return type;
	}
	
	public String getContent(){
		if(content==null){
			return null;
		}
		else
		return content;
	}
	
}
