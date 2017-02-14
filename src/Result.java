import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

public class Result extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel all;
	private Personal_Info personal_info;
	private JButton save;
	private JPanel p3;
	private JPanel info;
	private ArrayList<String> list;
	static JPanel test;

	public Result(JPanel all) {

		this.all = all;
		Show_left_Panel();

		test = new JPanel(new BorderLayout());
		test.add(new JLabel(""));

		all.add(test);
		add(all);

	}

	public static void set(JPanel a) {
		test.add(a);
		test.repaint();
		test.revalidate();
		
	}

	public void Show_left_Panel() {

		personal_info = new Personal_Info();
		info = personal_info.display_vertical();

		p3 = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;

		p3.add(info, c);

		save = new JButton("Done");
		save.addActionListener(new handler());

		c.gridx = 0;
		c.gridy = 1;

		p3.add(save, c);

		p3.setBorder(BorderFactory.createTitledBorder("Client Info"));

		all.add(p3);

	}

	private class handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == save) {

				if (DB_operation.CheckComponent(p3) == 1 && DB_operation.CheckComponent(info) == 1) {

					My_JOptionPane pane = new My_JOptionPane();
					int employeeid = pane.show_hosp_dialog();

					My_JOptionPane o = new My_JOptionPane();
					o.show_optionpane();

					int clientid = DB_operation.Insert_client_info(personal_info.getData_ver());
					list = new ArrayList<>();
					list.add(clientid + "");
					list.add(employeeid + "");
					list.add(3 + "");

					SimpleDateFormat df = new SimpleDateFormat("yyyy-M-dd");
					Date today = Calendar.getInstance().getTime();
					String reportDate = df.format(today);

					list.add(reportDate);

					ArrayList<String> a = Car_insurance.get_IDs();
					JOptionPane.showMessageDialog(null, a.get(0));

					list.add(a.get(0));
					list.add(a.get(1));
					list.add(a.get(2));

					 DB_operation.insert_policy_car(list);

				}
			}
		}
	}

}
