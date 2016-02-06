import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class StoreScreen extends JFrame {
	public StoreScreen() {
		
		String imagePath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
		
		setMaximumSize(new Dimension(600, 400));
		setMinimumSize(new Dimension(600, 400));
		setResizable(false);
		setPreferredSize(new Dimension(600, 400));
		getContentPane().setBackground(new Color(0, 128, 128));
		setTitle("Store Screen");
		getContentPane().setLayout(null);
		getContentPane().setSize(600, 400);
		
	    JLabel background=new JLabel(new ImageIcon(imagePath + separator + "images" + separator
                + "store.png"));
	    background.setSize(600, 400);
	    add(background);
	}
}
