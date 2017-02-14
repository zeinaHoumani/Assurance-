import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class health extends JFrame {
	private static final long serialVersionUID = 1L;

	private JButton Done;
	
	private JCheckBox No;
	private JCheckBox Asthma;
	private JCheckBox BoodPressure;
	private JCheckBox D_o_A;
	private JCheckBox othor;
	private JCheckBox cancer;
	private JCheckBox Diabetes;
	private JCheckBox Cholesterol;
	private JCheckBox alcohol;
	private JCheckBox ajouter_element;
	private JCheckBox HeartProblem;
	
	private JRadioButton No2;
	private JRadioButton Yes;

	
	private JLabel Titre;
	private JLabel Q1;
	
	private JTextField otherTF;
	
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private JPanel data;
	private ButtonGroup group ;
	private GridBagConstraints c;
	private GridBagConstraints con;

	private JPanel all;
	
	private JScrollPane o;
	public health(JPanel all,GridBagConstraints c) {

		this.con=c;
	    this.all=all;
	
	}
	
	
	public void GUI() {
		
		Titre = new My_JLabel("Have you ever been treated for one of the following condition ?", SwingConstants.CENTER);
		Q1 = new My_JLabel("Do you use tobaco in any form?", SwingConstants.LEFT);
		p1 = new JPanel(new GridLayout(7, 3));

		
		No = new My_JCheckBox("No");
		HeartProblem = new My_JCheckBox("HeartProblem");
		Asthma = new My_JCheckBox("Asthma");
		BoodPressure = new My_JCheckBox("BloodPressure");
		D_o_A = new My_JCheckBox("Depression or Anxiety");
		othor = new My_JCheckBox("other significant issues");
		cancer = new My_JCheckBox("cancer");
		Diabetes = new My_JCheckBox("Diabetes");
		Cholesterol = new My_JCheckBox("Cholesterol");
		alcohol = new My_JCheckBox("alcohol");

		Yes=new JRadioButton("Yes");
		No2=new JRadioButton("No");
		
	    group = new ButtonGroup();
		group.add(Yes);
		group.add(No);

		p1.add(Titre);
		p1.add(new JLabel(""));
		p1.add(new JLabel(""));
		p1.add(No);
		p1.add(HeartProblem);
		p1.add(Asthma);
		p1.add(BoodPressure);
		p1.add(D_o_A);
		p1.add(othor);
		p1.add(cancer);
		p1.add(Diabetes);
		p1.add(new JLabel(""));
		p1.add(Cholesterol);
		p1.add(alcohol);
		p1.add(new JLabel(""));
		
		all.setBackground(new Color (246,247,252));
		
	    o = new JScrollPane(p1);
		o.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		o.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		o.setBounds(13, 10, 1029, 150);

		JPanel contentPane = new JPanel(null);
		contentPane.setPreferredSize(new Dimension(1070, 160));
		contentPane.add(o);
		
		p3 = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();

		c.weightx = c.weighty = 0.2;
		//c.anchor = GridBagConstraints.FIRST_LINE_START;

		c.gridx = 0;
		c.gridy = 0;

		c.fill = GridBagConstraints.HORIZONTAL;
		p3.add(contentPane, c);

		p3.repaint();

		p2 = new JPanel(new GridLayout(1, 3));
		p2.add(Q1);
		p2.add(Yes);
		p2.add(No2);
		
		c.weightx = c.weighty = 1;

		c.gridx = 0;
		c.gridy = 1;

		p3.add(p2, c);
		p3.revalidate();
		p3.setBorder(BorderFactory.createTitledBorder("Health Info"));
		//p3.setPreferredSize(new Dimension(900, 500));
		JPanel a = new JPanel(new GridBagLayout());
		a.add(p3);
		//a.setPreferredSize(new Dimension(900, 900));
		con.fill = GridBagConstraints.HORIZONTAL;
		
		con.weightx = con.weighty = 0.1;
		con.gridx = 0;
		con.gridy = 2;
		
		all.add(a,con);
		
		othor.addActionListener(new handler());

	}

	
	public Container get_panel()
	{
		return p1;
	}

	public ButtonGroup get(){
		return group;
	}
	
	
	
	public void show_ImputDialog() {

		otherTF = new JTextField(10);
		data = new JPanel(new GridLayout(3, 2));
		Done = new JButton("DONE");
		data.add( new JLabel("New Disease : "));
		data.add(otherTF);
		data.add(new JLabel(""));
		data.add(new JLabel(""));
		data.add(new JLabel(""));
		data.add(Done);
		Done.addActionListener(new handler());

		JOptionPane.showOptionDialog(this, data, "Adding new Disease", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, new JLabel[] { }, null);
		
		
	}
	
	
	public boolean CheckComponent(String s) {
		for (Component component : p1.getComponents()) {
			if (component instanceof JCheckBox) {
				if (s.equals(((JCheckBox) component).getText())) {
					My_JOptionPane.close_My_JOptionPane();
					return false;
				}
			}
		}
		return true;
	}
	
	

	public void ajouter() {
		String element = ajouter_element.getText();
		if (CheckComponent(element)) {
			p1.add(ajouter_element);
			p1.revalidate();
			p1.repaint();
			ajouter_element.setSelected(true);
			othor.setSelected(false);
		}
		else {
			JOptionPane.showMessageDialog(null, "look Carefully! the disease exists already");
			othor.setSelected(false);
			My_JOptionPane.close_My_JOptionPane();

		}

	}

	
	public class handler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == othor) {
				show_ImputDialog();

			}
			if (event.getSource() == Done) {
				ajouter_element = new JCheckBox(otherTF.getText());
				ajouter();
				My_JOptionPane.close_My_JOptionPane();

			}
		}

	}

	
	public  ArrayList<String> getData(int clientid){
		
		
		ArrayList<String> list = new ArrayList<>();
		list.add(clientid+"");
		
		for (Component component : p1.getComponents()) {
			
			if (component instanceof JCheckBox) {
				if (((JCheckBox) component).isSelected()){
					if(!((JCheckBox) component).getText().equals("No"))
					list.add(((JCheckBox) component).getText()); }
				
			}
			if (component instanceof JRadioButton){
			if(((JRadioButton) component).isSelected()){
				
				JOptionPane.showMessageDialog(null, "radio");
			}}
			
		}
		return list;
	}
		
	
}
