import java.awt.*;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel{
	private static final long serialVersionUID = 1L;


	Image img;
    
    public BackgroundPanel()
    {
        img = Toolkit.getDefaultToolkit().createImage("Pictures\\back.jpg");
    }

    public void paint(Graphics g)
    {
        g.drawImage(img, 0, 0, null);// Draws the img to the BackgroundPanel.
    }
}