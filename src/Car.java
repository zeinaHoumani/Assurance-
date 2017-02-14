import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
/*
 * 
 */
public class Car extends JFrame implements ActionListener {

	private JLabel licenceType;
	private JLabel licenceNb;
	private JLabel licenceheldfor;
	private JLabel Q1;
	private JLabel Q2;
	private JLabel Q3;
	private JLabel Q4;
	private JLabel Q5;
	private JLabel Q6;
	private JLabel Q7;
	private JLabel Q8;
	private JLabel Q9;
	private String name;
	JPanel all;

	private JList<String> list;

	private JRadioButton No;
	private static JRadioButton Yes;
	private JRadioButton No2;
	private static JRadioButton Yes2;

	private JComboBox<String> licenceTypecmb;
	private String names[] = { "C Car", "LR Light rigid", "MR Medium rigid", "HR Heavy rigid" };
	private JComboBox<String> Mon;
	private String Month[] = { "January", "February", "March", "April", "MAy", "June", "July", "August", "September",
			"October", "November", "December" };
	private JComboBox<String> Day;
	private JComboBox<String> Year;
	private JComboBox<String> MakeVecomb;
	private JComboBox<String> ModelVecom;
	private JComboBox<String> Parkedcmb;
	private String Parked[] = { "in a garage ", "Under a carport", "on an (uncovered) driveway", "on the street",
			"SomeWhere else" };
	private JComboBox<String> securcmb;
	private String secur[] = { "No Alarm ", "Alarm" };

	private JTextField licenceNbTF;
	private JTextField yearsTF;
	private JTextField nameveTF;
	private JTextField price;

	private JPanel p;
	private JPanel p1;
	private JPanel licence;
	private JPanel border;

	private JCheckBox toggleDnD;

	public Car(JPanel border) {
		this.border = border;
		Show_Panel();

	}

