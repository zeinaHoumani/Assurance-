import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Car_insurance extends JFrame {
	private static final long serialVersionUID = 1L;

	private ImageIcon icon;
	private int licenceID;
	private int infoID;
	private Car car;
	private static ArrayList<String> list;
	private JPanel panel2;
	private JPanel panel3;
	private JTabbedPane tabbedPane;

	public Car_insurance() {

		setTitle(" Car Insurance Page");
		setSize(1220, 655);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setLayout(new BorderLayout());

		Display_TabbedPane();

	}

	public void Display_TabbedPane() {

		tabbedPane = new JTabbedPane();
		tabbedPane.addChangeListener(new changeListener());
		BufferedImage img;
		try {
			img = ImageIO.read(new File("Pictures\\1.gif"));

			icon = new ImageIcon(img);
		} catch (IOException e) {
			e.printStackTrace();
		}

		panel2 = new JPanel(new BorderLayout());

		car = new Car(panel2);

		tabbedPane.addTab("Car", icon, panel2);

		try {
			img = ImageIO.read(new File("Pictures\\2.png"));
			icon = new ImageIcon(img);
		} catch (IOException e) {
			e.printStackTrace();
		}

		panel3 = new JPanel(new GridLayout(1, 2));

		new Result(panel3);
		tabbedPane.addTab("Result", icon, panel3);

		add(tabbedPane);

	}

	private class changeListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {

			JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
			int index = sourceTabbedPane.getSelectedIndex();
			if (sourceTabbedPane.getTitleAt(index).equals("Result")) {

				if (DB_operation.CheckComponent(car.t()) == 1) {

					licenceID = DB_operation.insert_licence(car.fill_licence());
					infoID = DB_operation.insert_car(car.fill_car());

					JOptionPane.showMessageDialog(null, infoID);
					String Start = car.policy_start();

					JPanel q = new right_panel().right(infoID);

					Result.set(q);

					
					list = new ArrayList<>();
					list.add(licenceID + "");
					list.add(infoID + "");
					list.add(Start);

				} else {
					JOptionPane.showMessageDialog(null, "there is some empty fields", "Check your entries",
							JOptionPane.ERROR_MESSAGE);

				}

			}
		}
	}

	public static ArrayList<String> get_IDs() {
		return list;
	}


}
