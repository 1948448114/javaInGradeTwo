package Frame;

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



import File.FileList;
import Main.Main;
import Message.ConnectMessage;

import com.sun.org.apache.bcel.internal.generic.NEW;

import clientThread.UDPclient;

public class FileFrame extends JFrame{
	private JPanel contentPane;
	private JTextField IPtext;
	private JTextField portText;
	public DefaultListModel listModel;
	public JButton connectButton;
	public JButton listButton;
	public JButton DownloadButton;
	public JList list;
	public JPopupMenu listMenu;
	
	private boolean is_refesh=false;
	
	
	String fileString="File";
	String noFileString="No File";
	String FileList="List";
	
	UDPclient client=null;
	public int threadNumber;
	
	
	public static ConnectMessage message=new ConnectMessage();
	public static FileList fileList;
	public FileFrame(){
		threadNumber=Main.ThreadNumber;
		fileList=new FileList(threadNumber);
		init();
	}
	
	
	void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 373, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//菜单
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
		
		
		listButton= new JButton("List");
		//设置可见性
		//listButton.setEnabled(false);
		listButton.setBounds(30, 49, 65, 23);
		contentPane.add(listButton);
		listButton.addActionListener(new ListListener());
		
		DownloadButton= new JButton("下载");
		//DownloadButton.setEnabled(false);
		DownloadButton.setBounds(131, 49, 65, 23);
		contentPane.add(DownloadButton);
		DownloadButton.addActionListener(new DnowloadListener());
		setListModel(new DefaultListModel());
		list=new JList(getListModel());
		//设置选择事件
		list.addListSelectionListener(new ListSelectionListener() {
			int i=0;
			@Override
			public void valueChanged(ListSelectionEvent e) {
				//这样保证只会选一次，否则可能会出现点击两次
				message.setFileName(list.getSelectedValue().toString());
				message.setIndex(list.getSelectedIndex());
				System.out.println("File Index:"+list.getSelectedIndex());
				System.out.println("File Name:"+message.getFileName());
				//System.out.println("File length:"+fileList.getFileInfo(message.getFileName()).getFileLength());
			}
		});
		list.setFixedCellHeight(20);
		list.setPreferredSize(new Dimension(305,296));
		
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		JScrollPane panel=new JScrollPane(list);
		//为list添加右击弹出菜单事件
		
