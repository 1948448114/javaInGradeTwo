package Message;

import Main.Main;

public class ConnectMessage {
	    String request=null;
	    String response=null;
	    String filename="D:\\";
	    String typeString=null;
	    String filePath=null;
	    String fileDic=null;
	    int index=-1;
	    //long progress=-1;
	    long FileLength=-1;
	    int ThreadNumber;
	    long progress[];
	  
	    
	    public ConnectMessage(){
	    	ThreadNumber=Main.ThreadNumber;
	    	progress=new long[ThreadNumber];
	    }
	    public long getFileLength() {
			return FileLength;
		}
		public void setFileLength(long fileLength) {
			FileLength = fileLength;
		}
		public long getProgress() {
			  long total=0;
			for(int i=0;i<ThreadNumber;i++){
				//System.out.println(i+" progress:"+progress[i]);
				total+=progress[i];
			}
			return total;
		}
		public void setProgress(int i, long progress) {
			this.progress[i] = progress;
		}
		
	    
	    public void setFileName(String name){
	    	filename=name;
	    }
	    public String getFileName(){
	    	return filename;
	    }
	    
	    public void setFilePath(String name){
	    	filePath=name;
	    }
	    
	    public String getFilePath(){
	    	return filePath;
	    }
	    
	    public void setFileDic(String name){
	    	fileDic=name;
	    }
	    
	    public String getFileDic(){
	    	return fileDic;
	    }
	    
	    public void setIndex(int i){
	    	index=i;
	    }
	    
	    public int getIndex(){
	    	return index;
	    }
	    
	    public void clear(){
	    	setFileLength(-1);
	    	setFileDic(null);
	    	setFileName(null);
	    	setFilePath(null);
	    	setIndex(-1);
	    	//setProgress(0);
	    	
	    }

}
