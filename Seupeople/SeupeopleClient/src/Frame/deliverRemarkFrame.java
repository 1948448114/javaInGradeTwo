package Frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Main.Main;

public class deliverRemarkFrame extends JFrame {
	private JPanel contentPane;
	private JTextArea textArea;
	private String conID;
	private String uID;
	public deliverRemarkFrame(String contentID,String userID)
	{
	
		conID=contentID;
		uID=userID;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("发表评论");
		panel.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				String timeNowString=df.format(new Date());// new Date()为获取当前系统时间
				String deliverRemarksString="IREM|"+textArea.getText()+"|"+timeNowString+"|"+conID+"|"+uID;
				String result=Main.getResultFromServer(deliverRemarksString);
				if(result.equals("OK"))
				{
					new Dialog("评论成功");
		
				}
				else {
					new Dialog("失败");
				}
				
			}
		});
		
	    textArea= new JTextArea();
		contentPane.add(textArea, BorderLayout.CENTER);
		}
		

}
