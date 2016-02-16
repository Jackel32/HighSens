import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Dimension;

public class AboutScreen extends JFrame{

	private JFrame frmAbout;
	private JTextField textField;

	/**
	 * Create the application.
	 */
	public AboutScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAbout = new JFrame();
		frmAbout.setMinimumSize(new Dimension(500, 375));
		frmAbout.setTitle("About");
		frmAbout.setBounds(100, 100, 500, 373);
		frmAbout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAbout.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 472, 317);
		frmAbout.getContentPane().add(tabbedPane);
		
		JScrollPane GameRules = new JScrollPane();
		GameRules.setBorder(null);
		tabbedPane.addTab("Game rules", null, GameRules, null);
		
		textField = new JTextField();
		textField.setText("");
		GameRules.setColumnHeaderView(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Intents and Purposes", null, scrollPane, null);
		
		JTextPane aboutText = new JTextPane();
		aboutText.setText("This program was authored by Bo Jiun Chen, Peize Chen, Matthew Kencheloe, Jeffrey Kim, Travis Meyers, Kenneth Peters, and Qing Zhao for the Spring 2016 Software Design and Development course at the University of Central Oklahoma. All rights reserved.\r\n");
		scrollPane.setColumnHeaderView(aboutText);
	}
}