		listMenu=new JPopupMenu();
		JMenuItem stopItem=new JMenuItem("暂停");
		stopItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				client.stopThread();
			}
		});
		JMenuItem continueItem=new JMenuItem("继续");
		continueItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sendDownloadRequest();
			}
		});
		stopItem.addActionListener(new stopAction());
		continueItem.addActionListener(new continueAction());
		listMenu.add(stopItem);
		listMenu.add(continueItem);
		list.add(listMenu);
		
		//添加单击事件
		list.addMouseListener(new listMenuListener());
	    //panel.setAutoscrolls(true);
		panel.setBounds(10, 104, 315,300);
		contentPane.add(panel);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(10, 10, 54, 15);
		contentPane.add(lblIp);
		
		IPtext = new JTextField();
		IPtext.setBounds(30, 7, 66, 21);
		contentPane.add(IPtext);
		IPtext.setColumns(10);
		
		JLabel lblPort = new JLabel("port");
		lblPort.setBounds(118, 10, 54, 15);
		contentPane.add(lblPort);
		
		portText = new JTextField();
		portText.setBounds(158, 7, 66, 21);
		contentPane.add(portText);
		portText.setColumns(10);
		
		connectButton = new JButton("连接");
		connectButton.setBounds(254, 6, 65, 23);
		contentPane.add(connectButton);
		connectButton.addActionListener(new connectListener(this));
		
		IPtext.setText("127.0.0.1");
		portText.setText("8888");
		setVisible(true);
		
	}
	public DefaultListModel getListModel() {
		return listModel;
	}

	public void setListModel(DefaultListModel listModel) {
		this.listModel = listModel;
	}

	//刷新list
	public void refreshList(String list2[]){
		    
		final String list1[] = list2;
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				
				// 第二次刷新
				if (is_refesh == true) {
					int k = 0;
					//先清空文件列表
					//fileList.removeAll();
					
					list.setVisibleRowCount((list1.length - 1)/2);
					for (int i = 1; i < list1.length;i=i+2) {
						System.out.println(list1[i]);
						
						listModel.setElementAt(list1[i], k);
						/*
						 * 将可下载的文件放到文件map里去储存起来
						 */
						fileList.addFile(list1[i]);
						//设置文件长度
						long length=Long.parseLong(list1[i+1]);
						fileList.getFileInfo(list1[i]).setFileLength(length);
						k++;
						
						listModel.setElementAt("", k);
						k++;
					}
					
					fileList.print();
				}
				// 第一次刷新
				if (is_refesh == false) {
					int k = 0;
					// TODO Auto-generated method stub
					list.setVisibleRowCount((list1.length - 1)/2);
					System.out.println("list length:"+list1.length);
					for (int i = 1; i < list1.length; i=i+2) {
						listModel.insertElementAt(list1[i],k);
						//listModel.add(k, list1[i]);
						/*
						 * 将可下载的文件放到文件map里去储存起来
						 */
						fileList.addFile(list1[i]);
						long length=Long.parseLong(list1[i+1]);
						fileList.getFileInfo(list1[i]).setFileLength(length);
						k++;
						listModel.insertElementAt("",k);
						//listModel.add(k, "");
						k++;
					}
					fileList.print();
					is_refesh = true;
				}
			}
		}).start();
}
	//连接监听事件
	class connectListener implements ActionListener{
		FileFrame fileFrame;
		public connectListener(FileFrame fileFrame){
			this.fileFrame=fileFrame;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			String ip=IPtext.getText();
			String port=portText.getText();
			client=new UDPclient(fileFrame, ip, Integer.parseInt(port));
		}
		
	}
	//获得可下载列表监听事件
	class ListListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			client.send(FileList);
		}
	}
	
	//下载监听事件
	class DnowloadListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			FileDialog fileDialog = new FileDialog(FileFrame.this, "保存",FileDialog.SAVE);
			fileDialog.setVisible(true);
			fileDialog.setFile(message.getFileName());
			if(fileDialog.getFile()!=null){
				message.setFileDic(fileDialog.getDirectory());
				message.setFilePath(fileDialog.getFile());
		}
			//message.setRequest(fileString);
			//发送消息
			System.out.println("File Name:"+message.getFileName());
			System.out.println("File length:"+fileList.getFileInfo(message.getFileName()).getFileLength());
			String requeString=fileString+"|"+message.getFileName()+"|"+String.valueOf(threadNumber);
			sendDownloadRequest();
//			long startposition[]=fileList.getFileInfo(message.getFileName()).getStartPosition();
//			long progress[]=fileList.getFileInfo(message.getFileName()).getDownloadLegth();
//			for(int i=0;i<threadNumber;i++){
//				requeString=requeString+"|"+String.valueOf(startposition[i]+progress[i]);
//			}
//			client.send(requeString);
		}
	}
	
	
	class listMenuListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			if(e.getButton()==3)
			{
				System.out.println("get");
				listMenu.show(list,e.getX(),e.getY());
			}
		}
	}
	
	class stopAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("stop");
		}
		
	}
	
	
	class continueAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("continue");
		}
		
	}
	
	
	public void sendDownloadRequest(){
		String requeString=fileString+"|"+message.getFileName()+"|"+String.valueOf(threadNumber);
		long startposition[]=fileList.getFileInfo(message.getFileName()).getStartPosition();
		long progress[]=fileList.getFileInfo(message.getFileName()).getDownloadLegth();
		for(int i=0;i<threadNumber;i++){
			requeString=requeString+"|"+String.valueOf(startposition[i]+progress[i]);
		}
		client.send(requeString);
	}
	
}

