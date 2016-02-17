import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StoreScreen extends JFrame {
	public StoreScreen() {
		setMinimumSize(new Dimension(650, 455));
		
		String imagePath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
		setResizable(false);
		setPreferredSize(new Dimension(650, 455));
		getContentPane().setBackground(new Color(0, 128, 128));
		setTitle("Store Screen");
		getContentPane().setLayout(null);
		getContentPane().setSize(600, 400);
		this.setLocationRelativeTo(null);
		
	    JLabel background=new JLabel(new ImageIcon(imagePath + separator + "images" + separator
                + "store.png"));
	    background.setLocation(10, 11);
	    background.setSize(607, 396);
	    getContentPane().add(background);
	    
	    JButton button = new JButton("<<<<<  Back");
	    button.setMaximumSize(new Dimension(650, 500));
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		final MainScreen mainScreen = new MainScreen();
	    		mainScreen.setVisible(true);
	    		setVisible(false);
	    	}
	    });
	    button.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
	    button.setBounds(182, 401, 272, 23);
	    getContentPane().add(button);
	}
}
