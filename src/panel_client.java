import javax.swing.*;
import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class panel_client extends JFrame {

		/*    ////   Components     /////    */
		private JTextField lastnameTF;
		private JTextField MothernameTF;
		private JTextField FathernameTF;
		private JTextField FirstnameTF;
		private JTextField ageTF;
		private JTextField BirthplaceTF;
		private JTextField JobTF ;
		private JTextField AddressTF ;
		private JTextField TelTF;
		
		private JButton submitB ;
		
		private JLabel PolicyLB;
		private JLabel DateLB;
		private JLabel lastnameLB;
		private JLabel MothernameLB;
		private JLabel FathernameLB;
		private JLabel FirstnameLB;
		private JLabel BirthdateLB;
		private JLabel BirthplaceLB;
		private JLabel MaritalstateLB;
		private JLabel JobLB;
		private JLabel AddressLB;
		private JLabel TelLB;
		
		private JCheckBox single;
		private JCheckBox Married; 
		private JCheckBox Divorced; 
		
		private JPanel p1;

		/*Component of Owr JoptionPane*/
		private JDialog dialog;
		private JLabel question;
		private JLabel name;
		private JLabel phone;
		private JRadioButton yes;
		private JRadioButton no;
		private JPanel data;
		private JPanel check ;
		private JPanel panel;
		private JButton option1;
		private JTextField phoneTF;
		private JTextField nameTF;
		
		/*data taked from the JoptionPane*/
		private String ApplicantName="";
		private String ApplicantPhone="";
		
		
		
		private My_JOptionPane pane;
		
		public panel_client() {
			
			  setTitle("Client Page");
			  setSize(800,400);
			  setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			  setVisible(true);
			
			this.addWindowListener(new MyWindowAdapter());
			//GUI();
			data=new JPanel();
			//Personal_Info p1=new  Personal_Info(data);
			
			//p1.Show_Panel();
			data.repaint();
			add(data);
		}
		
		/*### When the Window open a question is showen ###*/
		private class MyWindowAdapter extends WindowAdapter{
			 @Override 
		        public void windowOpened(WindowEvent e) {
				// show_optionpane();
				 
			    pane=new My_JOptionPane();
				pane.show_optionpane();
			 }
			 
			 
		 }
		
/*		public void GUI()
		{
			  setTitle("Client Page");
			  setSize(800,400);
			  setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			  setVisible(true);
		      
		      /*### initialize all components ###*/
	/*	      FirstnameTF=new JTextField(10);
		      lastnameTF =new JTextField(10);
		      MothernameTF =new JTextField(10);
		      FathernameTF=new JTextField(10);
		      ageTF=new JTextField(10);
		      BirthplaceTF=new JTextField(10);
			  JobTF = new JTextField(10);
		      AddressTF = new JTextField(10);
			  TelTF = new JTextField(10);
			  
			  PolicyLB=new JLabel("Policy number:",SwingConstants.CENTER);
			  DateLB=new JLabel("Date:",SwingConstants.CENTER);
			  lastnameLB=new JLabel("Last Name:",SwingConstants.CENTER);
			  MothernameLB=new JLabel("Mother Name:",SwingConstants.CENTER);
			  FathernameLB=new JLabel("Father Name:",SwingConstants.CENTER);
			  FirstnameLB=new JLabel("First Name:",SwingConstants.CENTER);
			  BirthdateLB=new JLabel("Birth Date:",SwingConstants.CENTER);
			  BirthplaceLB=new JLabel("Birth Place:",SwingConstants.CENTER);
			  MaritalstateLB=new JLabel("Marital state:",SwingConstants.CENTER);
			  JobLB=new JLabel("Job:",SwingConstants.CENTER);
			  AddressLB=new JLabel("Address:",SwingConstants.CENTER);
			  TelLB=new JLabel("Tel: ",SwingConstants.CENTER);
			  
			  single=new JCheckBox( "single" ); 
			  Married=new JCheckBox( "Married" );
			  Divorced=new JCheckBox( "Divorced" ); 
			  
			  ButtonGroup group = new ButtonGroup();
			  group.add(single);
			  group.add(Married);
			  group.add(Divorced);
			  
			  
			  /*### Add all components to a panel ###*/
		/*	  p1 =new JPanel( new GridLayout(9,6));
			  p1.setBorder(BorderFactory.createTitledBorder("Personal Info"));
			  
			  p1.add(PolicyLB);
			  p1.add(new JLabel("  "));p1.add(new JLabel("  "));p1.add(new JLabel("  "));p1.add(new JLabel("  "));p1.add(new JLabel("  "));
			  p1.add(DateLB);
			  p1.add(new JLabel("  "));p1.add(new JLabel("  "));p1.add(new JLabel("  "));p1.add(new JLabel("  "));p1.add(new JLabel("  "));
			  p1.add(FirstnameLB);
			  p1.add(FirstnameTF);
			  
			  p1.add(lastnameLB);
			  p1.add(lastnameTF);
			  p1.add(FathernameLB);
			  p1.add(FathernameTF);
			  p1.add(new JLabel("  "));p1.add(new JLabel("  "));p1.add(new JLabel("  "));p1.add(new JLabel("  "));p1.add(new JLabel("  "));p1.add(new JLabel("  "));
			  p1.add(MothernameLB);
			  p1.add(MothernameTF);
			  p1.add(BirthdateLB);
			  p1.add(ageTF);
			  p1.add(BirthplaceLB);
			  p1.add(BirthplaceTF);
			  p1.add(new JLabel("  "));p1.add(new JLabel("  "));p1.add(new JLabel("  "));p1.add(new JLabel("  "));p1.add(new JLabel("  "));p1.add(new JLabel("  "));
			  p1.add(MaritalstateLB);
			  p1.add(single);
			  p1.add(Married);
			  p1.add(Divorced);
			  p1.add(new JLabel("  "));p1.add(new JLabel("  "));
			  p1.add(new JLabel("  "));p1.add(new JLabel("  "));p1.add(new JLabel("  "));p1.add(new JLabel("  "));p1.add(new JLabel("  "));p1.add(new JLabel("  "));
			  p1.add(JobLB);
			  p1.add(JobTF);
			  p1.add(AddressLB);
			  p1.add(AddressTF);
			  p1.add(TelLB);
			  p1.add(TelTF);
			  
			  JPanel p3 =new JPanel(new GridBagLayout());
			  GridBagConstraints c= new GridBagConstraints();
			  c.gridx=0;
			  c.gridy=0;
			  p3.add(p1,c);
			  add(p3);
			
			submitB=new JButton("insert");
			add(submitB,BorderLayout.SOUTH);
			submitB.addActionListener(new handler());	
			 
		}
		*/
		
		/*### check all the components in a panel to verify if there is an empty fields ###*/
/*		public int CheckComponent(JPanel p) {
			
			Component[] components = p.getComponents();
			
			for (Component component : components) {
				
				if (component instanceof JTextField)
				{
					if (((JTextField) component).getText().equals("")) {
						
						JOptionPane.showMessageDialog(null, "there is some empty fields", "Check your entries",JOptionPane.ERROR_MESSAGE);
						component.requestFocus();
						
						return 0;
						}
				}
			}
			return 1;
		}
		
		
		/*### enable/disable all components in a container ###*/
	/*	public void Enable_Components(Container p,boolean enable){
			
			for (Component component : p.getComponents())
			{
					component.setEnabled(enable);
				
					if (component instanceof Container) 
					{
				       Enable_Components((Container)component, enable);
				     }
			}
		}
		
		
		/*### designing our own shape of Joption pane ###*/
	/*	public void show_optionpane(){
			
			 question=new JLabel("is the policy applicant different from the policy owner? ");*/
			
			/*one radio button in a time*/
		/*	 yes= new JRadioButton("yes");
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
		/*	Enable_Components(data,false);
			
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
		    option1.setEnabled(false);
		    
		    phoneTF.addActionListener(new handler());
		    yes.addActionListener(new handler());
		    no.addActionListener(new handler());
		    option1.addActionListener(new handler());
		    
		    
		  /*  dialog = new JDialog(this, "Dialog Title", ModalityType.APPLICATION_MODAL);
		    
		    dialog.getContentPane().add(panel);
		    dialog.pack();
		    dialog.setSize(650, 180);
		    dialog.setLocationRelativeTo(this);
		    dialog.setVisible(true);*/
		    
	/*		JOptionPane.showOptionDialog( 
		            this, 
		            panel,
		            "Question",
		            JOptionPane.DEFAULT_OPTION,
		            JOptionPane.QUESTION_MESSAGE,null,new JButton[]{option1},option1 );

		}
		*/

	/*	
		private class handler implements ActionListener{
			
			public void actionPerformed(ActionEvent event)
				{
					if(event.getSource()==submitB){ 
						if(DB_operation.CheckComponent(p1)==1){
							
						/*### insert the informations in our database with the static function insert_client_info ###*/
		/*				ArrayList<String> list=new ArrayList<>();
						list.add(FirstnameTF.getText());
						list.add(lastnameTF.getText());
						list.add(FathernameTF.getText());
						list.add(MothernameTF.getText());
						list.add(AddressTF.getText());
						list.add(JobTF.getText());
						list.add(TelTF.getText());
						
						String state="";
						if(Married.isSelected())state="married";
						else if(Divorced.isSelected())state="divorced";
						else if(single.isSelected())state="single";
						
						list.add(state);
						list.add(ageTF.getText());
						list.add(BirthplaceTF.getText());
						
						DB_operation.Insert_client_info(list);
						}
					}
					
					/*the Done button in the Joption pane*/
			/*		if(event.getSource()==option1){
						
						ApplicantName=nameTF.getText();
						ApplicantPhone=phoneTF.getText();
						
						//JOptionPane.showMessageDialog(null, "hello"+ApplicantName+""+ApplicantPhone);
						//close_JOptionPane();
						pane.close_My_JOptionPane();
						
				            }

			
					
					/* the yes/no radio button in the JOption Pane*/
				/*	if(event.getSource()==yes)
					{
						DB_operation.Enable_Components(check,false);
						DB_operation.Enable_Components(data, true);
					}
					if(event.getSource()==no){ 
						//close_JOptionPane();
						pane.close_My_JOptionPane();
						}
					
					/* the text field in the Joption pane*/
				/*	if(event.getSource()==phoneTF)
					{
						if(DB_operation.CheckComponent(data)==1)//all the fields are filled
						option1.setEnabled(true);
						
						}
						
							
				}
		}
		
		
	/*	
	public void close_JOptionPane()
	{
		/*to close just the joption pane not the entire program*/
	/*	 Window[] windows = Window.getWindows();
           for (Window window : windows) {
               if (window instanceof JDialog) {
                   JDialog dialog = (JDialog) window;
                   if (dialog.getContentPane().getComponentCount() == 1
                           && dialog.getContentPane().getComponent(0) instanceof JOptionPane) {
                       dialog.dispose();
                   }
               }
           }
		
	}*/
		
	/*public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
            
            public void run() {
            	
		panel_client frame=new panel_client();
            }


	});

	}*/
}
