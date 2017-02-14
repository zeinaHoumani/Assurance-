import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

public class Life_Insurance extends JFrame{
	private static final long serialVersionUID = 1L;
	
	
	private JButton Save;
	private JButton Reset;
	
	private JPanel Buttons;
	private JPanel all;
	
	private Personal_Info p1;
	private life_policyPanel p2;
	private health p3;
	private My_JOptionPane pane;
	
	private GridBagConstraints c;
	int clientid;
	
	
	
	public Life_Insurance() {
		
		  setTitle("Life Insurance Page");
		  setSize(1220,685);
		  setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		  setLocationRelativeTo(null);
		  setVisible(true);
		  
		  all=new JPanel(new GridBagLayout());
		   c=new GridBagConstraints();
		   
		 
		  this.addWindowListener(new MyWindowAdapter());
		
		p1=new  Personal_Info(all,c);
		p1.Show_header();
		p1.Show_Panel();
		
		all.repaint();

		p2=new life_policyPanel(all, c);
		p2.Display();
		
		all.repaint();
		
		p3=new health(all,c);
		p3.GUI();
		
		all.repaint();
		
		final_panel();
		
		all.repaint();
		
		add(all);
	
		
	}
	
	/*### When the Window opens a question will be showed ###*/
	private class MyWindowAdapter extends WindowAdapter{
		 @Override 
	        public void windowOpened(WindowEvent e) {
			 p1.get_policyLB().setText(p1.get_policyLB().getText().concat("  "+DB_operation.Number_policy()+""));
			
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-M-dd");
				Date today = Calendar.getInstance().getTime();
				String reportDate = df.format(today);
			 
			 p1.get_dateLB().setText(p1.get_dateLB().getText().concat("  "+reportDate));
			 
		       
				
				} 	 
	 }
	
	
	
	public void final_panel()
	{
		Save=new JButton("Save");
		Reset=new JButton("Reset");
		
		Buttons=new JPanel(new FlowLayout());
		Buttons.add(Save);
		Buttons.add(Reset);
		
		Buttons.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        c.anchor = GridBagConstraints.LINE_END;
		
		c.weightx = c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 4;
		
		all.add(Buttons,c);
		
		Save.addActionListener(new handler());
		Reset.addActionListener(new handler());
		
	}
	
	
	private class handler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==Save){
				
				
				if(DB_operation.CheckComponent(p1.get_panel())==1 && DB_operation.CheckComponent(p2.get_panel())==1){
					
					 pane=new My_JOptionPane();
						pane.show_optionpane();
						
						int employeeid=pane.show_hosp_dialog();
					
					clientid=DB_operation.Insert_client_info(p1.getData());
					
					DB_operation.insert_disease(p3.getData(clientid));
					
					if(life_policyPanel.replace())
						DB_operation.Delete_policy(clientid);
					
					ArrayList<String> temp=p2.getData(clientid);
					temp.add(1, employeeid+"");
					DB_operation.insert_policy(temp);
				}
			}
			
			if(e.getSource()==Reset){
				
				Reset(all);
				Reset((JPanel)p3.get_panel());
			}
			
		}
		
		
	}
	
	
	
	public void Reset(JPanel p)
	{
	
		Component[] components = p.getComponents();
		for (Component component : components) {
			
			if (component instanceof JTextField ) {
				((JTextField) component).setText(""); }
			
			if(component instanceof JRadioButton)
			{
				( (AbstractButton) component).setSelected(false);
				p3.get().clearSelection();
				p2.get1().clearSelection();
				p2.get().clearSelection();
			}
			if(component instanceof JCheckBox)
			{
				((AbstractButton) component).setSelected(false);
				p1.get().clearSelection();
				
			}
			
			if(component instanceof JPanel){ //clear the components for every single panel in the frame
				Reset((JPanel)component);
			}
		}
			
					
	}

	

}
