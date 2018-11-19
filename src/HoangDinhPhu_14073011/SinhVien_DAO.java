package HoangDinhPhu_14073011;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SinhVien_DAO implements SinhVien_interface {


	public SinhVien_DAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<SinhVien> getAllTableSinhVien() {
		//		do something
		ArrayList<SinhVien> dssv = new ArrayList<SinhVien>();

		try {
			Connection con = ConnectBD.getInstance().getConnection();
			String sql = "select * from tbSinhVien";
			Statement statement = con.createStatement();
			
			//thuc thi cau lenh sql roi tra ve choi doi tuong resultSet
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				int maSV = resultSet.getInt(1);
				String hoTenSV = resultSet.getString(2);
				String email = resultSet.getString(3);
				String diaChi = resultSet.getString(4);
				String maLop = resultSet.getString(5);
				SinhVien sinhVien = new SinhVien(maSV, hoTenSV, email, diaChi, maLop);
				dssv.add(sinhVien);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return dssv;
	}
	@Override
	public boolean crateSinhVien(SinhVien sinhVien) {
		Connection con = ConnectBD.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		int n = 0;
		
		try {
			preparedStatement = con.prepareStatement("insert into tbSinhVien values(?,?,?,?,?)");
			preparedStatement.setInt(1, sinhVien.getMaSV());
			preparedStatement.setString(2, sinhVien.getHoTenSV());
			preparedStatement.setString(3, sinhVien.getEmail());
			preparedStatement.setString(4, sinhVien.getDiaChi());
			preparedStatement.setString(5, sinhVien.getMaLop());
			n = preparedStatement.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return n > 0;
	}
	@Override
	public boolean deleteSinhVien(SinhVien sinhVien) {
		// TODO Auto-generated method stub
		Connection con = ConnectBD.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		String string = String.valueOf(sinhVien.getMaSV());
		String sql = "delete from tbSinhVien where maSV= "+string+";";
		int n = 0;
		
		try {
			
			preparedStatement = con.prepareStatement(sql);
			n = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n > 0;
	}
	@Override
	public ArrayList<SinhVien> getSinhVienByMaLop(String maLop) {
		// TODO Auto-generated method stub
		ArrayList<SinhVien> dssv = new ArrayList<SinhVien>();
		Connection connection = ConnectBD.getInstance().getConnection();
		String sql = "select * from tbSinhVien where maLop=?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, maLop);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				SinhVien sinhVien = new SinhVien(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5));
				dssv.add(sinhVien);
			}
			preparedStatement.close();
			return dssv;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
