import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Mylogo extends JPanel {
	private static final long serialVersionUID = 1L;
	
	
	private BufferedImage image;
	private JLabel LB;
	int x=0;
	
	public Mylogo(int width) {
		
		x=width;
	}

	
	@Override
	public void paintComponent(Graphics g) {
		
		int y=x-330; // TO PLACE THE LOGO IN THE RIGHT PLACE WHATEVER THE SIZE OF THE FRAME
		super.paintComponent(g);
		 
	       Graphics2D g2 = (Graphics2D)g;
	       
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	       
	        Font font = new Font("Serif", Font.BOLD, 36);
	        g2.setFont(font);

	        Color myNewGray = new Color (116, 116, 116);
	        g2.setColor(myNewGray);
	        
	        g2.drawString("Insurance",150+y, 80); 
	        
	      
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	       
	        font = new Font("Serif", (Font.ITALIC), 30);
	        g2.setFont(font);
	        
	        /*### Create Ower Own Color ###*/
	        Color myNewBlue = new Color (5, 180, 206);
	        g2.setColor(myNewBlue);
	        
	        g2.drawString("Made Easy", 158+y, 105); 
	        
	        LB = new JLabel();
	        
	        try {
				image = ImageIO.read(new File("Pictures\\logo.png"));
				}
	        catch (IOException e) {e.printStackTrace();}
			
	        ImageIcon clienticon = new ImageIcon(image);
	        LB.setIcon(clienticon); 
	        
	        g.drawImage(image,y, 2, null);      
	    
	      }


}
