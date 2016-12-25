package Application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import database.Company;
import database.SqlConnection;

public class Operations {

	
	//private static final String[] COLUMNS = {"Company", "Stock Price"};
	public static DefaultTableModel writeResult (ResultSet res) throws Exception {
		ResultSetMetaData metaData = res.getMetaData();
		
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}
		
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (res.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(res.getObject(columnIndex));
			}
			data.add(vector);
		}
		return new DefaultTableModel(data, columnNames);
	}
	
	public static ResultSet writeTableResult() throws Exception {
		Connection conn = null;
		conn = SqlConnection.getConnection("jdbc:mysql://localhost:3306/stocks");
		Statement st = conn.createStatement();
		String sql = "CREATE TEMPORARY TABLE temp_table SELECT * FROM stocks.companies, stocks.stockvalue WHERE companies.companies_id = stockvalue.stockvalue_id";
		String sqlb = "ALTER TABLE temp_table DROP COLUMN companies_id";
		String sqlc = "ALTER TABLE temp_table DROP COLUMN stockvalue_id";
		String sqld = "ALTER TABLE temp_table DROP COLUMN marketcap";
		String sqle = "ALTER TABLE temp_table DROP COLUMN ticker";
		String sqlf = "ALTER TABLE temp_table DROP COLUMN lastprice";
		String sqlg = "SELECT * FROM temp_table";
		st.executeUpdate(sql);
		st.executeUpdate(sqlb);
		st.executeUpdate(sqlc);
		st.executeUpdate(sqld);
		st.executeUpdate(sqle);
		st.executeUpdate(sqlf);
		ResultSet res = st.executeQuery(sqlg);
		return res;
	}
	//public static DefaultTableModel createTableModel() throws Exception {
		   
		//DefaultTableModel tableModel = new DefaultTableModel(COLUMNS, 0);
		
		//ArrayList<Company> companyList = Company.getCompanies();
		//for (int i = 0; i < companyList.size(); i++) {
			//Object[] row = {companyList.get(i).getName(), companyList.get(i).getLastValue()};
			//tableModel.addRow(row);
		//}
		
		//return tableModel;
	//}

}
