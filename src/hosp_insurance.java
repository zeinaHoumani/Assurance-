import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.*;

public class hosp_insurance extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel all;
	private JPanel p;
	private GridBagConstraints c;
	private String name[] = { "Policy ID", "Client ID", "Client Name" };
	private BufferedImage img;
	private JPanel test;
	private JButton reload;
	private JTextField searchTF;
	private JTextField AmountTF;
	private JLabel Hosp_forLB;
	private JLabel AmountLB;
	private JComboBox<String> Hosp_TypeCB;
	private String names[] = { "Guaranteed accident", "circulation accident", "public transport" };
	private JPanel comb;
	private Personal_Info info;
	private JTable A;
	private JComboBox<String> selectCB;

	private JButton AddB;
	private JButton ModifyB;
	private JButton DeleteB;
	private JButton SearchB;
	private JButton ResetB;
	private JButton ExitB;
	private JPanel p1;
	private JPanel q;
	
	int clientid=0;
	int policyid=0;

	public hosp_insurance() {

		setTitle("hosp_insurance Page");
		setSize(1220,655);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		all = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();

		dispaly_left_panel();
		display_right_panel();
		Buttons_Panel();
	

	}

	public void display_right_panel() {

		/*
		 * fill the Jtable using the resultSetToTableModel function in the
		 * package rs2xml. The Problem we faced : By default the isCellEditable
		 * function in any tablemodel is true , but in ower project we don't
		 * want the user to edit the table directly so we create a sub class
		 * My_tableModel from the super class DbUtilies and we override the
		 * function resultSetToTableModel(ResultSet rs) in such a way that the
		 * defaultModel used in it has the isCellEditable false
		 */

		A = new JTable(My_tableModel.resultSetToTableModel(DB_operation.fill_table()));
		A.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		A.setGridColor(Color.LIGHT_GRAY);
		A.setShowHorizontalLines(false);
		A.getTableHeader().setReorderingAllowed(false);
		A.addMouseListener(new Mouse_adapter());

		JScrollPane scrollPane = new JScrollPane(A);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 400, 50);

		scrollPane.setPreferredSize(new Dimension(550, 400));

		JPanel top = new JPanel(new GridLayout(3, 4));
		JLabel selectLB = new My_JLabel("Select:  ", SwingConstants.LEFT);
		JLabel searchLB = new My_JLabel("Search:  ", SwingConstants.LEFT);
		searchTF = new My_JTextField(10);
		searchTF.setBorder(BorderFactory.createLoweredBevelBorder());
		searchTF.addKeyListener(new My_keyHandler());
		selectCB = new JComboBox<>(name);
		selectCB.addActionListener(new table_Panel_handler());
		reload = new JButton("Reload");
		reload.setBackground(Color.white);
		reload.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		reload.addActionListener(new table_Panel_handler());

		try {
			img = ImageIO.read(new File("Pictures\\reload.jpg"));
			reload.setIcon(new ImageIcon(img));

		} catch (Exception ex) {
			System.out.println(ex);
		}

		top.add(selectLB);
		top.add(selectCB);
		top.add(new JLabel(""));
		top.add(new JLabel(""));

		top.add(new JLabel(""));
		top.add(new JLabel(""));
		top.add(new JLabel(""));
		top.add(new JLabel(""));

		top.add(searchLB);
		top.add(searchTF);
		top.add(new JLabel(""));
		top.add(reload);

		top.setPreferredSize(new Dimension(470, 75));

		p = new JPanel(new GridBagLayout());
		GridBagConstraints co = new GridBagConstraints();

		co.weighty = co.weightx = 2;

		co.gridx = 0;
		co.gridy = 0;

		p.add(top, co);

		co.weighty = 1;
		co.gridx = 0;
		co.gridy = 1;

		p.add(scrollPane, co);

		p.setPreferredSize(new Dimension(610, 520));
		p.setBorder(BorderFactory.createEtchedBorder());
		c.weightx = c.weighty = 1;
		c.gridx = 1;
		c.gridy = 0;

		all.add(p, c);
		all.setBorder(BorderFactory.createEtchedBorder());
	}

	public void dispaly_left_panel() {

		GridBagConstraints q1 = new GridBagConstraints();
		info = new Personal_Info();

		test = info.display_vertical();
		q1.gridx = 0;
		q1.gridy = 0;

	    q = new JPanel(new BorderLayout());
		q.add(test, BorderLayout.CENTER);
		q.setPreferredSize(new Dimension(520, 520));
		q.setBorder(BorderFactory.createEtchedBorder());

		q.add(Show_Panel(), BorderLayout.SOUTH);

		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.EAST;
		all.add(q, c);
	}

	 
	/** the Hospitalisation type Panel **/
	@SuppressWarnings("deprecation")
	public JPanel Show_Panel() {

		Hosp_forLB = new My_JLabel("Hospitalization for:", SwingConstants.CENTER);
		AmountLB = new My_JLabel("Amount", SwingConstants.CENTER);
		Hosp_TypeCB = new JComboBox<String>(names);
		AmountTF = new My_JTextField(10);
		AmountTF.enable(false);
		AmountTF.setText("75$");
		
		comb = new JPanel(new GridLayout(3, 2));
		comb.add(Hosp_forLB);
		comb.add(Hosp_TypeCB);
		comb.add(new JLabel("  "));
		comb.add(new JLabel("  "));
		comb.add(AmountLB);
		comb.add(AmountTF);

		
		Hosp_TypeCB.addActionListener(new combo_handler());
		comb.setPreferredSize(new Dimension(50, 70));
		return comb;

	}

	public void Buttons_Panel() {

		AddB = new JButton("Add");
		AddB.setBackground(Color.white);
		AddB.setToolTipText("Add new policy");
		ModifyB = new JButton("Modify");
		ModifyB.setBackground(Color.white);
		ModifyB.setToolTipText("update a policy");
		DeleteB = new JButton("Delete");
		DeleteB.setBackground(Color.white);
		DeleteB.setToolTipText("Delete a policy");
		SearchB = new JButton("Search");
		SearchB.setBackground(Color.white);
		SearchB.setToolTipText("Search a policy");
		ResetB = new JButton("Reset");
		ResetB.setBackground(Color.white);
		ResetB.setToolTipText("Reset the frame");
		ExitB = new JButton("Exit");
		ExitB.setBackground(Color.white);
		ExitB.setToolTipText("Exit");

		AddB.setPreferredSize(new Dimension(100, 30));
		ModifyB.setPreferredSize(new Dimension(100, 30));
		DeleteB.setPreferredSize(new Dimension(100, 30));
		SearchB.setPreferredSize(new Dimension(105, 30));
		ResetB.setPreferredSize(new Dimension(100, 30));
		ExitB.setPreferredSize(new Dimension(100, 30));

		try {
			img = ImageIO.read(new File("Pictures\\add.png"));
			AddB.setIcon(new ImageIcon(img));

			img = ImageIO.read(new File("Pictures\\modify.png"));
			ModifyB.setIcon(new ImageIcon(img));

			img = ImageIO.read(new File("Pictures\\delete1.png"));
			DeleteB.setIcon(new ImageIcon(img));

			img = ImageIO.read(new File("Pictures\\search.png"));
			SearchB.setIcon(new ImageIcon(img));

			img = ImageIO.read(new File("Pictures\\reset.png"));
			ResetB.setIcon(new ImageIcon(img));

			img = ImageIO.read(new File("Pictures\\exit.png"));
			ExitB.setIcon(new ImageIcon(img));

			p1 = new JPanel();

			p1.add(AddB);
			p1.add(ModifyB);
			p1.add(DeleteB);
			p1.add(SearchB);
			p1.add(ResetB);
			p1.add(ExitB);
			p1.setBorder(BorderFactory.createEtchedBorder());


			c.gridx = 1;
			c.gridy = 1;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.0;
			c.weighty = 0.5;

			all.add(p1, c);
			add(all);

			SearchB.addActionListener(new Button_handler());
			DeleteB.addActionListener(new Button_handler());
			AddB.addActionListener(new Button_handler());
			ModifyB.addActionListener(new Button_handler());
			ResetB.addActionListener(new Button_handler());
			ExitB.addActionListener(new Button_handler());
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/** Handler of the hospitalisation combobox **/
	private class combo_handler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == Hosp_TypeCB) {
				String t1 = "Guaranteed accident";
				String t2 = "circulation accident";
				String t3 = "public transport";
				String i = "75 $";
				String j = "150 $";
				String k = "225 $";
				if (t1.equals(Hosp_TypeCB.getSelectedItem())) {
					AmountTF.setText(i);
				}
				if (t2.equals(Hosp_TypeCB.getSelectedItem())) {
					AmountTF.setText(j);
				}
				if (t3.equals(Hosp_TypeCB.getSelectedItem())) {
					AmountTF.setText(k);
				}
			}

		}

	}
	
	
	public void Reload_right_panel(){
		
		A.setModel(My_tableModel.resultSetToTableModel(DB_operation.fill_table()));
		searchTF.setText("");
	}
	
	
	public void Reload_left_panel(JPanel p){
		
		Component[] components = p.getComponents();
		for (Component component : components) {
			
			if (component instanceof JTextField ) {
				((JTextField) component).setText(""); }
			
			if (component instanceof JComboBox<?> ) {
				((JComboBox<?>) component).setSelectedIndex(0);}
			
			if(component instanceof JPanel){ //clear the components for every single panel in the frame
				Reload_left_panel((JPanel)component);
			}
		}
			
	}
	

	/** Handler **/
	private class table_Panel_handler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == reload) {
				Reload_right_panel();
			}

			if (e.getSource() == selectCB) {

				if (!searchTF.getText().equals("")) {

					searchTF.setText("");
					A.setModel(My_tableModel.resultSetToTableModel(DB_operation.fill_table()));

				}
			}
		}
	}

	/** each key pressed in the search textfield run a query **/
	private class My_keyHandler implements KeyListener {

		@Override
		public void keyReleased(KeyEvent e) {

			if (e.getSource() == searchTF) {

				if (selectCB.getSelectedItem().equals("Policy ID")) {

					if (backspace(e) == 2) {
						try {
							Integer.parseInt(searchTF.getText());
							update_table(DB_operation.Search_Policy(Integer.parseInt(searchTF.getText())));
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "a number is expected!");
						}
					}
				}

				if (selectCB.getSelectedItem().equals("Client ID")) {

					if (backspace(e) == 2) {
						try {
							Integer.parseInt(searchTF.getText());
							update_table(DB_operation.Search_Client(Integer.parseInt(searchTF.getText())));
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "a number is expected!");
						}
					}
				}

				if (selectCB.getSelectedItem().equals("Client Name")) {
					if (backspace(e) == 2) {
						try {
							Integer.parseInt(searchTF.getText());
							JOptionPane.showMessageDialog(null, "a Name is expected!");
						} catch (NumberFormatException ex) {
							update_table(DB_operation.search_Client_Name(searchTF.getText()));
						}

					}
				}

			}

		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
		}

	}

	/** 1:Reaload 2: Update **/
	public int backspace(KeyEvent e) {

		if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE || e.getKeyChar() == KeyEvent.VK_DELETE) {
			if (searchTF.getText().length() == 0) {
				A.setModel(My_tableModel.resultSetToTableModel(DB_operation.fill_table()));
				return 1;
			}
		}
		return 2;
	}

	/** Query has a result Or not **/
	public void update_table(ResultSet rs) {
		if (rs == null) {
			JOptionPane.showMessageDialog(null, "No data !", "error", JOptionPane.ERROR_MESSAGE);
		} else
			A.setModel(My_tableModel.resultSetToTableModel(rs));
	}

	
	
	/**
	 * when a Row in the table is selected all the data are dispalyed in the
	 * textfields
	 **/
	private class Mouse_adapter implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			final JTable target = (JTable) e.getSource();
			final int row = target.getSelectedRow();
			ArrayList<String> list = new ArrayList<>();

			list.add((String) target.getValueAt(row, 8));
			list.add((String) target.getValueAt(row, 9));
			list.add((String) target.getValueAt(row, 10));
			list.add((String) target.getValueAt(row, 11));
			list.add((String) target.getValueAt(row, 16));
			list.add((String) target.getValueAt(row, 17));
			list.add((String) target.getValueAt(row, 15));
			list.add((String) target.getValueAt(row, 13));
			list.add((String) target.getValueAt(row, 12));
			list.add((String) target.getValueAt(row, 14));

			clientid=(int) target.getValueAt(row, 7);
			policyid=(int) target.getValueAt(row, 0);
			
			
			info.fill_data(list);
			fill_data((String) target.getValueAt(row, 2),(String) target.getValueAt(row, 3));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent ev) {
		}
	}

	
	
	private class Button_handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String rep = "";
			
			if (e.getSource() == SearchB) {
				
					rep = JOptionPane.showInputDialog("Policy Number: ");
					
				if(rep!=null)
				{
					try {
						Integer.parseInt(rep);
						
						ArrayList<String> list = DB_operation.fill_fields(Integer.parseInt(rep));
						
						if(list.size()!=0){
						info.fill_data(list);
						Hosp_TypeCB.setSelectedItem(list.get(list.size() - 2));
						}
						else{
							JOptionPane.showMessageDialog(null, "WRONG number!!");}
						
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "a number is expected!");
					}
				
				}
			}
			
			if(e.getSource()==ResetB){
				Reload_left_panel(q);
				Reload_right_panel();
			}
			
			if(e.getSource()==DeleteB){
				
				rep = JOptionPane.showInputDialog("Policy Number: ");
				
				if(rep!=null)
				{
					try {
						int x=Integer.parseInt(rep);
						if(DB_operation.Delete_policy_id(x)){
							JOptionPane.showConfirmDialog(null, "Record deleted successfully", "success",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
						    Reload_left_panel(q);
						
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "a number is expected!");
					}
				}
			}
			
			if(e.getSource()==ModifyB){
				
				ArrayList<String> list=info.getData_ver();
				list.add(clientid+"");
				
				ArrayList<String> list1=getData();
				list1.add(policyid+"");
				
				int[]updated=info.get_table();
				
				//if correcte entry
				
				DB_operation.update_policy(list,list1,updated);
				
				JOptionPane.showConfirmDialog(null, "Record updated successfully", "success",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
				 
			}
			
			if(e.getSource()==AddB){
				
				if(DB_operation.CheckComponent(test)==1){
					
				My_JOptionPane o= new My_JOptionPane();
				
				int employeeid=o.show_hosp_dialog();
				
				int clientid=DB_operation.Insert_client_info(info.getData_ver());
				ArrayList<String> list=new ArrayList<>();
				list.add(clientid+"");
				list.add(employeeid+"");
				list.add(2+"");
				list.add(AmountTF.getText());
				
				 SimpleDateFormat df = new SimpleDateFormat("yyyy-M-dd");
					Date today = Calendar.getInstance().getTime();
					String reportDate = df.format(today);
				
				list.add(reportDate);
				list.add(Hosp_TypeCB.getSelectedItem().toString());
				
				DB_operation.insert_hosp_policy(list);
				}
				}
			
			if(e.getSource()==ExitB){
				dispose();
			}
			}
		
		}
	
	public ArrayList<String> getData() {

		ArrayList<String> list = new ArrayList<>();
		list.add(Hosp_TypeCB.getSelectedItem().toString());
		list.add(AmountTF.getText());
		return list;
	}
	
	
public void fill_data(String x, String y){
		Hosp_TypeCB.setSelectedItem(x);
		AmountTF.setText(y);
	}
	
}
