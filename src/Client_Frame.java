import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Client_Frame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel idLB;
	private JLabel nameLB;
	private JLabel ageLB;
	private JLabel phoneLB;
	private JLabel addressLB;

	private JPanel panel;
	private JPanel logo;
	private JLabel details;

	private String s;
	private JTable A;
	private JScrollPane scrollPane;

	private ArrayList<String> list;
	private BufferedImage image;

	public Client_Frame(String s) {
		this.s = s;

		this.addWindowListener(new MyWindowAdapter());

		DisplayGUI();

	}

	public void DisplayGUI() {
		setTitle("Client Form");
		setSize(1000, 690);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setBackground(Color.orange);

		JPanel all = new JPanel();
		all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));

		/** Create Labels **/
		idLB = new JLabel("Client ID: ", SwingConstants.LEFT);
		nameLB = new JLabel("Name: ", SwingConstants.LEFT);
		ageLB = new JLabel("Age: ", SwingConstants.CENTER);
		addressLB = new JLabel("Address: ", SwingConstants.LEFT);
		phoneLB = new JLabel("Phone: ", SwingConstants.CENTER);

		idLB.setFont(new Font("Calibri", Font.BOLD, 17));
		nameLB.setFont(new Font("Calibri", Font.BOLD, 17));
		ageLB.setFont(new Font("Calibri", Font.BOLD, 17));
		addressLB.setFont(new Font("Calibri", Font.BOLD, 17));
		phoneLB.setFont(new Font("Calibri", Font.BOLD, 17));

		/** Design The Header Of the Frame **/
		panel = new JPanel(new GridLayout(5, 2));
		panel.add(idLB);
		panel.add(new JLabel("  "));
		panel.add(new JLabel("  "));
		panel.add(new JLabel("  "));
		panel.add(nameLB);
		panel.add(ageLB);
		panel.add(new JLabel("  "));
		panel.add(new JLabel("  "));
		panel.add(addressLB);
		panel.add(phoneLB);
		panel.setPreferredSize(new Dimension(500, 90));

		/* ### add Header to the Frame ### */
		JPanel x = new JPanel(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		/** our logo in Mylogo class **/
		logo = new Mylogo(this.getWidth());

		d.gridx = 0;
		d.gridy = 0;
		d.weightx = d.weighty = 0;
		d.fill = GridBagConstraints.HORIZONTAL;

		logo.setPreferredSize(new Dimension(900, 120));
		x.add(logo, d);

		d.gridx = 0;
		d.gridy = 1;
		d.weightx = d.weighty = 0.5;

		d.fill = GridBagConstraints.HORIZONTAL;
		x.add(panel, d);
		x.setPreferredSize(new Dimension(500, 30));

		panel.setBorder(BorderFactory.createLoweredSoftBevelBorder());

		all.add(x);

		/** Show the policies **/

		JPanel life = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = c.weighty = 0;
		life.add(title_report("Life Insurance:"), c);

		c.gridx = 0;
		c.gridy = 1;
		c.weightx = c.weighty = 0.1;
		life.add(get_policies("1", s), c);

		c.gridx = 0;
		c.gridy = 2;
		c.weightx = c.weighty = 0;
		life.add(title_report("Hospitalization Insurance:"), c);

		c.gridx = 0;
		c.gridy = 3;
		c.weightx = c.weighty = 0.1;
		life.add(get_policies("2", s), c);

		c.gridx = 0;
		c.gridy = 4;
		c.weightx = c.weighty = 0;
		life.add(title_report("Car Insurance:"), c);

		c.gridx = 0;
		c.gridy = 5;
		c.weightx = c.weighty = 0.1;
		life.add(get_policies("3", s), c);

		life.setPreferredSize(new Dimension(800, 250));
		all.add(life);

		add(all);

	}

	/** Fill all the tables for every insurance type **/
	public JScrollPane get_policies(String type, String id) {

		A = new JTable(My_tableModel.resultSetToTableModel(DB_operation.fill_client_table(id, type)));
		A.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		A.setGridColor(Color.LIGHT_GRAY);
		A.setShowHorizontalLines(false);
		A.getTableHeader().setReorderingAllowed(false);
		scrollPane = new JScrollPane(A);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 50, 150);
		scrollPane.setPreferredSize(new Dimension(800, 40));

		return scrollPane;

	}

	/** Return a Panel That Contains the title and an icon **/
	public JPanel title_report(String title) {

		JLabel titre = new My_JLabel(title, new Color(5, 180, 206));
		details = new JLabel();
		try {
			image = ImageIO.read(new File("Pictures\\rep.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ImageIcon clienticon = new ImageIcon(image);
		details.setIcon(clienticon);
		details.setToolTipText("Click here to get more details");
		details.addMouseListener(new My_Click());

		JPanel m = new JPanel(new FlowLayout());
		m.add(titre);
		m.add(details);

		return m;

	}

	private class My_Click implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
		}

		public void mouseEntered(MouseEvent arg0) {
		}

		public void mouseExited(MouseEvent arg0) {
		}

		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {

			new PDF_Report(s);

		}

	}

	/** Fill Client's Details in the labels of the Header **/
	public void fill(String id, String n, String address, String phone, String age) {
		idLB.setText(idLB.getText().concat(id));
		nameLB.setText(nameLB.getText().concat(n));
		ageLB.setText(ageLB.getText().concat(age));
		addressLB.setText(addressLB.getText().concat(address));
		phoneLB.setText(phoneLB.getText().concat(phone));

	}

	/** When the Window open the Header is Updated **/
	private class MyWindowAdapter extends WindowAdapter {
		@Override
		public void windowOpened(WindowEvent e) {
			list = DB_operation.client_info(s);
			fill(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4));
		}

	}
}
