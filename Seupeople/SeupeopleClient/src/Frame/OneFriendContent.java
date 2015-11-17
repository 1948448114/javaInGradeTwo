package Frame;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import com.sun.org.apache.xerces.internal.impl.dv.xs.BooleanDV;

import Main.Main;

public class OneFriendContent extends JFrame{
	private String[] contentStrings;
	private String[] timeStrings ;
	private String[] remarkStrings;
	private String[] remarkerStrings;
	private String[] remarkTimeStrings;
	private String[] contentIDStrings;
  
	private String IDStrings;
	private  String[]contenterStrings;
   
	private String[]wholeRemarkStrings;
	private boolean flag;
	
	public OneFriendContent(String[] cStrings,String ID,boolean f)
	{
		flag=f;
		IDStrings=ID;
		int length=(cStrings.length)/4;
		int j=0;

		contentStrings=new String[length];
		timeStrings=new String[length];
		contentIDStrings=new String[length];
		contenterStrings=new String[length];
	
				while(j<cStrings.length)
		{
			if((j+1)%4==1)
				contentIDStrings[j/4]=cStrings[j];
			else if((j+1)%4==2)
				contentStrings[j/4]=cStrings[j];
			else if((j+1)%4==3)
				timeStrings[j/4]=cStrings[j];
			else
				contenterStrings[j/4]=cStrings[j];
			
			j++;
		}
		

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 200, 450, 450);
		
		JPanel tempPane = new JPanel(new GridLayout(length, 1, 10, 50));
		tempPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel[] pictureLabel = new JLabel[length];
		
		JLabel[] timeLabel=new JLabel[length];
		JLabel[] contentLabel= new JLabel[length];
		JLabel[] friendLabels=new JLabel[length];
		
		JButton []viewRemarkButtons=new JButton[length];
		JButton[] giveRemarkButtons=new JButton[length];
		JButton[] deleteContentButtons=new JButton[length];
		
		JSplitPane[] splitPanels = new JSplitPane[length];
		JSplitPane[] wholePanels = new JSplitPane[length];
		JSplitPane[] conPanes = new JSplitPane[length];
		
		JPanel[] remarkPanes=new JPanel[length];
		
		int i = 0;
		while (i < length) {

			pictureLabel[i] = new JLabel(contenterStrings[i], JLabel.CENTER);
			
			contentLabel[i] = new JLabel(contentStrings[i], JLabel.CENTER);
			contentLabel[i].setFont(new Font("隶书", Font.BOLD, 22));
			timeLabel[i]=new JLabel(timeStrings[i],JLabel.RIGHT);
			
			remarkPanes[i]=new JPanel(new FlowLayout());
			viewRemarkButtons[i]=new JButton("查看评论");
			giveRemarkButtons[i]=new JButton("评论");
			deleteContentButtons[i]=new JButton("删除该动态");
			final int temp=i;
			deleteContentButtons[i].addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					String deleteString="DCON|"+contentIDStrings[temp];
					String deleteResultString=Main.getResultFromServer(deleteString);
					if(deleteResultString.equals("OK"))
					{
						new Dialog("删除成功");
						
					}
					else {
						new Dialog("删除失败");
						
					}
					
					
				}
			});
			viewRemarkButtons[i].addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					String remarkString="QREM|"+contentIDStrings[temp];
					String remarkContentsString=Main.getResultFromServer(remarkString);
					if(remarkContentsString.equals("No Remark"))
						new Dialog("该动态没有评论!");
					else {
					
					
					wholeRemarkStrings=remarkContentsString.split("\\|");
					int Rlength=wholeRemarkStrings.length/3;
					remarkStrings=new String[Rlength];
					remarkTimeStrings=new String [Rlength];
					remarkerStrings=new String[Rlength];
					int k=0;
					while(k<wholeRemarkStrings.length)
					{
						if((k+1)%3==1)
							remarkStrings[k/3]=wholeRemarkStrings[k];
						else if((k+1)%3==2)
							remarkTimeStrings[k/3]=wholeRemarkStrings[k];
						else if((k+1)%3==0){
							remarkerStrings[k/3]=wholeRemarkStrings[k];
						}
						k++;
					}
					viewRemarksFrame frame1=new viewRemarksFrame(remarkStrings, remarkTimeStrings, remarkerStrings);
					frame1.setVisible(true);
					frame1.setTitle("评论");
					}
					
					
					
					
					
				}
			});
			
			giveRemarkButtons[i].addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					deliverRemarkFrame giveFrame=new deliverRemarkFrame(contentIDStrings[temp],IDStrings);
					giveFrame.setTitle("发表评论");
					giveFrame.setVisible(true);
					
				}
			});
				
		
			remarkPanes[i].add(viewRemarkButtons[i]);
			remarkPanes[i].add(giveRemarkButtons[i]);
			if(flag)
			{
			remarkPanes[i].add(deleteContentButtons[i]);
			}
			
			conPanes[i]=new JSplitPane(JSplitPane.VERTICAL_SPLIT,false,contentLabel[i],timeLabel[i]);
			conPanes[i].setDividerLocation(40);
			conPanes[i].setDividerSize(1);
			splitPanels[i] = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false,
					pictureLabel[i], conPanes[i]);
			splitPanels[i].setEnabled(false);
			splitPanels[i].setDividerLocation(100);
			// 设置JSplitPane是否可以展开或收起(如同文件总管一般),设为true表示打开此功能.
			splitPanels[i].setDividerSize(5);
			wholePanels[i] = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true,
					splitPanels[i], remarkPanes[i]);
			wholePanels[i].setDividerLocation(90);
			// 设置JSplitPane是否可以展开或收起(如同文件总管一般),设为true表示打开此功能.
			wholePanels[i].setOneTouchExpandable(true);
			wholePanels[i].setDividerSize(5);
			// cPanels[i]=new JPanel();
			// cPanels[i].add(label[i]);

			tempPane.add(wholePanels[i]);
			i++;

		}

		JScrollPane contentPane = new JScrollPane(tempPane);
		setContentPane(contentPane);
		

		
	}


}
