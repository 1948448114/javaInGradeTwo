package SQLoperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.ReturnaddressType;



public class mysqlOpeartion {
	public static String url="jdbc:mysql://localhost:3306/seupeople";
	public static String user="root";
	public static String pwd="084358";
	
	
	String nouserString="No User";
	String errorString="error";
	String okString="OK";
	String noFriendString="No Friend";
	String noTypeString="No Type";
	String noContentString="No Content";
	String noRemarkString="No Remark";
	
	String querryPwsString="QPSW";
	String querryRegisterString="QREG";
	String querryFriendString="QFRI";
	String querryContent="QCON";
	String querryRemarkString="QREM";
	String querryPersonalContent="QPCON";
	String querryInformationString="QINF";
	
	
	String updatePasswordString="UPSW";
	String deleteFriendString="DFRI";
	String deleteContentString="DCON";
	String updateNameString="UNAM";
	String updateFriendShipString="UFRS";
	String insertContentString="ICON";
	String insertRemarkString="IREM";
	String insertFriendString="IFRI";
	String insertNimingContentString="INCON";
	
	
	String registerString="REG";
	
	
	Connection conn;
	Statement sql;
	private boolean flag;
	/*
	 * 
	 * 
	 */
	public mysqlOpeartion() {
		Connect();
		//flag=false;
	}
	/*
	 * 
	 * 
	 * 
	 */
	 
