package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DatabaseTable {
	
	public static void post() throws Exception {
		final String username1 = "Donnie";
		final String password1 = "nigger";
		final String username2 = "Zack";
		final String password2 = "bitch";
		
		try {
			Connection con = SqlConnection.getConnection("jdbc:mysql://localhost:3306/login");
			PreparedStatement posted = con.prepareStatement("INSERT INTO logininfo (username, password) VALUES ('"+username1+"', '"+password1+"')");
			PreparedStatement posted2 = con.prepareStatement("INSERT INTO logininfo (username, password) VALUES ('"+username2+"', '"+password2+"')");
			
			posted.executeUpdate();
			posted2.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		finally {
			System.out.println("Insert Completed.");
		}
	}
	
}
