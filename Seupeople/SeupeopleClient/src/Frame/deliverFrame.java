package Frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.PUBLIC_MEMBER;

import Main.Main;

import java.util.Date;
import java.text.SimpleDateFormat;



public class deliverFrame extends JFrame{
	
	private JPanel contentPane;
	private JTextArea textArea;
	private String UserIDString;
	public deliverFrame(String userid)
	{
		UserIDString=userid;
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(200, 200, 450, 300);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(new BorderLayout(0, 0));
	
	JPanel panel = new JPanel();
	contentPane.add(panel, BorderLayout.SOUTH);
	
	JButton btnNewButton = new JButton("发表");
	panel.add(btnNewButton);
	
	
	
    textArea= new JTextArea();
	contentPane.add(textArea, BorderLayout.CENTER);
	
btnNewButton.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			String contentString=textArea.getText();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String timeNowString=df.format(new Date());// new Date()为获取当前系统时间
			
			String deliverString="ICON|"+contentString+"|"+timeNowString+"|"+UserIDString;
			String temp=Main.getResultFromServer(deliverString);
			if(temp.equals("OK"))
				new Dialog("发表成功");
			else {
				new Dialog("发表失败");
			}
			
			
		}
	});
	}
	
}
