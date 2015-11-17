package File;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class FileList {
	public HashMap<String, FileInfo> fileList=new HashMap<>();
	int ThreadNumber;
	public FileList(int number){
		ThreadNumber=number;
	}
	
	public int getThreadNumber(){
		return ThreadNumber;
	}
	public void addFile(String path){
		fileList.put(path,new FileInfo(ThreadNumber));
		
	}
	public FileInfo getFileInfo(String path){
		return fileList.get(path);
	}
	
	public void removeAll(){
		fileList.clear();
	}
	
	public void print(){
		Set<String> temp=fileList.keySet();
		for(Iterator<String> it=temp.iterator();it.hasNext();)
		{
			System.out.println(it.next());
			
		}
	}

}
