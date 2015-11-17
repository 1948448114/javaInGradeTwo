package Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.sun.org.apache.bcel.internal.generic.DADD;

import Message.ConnectMessage;
import Message.TcpMessage;
import Message.UserMessage;
import clientThread.Client;

public class MainFrame extends JFrame{
	
	String address="127.0.0.1";
	int port=8888;
	
	
	private JPanel contentPane;
	private JTextField IPtext;
	private JTextField portText;
	public JTextField NameText;
	public JTextArea allMessageText;
	JTextArea inputText;
	private JButton SendButton;
	public JButton connectButton;
	public JTextField textField_3;
	DefaultMutableTreeNode root;
	JTree tree;
	
	public static ConnectMessage message=new ConnectMessage();
	public static UserMessage userMessage=new UserMessage();
	
	public MainFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 463);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel IPlabel = new JLabel("IP");
		IPlabel.setBounds(10, 10, 24, 15);
		contentPane.add(IPlabel);
		
		IPtext = new JTextField(address);
		IPtext.setBounds(36, 7, 66, 21);
		contentPane.add(IPtext);
		IPtext.setColumns(10);
		
		JLabel portLabel = new JLabel("port");
		portLabel.setBounds(126, 10, 31, 15);
		contentPane.add(portLabel);
		
		portText = new JTextField(String.valueOf(port));
		portText.setBounds(167, 7, 66, 21);
		contentPane.add(portText);
		portText.setColumns(10);
		
		
		allMessageText=new JTextArea(15, 20);
		JScrollPane scrollPane = new JScrollPane(allMessageText);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 73, 203, 286);
		contentPane.add(scrollPane);
		
		inputText=new JTextArea(1,20);
		JScrollPane scrollPane_1 = new JScrollPane(inputText);
		scrollPane_1.setBounds(10, 369, 278, 32);
		contentPane.add(scrollPane_1);
		
		SendButton = new JButton("发送");
		SendButton.addActionListener(new sendListener());
		SendButton.setBounds(294, 378, 66, 23);
		contentPane.add(SendButton);
		//SendButton.setEnabled(false);
		
		connectButton = new JButton("连接");
		connectButton.addActionListener(new connectListener(this));
		//btnNewButton_1.setAlignmentX();
		connectButton.setBounds(248, 6, 66, 23);
		contentPane.add(connectButton);
		
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setBounds(10, 35, 54, 28);
		contentPane.add(lblNewLabel_2);
		
		NameText = new JTextField();
		NameText.setBounds(35, 38, 86, 21);
		contentPane.add(NameText);
		NameText.setColumns(15);
		
		textField_3 = new JTextField();
		textField_3.setBounds(136, 38, 77, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(15);
		
		JButton btnNewButton = new JButton("聊天");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-genera
				userMessage.setToUserName(textField_3.getText());
				//deleteList();
			}
		});
		btnNewButton.setBounds(248, 38, 67, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(231, 73, 129, 286);
		contentPane.add(scrollPane_2);
		
		
		root=new DefaultMutableTreeNode("在线好友");
		DefaultMutableTreeNode node1=new DefaultMutableTreeNode("127.0.0.1");
		
		
		DefaultMutableTreeNode node2=new DefaultMutableTreeNode("127.0.0.2");
//		root.add(node2);
//		root.add(node1);
		tree = new JTree(root);
		tree.setRootVisible(true);
		//tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		 ((DefaultTreeModel)tree.getModel()).setRoot(root);
		 
//		 
//		 tree.addTreeSelectionListener(new TreeSelectionListener() {
//			
//			@Override
//			public void valueChanged(TreeSelectionEvent e) {
//				// TODO Auto-generated method stub
//				 DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree  
//	                        .getLastSelectedPathComponent(); 
//				  if (node.isLeaf()) { 
//					 userMessage.setToUserName(node.toString());
//				  }
//				
//			}
//		});
		scrollPane_2.setColumnHeaderView(tree);
		
		
		setVisible(true);
		
	}
	
	
	class sendListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String requestString=null;
			requestString=inputText.getText();
			message.setRequest(requestString);
			allMessageText.append(userMessage.getUsername()+"\n");
			allMessageText.append(requestString+"\n");
			//System.out.println(message.getRequest());
			
		}
		
	}
	
	
	class connectListener implements ActionListener{
		MainFrame mainFrame;
		public connectListener(MainFrame mainFrame){
			this.mainFrame=mainFrame;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			String add=null;
			add=IPtext.getText();
			
			if(add.equals(""))
				new Dialog("请输入IP");
			String port=null;
			port=portText.getText();
			if(port.equals(""))
				new Dialog("请输入端口");
			int Port=Integer.parseInt(port);
			
			
			new Client(mainFrame,add, Port);
		}
		
	}
	
	
	public void addList(String string){
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

		DefaultMutableTreeNode node1=new DefaultMutableTreeNode(string);
		int k=root.getChildCount();
			model.insertNodeInto(node1, root, k);
			tree.expandRow(0);
		
		
		//model.insertNodeInto(node1, root,k); 
	}
	
	
	public void deleteList(){
		System.out.println(userMessage.getToUsername());
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		int k=root.getChildCount();
		System.out.println(k);
		//这里需注意，删除的机智是从后开始删的，所以没删一个节点数量减少一个，所以需要i从最大的开始，切记
		//否则就会数组越界，和list的删除类似，需要注意
			for(int i=k-1;i>=0;i--){
		    System.out.println(i);
			DefaultMutableTreeNode node=(DefaultMutableTreeNode)root.getChildAt(i);
			model.removeNodeFromParent(node);
		
		}
	}

}
//class Dialog{
//	public Dialog(String string) {
//		JOptionPane.showMessageDialog(null, string,"聊天室",JOptionPane.INFORMATION_MESSAGE);
//	}
//}
