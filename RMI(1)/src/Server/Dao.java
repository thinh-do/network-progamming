package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {
	Connection conn;

	public Dao(String dbFile) throws SQLException, ClassNotFoundException {
		String driverName="net.ucanaccess.jdbc.UcanaccessDriver";
		String url="jdbc:ucanaccess://"+dbFile;
		Class.forName(driverName);
		conn=DriverManager.getConnection(url);
		
	}
	
	

}
