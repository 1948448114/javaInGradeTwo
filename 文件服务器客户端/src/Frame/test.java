package Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.GridLayout;

public class test extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

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
		setBounds(100, 100, 373, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("List");
		btnNewButton.setBounds(30, 49, 65, 23);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("œ¬‘ÿ");
		button.setBounds(131, 49, 65, 23);
		contentPane.add(button);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(10, 10, 54, 15);
		contentPane.add(lblIp);
		
		textField = new JTextField();
		textField.setBounds(30, 7, 66, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPort = new JLabel("port");
		lblPort.setBounds(118, 10, 54, 15);
		contentPane.add(lblPort);
		
		textField_1 = new JTextField();
		textField_1.setBounds(158, 7, 66, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button_1 = new JButton("¡¨Ω”");
		button_1.setBounds(254, 6, 65, 23);
		contentPane.add(button_1);
		
		JList list_1 = new JList();
		list_1.setSelectedIndices(new int[] {2});
		list_1.setSelectedIndex(2);
		list_1.setBounds(284, 163, 0, -46);
		contentPane.add(list_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 215, 25);
		contentPane.add(lblNewLabel);
		
		

		 DefaultListModel listModel = new DefaultListModel();

		    listModel.addElement("Debbie Scott");

		    listModel.addElement("Scott Hommel");

		    listModel.addElement("Sharon Zakhour");
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 104, 317, 271);
		contentPane.add(scrollPane);
	}
}
