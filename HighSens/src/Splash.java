import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Splash extends JFrame {

	private JProgressBar progressBar;
	private JPanel contentPane;

private int duration = 2000;
	
	public Splash()
	{
		getContentPane().setBackground(new Color(100, 149, 237));
		setBackground(new Color(65, 105, 225));
		getContentPane().setMinimumSize(new Dimension(500, 375));
		setUndecorated(true);
		setPreferredSize(new Dimension(500, 400));
		setMinimumSize(new Dimension(500, 400));
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Sha\\Desktop\\UCO\\Software Design and Development\\HighSens\\HighSens\\images\\Team Logo.png"));
		lblNewLabel.setBounds(0, 0, 500, 375);
		getContentPane().add(lblNewLabel);
		
		JProgressBar progressBar = new JProgressBar(0, duration);
		progressBar.setForeground(new Color(255, 255, 0));
		progressBar.setBounds(177, 377, 146, 14);
		progressBar.setStringPainted(true);
		getContentPane().add(progressBar);
		this.setLocationRelativeTo(null);
	}
	
	public void showSplash()
	{	
		setVisible(true);
		try
		{
			
			Thread.sleep(duration);
			
		}
		
		catch (Exception e){}
		setVisible(false);
		
	}
}