	public void Connect(){
		flag=true;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pwd);
			sql=(Statement)conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			//if (!conn.isClosed())
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e);
			System.out.println("connect to sql error");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			System.out.println("JDBC ERRER?");
			//e.printStackTrace();
		}

	}
	/*
	 * 
	 * 
	 */
	public Connection getConnection(){
		return conn;
	}
	
	/*
	 * 
	 * 
	 */
	public String Operation(String typeString,String queryString)
	{
		
			if(typeString.equals(querryPwsString))
				return querrypws(queryString);
			else if(typeString.equals(querryRegisterString))
				return querryRegister(queryString);
			else if(typeString.equals(registerString))
				return Register(queryString);
			else if(typeString.equals(querryFriendString))
				return QuerryFriend(queryString);
			else if(typeString.equals(querryContent))
				return querryContent(queryString);
			else if(typeString.equals(querryRemarkString))
				return querryRemark(queryString);
			else if(typeString.equals(updatePasswordString))
				return UpdatePassword(queryString);
			else if(typeString.equals(deleteContentString))
				return DeleteContent(queryString);
			else if(typeString.equals(insertContentString))
				return insertContent(queryString);
			else if(typeString.equals(deleteFriendString))
				return DeleteFriend(queryString);
			else if(typeString.equals(insertRemarkString))
				return InsertRemark(queryString);
			else if(typeString.equals(updateFriendShipString))
				return UpdateFriendship(queryString);
			else if(typeString.equals(insertFriendString))
				return InsertFriend(queryString);
			else if(typeString.equals(querryPersonalContent))
				return QuerryPersonalContent(queryString);
			else if(typeString.equals(querryInformationString))
				return QuerryInformation(queryString);
			else if(typeString.equals(insertNimingContentString)){
				return InsertNimingContent(queryString);
			}
			else{
				System.out.println("NO type");
				return noTypeString;
			}
//			if(sql.execute(querystring)){
//			ResultSet resultSet=sql.getResultSet();
//			return resultSet;
//			if(resultSet==(null))
//			{
//			resultSet.next();
//			String passwordString=resultSet.getString(1);
//			return passwordString;
//			}
		
	}
	 

	

	/*
	 * 
	 * 
	 */
	public void close(){
		try {
			conn.close();
			sql.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			//e.printStackTrace();
		}
	}
	
	/*
	 * 查询密码
	 */
	String querrypws(String queryString){
		
			
		try {
			sql.executeQuery(queryString);
			ResultSet resultSet=sql.getResultSet();
			if(resultSet.next()==false){
				return nouserString;
			}
			else {
				String resultString=resultSet.getString(1);
				return resultString;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			//e.printStackTrace();
			return errorString;
		}
	}
	/*
	 * 查询个人信息
	 */
	String QuerryInformation(String string){
		String queString=String.format("select username from login where id='%s'",string);
		try {
			ResultSet resultSet=sql.executeQuery(queString);
			resultSet.next();
			String result=resultSet.getString(1);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return errorString;
		}
	}
	
	/*
	 * 修改密码，成功则返回ok，否则返回error
	 */
	
	String UpdatePassword(String qString){
		String ID;
		String password;
		String temp[]=qString.split("\\|");
		ID=temp[0];
		password=temp[1];
	
		String string=String.format("update login set password='%s' "
				+ "where id='%s'",password,ID);
				
		try {
			int i=sql.executeUpdate(string);
			if(i==1)
				return okString;
			else {
				return errorString;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			return errorString;
		}
	}
	
	
	/*
	 * 查询是否已有账号
	 */
	String querryRegister(String queryString){
		
			
		String result=null;
		result=querrypws(queryString);
		if(result.equals(nouserString))
			return okString;
		else {
			return errorString;
		}
	}
	
	/*
	 * 写入注册账号
	 */
	
	String Register(String string){
		
			
		int i = 0;
		try {
			i = sql.executeUpdate(string);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			//.printStackTrace();
			return errorString;
		}
		if(i==1)
			return okString;
		else {
			return errorString;
		}
	}
	
	/*
	 * 查询好友列表
	 */
	String QuerryFriend(String id1){ 
		
			
		//String stringset[];
		String result="";
		//int count=0;
		
			
		String querryString=String.format("select id2 from friend where id1='%s'",id1);
		//String querryString1="select id2 from friend where id3="+id1;
		try {
			ResultSet resultSet=sql.executeQuery(querryString);
			if(resultSet.next()==false)
				return noFriendString;
			result=resultSet.getString(1);
			while(resultSet.next()){
//				if(count==0)
//					result=resultSet.getString(1);
//				else
				  result=result+"|"+resultSet.getString(1);
//				count++;
			}
//			
			//ResultSet resultSet1=sql.executeQuery(querryString1);
			//resultSet1.next();
			//result=resultSet1.getString(1);
//			while(resultSet1.next()){
//				if(count==0)
//					result=resultSet1.getString(1);
//				else
//				  result=result+"|"+resultSet1.getString(1);
//			}
//			if(result.equals(""))
//				return noFriendString;
//			else
			   return result.trim();
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println(e);
			return errorString;
		}
		
	}
	
	/*
	 * 查询有没有动态更新，有则返回content内容，没有则返回No Content
	 */	
	String querryContent(String id){
		
			
		try {
			ArrayList<String> friend1 = new ArrayList<>();
			String querryFriendID = String.format("select id1 from friend where status1='1' and id2='%s'",id);
			// 取出能看的的人的id
			ResultSet friendSet = sql.executeQuery(querryFriendID);
			if (friendSet.next() == true) {
				friend1.add(friendSet.getString(1));
				while (friendSet.next()) {
					friend1.add(friendSet.getString(1));
				}
			}
			friend1.add(id);
			// String friend[] = new String[friend1.size()];
			// friend1.toArray(friend);
			System.out.println("朋友");
			System.out.println(friend1);
			ArrayList<String> contentIDArrayList = new ArrayList<>();
			// 取出content id
			int count = 0;
			for (int i = 0; i < friend1.size(); i++) {
				String querryContentID = String.format("select contentID from content where id='%s' and status='0'"
						,friend1.get(i));
				ResultSet contIDSet = sql.executeQuery(querryContentID);
				if (contIDSet.next() == false)
					break;
				contentIDArrayList.add(contIDSet.getString(1));
				count++;
				while (contIDSet.next()) {
					contentIDArrayList.add(contIDSet.getString(1));
				}
				// for (int k = 0; k < contentIDArrayList.size(); k++)
				// System.out.println(contentIDArrayList.get(k));
			}
			// 解决如果有一个好友没有conent就return了

			if (count == 0)
				return noContentString;
			System.out.println(contentIDArrayList);
			// 取出contet内容
			String resultString = "";
			int flag1 = 0;
			for (int k = 0; k < contentIDArrayList.size(); k++) {

				String Content =String.format("select content,time,id from content where contentID='%s'"
						,contentIDArrayList.get(k));
				ResultSet contentSet = sql.executeQuery(Content);
				// if(contentSet.next()==false)
				// System.out.println("No content");
				// resultString=contentSet.getString(1);
				// for(int i=2;i<4;i++)
				// resultString=resultString+"|"+contentSet.getString(i);
				while (contentSet.next()) {
					for (int i = 1; i < 4; i++)
						if (flag1 == 0 && i == 1) {
							resultString = contentIDArrayList.get(k);
							resultString = resultString + "|"
									+ contentSet.getString(i);
						} else {
							if (i == 1 && flag1 == 1) {
								resultString = resultString + "|"
										+ contentIDArrayList.get(k);
							}
							resultString = resultString + "|"
									+ contentSet.getString(i);
						}
					flag1 = 1;
				}
			}
			return resultString;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return errorString;
		}
	}
    /*
     * 删除content
     */
	
	String DeleteContent(String id){
		String string=String.format("delete from content where contentid='%s'",id);
		String string1=String.format("delete from remark where contentID='%s'",id);
		try {
			int i=sql.executeUpdate(string);
			int k=sql.executeUpdate(string1);
			if(i>=1&&k>=1)
				return okString;
			else {
				return errorString;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			return errorString;
		}
	}
	/*
	 * 查询评论，没有则返回No Remark
	 */
	String querryRemark(String contentId){
		
		String id=getIDFromContent(contentId);	
		String string=String.format("select remark,time,remarkerID "
				+ "from remark where contentID='%s'",contentId);
		ResultSet resultSet;
		String Result="";
		try {
			resultSet = sql.executeQuery(string);
		if(resultSet.next()==false)
			return noRemarkString;
		else{
			if(IsFriend(id, resultSet.getString(3))){
			Result+=resultSet.getString(1);
			Result=Result+"|"+resultSet.getString(2);
			Result=Result+"|"+resultSet.getString(3);
			}
			while(resultSet.next()){
				if(IsFriend(id, resultSet.getString(3))){
				for(int i=1;i<=3;i++){
					Result=Result+"|"+resultSet.getString(i);
				}
				}
			}
			
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			return errorString;
		}
		return Result;
		
	}
	
	/*
	 * 返回评论列表长度
	 */
	private int getContentTableLength(){
		String string="select * from content ";
		ResultSet resultSet;
		try {
			resultSet = sql.executeQuery(string);
			resultSet.last();
			int k=resultSet.getRow();
			return k;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			return -1;
		}
		
		
	}
	
	
	/*
	 * 添加content
	 */
	 String insertContent(String string){
		int length=getContentTableLength()+1;
		String temp[]=string.split("\\|");
		String content=temp[0];
		String time=temp[1];
		String Id=temp[2];
		String insertString="insert into content (contentID,content,time,id,status) values('"+length+"','"+content
				+"','"+time+"','"+Id+"','"+"0"+"')";
		try {
			int k=sql.executeUpdate(insertString);
			if(k>=1)
				return okString;
			else {
				return errorString;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			return errorString;
		}
	}
 
	 /*
	  * 删除朋友
	  */
	 String DeleteFriend(String queryString) {
			// TODO Auto-generated method stub
		 String temp[]=queryString.split("\\|");
		 String id1=temp[0];
		 String id2=temp[1];
		 String deleteString=String.format("delete from friend where ID1='%s' and ID2='%s' "
		 		+ "or ID1='%s' and ID2='%s'",id1,id2,id2,id1);
		 try {
			int k=sql.executeUpdate(deleteString);
			if(k>=1)
				return okString;
			else 
				return errorString;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return errorString;
		}
	
		}

	 
	 /*
	  * 插入评论
	  */
	String InsertRemark(String queryString) {
			// TODO Auto-generated method stub
		String temp[]=queryString.split("\\|");
		String remark=temp[0];
		String time=temp[1];
		String contentID=temp[2];
		String remarkerID=temp[3];
		String insString=String.format("insert into remark (remark,time,contentID,remarkerID) values("
				+ "'%s','%s','%s','%s')",remark,time,contentID,remarkerID);
		try {
			int k=sql.executeUpdate(insString);
			if(k>=1)
				return okString;
			else
				return errorString;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return errorString;
		}
		}
	 
	/*
	 * 更改好友权限
	 */
	String UpdateFriendship(String queryString){
		String temp[]=queryString.split("\\|");
		String id1=temp[0];
		String id2=temp[1];
		String type=temp[2];
		String updateString="";
		try {
		if(type.equals("Vis")){
			updateString=String.format("update friend set status1='%s' "
					+ "where ID1='%s' and ID2='%s'","1",id1,id2);
				
		}
		else{
			updateString=String.format("update friend set status1='%s' "
					+ "where ID1='%s' and ID2='%s'","0",id1,id2);
			
		}
		int k=sql.executeUpdate(updateString);
		if(k>=1)
			return okString;
		else
			return errorString;
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return errorString;
			}
	}
	
	/*
	 * 添加好友
	 */
	String InsertFriend(String queryString){
		
		String temp[]=queryString.split("\\|");
		String id1=temp[0];
		String id2=temp[1];
		String string=String.format("select * from login where id='%s'",id1);
		try {
			ResultSet resultSet=sql.executeQuery(string);
		if(resultSet.next()==false){
			return nouserString;
		}
		else{
			String string2=String.format("select * from friend where ID1='%s' and ID2='%s'",id1,id2);
			ResultSet resultSet2=sql.executeQuery(string2);
			if(resultSet2.next()==true){
				return okString;
			}
			else{
		String insertString=String.format("insert into friend (ID1,ID2) values"
				+ "('%s','%s')",id1,id2);
		
			sql.executeUpdate(insertString);
			insertString=String.format("insert into friend (ID1,ID2) values"
				+ "('%s','%s')",id2,id1);
		   sql.executeUpdate(insertString);
		   return okString;
			}
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return errorString;
		}
		
	}
	/*
	 * 获取个人说说
	 */
	String QuerryPersonalContent(String ID){
		String querry=String.format("select contentID,content,time from content where id='%s'",ID);
		ResultSet resultSet;
		String Result="";
		try {
			resultSet = sql.executeQuery(querry);
		if(resultSet.next()==false)
			return noContentString;
		else{
			Result+=resultSet.getString(1);
			Result=Result+"|"+resultSet.getString(2);
			Result=Result+"|"+resultSet.getString(3);
			Result=Result+"|"+ID;
			while(resultSet.next()){
				for(int i=1;i<=3;i++){
					Result=Result+"|"+resultSet.getString(i);
					if(i==3)
						Result=Result+"|"+ID;
				}
			}
			
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			return errorString;
		}
		return Result;
	}
	
	/*
	 * 发匿名说说
	 */
	
	String InsertNimingContent(String string){
		int length=getContentTableLength()+1;
		String temp[]=string.split("\\|");
		String content=temp[0];
		String time=temp[1];
		String Id=temp[2];
		String insertString="insert into content (contentID,content,time,id,status,niming) values('"+length+"','"+content
				+"','"+time+"','"+Id+"','"+"0"+"','1'"+"')";
		try {
			int k=sql.executeUpdate(insertString);
			if(k>=1)
				return okString;
			else {
				return errorString;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			return errorString;
		}
		
	}
	/*
	 * 查询是否是朋友
	 */
	Boolean IsFriend(String id1,String id2){
		Statement sql1 = null;
		Connection conn1 = null;
		if(id1.equals(id2))
			return true;
		String string=String.format("select * from friend where ID1='%S' and ID2='%s'",id1,id2);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn1= DriverManager.getConnection(url, user, pwd);
		sql1=(Statement)conn1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSet1=sql1.executeQuery(string);
			if(resultSet1.next()==false)
			return false;
		else {
			return true;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally{
			try {
				conn1.close();
				sql1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/*
	 * 从contentId得到发的人的ID
	 */
	String getIDFromContent(String contentId){
		Statement sql1 = null;
		Connection conn1 = null;
		String qu=String.format("select id from content where contentId='%S'",contentId);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn1= DriverManager.getConnection(url, user, pwd);
		sql1=(Statement)conn1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSet1=sql1.executeQuery(qu);
			resultSet1.next();
			return resultSet1.getString(1);
			} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return errorString;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return errorString;
		}
	finally{
		try {
			conn1.close();
			sql1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}
	
}
	


