import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class about extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public about() {
		setMinimumSize(new Dimension(531, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 490, 308);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("Intents and Purposes", null, scrollPane_1, null);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("This program was authored by Bo Jiun Chen, Peize Chen, Matthew Kencheloe, Jeffrey Kim, Travis Meyers, Kenneth Peters, and Qing Zhao for the Spring 2016 Software Design and Development course at the University of Central Oklahoma. All rights reserved.\r\n");
		scrollPane_1.setColumnHeaderView(textPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		tabbedPane.addTab("Game rules", null, scrollPane, null);
		
		JButton button = new JButton("<<<<<< Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final MainScreen mainScreen = new MainScreen();
				mainScreen.setVisible(true);
				setVisible(false);
			}
		});
		button.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		button.setBounds(125, 329, 272, 23);
		contentPane.add(button);
	}

}
