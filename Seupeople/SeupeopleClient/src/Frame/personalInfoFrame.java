package Frame;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class personalInfoFrame extends JFrame {
	private String userNameString;
	private String userID;
	private JPanel contentPanel;
	
	public personalInfoFrame(String name,String ID)
	{
		userNameString=name;
		userID=ID;
		contentPanel=new JPanel(new GridLayout(2,1));
		setContentPane(contentPanel);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 200, 200);
		
		JLabel nameJLabel=new JLabel("Name",JLabel.CENTER);
		JLabel IDLabel=new JLabel("ID",JLabel.CENTER);
		JLabel userNameJLabel=new JLabel(userNameString,JLabel.CENTER);
		JLabel userIDJLabel=new JLabel(userID,JLabel.CENTER);
		JSplitPane namePane1=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,nameJLabel,userNameJLabel);
		namePane1.setDividerLocation(50);
		namePane1.setDividerSize(5);
		
		JSplitPane IDPanel=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,IDLabel,userIDJLabel);
		IDPanel.setDividerLocation(50);
		IDPanel.setDividerSize(5);
		
		contentPanel.add(namePane1);
		contentPanel.add(IDPanel);
		
	
	
	}

}
