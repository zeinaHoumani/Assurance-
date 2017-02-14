import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;


public class My_JOptionPane extends JOptionPane{
	private static final long serialVersionUID = 1L;
	
	private JLabel question;
	private JLabel name;
	private JLabel phone;
	private JRadioButton yes;
	private JRadioButton no;
	private JPanel data;
	private JPanel check;
	private JPanel panel;
	private JButton option1;
	private JTextField phoneTF;
	private JTextField nameTF;
	
	
	
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
	private JButton hosp;

	/* data taked from the JoptionPane */
	private static String ApplicantName = "";
	private static String ApplicantPhone = "";
	private static int employeeID;
	
	
	
	public static void close_My_JOptionPane() {
		
		/* to close just the joption pane not the entire program */
		Window[] windows = Window.getWindows();
		for (Window window : windows) {
			if (window instanceof JDialog) {
				JDialog dialog = (JDialog) window;
				if (dialog.getContentPane().getComponentCount() == 1
						&& dialog.getContentPane().getComponent(0) instanceof JOptionPane) {
					dialog.dispose();
				}
			}
		}

	}

	
	/*### designing our own shape of Joption pane ###*/
	public void show_optionpane(){
		
		 question=new JLabel("Is the policy applicant different from the policy owner? ");
		
		/*one radio button in a time*/
		 yes= new JRadioButton("yes");
		 no= new JRadioButton("no");
		 ButtonGroup group = new ButtonGroup();
		 group.add(yes);
		 group.add(no);
		 
		    
		name=new JLabel("Applicant name: ");
	    phone=new JLabel("Applicant phone: ");
	    nameTF=new JTextField(10);
		phoneTF=new JTextField(10);
		
	    data=new JPanel(new FlowLayout());
		data.add(name);
		data.add(nameTF);
		data.add(new JLabel(""));
		data.add(phone);
		data.add(phoneTF);
		
		/* the data components are disabled */
		DB_operation.Enable_Components(data,false);
		
		check = new JPanel(new FlowLayout());
		check.add(question);
		check.add(new JLabel(""));
		check.add(yes);
		check.add(no);
				
				
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS) );
		panel.add(check);
		panel.add(data);
		
	     
		option1 = new JButton("Done");
		option1.setToolTipText("To enable it fill the fields and press enter");
		//option1.addMouseListener(new Mouse_Handler());
	    option1.setEnabled(false);
	    
	    Add_Handlers();
	    int n=0;
	    do{
		n=JOptionPane.showOptionDialog( 
	            this, 
	            panel,
	            "Question",
	            JOptionPane.DEFAULT_OPTION,
	            JOptionPane.QUESTION_MESSAGE,null,new JButton[]{option1},option1 );

	}while(no.isSelected()==false && nameTF.getText().equals("") && phoneTF.getText().equals("") && n==JOptionPane.CLOSED_OPTION);
	}
	
	
	
	public void show_ImputDialog() {

		data = new JPanel(new GridLayout(3, 2));
		JButton Done = new JButton("DONE");
		data.add(new JLabel("New Disease : "));
		data.add(new JTextField(10));
		data.add(new JLabel(""));
		data.add(new JLabel(""));
		data.add(new JLabel(""));
		data.add(Done);
		//Done.addActionListener(new handler());

		JOptionPane.showOptionDialog(this, data, "Adding new Disease", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, new JLabel[] { }, null);
		
		

	}
	
	public void Add_Handlers(){
		phoneTF.addActionListener(new handler_JOptionPane());
	    yes.addActionListener(new handler_JOptionPane());
	    no.addActionListener(new handler_JOptionPane());
	    option1.addActionListener(new handler_JOptionPane());
		
	}	

	private class handler_JOptionPane implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			
			/* the Done button */
			if (event.getSource() == option1)
			{
				ApplicantName = nameTF.getText();
				ApplicantPhone = phoneTF.getText();

				close_My_JOptionPane();
			}

			if (event.getSource() == yes) {
				DB_operation.Enable_Components(check, false);
				DB_operation.Enable_Components(data, true);
			}
			if (event.getSource() == no) {
				close_My_JOptionPane();
			}

			if (event.getSource() == phoneTF) {
				if (DB_operation.CheckComponent(data) == 1)// all the fields are filled
					option1.setEnabled(true);

			}
			
			if(event.getSource()==hosp){
				
				employeeID=Integer.parseInt(idTF.getText());
				close_My_JOptionPane();
				
				
			}

		}

	}
	
	
	public static ArrayList<String> Applicant_Info(){

		if(!ApplicantName.equals("") && !ApplicantPhone.equals("") ){
		
		ArrayList<String> list = new ArrayList<>();
		list.add(ApplicantName);
		list.add(ApplicantPhone);
		return list;
		}
		
	return null;
	}

	

	public int show_hosp_dialog(){
		
		 idTF = new JTextField(10);
		  idTF.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		  
		  idLB=new JLabel("Agent ID: ");
		  
		  nameLB=new JLabel("Name: ");
		  phoneLB=new JLabel("Phone Number : ");
		  
		  searchLB = new JLabel();
		  searchLB.setSize(20, 20);
		  
		try {
			image = ImageIO.read(new File("Pictures\\Search.png"));}
		catch (IOException e) {e.printStackTrace();}
		
		  ImageIcon icon = new ImageIcon(DB_operation.scaledImage(image,searchLB.getWidth(),searchLB.getHeight()));
	       searchLB.setIcon(icon);
		  
		  panel2=new JPanel(new FlowLayout());
		  panel2.add(idLB);
		  panel2.add(idTF);
		  panel2.add(searchLB);
		 
		  panel1=new JPanel();
		  panel1.setBorder(BorderFactory.createTitledBorder("Enter Agent Details"));
		  
		  
		  panel4=new JPanel(new FlowLayout());
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
			
		  idTF.addActionListener(new handler()); // to show details on the employee when the enter key is clicked 
		  idTF.addKeyListener(new MyKeyListener());//if one number is deleted from the id the information will disappear 
		  
		  panel1.setPreferredSize(new Dimension(350, 100));
		    hosp = new JButton("Done");
		    hosp.setEnabled(false);
		   hosp.addActionListener(new handler_JOptionPane());
		    hosp.setToolTipText("To enable it enter a correct ID !!");
		    
		    
		    JOptionPane.showOptionDialog( 
			
		            this, 
		            panel1,
		            "Employee",
		            JOptionPane.DEFAULT_OPTION,
		            JOptionPane.QUESTION_MESSAGE,null,new JButton[]{hosp},hosp );
			

			 return employeeID;
						 
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
					hosp.setEnabled(true);
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
				hosp.setEnabled(false);
			}
			
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e){}
			
		}
}
