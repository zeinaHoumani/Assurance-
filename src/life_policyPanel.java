import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class life_policyPanel extends JFrame{
	private static final long serialVersionUID = 1L;
	
	
	private JTextField DateTF;
	private JTextField Insurrance_AmountTF;
	private JTextField Benificiary1LNTF;
	private JTextField Benificiary2LNTF;
	
	private JLabel DateLB;
	private JLabel Q1LB;
	private JLabel Q2LB;
	private JLabel Insurrance_AmountLB;
	private JLabel Benificiary1LNLB;
	private JLabel Benificiary2LNLB;
	private JLabel Term_lengthLB;
	
	private JRadioButton No;
	private JRadioButton Yes;
	private JRadioButton No2;
	private static JRadioButton Yes2;
	private ButtonGroup group;
	private ButtonGroup group1;
	
	private JPanel p2 ;
    private JPanel all;
	private JComboBox<String> TermCB;

	private JPopupMenu menu;
	private JMenuItem item;
	private JSlider sl;

	private String names[] = { "5 Year Term", "10 Year Term", "15 Year Term", "20 Year Term" };
	
	private GridBagConstraints con;
	
	
	
	public life_policyPanel(JPanel all,GridBagConstraints c){
		
		this.con=c;
	    this.all=all;		
	}
	
	
	public void Display(){
		
		 
	    menu=new JPopupMenu();
	    item=new JMenuItem("Current Date");
	    menu.add(item);
		
		DateLB = new My_JLabel("Date: ", SwingConstants.LEFT);
		DateTF = new JTextField(10);
		DateTF.setEnabled(false);
		
		
		DateTF.addMouseListener(new MyMouseListener());
        item.addActionListener(new handler());
		
		Insurrance_AmountLB = new My_JLabel("Insurrance_Amount: ", SwingConstants.LEFT);
		Insurrance_AmountTF = new JTextField("500 $");
		
		Term_lengthLB = new My_JLabel("Term_length: ", SwingConstants.LEFT);
		TermCB = new JComboBox<String>(names);
		
		Q1LB = new My_JLabel("Do you have a life Insurance Policy?", SwingConstants.LEFT);
		Q2LB = new My_JLabel("Do you want to replace this policy?", SwingConstants.LEFT);
		Benificiary1LNLB = new My_JLabel("Name of 1st beneficary", SwingConstants.LEFT);
		Benificiary2LNLB = new My_JLabel("Name of 2sd beneficary", SwingConstants.LEFT);
		Benificiary1LNTF= new JTextField(10);
		Benificiary2LNTF = new JTextField(10);
		
		
		JSlider slider = new JSlider(JSlider.HORIZONTAL,500, 10000, 2000); 
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(2000);  
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		
		
		JPanel s=new JPanel(new BorderLayout());
	    sl= new JSlider(500,10000);
		s.add(sl);
		s.add(Insurrance_AmountTF,BorderLayout.WEST);
	
		 sl.addChangeListener(new slider_handler());
		 Insurrance_AmountTF.addKeyListener(new TextField_handler());
		
		
		
		/*one radio button in a time*/
		Yes = new JRadioButton("Yes");
		No = new JRadioButton("No");
	    group1 = new ButtonGroup();
		group1.add(Yes);
		group1.add(No);
		
		Yes2 = new JRadioButton("Yes");
		No2 = new JRadioButton("No");
	    group = new ButtonGroup();
		group.add(Yes2);
		group.add(No2);
		
		p2 = new JPanel(new GridLayout(8, 3));
		p2.add(DateLB);
		p2.add(Insurrance_AmountLB);
		p2.add(Term_lengthLB);
		
		
		p2.add(DateTF);
		p2.add(s);
		p2.add(TermCB);
		
		p2.add(new JLabel("  "));p2.add(new JLabel("  "));p2.add(new JLabel("  "));
		
		p2.add(Benificiary1LNLB);p2.add(Benificiary1LNTF);p2.add(new JLabel("  "));
		p2.add(Benificiary2LNLB);p2.add(Benificiary2LNTF);p2.add(new JLabel("  "));
		
		p2.add(Q1LB);
		p2.add(Yes);
		p2.add(No);
		
		
		p2.add(Q2LB);
		p2.add(Yes2);
		p2.add(No2);
		
		Q2LB.setVisible(false);
		Yes2.setVisible(false);
		No2.setVisible(false);
		
		p2.setBorder(BorderFactory.createTitledBorder("Policy Info"));
		
		con.fill = GridBagConstraints.HORIZONTAL;
		
		con.weightx = con.weighty = 0.1;
		con.gridx = 0;
		con.gridy = 3;
		
		p2.setPreferredSize(new Dimension(1070, 220));
		all.add(p2,con);
		
		Yes.addActionListener(new handler_PolicyPanel());
		No.addActionListener(new handler_PolicyPanel());
	}
	
	
	private class handler_PolicyPanel implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		
			if (e.getSource() == Yes) {
				Q2LB.setVisible(true);
				Yes2.setVisible(true);
				No2.setVisible(true);
				p2.repaint();
			}
			if(e.getSource() == No){
				
				Q2LB.setVisible(false);
				Yes2.setVisible(false);
				No2.setVisible(false);
				p2.repaint();
				
			}

		}
	}
	
	public ButtonGroup get(){
		return group;
	}
	
	public ButtonGroup get1(){
		return group1;
	}
	public static boolean replace()
	{
		if(Yes2.isSelected())return true;
		return false;
	}
	
	
	
	public ArrayList<String> getData(int clientid){
		
	    ArrayList<String> list=new ArrayList<>();
	    list.add(clientid+"");
	    list.add("1");
		list.add(Insurrance_AmountTF.getText());
		list.add(TermCB.getSelectedItem().toString());
		list.add(DateTF.getText());
		list.add(Benificiary1LNTF.getText());
		list.add(Benificiary2LNTF.getText());
		
		ArrayList<String> App=My_JOptionPane.Applicant_Info();
		
				if(App==null){
					list.add("Insured Name");list.add("Insured Phone");
				}
				else{
					list.add(App.get(0));
					list.add(App.get(1));
				    }
		return list;
	}
	
	
	
	public Container get_panel()
	{
		return p2;
	}
	
	
	
	private class MyMouseListener implements MouseListener{
		@Override
		public void mouseReleased(MouseEvent ev) {
		    if(SwingUtilities.isRightMouseButton(ev)){
		        menu.show(ev.getComponent(), ev.getX(),ev.getY());
		    }
		}

		@Override
		public void mouseClicked(MouseEvent ev) {
			 if(SwingUtilities.isRightMouseButton(ev)){
			        menu.show(ev.getComponent(), ev.getX(),ev.getY());
			    }
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {	
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
		@Override
		public void mousePressed(MouseEvent e) {
		}


  }
	
	
	private class handler implements ActionListener{
		
		public void actionPerformed(ActionEvent event)
			{
				if(event.getSource()==item){ 
					
				    SimpleDateFormat df = new SimpleDateFormat("yyyy-M-dd");
					Date today = Calendar.getInstance().getTime();
					String reportDate = df.format(today);
					DateTF.setText(reportDate);
				}
			}
	}
	
	
	
	private class slider_handler implements ChangeListener
	{

		@Override
		public void stateChanged(ChangeEvent e) {
			String value=""+sl.getValue();
			if(e.getSource()==sl)
			{
			Insurrance_AmountTF.setText(value);
			}
			
		}
		
	}
	private class TextField_handler implements KeyListener
	{

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
		
			 String typed = Insurrance_AmountTF.getText();
			 
            sl.setValue(500);
             if (!typed.matches("\\d+")) {
                 return;
             }
             int value = Integer.parseInt(typed);
             sl.setValue(value);
					
		}

		
		
		
	}
	
	
}
