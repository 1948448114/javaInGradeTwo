package File;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Administrator on 2014/12/24.
 */
public class getFileList {
   public static  String FilePath="D:\\serverFile\\";
    public getFileList(){

    }

    public long getFileLength(String path){
        File file=new File(FilePath+path);
        try {
            FileInputStream inputStream=new FileInputStream(file);
            return  inputStream.available();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return  -1;
        } catch (IOException e) {
            e.printStackTrace();
            return  -1;
        }

    }
    public String getList(){
        File file=new File(FilePath);
        String result="";
        int flag=0;
        if(file.isDirectory()){
            for(String path:file.list()){
              //  if(flag==1){
                long length=getFileLength(path);
                result=result+"|"+path+"|"+String.valueOf(length);
           // }
//                if(flag==0)
//                {
//                    result=path;
//                    flag=1;
//                }
            }
        }
        return result;
    }
}
