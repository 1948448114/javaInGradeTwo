package Frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.MenuItem;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import Main.Main;


public class userFrame extends JFrame {
	private Icon userImg;

	private String userID;
	private JTree tree;
	private JPopupMenu popupMenu = new JPopupMenu();
	private JMenuItem menuItem1,menuItem2,menuItem3,menuItem4,menuItem5,menuItem6,menuItem7;
	private JMenu menu1,menu2;
	private JMenu friendMenu;
	JScrollPane scrollPane;
	JPanel panel_1;
	
	private DefaultMutableTreeNode selectedNode;
	private DefaultMutableTreeNode rootNode;
	private String[]friendLists;
	private userFrame thisFrame;


public DefaultMutableTreeNode getRoot()
{
return rootNode;	
}
public String getID()
{
	return userID;
	}
	public userFrame(String id,String[]friends) {

		thisFrame=this;
		userID=id;
		friendLists=friends;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 254, 555);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 246, 64);
		contentPane.add(panel);
		panel.setLayout(null);

	   
		JButton btnNewButton_1 = new JButton(userID);
		btnNewButton_1.setBounds(20, 10, 100, 50);
		panel.add(btnNewButton_1);
		
		JButton searchButton=new JButton("���");
		searchButton.setBounds(150,10,60,50);
		panel.add(searchButton);
		
		JButton refreshButton=new JButton("s");
		refreshButton.setBounds(210,10,20,50);
		panel.add(refreshButton);
		
		refreshButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				String refreshStrings="QFRI|"+userID;
				String refreshResultString=Main.getResultFromServer(refreshStrings);
				friendLists=refreshResultString.split("\\|");
				setFriendTree(false);
				scrollPane.setViewportView(tree);
				panel_1.add(scrollPane);
				
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			
		
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
	            String personalInfo="QPCON|"+userID;
	       
	            String personalIfoString=Main.getResultFromServer(personalInfo);
	         
	            if(personalIfoString.equals("No Content"))
	            	new Dialog("�ú��ѻ�û�з������̬!");
	            else
	            {
	            
	            String[]contentStrings=personalIfoString.split("\\|");
	         
	            
				JFrame personalFrame=new OneFriendContent(contentStrings,userID,true);
				personalFrame.setVisible(true);
				personalFrame.setTitle("������ҳ");
	            }
			}
		});
		
		searchButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				searchFrame sFrame=new searchFrame(thisFrame);
				sFrame.setVisible(true);
				sFrame.setTitle("��Ӻ���");
				
			}
		});
		JButton deliverButton=new JButton("��״̬");
		deliverButton.setBounds(130, 460, 100, 45);
		contentPane.add(deliverButton);
		
		JButton friendButton=new JButton("����Ȧ");
		friendButton.setBounds(20, 460, 100, 45);
		contentPane.add(friendButton);
		
		deliverButton.addActionListener(new ActionListener() {
			
		
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				deliverFrame deliverFrame=new deliverFrame(userID);
				deliverFrame.setVisible(true);
				deliverFrame.setTitle("����̬");
			}
		});

