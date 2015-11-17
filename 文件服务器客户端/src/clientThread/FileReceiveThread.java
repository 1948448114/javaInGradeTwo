package clientThread;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.net.UnknownHostException;

import Frame.Dialog;
import Frame.FileFrame;

public class FileReceiveThread extends Thread{
	Socket fileSocket=null;
	InputStream input;
	FileFrame fileFrame;
	boolean flag=true;
	int length;
	long writePosition;
	int threadNumber;
	long amount=0;
   public FileReceiveThread(FileFrame fileFrame,String address,int port,int i,long position){
	   try {
		this.fileFrame=fileFrame;
		fileSocket=new Socket(address,port);
		input=fileSocket.getInputStream();
			//写入位置
		writePosition=position;
		threadNumber=i;
		amount=fileFrame.fileList.getFileInfo(fileFrame.message.getFileName()).getDownloadLegth(i);
		start();
	} catch (UnknownHostException e) {
		System.out.println("无法连接到文件socket");
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	   
   }
   
   public void stopThread(){
	   flag=false;
   }
    public void run(){
			try {
				byte by[] = new byte[1024 * 10];
				BufferedInputStream in = new BufferedInputStream(input);
				int i = 0;
				System.out.println(threadNumber+" start write file");
				File saveFile = new File(FileFrame.message.getFileDic(),
						FileFrame.message.getFilePath());
				if (!saveFile.exists()) {
					saveFile.createNewFile();
					System.out.println("create successfully");
				}
				
				//随机流写文件
				RandomAccessFile write=new RandomAccessFile(saveFile,"rw");
				write.seek(writePosition);
				int length = 0;
			
				boolean flag1 = true;
				while (flag1&&flag) {
					length = in.read(by);
					write.write(by, 0, length);
					amount+=length;
					//设置下载进度
					FileFrame.message.setProgress(threadNumber,amount);
//					if (length < 1024)
//						flag1 = false;
				}
				FileFrame.message.setProgress(threadNumber,amount);
				System.out.println(threadNumber+"Thread:"+amount);
				System.out.println("Thread:"+threadNumber+"  over");
				
				//out.flush();
				//new Dialog("下载成功");
				
				// 设置类型为空，重新开始
				//FileFrame.message.clear();
				//FileFrame.message.setTypename(null);
				//将已经下载的长度存到文件信息里
				fileFrame.fileList.getFileInfo(fileFrame.message.getFileName()).setDownloadLegth(threadNumber,amount);
				write.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		try {
			input.close();
			fileSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		}

	}
}
