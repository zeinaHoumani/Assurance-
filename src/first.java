import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class first extends JFrame{
	private static final long serialVersionUID = 1L;
	
	
	private JPanel clientPanel;
	private JPanel agentPanel;
	private JPanel imagePanel;
	private BufferedImage imageAgent;
	private BufferedImage imageClient;
	private JLabel agentLB;
	private JLabel clientLB;
	private JLabel imageAgentLB;
	private JLabel imageClientLB;
	private JFrame frame;

	
	public first(){
		frame=this;
		displayGUI();
		Handlers();
	}
	
	
	public void displayGUI()
	{
		setTitle("Select User Page");
		setSize(570,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setVisible(true);
	    
	    /** Change the size of the labels **/
	    imageAgentLB=new JLabel("Agent");
	    imageAgentLB.setFont(new Font(imageAgentLB.getFont().getName(), imageAgentLB.getFont().getStyle(), 20));
	    
	    imageClientLB=new JLabel("Client");
	    imageClientLB.setFont(new Font(imageClientLB.getFont().getName(), imageClientLB.getFont().getStyle(), 20));
		   
	    
	    /** resize the labels that will contain the images **/
	    agentLB = new JLabel();
	    agentLB.setSize(260, 288);
	    
	    clientLB = new JLabel();
        clientLB.setSize(260, 288);
	    
        
        /** load the first image**/
	    try {
			imageAgent = ImageIO.read(new File("Pictures\\agent2.png"));}
	    catch (IOException e) {e.printStackTrace();}
	    
	    ImageIcon agenticon = new ImageIcon(DB_operation.scaledImage(imageAgent,agentLB.getWidth(),agentLB.getHeight()));
        agentLB.setIcon(agenticon);
        
        /** add the image and the label of the image in one panel **/
        JPanel p=new JPanel();
        p.add(imageAgentLB);
        
        agentPanel = new JPanel(new BorderLayout());
        agentPanel.add( agentLB, BorderLayout.CENTER);
       
        agentPanel.add( p, BorderLayout.SOUTH);
        agentPanel.setBorder(BorderFactory.createRaisedBevelBorder()); 
        
       
        /* ### load the second image ###*/
        try {
			imageClient = ImageIO.read(new File("Pictures\\agent.jpg"));}
        catch (IOException e) {e.printStackTrace();}
		
        ImageIcon clienticon = new ImageIcon(DB_operation.scaledImage(imageClient,clientLB.getWidth(),clientLB.getHeight()));
        clientLB.setIcon(clienticon);
        
        
        /*### add the image and the label of the image in one panel ###*/
        JPanel p1=new JPanel();
        p1.add(imageClientLB);
        
        /*### Create Ower Own Color ###*/
        Color myNewBlue = new Color (210, 223, 255);
        p1.setBackground(myNewBlue);
        p.setBackground(myNewBlue);
        
        clientPanel = new JPanel(new BorderLayout());
        clientPanel.add( clientLB, BorderLayout.CENTER );
     
        clientPanel.add( p1, BorderLayout.SOUTH);
        clientPanel.setBorder(BorderFactory.createRaisedBevelBorder()); 
        
       
        /* ### to Separate the two images ###*/
        JPanel empty=new JPanel();
        
        /*### Organize the two Panels with GridBagLayout###*/
        
        imagePanel=new JPanel(new GridBagLayout());
        GridBagConstraints c2= new GridBagConstraints();
		 
		  
		  c2.gridx=0;
		  c2.gridy=0;
		  imagePanel.add(agentPanel,c2); 
		  
		  c2.gridx=1;
		  c2.gridy=0;
		  imagePanel.add(empty,c2); 
		  
		  c2.gridx=2;
		  c2.gridy=0;
		  imagePanel.add(clientPanel,c2); 
		  
        
		  imagePanel.setBorder(BorderFactory.createTitledBorder("Select User"));
		  JPanel t=new JPanel();
		  t.add(imagePanel);
		  add(t,BorderLayout.CENTER);
	}
	
	
	public void Handlers()
	{
		agentPanel.addMouseListener(new MyMouseListener());
		clientPanel.addMouseListener(new MyMouseListener());
	}
	
	
	private class MyMouseListener implements MouseListener{

		@SuppressWarnings("deprecation")
		@Override
		public void mouseClicked(MouseEvent ev) {
			
			if(ev.getSource()==agentPanel){
				
				 EventQueue.invokeLater(new Runnable() {
			            @Override
			            public void run() {
			            	
				 new HomePage(frame);
			            	frame.hide();
			}
				 });
			}
			
			if(ev.getSource()==clientPanel){
				new Client_ID(frame);
				frame.hide();
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
		
		
	
}
