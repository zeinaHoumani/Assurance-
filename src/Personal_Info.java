import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

public class Personal_Info extends JPanel {
	private static final long serialVersionUID = 1L;

	/* //// Components ///// */
	private JTextField lastnameTF;
	private JTextField MothernameTF;
	private JTextField FathernameTF;
	private JTextField FirstnameTF;
	private JTextField ageTF;
	private JTextField BirthplaceTF;
	private JTextField JobTF;
	private JTextField AddressTF;
	private JTextField TelTF;
	private JTextField MaritalstateTF;

	private JLabel PolicyLB;
	private JLabel DateLB;
	public JLabel lastnameLB;
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
	private JPanel all;
	private JPanel def;
	private JPanel vertical;
	private ButtonGroup group;
	private GridBagConstraints con;
	
	int[] updated={0,0,0,0,0,0,0,0,0,0};
	

	public Personal_Info(JPanel all, GridBagConstraints c) {

		this.con = c;
		this.all = all;

	}

	public Personal_Info() {
	}

	public void Show_header() {
		
		
		
		PolicyLB = new My_JLabel("Policy Number:", SwingConstants.CENTER, new Color(5, 180, 206));
		DateLB = new My_JLabel("Date:", SwingConstants.CENTER, new Color(5, 180, 206));

		def = new JPanel(new GridLayout(2, 2));
		def.add(PolicyLB);
		def.add(new JLabel("  "));
		def.add(DateLB);
		def.add(new JLabel("  "));
		def.setBorder(BorderFactory.createRaisedBevelBorder());

		con.weightx = con.weighty = 0.0;

		con.gridx = 0;
		con.gridy = 0;

		con.fill = GridBagConstraints.FIRST_LINE_START;
		all.add(def, con);
	}

	public void create_component() {

		/* ### initialize all components ### */
		FirstnameTF = new My_JTextField(10);
		lastnameTF = new My_JTextField(10);
		MothernameTF = new My_JTextField(10);
		FathernameTF = new My_JTextField(10);
		ageTF = new My_JTextField(10);
		BirthplaceTF = new My_JTextField(10);
		JobTF = new My_JTextField(10);
		AddressTF = new My_JTextField(10);
		TelTF = new My_JTextField(10);
		MaritalstateTF = new My_JTextField(10);

		lastnameLB = new My_JLabel("Last Name:", SwingConstants.CENTER);
		MothernameLB = new My_JLabel("Mother Name:", SwingConstants.CENTER);
		FathernameLB = new My_JLabel("Father Name:", SwingConstants.CENTER);
		FirstnameLB = new My_JLabel("First Name:", SwingConstants.CENTER);
		BirthdateLB = new My_JLabel("Age:", SwingConstants.CENTER);
		BirthplaceLB = new My_JLabel("Birth Place:", SwingConstants.CENTER);
		MaritalstateLB = new My_JLabel("Marital state:", SwingConstants.CENTER);
		JobLB = new My_JLabel("Job:", SwingConstants.CENTER);
		AddressLB = new My_JLabel("Address:", SwingConstants.CENTER);
		TelLB = new My_JLabel("Tel: ", SwingConstants.CENTER);

		single = new My_JCheckBox("single");
		Married = new My_JCheckBox("Married");
		Divorced = new My_JCheckBox("Divorced");
		
		group = new ButtonGroup();
		group.add(single);
		group.add(Married);
		group.add(Divorced);
		
		
		    

		
	}
	


	/* Show Components in a horizontal View */

	public void Show_Panel() {
		create_component();

		/* ### Add all components to a panel ### */
		p1 = new JPanel(new GridLayout(4, 6));
		p1.setBorder(BorderFactory.createTitledBorder("Personal Info"));

		p1.add(FirstnameLB);
		p1.add(FirstnameTF);
		p1.add(lastnameLB);
		p1.add(lastnameTF);
		p1.add(FathernameLB);
		p1.add(FathernameTF);

		p1.add(MothernameLB);
		p1.add(MothernameTF);
		p1.add(BirthdateLB);
		p1.add(ageTF);
		p1.add(BirthplaceLB);
		p1.add(BirthplaceTF);

		p1.add(MaritalstateLB);
		p1.add(single);
		p1.add(Married);
		p1.add(Divorced);
		p1.add(new JLabel("  "));
		p1.add(new JLabel("  "));

		p1.add(JobLB);
		p1.add(JobTF);
		p1.add(AddressLB);
		p1.add(AddressTF);
		p1.add(TelLB);
		p1.add(TelTF);

		p1.setPreferredSize(new Dimension(1070, 126));

		con.weightx = con.weighty = 0.1;

		con.gridx = 0;
		con.gridy = 1;

		con.fill = GridBagConstraints.HORIZONTAL;
		all.add(p1, con);

	}

	/* Show Components in an vertical view */