	public void Show_Panel() {

		licenceType = new JLabel("LicenceType:", SwingConstants.LEFT);
		licenceNb = new JLabel("Licence Number:", SwingConstants.LEFT);
		licenceheldfor = new JLabel("Licence held for:", SwingConstants.CENTER);
		Q1 = new JLabel("when would you want your new Policy start? ", SwingConstants.LEFT);
		Q2 = new JLabel("Please Select the year of your vehicle:", SwingConstants.LEFT);
		Q3 = new JLabel("Please enter the name of your vehicle:", SwingConstants.LEFT);
		Q4 = new JLabel("Please Select the Model of your vehicle:", SwingConstants.LEFT);
		Q5 = new JLabel("when the vehicle parked", SwingConstants.LEFT);
		Q6 = new JLabel("Have you had any accidents int the Past 3 years", SwingConstants.LEFT);
		Q7 = new JLabel("Roughly ,how much is the  car currently worth?", SwingConstants.LEFT);
		Q8 = new JLabel("Security System", SwingConstants.LEFT);
		Q9 = new JLabel("Does the car have any modification?", SwingConstants.LEFT);

		licenceTypecmb = new JComboBox<String>(names);
		Day = new JComboBox<>();
		Year = new JComboBox<>();
		Mon = new JComboBox<String>(Month);
		securcmb = new JComboBox<String>(secur);
		MakeVecomb = new JComboBox<String>();
		ModelVecom = new JComboBox<>();
		Parkedcmb = new JComboBox<>(Parked);

		Yes = new JRadioButton("Yes");
		No = new JRadioButton("No");
		ButtonGroup group = new ButtonGroup();
		group.add(Yes);
		group.add(No);

		Yes2 = new JRadioButton("Yes");
		No2 = new JRadioButton("No");
		ButtonGroup group2 = new ButtonGroup();
		group.add(Yes);
		group.add(No);

		licenceNbTF = new JTextField(10);
		yearsTF = new JTextField("    / Years");
		nameveTF = new JTextField(10);
		nameveTF.addMouseListener(new Mouse());
		price = new JTextField("enter the value in $");

		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listModel.addElement("Porsche");
		listModel.addElement("BMW");
		listModel.addElement("Mercedes");
		listModel.addElement("Honda");
		listModel.addElement("Mazda");
		listModel.addElement("Opell");
		listModel.addElement("Nissane");
		toggleDnD = new JCheckBox("Turn on Drag and Drop");

		list = new JList<String>(listModel);

		list.setTransferHandler(new ListTransferHandler());
		list.setDropMode(DropMode.ON_OR_INSERT);

		JScrollPane listView = new JScrollPane(list);
		listView.setPreferredSize(new Dimension(300, 300));
		UndoManager manager = new UndoManager();

		licenceNbTF.getDocument().addUndoableEditListener(manager);

		JToolBar toolbar = new JToolBar();

		toolbar.add(UndoManagerHelper.getUndoAction(manager));
		toolbar.add(UndoManagerHelper.getRedoAction(manager));
		toolbar.setBackground(Color.white);
		all = new JPanel(new GridLayout(4, 1));

		licence = new JPanel(new GridLayout(3, 4));

		licence.add(licenceType);
		licence.add(licenceTypecmb);
		licence.add(licenceheldfor);
		licence.add(yearsTF);

		licence.add(new JLabel(""));
		licence.add(new JLabel(""));
		licence.add(new JLabel(""));
		licence.add(new JLabel(""));

		licence.add(licenceNb);
		licence.add(licenceNbTF);
		licence.add(new JLabel(""));
		licence.add(toolbar);
		licence.setBorder(BorderFactory.createTitledBorder("Licence Details "));

		calender();
		JPanel calender = new JPanel(new FlowLayout());

		calender.add(Q1);
		calender.add(Day);
		calender.add(Mon);
		calender.add(Year);
		calender.setBorder(BorderFactory.createTitledBorder("Policy Start "));

		JPanel vehicle = new JPanel(new GridLayout(3, 2));

		vehicle.add(Q3);
		vehicle.add(nameveTF);
		vehicle.add(Q2);
		vehicle.add(MakeVecomb);
		vehicle.add(Q4);
		vehicle.add(ModelVecom);

		vehicle.setBorder(BorderFactory.createTitledBorder("Vehicle Details "));

		JPanel listOfvehi = new JPanel(new GridLayout(2, 1));
		listOfvehi.add(listView);
		listOfvehi.add(toggleDnD);
		listOfvehi.setBorder(BorderFactory.createTitledBorder("List of vehicule "));

		JPanel listVeh = new JPanel(new GridLayout(1, 2));
		listVeh.add(vehicle);
		listVeh.add(listOfvehi);

		toggleDnD.setActionCommand("toggleDnD");
		toggleDnD.addActionListener(this);

		JPanel parked = new JPanel(new GridLayout(3, 2));

		parked.add(Q5);
		parked.add(Parkedcmb);

		parked.setBorder(BorderFactory.createTitledBorder("Vehicle Parked "));

		JPanel accident = new JPanel(new GridLayout(2, 2));
		accident.add(Q6);
		accident.add(new JLabel(""));
		accident.add(Yes);
		accident.add(No);

		accident.setBorder(BorderFactory.createTitledBorder("Accident "));

		JPanel modification = new JPanel(new GridLayout(2, 2));

		modification.add(Q9);
		modification.add(new JLabel(""));
		modification.add(Yes2);
		modification.add(No2);

		modification.setBorder(BorderFactory.createTitledBorder("Modification Car "));

		JPanel security = new JPanel(new GridLayout(3, 2));

		security.add(Q8);
		security.add(securcmb);

		security.setBorder(BorderFactory.createTitledBorder("Security Car "));

		JPanel money = new JPanel(new GridLayout(3, 4));

		money.add(Q7);
		money.add(price);

		money.setBorder(BorderFactory.createTitledBorder("Price "));

		JPanel lica = new JPanel(new GridLayout(1, 2));
		lica.add(licence);
		lica.add(calender);

		JPanel accmod = new JPanel(new GridLayout(1, 2));
		accmod.add(accident);
		accmod.add(modification);

		JPanel parsec = new JPanel(new GridLayout(1, 2));
		parsec.add(parked);
		parsec.add(security);

		// JPanel border=new JPanel(new BorderLayout());

		JLabel north = new JLabel("                   ");
		border.add(BorderLayout.NORTH, north);

		all.add(lica);
		all.add(listVeh);
		all.add(parsec);
		all.add(accmod);

		border.add(BorderLayout.CENTER, all);

		border.add(BorderLayout.SOUTH, new Moving_Car());

	}

	public void calender() {
		for (int i = 1; i < 30; i++) {
			String I = "" + i;
			Day.addItem(I);
		}
		for (int i = 2017; i < 2030; i++) {
			String I = "" + i;
			Year.addItem(I);
		}
		for (int i = 2014; i <= 2015; i++) {
			String I = "" + i;
			MakeVecomb.addItem(I);
		}

	}

	static class UndoManagerHelper {

		public static Action getUndoAction(UndoManager manager, String label) {
			return new UndoAction(manager, label);
		}

		public static Action getUndoAction(UndoManager manager) {
			return new UndoAction(manager, "Undo");
		}

		public static Action getRedoAction(UndoManager manager, String label) {
			return new RedoAction(manager, label);
		}

		public static Action getRedoAction(UndoManager manager) {
			return new RedoAction(manager, "Redo");
		}

		private abstract static class UndoRedoAction extends AbstractAction {
			UndoManager undoManager = new UndoManager();

			String errorMessage = "Cannot undo";

			String errorTitle = "Undo Problem";

			protected UndoRedoAction(UndoManager manager, String name) {
				super(name);
				undoManager = manager;
			}

			public void setErrorMessage(String newValue) {
				errorMessage = newValue;
			}

			public void setErrorTitle(String newValue) {
				errorTitle = newValue;
			}

			protected void showMessage(Object source) {
				if (source instanceof Component) {
					JOptionPane.showMessageDialog((Component) source, errorMessage, errorTitle,
							JOptionPane.WARNING_MESSAGE);
				} else {
					System.err.println(errorMessage);
				}
			}
		}

