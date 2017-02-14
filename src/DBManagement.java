
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

										/* cette Classe est pour faire la connection avec notre base de donnee */
                              
public class DBManagement {
	public Connection con = null;
	public Statement s;
	public PreparedStatement p;
	public ResultSet rs;


	
	public void connect() {
		
		String url="jdbc:mysql://localhost:3306/insurance";
		String user="zeina";
		String password="123";
		
		try {
		
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			con = DriverManager.getConnection(url ,user, password);
			
			
			/*FOR SQLSERVER: con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306;databaseName=insurance;user=zeina;password=123;");*/
			
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		
		
			s = con.createStatement();
	} catch (Exception e) {
		 e.printStackTrace();
		}	
	}

	
	/*
	 * fct pour executer les requêtes  sql 'Select from where'
	 * 
	 */
	public ResultSet query(String str) {

		try {
			rs = s.executeQuery(str);
		} catch (SQLException e) {
		// e.printStackTrace();
		}
		return rs;
	}
	
	
	/*
	 * fct pour executer les requêtes  sql 'Update/Insert/Delete'
	 * 
	 */
	public ResultSet Parameteredquery(String str,ArrayList<String> arr) {   

		try {
			p = con.prepareStatement(str,Statement.RETURN_GENERATED_KEYS);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		int counter=1;
		
		for(String parameter : arr){
			try {
				p.setString(counter, parameter);
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			counter++;
		}
		
		try {
			
			 p.executeUpdate();
			 rs=p.getGeneratedKeys();
		} catch (SQLException e) {
		 e.printStackTrace();
		}
		return rs;
		
	}
	
	/*
	 * fct pour fermer les connections
	 * 
	 */
	

	public void disconnect() {
		try {
			s.close();
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
}
