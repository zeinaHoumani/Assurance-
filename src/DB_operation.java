import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DB_operation {

	private static ResultSet rs = null;

	static DBManagement d1 = new DBManagement();
	
	
	
	/*_______________________________________________________static functions_____________________________________________________________*/

	/*
	 * ### check all the components in a panel to verify if there is an empty
	 * fields ###
	 */
	
	public static int CheckComponent(Container p) {

		Component[] components = p.getComponents();
		for (Component component : components) {

			if (component instanceof JTextField) {
				if (((JTextField) component).getText().equals("")) {
					JOptionPane.showMessageDialog(null, "there is some empty fields", "Check your entries",JOptionPane.ERROR_MESSAGE);
					component.requestFocus(); 
					return 0;
				}
			}
			
		}
		return 1;
	}

	/* ### enable/disable all components in a container ### */
	public static void Enable_Components(Container p, boolean enable) {

		for (Component component : p.getComponents()) {
			component.setEnabled(enable);

			if (component instanceof Container) {
				Enable_Components((Container) component, enable);
			}
		}
	}
	
	
	
	/*###  Function for resizing the images ###*/
	public static Image scaledImage(Image img,int w,int h)
	{
		BufferedImage resizedImage=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2=resizedImage.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(img, 0, 0, w, h, null);
		g2.dispose();
		
		return resizedImage;
		
	}
	

/*______________________________________________________Database_____________________________________________________________________________*/	
	
	
	
	public static ArrayList<String> employee_info(String id) {
		int x = (Integer.parseInt(id));
		d1.connect();

		rs = d1.query(
				"Select employee.firstname,employee.lastname,employee.phone from employee where employee.employeeID="
						+ x + ";");
		ArrayList<String> list = new ArrayList<>();

		try {

			while (rs.next()) {

				list.add(rs.getString(1) + " " + rs.getString(2));
				list.add(rs.getString(3));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		d1.disconnect();
		return list;
	}

	public static ArrayList<String> client_info(String s) {
		int x = (Integer.parseInt(s));
		d1.connect();

		rs = d1.query("Select * from clients where clients.clientID=" + x + ";");
		ArrayList<String> list = new ArrayList<>();

		try {

			while (rs.next()) {

				list.add(rs.getInt(1) + "");
				list.add(rs.getString(2) + " " + rs.getString(3));
				list.add(rs.getString(6));
				list.add(rs.getString(8));
				list.add(rs.getString(10));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		d1.disconnect();
		return list;
	}

	public static int Number_policy() {
		int max = 0;
		d1.connect();
		String query = "Select MAX(policyID) from policy";
		rs = d1.query(query);

		try {
			rs.next();
			max = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return max;
	}

	public static int Insert_client_info(ArrayList<String> list) {

		int clientid = 0;
		d1.connect();
		/* if the client exist already */
		String query1 = "Select clientID from clients where clients.firstname='" + list.get(0)
				+ "' and clients.lastname='" + list.get(1) + "' and clients.phone='" + list.get(6) + "'";
		rs = d1.query(query1);

		try {
			if (rs.next()) {
				clientid = rs.getInt(1);
			} else {
				String query = "insert into clients (firstname,lastname,fathername,mothername,address,job,phone,state,age,birthplace)  values (?,?,?,?,?,?,?,?,?,?)";
				rs = d1.Parameteredquery(query, list);
				rs.next();
				clientid = rs.getInt(1);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		d1.disconnect();
		//JOptionPane.showMessageDialog(null, "id:" + clientid);
		return clientid;

	}

	public static void Delete_policy(int clientid) {

		ArrayList<String> list = new ArrayList<>();
		list.add(clientid + "");
		list.add("1");
		String query = "Delete from policy where policy.clientID=? and policy.type=?";
		d1.connect();
		rs = d1.Parameteredquery(query, list);

		try {
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void insert_policy(ArrayList<String> list) {

		int policyid = 0;
		String query = "insert into policy (clientID,employeeID,type,amount,term,date,first_Benef,Second_Benef,Applicant_name,Applicant_phone)  values (?,?,?,?,?,?,?,?,?,?)";
		d1.connect();
		rs = d1.Parameteredquery(query, list);

		try {
			rs.next();
			policyid = rs.getInt(1);
			JOptionPane.showMessageDialog(null, "Policy Number: " + policyid +"added successfully ");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		d1.disconnect();

	}

	public static void insert_disease(ArrayList<String> list) {

		String query = "Insert into client_disease(clientID,disease) values (?,?)";
		if (list.size() > 1) {
			d1.connect();

			for (int i = 1; i < list.size(); i++) {
				ArrayList<String> arr = new ArrayList<>();
				arr.add(list.get(0));
				arr.add(list.get(i));
				rs = d1.Parameteredquery(query, arr);
				arr.clear();
			}

			d1.disconnect();
		}

	}

	public static ResultSet fill_table() {

		String query = "Select policy.policyID,policy.date,policy.hosp_type,policy.amount,employee.employeeID,employee.firstname,employee.lastname, "
				+ "clients.clientID,clients.firstname,clients.lastname,clients.fathername,clients.mothername,clients.address,clients.job"
				+ ",clients.phone,clients.state,clients.age,clients.birthplace"
				+ " from clients , policy , employee"
				+ " where policy.clientID=clients.clientID and employee.employeeID = policy.employeeID and policy.type=2";

		d1.connect();
		rs = d1.query(query);
		return rs;

	}

	
	
	/*
	 * we replaced this function with the built in function in the package
	 * rs2xml
	 */

	public DefaultTableModel fill_no_rs2xml(DefaultTableModel model) {

		String query = "Select policy.policyID,policy.date,employee.employeeID,employee.firstname,employee.lastname, "
				+ "clients.clientID,clients.firstname,clients.lastname,clients.fathername,clients.mothername,clients.address,clients.job"
				+ "clients.phone,clients.state,clients.age,clients.birthplace"
				+ " from clients , policy , employee"
				+ " where policy.clientID=clients.clientID and employee.employeeID = policy.employeeID ";

		d1.connect();
		rs = d1.query(query);

		Object columnNames[] = { "PolicyID", "Date", "ClientID", "Name", "address", "employeeID", "Employee Name" };
		model.addColumn(columnNames);

		try {
			while (rs.next()) {
				String name = rs.getString(4) + " " + rs.getString(5);
				String employee = rs.getString(8) + " " + rs.getString(9);
				JOptionPane.showMessageDialog(null, name + "///" + employee);
				model.addRow(new Object[] { rs.getInt(1), rs.getString(2), rs.getInt(3), name, rs.getString(6),
						rs.getInt(7), employee });
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error encountred while selecting data from the database");
		}
      
		return model;

	}
	
	public static ResultSet Search_Policy(int id){
		
		
		String query="Select policy.policyID,policy.date,policy.hosp_type,policy.amount,employee.employeeID,employee.firstname,employee.lastname, "
				+ "clients.clientID,clients.firstname,clients.lastname,clients.fathername,clients.mothername,clients.address,clients.job"
				+ ",clients.phone,clients.state,clients.age,clients.birthplace"
				+ " from clients , policy , employee"
				+ " where policy.clientID=clients.clientID and employee.employeeID = policy.employeeID and policy.type=2 and policy.policyID LIKE '"+id+"%'";
		
		
		d1.connect();
		rs = d1.query(query);
		try {
			if(rs.next()) {
				rs.beforeFirst();
				return rs;}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error encountred while selecting data from the database");
			}
		
		   return null;
		
	}
	
	
public static ResultSet Search_Client(int id){
		
	
	String query="Select policy.policyID,policy.date,policy.hosp_type,policy.amount,employee.employeeID,employee.firstname,employee.lastname, "
			+ "clients.clientID,clients.firstname,clients.lastname,clients.fathername,clients.mothername,clients.address,clients.job"
			+ ",clients.phone,clients.state,clients.age,clients.birthplace"
			+ " from clients , policy , employee"
			+ " where policy.clientID=clients.clientID and employee.employeeID = policy.employeeID and policy.type=2 and  clients.clientID LIKE '"+id+"%'";
	
	
		d1.connect();
		rs = d1.query(query);
		try {
			if(rs.next()) {
				rs.beforeFirst();
				return rs;}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error encountred while selecting data from the database");
		}
		   return null;
		
	}

public static ResultSet search_Client_Name(String c){
	
	String query="Select policy.policyID,policy.date,policy.hosp_type,policy.amount,employee.employeeID,employee.firstname,employee.lastname, "
			+ "clients.clientID,clients.firstname,clients.lastname,clients.fathername,clients.mothername,clients.address,clients.job"
			+ ",clients.phone,clients.state,clients.age,clients.birthplace"
			+ " from clients , policy , employee"
			+ " where policy.clientID=clients.clientID and employee.employeeID = policy.employeeID and policy.type=2 and  clients.firstname LIKE '"+c+"%'";
	
	

		d1.connect();
		rs = d1.query(query);
		try {
			if(rs.next()) {
				rs.beforeFirst();
				return rs;}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error encountred while selecting data from the database");
			}
		
		   return null;
}
	
	

public static ArrayList<String> fill_fields(int id){
	
	ArrayList<String>list=new ArrayList<>();
	String query="Select clients.firstname,clients.lastname,clients.fathername,clients.mothername,clients.age,clients.birthplace,"
			+ "clients.state,clients.job,clients.address,"+
             "clients.phone,policy.hosp_type,policy.amount"
             + " from clients , policy"
             + " where policy.clientID=clients.clientID and policy.policyID="+id;
		
		d1.connect();
		rs = d1.query(query);
		try {
			while(rs.next())
			{
				list.add(rs.getString(1));
				list.add(rs.getString(2));list.add(rs.getString(3));list.add(rs.getString(4));list.add(rs.getString(5));list.add(rs.getString(6));
				list.add(rs.getString(7));list.add(rs.getString(8));list.add(rs.getString(9));
				list.add(rs.getString(10));list.add(rs.getString(11));list.add(rs.getString(12));
			}
			
			return list;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error encountred while selecting data from the database");
			}
		d1.disconnect();
		return null;		   
}


public static boolean Delete_policy_id(int id){
	
	String query = "Delete from policy where policy.policyID=?";
	ArrayList<String> list=new ArrayList<>();
	list.add(id+"");
	d1.connect();
	rs = d1.Parameteredquery(query, list);

	try {
		rs.next();
		return true;
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, "Error encountred while deleting data from the database");
	}
	d1.disconnect();
	return false;

}


public static void update_policy(ArrayList<String>list,ArrayList<String>list1,int[]updated){
	
/*	String query="Update clients set clients.firstname=? , clients.lastname=? ,clients.fathername=?,"
			+ " mothername=?, address=? , job=? , phone=? , state=?, age=?, birthplace=? where clients.clientID=?";
	
	*/
	String query1="Update clients set ";
	int size=-1;
	ArrayList<String>list2= new ArrayList<>();
	for(int i=0;i<updated.length;i++){
		
		if(updated[i]>0){
			size++;
			//System.out.println(size);
		}
	}
		
	    if(updated[0]>0){query1 += "clients.firstname=?";list2.add(list.get(0)); if(size!=0) {query1=query1+" , "; size--;}}
		if(updated[1]>0){query1 += "clients.lastname=?";list2.add(list.get(1)); if(size!=0) {query1=query1+" , "; size--;}}
		if(updated[2]>0){query1 += "clients.fathername=?";list2.add(list.get(2)); if(size!=0) {query1=query1+" , "; size--;}}
		if(updated[3]>0){query1 += "mothername=?";list2.add(list.get(3));if(size!=0) {query1=query1+" , "; size--;}}
		if(updated[4]>0){query1 += "address=? ";list2.add(list.get(4));if(size!=0) {query1=query1+" , "; size--;}}
		if(updated[5]>0){query1 += " job=?";list2.add(list.get(5));if(size!=0) {query1=query1+" , "; size--;}}
		
		if(updated[6]>0){query1 += "phone=?";list2.add(list.get(6));if(size!=0) {query1=query1+" , "; size--;}}
		if(updated[7]>0){query1 += "state=?";list2.add(list.get(7));if(size!=0) {query1=query1+" , "; size--;}}
		if(updated[8]>0){query1 += "age=?";list2.add(list.get(8));if(size!=0) {query1=query1+" , "; size--;}}
		if(updated[9]>0){query1 += "birthplace=?";list2.add(list.get(9));if(size!=0) {query1=query1+" , "; size--;}}
		
		query1=query1+" where clients.clientID=?";
	list2.add(list.get(list.size()-1));
	
	System.out.println(query1);
	
	d1.connect();
	rs = d1.Parameteredquery(query1, list2);
	try {
		rs.next();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	d1.disconnect();
	
	String query2="Update policy set policy.hosp_type=? , amount=? where policy.policyID=?";
	d1.connect();
	rs = d1.Parameteredquery(query2, list1);
	
	try {
		rs.next();
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, "Error encountred while updating data in the database");	}
	d1.disconnect();
	
	
}


public static void insert_hosp_policy(ArrayList<String> list)
{
		int policyid = 0;
		String query = "insert into policy (clientID,employeeID,type,amount,date,hosp_type)  values (?,?,?,?,?,?)";
		d1.connect();
		rs = d1.Parameteredquery(query, list);

		try {
			rs.next();
			policyid = rs.getInt(1);
			JOptionPane.showMessageDialog(null, "id:" + policyid);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		d1.disconnect();

	}

/** Fill the JTable In the Hospitalization Frame **/
public static ResultSet fill_client_table(String s, String p){
	
	String query = "Select policy.policyID,insurance.Type,policy.date,policy.amount,employee.firstname,employee.lastname"
			+ " from policy , employee , insurance"
			+ " where employee.employeeID = policy.employeeID and policy.type=insurance.id and policy.type='"+p+"' and policy.clientID='"+s+"'";

	d1.connect();
	rs = d1.query(query);
	return rs;
	
}

/** Get All the Policies for A Client **/
public static ResultSet ALL(String type, String id)
{
	String query="Select * from policy,insurance , employee,car_model,car_info  where employee.employeeID = policy.employeeID and policy.type=insurance.id and car_info.model=car_model.model and policy.car_info=car_info.id and policy.type='"+type+"' and policy.clientID='"+id+"'  ORDER BY policy.clientID";

	d1.connect();
	rs = d1.query(query);
	return rs;
	
}


/** Get All the policies to write a report for employee **/
public static ResultSet Employee_report(String type){
	
	String query="Select * from policy,insurance , employee,car_model,car_info  where employee.employeeID = policy.employeeID and policy.type=insurance.id and car_info.model=car_model.model and policy.car_info=car_info.id and policy.type='"+type+"'  ORDER BY policy.clientID";

	System.out.println(query);
	d1.connect();
	rs = d1.query(query);
	return rs;
	
	
}

/** fill the combobox with all the model of a specific car company**/
public static ResultSet fill_models(String Company){
	
	
	String query="Select model from car_model where car_model.company='"+Company+"'";
	d1.connect();
	rs = d1.query(query);
	return rs;
}


public static int insert_licence(ArrayList<String> list){

	int licenceid = 0;
	d1.connect();
	String query1 ="Select * from licence where licence.licence_nb='"+list.get(1)+"'";
	rs = d1.query(query1);

	try {
		if (rs.next()) {
			licenceid = rs.getInt(1);
		} else {
	
	String query = "insert into licence (licence_type,licence_nb,licence_to)  values (?,?,?)";
	
	rs = d1.Parameteredquery(query, list);

	try {
		rs.next();
		licenceid = rs.getInt(1);
	} catch (SQLException e) {
		e.printStackTrace();
	}
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	d1.disconnect();
	return licenceid;
}


public static int insert_car(ArrayList<String> list){
	
	int infoid=0;
	String query="insert into car_info (model,parked,accident,security,modified)  values (?,?,?,?,?)";
	d1.connect();
	rs = d1.Parameteredquery(query, list);
	
	try {
		rs.next();
		infoid = rs.getInt(1);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	d1.disconnect();
	return infoid;
}


public static int insert_policy_car(ArrayList<String> list){
	
	
	int policyid=0;
	String query="insert into policy (clientID,employeeID,type,date,licence,car_info,policy_start)  values (?,?,?,?,?,?,?)";
	d1.connect();
	rs = d1.Parameteredquery(query, list);
	
	try {
		rs.next();
		policyid = rs.getInt(1);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	d1.disconnect();
	return policyid;
}



/** in Car Insurance Frame **/
public static ArrayList<String> Display_result(int id){
	
	ArrayList<String> list= new ArrayList<>();
	
	String query="Select car_model.company,car_model.image, car_model.model,car_model.year ,car_info.accident , car_info.security "
			+ ", car_info.modified from car_model,car_info where car_info.model=car_model.model and car_info.id='"+id+"'";
	
	
	
	d1.connect();
	rs = d1.query(query);
	
	try {
		rs.next();
		
			list.add(rs.getString(1));
			list.add(rs.getString(2));
			list.add(rs.getString(3));
			list.add(rs.getString(4));
			list.add(rs.getString(5));
			list.add(rs.getString(6));
			list.add(rs.getString(7));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return list;
	
	
	
	
	
}
}
