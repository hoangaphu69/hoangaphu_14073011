package HoangDinhPhu_14073011;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LopHoc_Dao {
	public ArrayList<LopHoc> getAllLopHoc(){
		ArrayList<LopHoc> dsLopHoc = new ArrayList<LopHoc>();

		try {

			Connection con = ConnectBD.getInstance().getConnection();
			String sql = "select * from tbLop";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				String maLop = resultSet.getString(1);
				String tenLop = resultSet.getString(2);
				LopHoc lopHoc = new LopHoc(maLop, tenLop);
				dsLopHoc.add(lopHoc);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLopHoc;
	}

}
