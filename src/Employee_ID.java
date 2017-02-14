import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Employee_ID extends JFrame {
	private static final long serialVersionUID = 1L;

	
	private JTextField idTF ;
	
	private JLabel idLB;
	private JLabel nameLB;
	private JLabel phoneLB;
	
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JLabel searchLB;
	private BufferedImage image;
	
	public Employee_ID(){
		displayGUI();
		}
	
	  public void displayGUI(){
		
		setTitle("Registration Page");
		setSize(500,400);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setVisible(true);
 	   
		  idTF = new JTextField(10);
		  idTF.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		  
		  idLB=new JLabel("Agent ID: ");
		  
		  nameLB=new JLabel("Name: ");
		  phoneLB=new JLabel("Phone Number : ");
		  
		  searchLB = new JLabel();
		  searchLB.setSize(20, 20);
		  
		try {
			image = ImageIO.read(new File("Search.png"));}
		catch (IOException e) {e.printStackTrace();}
		
		  ImageIcon icon = new ImageIcon(scaledImage(image,searchLB.getWidth(),searchLB.getHeight()));
	       searchLB.setIcon(icon);
		  
		  panel2=new JPanel(new FlowLayout());
		  panel2.add(idLB);
		  panel2.add(idTF);
		  panel2.add(searchLB);
		  //panel2.setBackground(Color.RED);
		 
		  panel1=new JPanel();
		  panel1.setBorder(BorderFactory.createTitledBorder("Enter Agent Details"));
		  
		  
		  panel4=new JPanel(new FlowLayout());
		//  panel4.setBackground(Color.orange);
		  panel4.add(nameLB);
		  panel4.add(new JLabel("      "));
		  panel4.add(new JLabel("      "));
		  panel4.add(phoneLB);
		  panel4.setVisible(false);
			
		  panel3=new JPanel();
		  panel3.setLayout(new BoxLayout(panel3, BoxLayout.PAGE_AXIS));
		  panel3.add(panel2);
		  panel3.add(panel4);
		  panel3.setBackground(Color.yellow);
		  
		  panel1.add(panel3);
		  add(panel1,BorderLayout.NORTH);
			
		  idTF.addActionListener(new handler()); // to show details on the employee when the enter key is clicked 
		  idTF.addKeyListener(new MyKeyListener());//if one number is deleted from the id the information will disappear 
	}

	private class handler implements ActionListener{
	
		public void actionPerformed(ActionEvent event)
			{
				if(event.getSource()==idTF){ 
				
					ArrayList<String> list=DB_operation.employee_info(idTF.getText());
					if(list.isEmpty())
						JOptionPane.showMessageDialog(null, "Invalid ID ! Try again", "Error", JOptionPane.ERROR_MESSAGE);
					else{
					nameLB.setText(nameLB.getText().concat(list.get(0)));
					phoneLB.setText(phoneLB.getText().concat(list.get(1)));
					panel4.revalidate();
					panel4.setVisible(true);
					}
					
					
				}
			}
	}
	
	
	private class MyKeyListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE || e.getKeyChar() == KeyEvent.VK_DELETE)
			{
				nameLB.setText("Name :");
				phoneLB.setText("Phone Number :");
				panel4.setVisible(false);
			}
			
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e){}
			
		}
	
	/*###  Function for resizing the images ###*/
	public static Image scaledImage(Image img,int w,int h)
	{
		BufferedImage resizedImage=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2=resizedImage.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(img, 0, 0, w, h, null);
		g2.dispose();
		
		return resizedImage;
		
	}
}
