package Frame;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

public class viewRemarksFrame extends JFrame {

	String[] remarkContentStrings;
	String[] remarkerStrings;
	String[] remarkTimeStrings;
	public viewRemarksFrame(String []c,String []t,String [] er)
	{
		int length=c.length;
		remarkContentStrings=c;
		remarkerStrings=er;
		remarkTimeStrings=t;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 300, 450, 450);
		
		JPanel tempPane = new JPanel(new GridLayout(length, 1, 10, 50));
		tempPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel[] pictureLabel = new JLabel[length];
		JLabel[] remarkLabel = new JLabel[length];
		JLabel[] timeLabel=new JLabel[length];
		
		JSplitPane[] splitPanels = new JSplitPane[length];
		JSplitPane[] wholePanels = new JSplitPane[length];
		
		int i = 0;
		while (i < length) {

			pictureLabel[i] = new JLabel(remarkerStrings[i], JLabel.CENTER);
			remarkLabel[i] = new JLabel(remarkContentStrings[i], JLabel.CENTER);
			timeLabel[i]=new JLabel(remarkTimeStrings[i],JLabel.RIGHT);
			
			wholePanels[i] = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true,
					remarkLabel[i], timeLabel[i]);
			wholePanels[i].setDividerLocation(120);
			// 设置JSplitPane是否可以展开或收起(如同文件总管一般),设为true表示打开此功能.
			wholePanels[i].setDividerSize(5);
			wholePanels[i].setEnabled(false);
			
			splitPanels[i] = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true,
					pictureLabel[i], wholePanels[i]);
			splitPanels[i].setDividerLocation(90);
			splitPanels[i].setDividerSize(5);
			splitPanels[i].setEnabled(false);
			tempPane.add(splitPanels[i]);
			i++;

		}
			
		JScrollPane contentPane = new JScrollPane(tempPane);
		setContentPane(contentPane);
			
		
	}
}
