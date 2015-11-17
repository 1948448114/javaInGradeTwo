package Serverthread;

import File.getFileList;
import File.FileInfo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2014/12/26.
 */
public class FileSendThread extends Thread{
    ServerSocket fileSocket;
    Socket sendSocket;
    String filePath= getFileList.FilePath+UDPserver.message.getFileName();
    int port;
    int index;
    FileInfo fileInfo;
    long start;
    long end;
    public FileSendThread(int index,FileInfo fileInfo){
        try {
            this.index=index;
            this.fileInfo=fileInfo;
            start=(fileInfo.getStartPosition())[index];
            end=(fileInfo.getEndPosition())[index];
            fileSocket=new ServerSocket(0);
            port=fileSocket.getLocalPort();
            System.out.println("����˷����ļ��˿ڣ�" + port + "�˿�");
            System.out.println("Start:"+start+"End:"+end);
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPort(){
        return port;
    }
    public void run(){
        try {
            sendSocket=fileSocket.accept();
            System.out.println("-----------------");
            System.out.println("�ͻ��˽����ļ��߳�����");
            String str="�ͻ��˷����ļ��˿�" + sendSocket.getPort()+"\r\n";
            UDPserver.write.writeUTF(str);
            System.out.println("�ͻ��˵�ַ:" + sendSocket.getInetAddress());
            System.out.println("�ͻ��˶˿�" + sendSocket.getPort());
            OutputStream out=sendSocket.getOutputStream();

            File file=new File(filePath);
            RandomAccessFile input=new RandomAccessFile(file,"rw");
            input.seek(start);
            byte by[]=new byte[1024*10];
            long copyLength=start;
            int readLength=1024*10;
            if(copyLength+readLength>end)
                readLength= (int) ((int)end-copyLength);
            int i=0;
            boolean flag=true;
            while((i=input.read(by,0,readLength))>-1&&flag){
                copyLength+=i;
                out.write(by,0,i);
                if(copyLength+readLength>end)
                    readLength= (int) ((int)end-copyLength);
                if(copyLength==end)
                    flag=false;
            }
            System.out.println("over"+":"+copyLength);
            input.close();
        } catch (IOException e) {

            System.out.println(port);
            System.out.println(e);
            e.printStackTrace();
        }
        finally {
            try {

                fileSocket.close();
                sendSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