friendButton.addActionListener(new ActionListener() {
			
		
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				String friendQueryString="QCON|"+userID;
				String allContents=Main.getResultFromServer(friendQueryString);
				if(allContents.equals("No Content"))
					new Dialog("����Ȧ��ʱû�ж�̬");
				else {
				String [] allContentsStrings=allContents.split("\\|");
				
			 OneFriendContent friendFrame=new OneFriendContent(allContentsStrings,userID,false);
			   friendFrame.setVisible(true);
			   friendFrame.setTitle("����Ȧ");
				}
				
			}
		});
		// Icon ZoneImg=new ImageIcon("C://zone.jpg");
		// JButton btnNewButton = new JButton(ZoneImg);
		// btnNewButton.setBounds(184, 16, 31, 33);
		// panel.add(btnNewButton);

		

		panel_1 = new JPanel();
		panel_1.setBounds(0, 61, 246, 399);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 226, 365);
		panel_1.add(scrollPane);
		setFriendTree(true);
		
		scrollPane.setViewportView(tree);

	
	}


	public void setFriendTree(boolean n)
	{
		if(n)
		{
		menuItem1=new JMenuItem("�鿴��̬");
		menuItem2=new JMenuItem("�鿴��Ϣ");
		menuItem3=new JMenuItem("ɾ������");
		menu1=new JMenu("����Ȩ��");
		menu2=new JMenu("��̬����");
		menuItem4=new JMenuItem("��̬����ɼ�");
		menuItem5=new JMenuItem("��̬���䲻�ɼ�");
		menuItem6=new JMenuItem("�����䶯̬");
		menuItem7 =new JMenuItem("ȡ����������");
		menu1.add(menuItem4);
		menu1.add(menuItem5);
		menu2.add(menuItem6);
		menu2.add(menuItem7);
		
        popupMenu.add(menuItem1);
        popupMenu.add(menuItem2);
        popupMenu.add(menuItem3);
        popupMenu.add(menu1);
        popupMenu.add(menu2);
        
        
     
        
	
		
		DefaultMutableTreeNode root=loadFriends();
		rootNode=root;
		tree=new JTree(root);
		tree.setFont(new Font("����",Font.BOLD,14));
		tree.add(popupMenu);
		tree.addMouseListener(new MouseListener() {

		
	
			public void mouseReleased(MouseEvent e) {
				// TODO �Զ����ɵķ������
				
			}
		
			public void mousePressed(MouseEvent e) {
				// TODO �Զ����ɵķ������
				  if (e.getButton() == e.BUTTON3) { 
					  
					  selectedNode=(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
					  popupMenu.show(tree, e.getX(), e.getY());
				  }
				
				     
			}
			
		
			public void mouseExited(MouseEvent e) {
				// TODO �Զ����ɵķ������
				
			}
			
		
			public void mouseEntered(MouseEvent e) {
				// TODO �Զ����ɵķ������
				
			}
			
	
			public void mouseClicked(MouseEvent e) {
				// TODO �Զ����ɵķ������
				
				
			}
		});
		
		menuItem1.addActionListener(new ActionListener() {
			
		
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				
			String loadOneFriendString="QPCON|"+selectedNode.toString();
			String OneFriContents=Main.getResultFromServer(loadOneFriendString);
	        if(OneFriContents.equals("No Content"));
			String []oneFriend=OneFriContents.split("\\|");
			
				
				OneFriendContent cFrame=new OneFriendContent(oneFriend,userID,false);
				cFrame.setVisible(true);
				cFrame.setTitle("��̬");
			}
		});
		  
		  menuItem2.addActionListener(new ActionListener() {
			
		
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				String queryInfoString="QINF|"+selectedNode.toString();
				String queryResultString=Main.getResultFromServer(queryInfoString);
				JFrame iFrame=new personalInfoFrame(queryResultString, userID);
				iFrame.setVisible(true);
			}
		});
		
		  menuItem3.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
			
			
				DefaultTreeModel model=new DefaultTreeModel(selectedNode.getParent());
//	
				model.removeNodeFromParent(selectedNode);
				String removeFriends="DFRI|"+selectedNode.toString()+"|"+userID;
				String removeResult=Main.getResultFromServer(removeFriends);
				if(removeResult.equals("OK"))
					new Dialog("ɾ���ɹ�");
				else {
					new Dialog("ɾ��ʧ��,���Ժ�����");
				}
//				model.reload(selectedNode.getParent());
//				System.out.println("OK");
//				DefaultMutableTreeNode parent=(DefaultMutableTreeNode)selectedNode.getParent();
//			    selectedNode.removeFromParent();
				
			
			    
			

			}
		});

		  menuItem4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				String closeVisitString="UFRS|"+userID+"|"+selectedNode.toString()+"|"+"Vis";
				String closeResult=Main.getResultFromServer(closeVisitString);
				if(closeResult.equals("OK"))
					new Dialog("������Ϊ�ɼ�");
				else {
					new Dialog("����ʧ��");
				}
				
			}
		});
		  
		  menuItem5.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				String openVisitString="UFRS|"+userID+"|"+selectedNode.toString()+"|"+"UVis";
				String openResult=Main.getResultFromServer(openVisitString);
				if(openResult.equals("OK"))
					new Dialog("������Ϊ���ɼ�");
				else {
					new Dialog("����ʧ��");
				}
				
			}
		});
		  
		  menuItem6.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				String pingbiString="UFRS|"+selectedNode.toString()+"|"+userID+"|"+"UVis";
				String pingbiResult=Main.getResultFromServer(pingbiString);
				if(pingbiResult.equals("OK"))
					new Dialog("���γɹ�");
				else {
					new Dialog("����ʧ��");
				}
				
				
				
			}
		});
		  
		  menuItem7.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				String quxiaoString="UFRS|"+selectedNode.toString()+"|"+userID+"|"+"Vis";
				String quxiaoReString=Main.getResultFromServer(quxiaoString);
				if(quxiaoReString.equals("OK"))
					new Dialog("ȡ�����γɹ�");
				else {
					new Dialog("ȡ������ʧ��");
				}
			}
		});
	

		}
		else {

			DefaultMutableTreeNode root=loadFriends();
			rootNode=root;
			tree=new JTree(root);
			tree.setFont(new Font("����",Font.BOLD,14));
	
	        
	        tree.addMouseListener(new MouseListener() {

	    		
	        	
				public void mouseReleased(MouseEvent e) {
					// TODO �Զ����ɵķ������
					
				}
			
				public void mousePressed(MouseEvent e) {
					// TODO �Զ����ɵķ������
					  if (e.getButton() == e.BUTTON3) { 
						  
						  selectedNode=(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
						  popupMenu.show(tree, e.getX(), e.getY());
					  }
					
					     
				}
				
			
				public void mouseExited(MouseEvent e) {
					// TODO �Զ����ɵķ������
					
				}
				
			
				public void mouseEntered(MouseEvent e) {
					// TODO �Զ����ɵķ������
					
				}
				
		
				public void mouseClicked(MouseEvent e) {
					// TODO �Զ����ɵķ������
					
					
				}
			});
			
	        
//	    	tree.add(popupMenu);
		}

	}

	
	public DefaultMutableTreeNode loadFriends() {
		int num = friendLists.length;
		int i = 0;

		DefaultMutableTreeNode root = new DefaultMutableTreeNode(" MyFriend",
				true);

		while (i < num) {
			DefaultMutableTreeNode friend = new DefaultMutableTreeNode(
					friendLists[i]);

			root.add(friend);
			i++;
		}

		return root;
	}


}

