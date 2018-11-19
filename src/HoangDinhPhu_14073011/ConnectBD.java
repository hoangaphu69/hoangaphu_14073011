package HoangDinhPhu_14073011;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectBD {
	public static Connection con = null;
	private static ConnectBD instance = new ConnectBD();
	public static ConnectBD getInstance() {
		return instance;
	}
	public void connect() {
		try {
			String url = "jdbc:sqlserver://localhost:49933;databasename=QLSV";
			String user = "sa";
			String password ="sapassword";
			con = DriverManager.getConnection(url, user, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			
	}
	public void disconnect() {
		
		if(con != null) {
			try {
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static Connection getConnection() {
		try {
			String url = "jdbc:sqlserver://localhost:49933;databasename=QLSV";
			String user = "sa";
			String password ="sapassword";
			con = DriverManager.getConnection(url, user, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

}
