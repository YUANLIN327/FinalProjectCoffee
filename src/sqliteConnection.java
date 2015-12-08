import java.sql.*;
import javax.swing.*;
public class sqliteConnection {
	Connection conn = null;
	
	
	public static Connection dbConnector() {
		// TODO Auto-generated method stub
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:EmployeeData.sqlite");
			System.out.println("Connection built");
			return conn;
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			System.out.println("Connection not built");
			return null;
		}
	}



	
}
