package Frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import java.awt.Color;

import javax.swing.JMenu;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class test extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	JTextArea allMessageText;
	JTextArea inputText;
	private JButton SendButton;
	private JButton connectButton;
	private JTextField textField;
	private JTextField textField_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 463);
		
		JMenuBar menuBar = new JMenuBar();
		//menuBar.setLayout(new FlowLayout());
		setJMenuBar(menuBar);
		
		JMenu optionMenu=new JMenu("选项");
		JMenu fileMenu=new JMenu("文件");
		JMenu aboutMenu=new JMenu("关于");
		
		JMenuItem exitItem=new JMenuItem("退出");
		JMenuItem aboutItem=new JMenuItem("关于我们");
		JMenuItem softItem=new JMenuItem("关于软件");
		
		
		optionMenu.add(exitItem);
		aboutMenu.add(aboutItem);
		aboutMenu.add(softItem);
		
		
		menuBar.add(fileMenu);
		menuBar.add(optionMenu);
		menuBar.add(aboutMenu);
		
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IP");
		lblNewLabel.setBounds(10, 10, 24, 15);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(36, 7, 66, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("port");
		lblNewLabel_1.setBounds(126, 10, 31, 15);
		contentPane.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(167, 7, 66, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		
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
		SendButton.setBounds(294, 378, 66, 23);
		contentPane.add(SendButton);
		
		connectButton = new JButton("连接");
		//btnNewButton_1.setAlignmentX();
		connectButton.setBounds(248, 6, 66, 23);
		contentPane.add(connectButton);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setBounds(10, 35, 54, 28);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(35, 38, 86, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u66F4\u6539");
		btnNewButton.setBounds(248, 38, 67, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(231, 73, 129, 286);
		contentPane.add(scrollPane_2);
		
		
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("在线好友");
		DefaultMutableTreeNode node1=new DefaultMutableTreeNode("127.0.0.1");
		
		root.add(node1);
		JTree tree = new JTree(root);
		tree.setRootVisible(true);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		 ((DefaultTreeModel)tree.getModel()).setRoot(root);
		scrollPane_2.setColumnHeaderView(tree);
		
		textField_3 = new JTextField();
		textField_3.setBounds(136, 38, 77, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		

		
	}
}