		public static class UndoAction extends UndoRedoAction {
			public UndoAction(UndoManager manager, String name) {
				super(manager, name);
				setErrorMessage("Cannot undo");
				setErrorTitle("Undo Problem");
			}

			public void actionPerformed(ActionEvent actionEvent) {
				try {
					undoManager.undo();
				} catch (CannotUndoException cannotUndoException) {
					showMessage(actionEvent.getSource());
				}
			}
		}

		public static class RedoAction extends UndoRedoAction {
			String errorMessage = "Cannot redo";

			String errorTitle = "Redo Problem";

			public RedoAction(UndoManager manager, String name) {
				super(manager, name);
				setErrorMessage("Cannot redo");
				setErrorTitle("Redo Problem");
			}

			public void actionPerformed(ActionEvent actionEvent) {
				try {
					undoManager.redo();
				} catch (CannotRedoException cannotRedoException) {
					showMessage(actionEvent.getSource());
				}
			}
		}

	}
	/*
	 * hfhfgggg
	 */
	private void displayDropLocation(final String string) {

		JOptionPane.showMessageDialog(null, string);

	}

	public void actionPerformed(ActionEvent e) {
		if ("toggleDnD".equals(e.getActionCommand())) {
			boolean toggle = toggleDnD.isSelected();

			nameveTF.setDragEnabled(toggle);
			list.setDragEnabled(toggle);

		}
	}

	class ListTransferHandler extends TransferHandler {

		public boolean canImport(TransferHandler.TransferSupport info) {
			// we only import Strings
			if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				return false;
			}

			JList.DropLocation dl = (JList.DropLocation) info.getDropLocation();
			if (dl.getIndex() == -1) {
				return false;
			}
			return true;
		}

		public boolean importData(TransferHandler.TransferSupport info) {
			if (!info.isDrop()) {
				return false;
			}

			// Check for String flavor
			if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				displayDropLocation("List doesn't accept a drop of this type.");
				return false;
			}

			JList.DropLocation dl = (JList.DropLocation) info.getDropLocation();
			DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();
			int index = dl.getIndex();
			boolean insert = dl.isInsert();
			// Get the current string under the drop.
			String value = listModel.getElementAt(index);

			// Get the string that is being dropped.
			Transferable t = info.getTransferable();

			String data;
			try {
				data = (String) t.getTransferData(DataFlavor.stringFlavor);

			} catch (Exception e) {
				return false;
			}

			// Display a dialog with the drop information.
			String dropValue = "\"" + data + "\" dropped ";
			if (insert) {
				if (dl.getIndex() == 0) {
					displayDropLocation(dropValue + "at beginning of list");
				} else if (dl.getIndex() >= list.getModel().getSize()) {
					displayDropLocation(dropValue + "at end of list");
				} else {
					String value1 = (String) list.getModel().getElementAt(dl.getIndex() - 1);
					String value2 = (String) list.getModel().getElementAt(dl.getIndex());
					displayDropLocation(dropValue + "between \"" + value1 + "\" and \"" + value2 + "\"");
				}
			} else {
				displayDropLocation(dropValue + "on top of " + "\"" + value + "\"");
			}

			if (insert) {
				listModel.add(index, data);
			} else {
				listModel.set(index, data);
			}
			return true;
		}

		public int getSourceActions(JComponent c) {
			return COPY;
		}

		protected Transferable createTransferable(JComponent c) {
			JList list = (JList) c;
			Object[] values = list.getSelectedValues();

			StringBuffer buff = new StringBuffer();

			for (int i = 0; i < values.length; i++) {
				Object val = values[i];
				buff.append(val == null ? "" : val.toString());
				if (i != values.length - 1) {
					buff.append("\n");
				}
			}
			return new StringSelection(buff.toString());
		}

	}

	private class Mouse implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			name = nameveTF.getText();
			fill_models();

		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

	}

	public void fill_models() {

		ResultSet rs = DB_operation.fill_models(name);

		try {
			ModelVecom.removeAllItems();
			while (rs.next()) {
				ModelVecom.addItem(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<String> fill_licence() {

		ArrayList<String> list = new ArrayList<>();
		list.add(licenceTypecmb.getSelectedItem().toString());
		list.add(licenceNbTF.getText());
		list.add(yearsTF.getText());

		return list;
	}

	
	public ArrayList<String> fill_car(){
		
		ArrayList<String> list = new ArrayList<>();
		
		list.add(ModelVecom.getSelectedItem().toString());
		
		list.add(Parkedcmb.getSelectedItem().toString());

		if (Yes.isSelected())
			list.add("yes");
		if (No.isSelected())
			list.add("no");

		list.add(securcmb.getSelectedItem().toString());

		if (Yes2.isSelected())
			list.add("yes");
		if (No2.isSelected())
			list.add("no");
		return list;
	}
	
	
	
	public String policy_start(){
		
		return new String (Day.getSelectedItem().toString() + "/" + Mon.getSelectedItem().toString() + "/"+ Year.getSelectedItem().toString());
	}
	
	public JPanel t(){
		return licence;
	}

	
}
