package Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import Main.Main;

public class searchFrame extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private userFrame PreviousFrame;
	
	public searchFrame(userFrame frame)
	{
	PreviousFrame=frame;
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 443, 195);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	textField = new JTextField();
	textField.setBounds(95, 21, 310, 39);
	contentPane.add(textField);
	textField.setColumns(10);
	
	JLabel lblNewLabel = new JLabel("好友ID");
	lblNewLabel.setBounds(10, 21, 75, 39);
	contentPane.add(lblNewLabel);
	
	JButton btnNewButton = new JButton("添加");
	btnNewButton.setBounds(289, 112, 116, 39);
	contentPane.add(btnNewButton);
	
	btnNewButton.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			String searchString="IFRI|"+PreviousFrame.getID()+"|"+textField.getText();
			String searchResultString=Main.getResultFromServer(searchString);
			if(searchResultString.equals("OK"))
			{
				new Dialog("添加成功!");
			DefaultMutableTreeNode friend = new DefaultMutableTreeNode(textField.getText());
		       PreviousFrame.getRoot().add(friend);
			}
			else if(searchResultString.equals("No User")){
				new Dialog("该用户不存在!");
				
			}
			
		}
	});
	}

}
