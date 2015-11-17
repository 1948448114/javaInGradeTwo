package clientThread;

import Frame.Dialog;

import Frame.FileFrame;

public class refreshProgressThread extends Thread{
	boolean flag=true;
	FileFrame fileFrame;
	long FileLength=-1;
	int index=-1;
	long progress;
	public refreshProgressThread(FileFrame fileFrame){
		this.fileFrame=fileFrame;
		FileLength=FileFrame.fileList.getFileInfo(fileFrame.message.getFileName()).getFileLength();
		index=FileFrame.message.getIndex();
		start();
	}
	
	
	public void setFlag(){
		flag=false;
	}
	public void run(){
		
		while(flag){
			
			progress=FileFrame.message.getProgress();
			System.out.println("Progress:"+progress+"/"+FileLength);
			long k=progress*100/FileLength;
			
			if(k==100)
				{
				flag=false;
				new Dialog("œ¬‘ÿÕÍ±œ");
				}
			fileFrame.listModel.setElementAt(k+"%", index+1);
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		System.out.println("refresh thread over");
		long dow[]=fileFrame.fileList.getFileInfo(fileFrame.message.getFileName()).getDownloadLegth();
		for(int i=0;i<fileFrame.threadNumber;i++){
			System.out.println(i+" "+dow[i]);
		}
	}

}
