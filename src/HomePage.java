import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class HomePage extends JFrame {
	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar;
	private JMenu CreateMenu,  DisplayMenu, ExitMenu;
	private JMenuItem life, hospi, car, Exit, life_insurance, hospitalization_insurance, car_insurance;
	private JFrame frame;
	
	public HomePage(JFrame frame){
		this.frame=frame;
		DisplayGUI();
		Handlers();

	}

	public void DisplayGUI() {

		setTitle(" Home Page");
		setSize(1365, 728);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

		BackgroundPanel back = new BackgroundPanel();
		add(back);

		// create a menu bar

		menuBar = new JMenuBar();

		// create menus

		CreateMenu = new JMenu("Create");
		DisplayMenu = new JMenu("Extract");
		ExitMenu = new JMenu("Exit");

		// create menu items

		life = new JMenuItem("Life Inssurance Policy");
		life.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));

		hospi = new JMenuItem("Hospitalization insurance Policy");
		hospi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));

		car = new JMenuItem("Car insurance Policy");
		car.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));


		life_insurance = new JMenuItem("Life Inssurance Policy");
		hospitalization_insurance = new JMenuItem("Hospitalization insurance Policy");
		car_insurance = new JMenuItem("Car insurance Policy");
		
		Exit = new JMenuItem("Exit");
		Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

		// add menu items to menus

		CreateMenu.add(life);
		CreateMenu.add(hospi);
		CreateMenu.add(car);
		
		DisplayMenu.add(life_insurance);
		DisplayMenu.add(hospitalization_insurance);
		DisplayMenu.add(car_insurance);
		
		ExitMenu.add(Exit);

		// add menu to menubar

		menuBar.add(CreateMenu);
		menuBar.add(DisplayMenu);
		menuBar.add(ExitMenu);

		setJMenuBar(menuBar);

		this.addKeyListener(new My_keyHandler());

	}

	public void Handlers() {
		
		/** listeners for click events on Menu items **/
		Exit.addActionListener(new handler());
		car.addActionListener(new handler());
		hospi.addActionListener(new handler());
		life.addActionListener(new handler());
		life_insurance.addActionListener(new handler());
		car_insurance.addActionListener(new handler());
		hospitalization_insurance.addActionListener(new handler());
		
		
		/** Activate JMenuBar on hover **/
		for (Component c : menuBar.getComponents()) {
			if (c instanceof JMenu) {
				c.addMouseListener(new My_mouseListener());
			}
		}
	}

	private class My_mouseListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
			((JMenu) e.getSource()).doClick();
		}

	}

	private class My_keyHandler implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@SuppressWarnings("deprecation")
		@Override
		public void keyReleased(KeyEvent e) {
			if ((e.getKeyCode() == KeyEvent.VK_L) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
				new Life_Insurance();
			}
			if ((e.getKeyCode() == KeyEvent.VK_H) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
				new hosp_insurance();
			}
			if ((e.getKeyCode() == KeyEvent.VK_C) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
				new Car_insurance();
			}
			if ((e.getKeyCode() == KeyEvent.VK_X) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
				dispose();
				frame.show();
				
			}

		}
	}

	private class handler implements ActionListener {

		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == life) {
				new Life_Insurance();
			}

			if (event.getSource() == car) {
				new Car_insurance();
			}

			if (event.getSource() == hospi) {
				new hosp_insurance();
			}

			if (event.getSource() == Exit) {
				
				dispose();
				frame.show();

			}
			
			if(event.getSource() == life_insurance)
			{
				new Employee_PDF("1");
			}
			
			if(event.getSource() == car_insurance)
			{
				new Employee_PDF("3");
			}
			
			if(event.getSource() == hospitalization_insurance)
			{
				new Employee_PDF("2");
			}
		}

	}

}
