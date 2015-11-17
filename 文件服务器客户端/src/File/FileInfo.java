package File;

import javax.swing.text.Position;

public class FileInfo {
	long FileLength=0;
	long downloadLegth[];
	long startPosition[];
	long endPosition[];

	int threadNumber;
	public FileInfo(int number){
		threadNumber=number;
		downloadLegth=new long[threadNumber];
		startPosition=new long[threadNumber];
		endPosition=new long[threadNumber];
		for(int i=0;i<threadNumber;i++){
			downloadLegth[i]=0;
			startPosition[i]=0;
			endPosition[i]=0;
		}
	}
	
	void setPostion(){
		long number=FileLength/threadNumber;
		for(int i=1;i<threadNumber;i++){
			startPosition[i]=startPosition[i-1]+number;
			endPosition[i-1]=startPosition[i];
		}
		endPosition[threadNumber-1]=FileLength;
	}
	
	
	public long[] getStartPosition(){
		return startPosition;
	}
	
	public long[] getEndPosition(){
		return endPosition;
	}
	public int getThreadNumber() {
		return threadNumber;
	}
	
//	public void setThreadNumber(int threadNumber) {
//		this.threadNumber = threadNumber;
//		
//	}
	public long getFileLength() {
		return FileLength;
	}
	public void setFileLength(long fileLength) {
		FileLength = fileLength;
		setPostion();
	}
	public long[] getDownloadLegth() {
		return downloadLegth;
	}
	
	public long getDownloadLegth(int i){
		return downloadLegth[i];
	}
	public void setDownloadLegth(int i,long downloadLegth) {
		this.downloadLegth[i] = downloadLegth;
	}
	

}