	public JPanel display_vertical() {

		create_component();
		vertical = new JPanel(new GridLayout(19, 2));
		

		vertical.add(FirstnameLB);
		vertical.add(FirstnameTF);
		vertical.add(new JLabel("  "));
		vertical.add(new JLabel("  "));
		vertical.add(lastnameLB);
		vertical.add(lastnameTF);
		vertical.add(new JLabel("  "));
		vertical.add(new JLabel("  "));
		vertical.add(FathernameLB);
		vertical.add(FathernameTF);
		vertical.add(new JLabel("  "));
		vertical.add(new JLabel("  "));
		vertical.add(MothernameLB);
		vertical.add(MothernameTF);
		vertical.add(new JLabel("  "));
		vertical.add(new JLabel("  "));
		vertical.add(BirthdateLB);
		vertical.add(ageTF);
		vertical.add(new JLabel("  "));
		vertical.add(new JLabel("  "));
		vertical.add(BirthplaceLB);
		vertical.add(BirthplaceTF);
		vertical.add(new JLabel("  "));
		vertical.add(new JLabel("  "));
		vertical.add(MaritalstateLB);
		vertical.add(MaritalstateTF);
		vertical.add(new JLabel("  "));
		vertical.add(new JLabel("  "));
		vertical.add(JobLB);
		vertical.add(JobTF);
		vertical.add(new JLabel("  "));
		vertical.add(new JLabel("  "));
		vertical.add(AddressLB);
		vertical.add(AddressTF);
		vertical.add(new JLabel("  "));
		vertical.add(new JLabel("  "));
		vertical.add(TelLB);
		vertical.add(TelTF);

		
		
		lastnameTF.addKeyListener(new handler());
		FirstnameTF.addKeyListener(new handler());
		ageTF.addKeyListener(new handler());
		BirthplaceTF.addKeyListener(new handler());
		JobTF.addKeyListener(new handler());
		TelTF.addKeyListener(new handler());
		MaritalstateTF.addKeyListener(new handler());
		AddressTF.addKeyListener(new handler());
		MothernameTF.addKeyListener(new handler());
		FathernameTF.addKeyListener(new handler());
		/*AmountTF.addKeyListener(new handler());
		update.addActionListener(new hand());*/
		
		
		vertical.setPreferredSize(new Dimension(490, 500));
		return vertical;

	}

	/* ### Get all the data from this Panel ### */
	public ArrayList<String> getData() {

		ArrayList<String> list = new ArrayList<>();
		list.add(FirstnameTF.getText());
		list.add(lastnameTF.getText());
		list.add(FathernameTF.getText());
		list.add(MothernameTF.getText());
		list.add(AddressTF.getText());
		list.add(JobTF.getText());
		list.add(TelTF.getText());

		String state = "";
		if (Married.isSelected())
			state = "married";
		else if (Divorced.isSelected())
			state = "divorced";
		else if (single.isSelected())
			state = "single";

		list.add(state);
		list.add(ageTF.getText());
		list.add(BirthplaceTF.getText());

		return list;
	}
	
	

	public void fill_data(ArrayList<String> list) {
		int counter = 0;
		Component[] components = vertical.getComponents();
		for (Component component : components) {
			if (component instanceof JTextField) {

				((JTextField) component).setText(list.get(counter));
				counter++;
			}

		}

	}

	public ButtonGroup get() {
		return group;
	}

	public JPanel get_panel() {
		return p1;
	}

	public JLabel get_policyLB() {
		return this.PolicyLB;
	}

	public JLabel get_dateLB() {
		return this.DateLB;
	}

	
	
	public ArrayList<String> getData_ver() {

		ArrayList<String> list = new ArrayList<>();
		list.add(FirstnameTF.getText());
		list.add(lastnameTF.getText());
		list.add(FathernameTF.getText());
		list.add(MothernameTF.getText());
		list.add(AddressTF.getText());
		list.add(JobTF.getText());
		list.add(TelTF.getText());
		list.add(MaritalstateTF.getText());
		list.add(ageTF.getText());
		list.add(BirthplaceTF.getText());

		return list;

	}
	


	private class handler implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == lastnameTF) {
				updated[1]++;

			}

			if (e.getSource() == FirstnameTF) {
				updated[0]++;

			}
			if (e.getSource() == ageTF) {
				updated[8]++;

			}
			if (e.getSource() == BirthplaceTF) {
				updated[9]++;

			}
			if (e.getSource() == JobTF) {
				updated[5]++;
			}
			if (e.getSource() == TelTF) {
				updated[6]++;
			}
			if (e.getSource() == MaritalstateTF) {
				updated[7]++;
			}
			if (e.getSource() == AddressTF) {
				updated[4]++;
			}
			if (e.getSource() == MothernameTF) {
				updated[3]++;
			}
			if (e.getSource() == FathernameTF) {
				updated[2]++;
			}
			

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}
	}	
	
	
	public int[] get_table()
	{
		
		return updated;
	}
	
}
