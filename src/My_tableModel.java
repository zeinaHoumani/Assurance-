import java.sql.*;
import java.util.Vector;
import javax.swing.table.*;
import net.proteanit.sql.DbUtils;


public class My_tableModel extends DbUtils {
	
	
	public My_tableModel(){
		super();
	}
	
	/** changing the fonction according to our need **/
	 public static TableModel resultSetToTableModel(ResultSet rs) {
			try {
			    ResultSetMetaData metaData = rs.getMetaData();
			    int numberOfColumns = metaData.getColumnCount();
			    Vector<String> columnNames = new Vector<String>();

			    // Get the column names
			    for (int column = 0; column < numberOfColumns; column++) {
				columnNames.addElement(metaData.getColumnLabel(column + 1));
			    }

			    // Get all rows.
			    Vector<Vector<Object>> rows = new Vector<Vector<Object>>();

			    while (rs.next()) {
				Vector<Object> newRow = new Vector<Object>();

				for (int i = 1; i <= numberOfColumns; i++) {
				    newRow.addElement(rs.getObject(i));
				}

				rows.addElement(newRow);
			    }

			    
			    /** The Modification -> Editable false**/
			    @SuppressWarnings("serial")
				DefaultTableModel model=new DefaultTableModel(rows, columnNames){
			    		  @Override
			    		  public boolean isCellEditable(int row, int column) {
			    		   return false;
			    		   }
			    		 };	    
			    
			    return model;
			} catch (Exception e) {
			    e.printStackTrace();

			    return null;
			}
		    }
	
	 
	 
}
