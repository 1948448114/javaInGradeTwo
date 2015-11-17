package Frame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;





public class test1 extends JFrame{
	
	private JPanel contentPane;
	public static void main(String[] args) throws IOException {
		
		String FilePath="D:\\serverFile\\test1.txt";
//	   
//	  System.out.println(FilePath);
//	        File file=new File(FilePath);
//	        String result=null;
//	        int flag=0;
//	        if(file.isDirectory()){
//	            for(String path:file.list()){
//	                if(flag==0)
//	                {
//	                    result=FilePath+path;
//	                    flag=1;
//	                }
//	                if(flag==1){
//	                    result=result+"|"+FilePath+path;
//	                }
//	            }
//	        System.out.println(result);
//	       
//	    }
		
		
		//File file=new File("D:\\Modem_I9300DDLE8_CWM.zip");
		File file=new File(FilePath);
		File file2=new File("D:\\test1.txt");
		try {
			FileInputStream input=new FileInputStream(file);
			FileOutputStream outputStream=new FileOutputStream("D:\\test1.txt",true);
			System.out.println(input.available());
			BufferedInputStream in=new BufferedInputStream(input);
			BufferedOutputStream outputStream2=new BufferedOutputStream(outputStream);
			byte temp[]=new byte[1024];
			int i=0;
			while((i=in.read(temp))!=-1){
				System.out.println(i);
				outputStream2.write(temp);
			}
			outputStream2.flush();
			System.out.println("ok");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public test1(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 373, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		setContentPane(contentPane);
		
		 DefaultListModel listModel = new DefaultListModel();

		    listModel.addElement("Debbie Scott");

		    listModel.addElement("Scott Hommel");

		    listModel.addElement("Sharon Zakhour");
		    
		JList list=new JList(listModel);
		list.setVisibleRowCount(2);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setAlignmentX(LEFT_ALIGNMENT);
		list.setPreferredSize(new Dimension(200,200));
		contentPane.add(list);
		JLabel oneJLabel=new JLabel("ÄãºÃ");
		JLabel twoJLabel=new JLabel("ºÇºÇ");
		JLabel jLabel=new JLabel("hah");
//		contentPane.add(oneJLabel);
//		contentPane.add(jLabel);
//		contentPane.add(twoJLabel);
		//contentPane.setLayout(new GridLayout(4, 2));
		//list.add(oneJLabel);
		
	}
   
}
