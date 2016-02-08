import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LevelSelectScreen extends JFrame {
	public LevelSelectScreen() {
		
		setMaximumSize(new Dimension(600, 400));
		setMinimumSize(new Dimension(600, 400));
		setResizable(false);
		setPreferredSize(new Dimension(600, 400));
		getContentPane().setBackground(new Color(0, 128, 128));
		setTitle("Level Select");
		getContentPane().setLayout(null);
		getContentPane().setSize(600, 400);
		
		JPanel pnl1 = new JPanel();
		pnl1.setBounds(29, 36, 125, 106);
		getContentPane().add(pnl1);
		pnl1.setLayout(null);
		
		JLabel lbl1 = new JLabel("New label");
		lbl1.setIcon(new ImageIcon("image/map.png"));
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setBounds(0, 0, 125, 106);
		pnl1.add(lbl1);
		
		JLabel lblTitle = new JLabel("Select Map");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
		lblTitle.setBounds(0, 0, 546, 50);
		getContentPane().add(lblTitle);
		
		JPanel pnl2 = new JPanel();
		pnl2.setBackground(new Color(0, 128, 128));
		pnl2.setBounds(216, 36, 125, 106);
		getContentPane().add(pnl2);
		pnl2.setLayout(null);
		
		JLabel lbl2 = new JLabel("New label");
		lbl2.setBackground(new Color(0, 139, 139));
		lbl2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2.setBounds(10, 11, 105, 80);
		pnl2.add(lbl2);
		
		JPanel pnl3 = new JPanel();
		pnl3.setBackground(new Color(0, 128, 128));
		pnl3.setBounds(392, 36, 125, 106);
		getContentPane().add(pnl3);
		pnl3.setLayout(null);
		
		JLabel lbl3 = new JLabel("New label");
		lbl3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl3.setBounds(10, 11, 105, 80);
		pnl3.add(lbl3);
		
		JPanel pnl4 = new JPanel();
		pnl4.setBackground(new Color(0, 128, 128));
		pnl4.setBounds(29, 213, 125, 102);
		getContentPane().add(pnl4);
		pnl4.setLayout(null);
		
		JLabel lbl4 = new JLabel("New label");
		lbl4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4.setBounds(10, 11, 105, 80);
		pnl4.add(lbl4);
		
		JPanel pnl5 = new JPanel();
		pnl5.setBackground(new Color(0, 128, 128));
		pnl5.setBounds(216, 213, 125, 102);
		getContentPane().add(pnl5);
		pnl5.setLayout(null);
		
		JLabel lbl5 = new JLabel("New label");
		lbl5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl5.setBounds(10, 11, 105, 80);
		pnl5.add(lbl5);
		
		JPanel pnl6 = new JPanel();
		pnl6.setBackground(new Color(0, 128, 128));
		pnl6.setBounds(392, 213, 125, 102);
		getContentPane().add(pnl6);
		pnl6.setLayout(null);
		
		JLabel lbl6 = new JLabel("New label");
		lbl6.setHorizontalAlignment(SwingConstants.CENTER);
		lbl6.setBounds(10, 11, 105, 80);
		pnl6.add(lbl6);
		
		JButton btnMap = new JButton("Map 1");
		btnMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JFrame game =  new Main();
		        game.setTitle("Tower Defence");
		        game.setResizable(false);
		        game.setLocationRelativeTo(null);
		        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        game.setVisible(true);
		        setVisible(false);
			}
		});
		
		btnMap.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		btnMap.setBounds(29, 152, 125, 23);
		getContentPane().add(btnMap);
		
		JButton btnMap2 = new JButton("Map 2");
		btnMap2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JFrame game =  new Frame();
			}
		});
		
		btnMap2.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		btnMap2.setBounds(216, 153, 125, 23);
		getContentPane().add(btnMap2);
		
		JButton btnMap3 = new JButton("Map 3");
		btnMap3.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		btnMap3.setBounds(392, 152, 125, 23);
		getContentPane().add(btnMap3);
		
		JButton btnMap4 = new JButton("Map 4");
		btnMap4.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		btnMap4.setBounds(29, 323, 125, 23);
		getContentPane().add(btnMap4);
		
		JButton btnMap5 = new JButton("Map 5");
		btnMap5.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		btnMap5.setBounds(216, 323, 125, 23);
		getContentPane().add(btnMap5);
		
		JButton btnMap6 = new JButton("Map 6");
		btnMap6.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		btnMap6.setBounds(392, 323, 125, 23);
		getContentPane().add(btnMap6);
	}
}
