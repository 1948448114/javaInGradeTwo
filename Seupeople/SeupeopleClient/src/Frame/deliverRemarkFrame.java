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
		
		JButton btnNewButton = new JButton("��������");
		panel.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
				String timeNowString=df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
				String deliverRemarksString="IREM|"+textArea.getText()+"|"+timeNowString+"|"+conID+"|"+uID;
				String result=Main.getResultFromServer(deliverRemarksString);
				if(result.equals("OK"))
				{
					new Dialog("���۳ɹ�");
		
				}
				else {
					new Dialog("ʧ��");
				}
				
			}
		});
		
	    textArea= new JTextArea();
		contentPane.add(textArea, BorderLayout.CENTER);
		}
		

}
