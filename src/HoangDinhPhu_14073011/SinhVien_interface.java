package HoangDinhPhu_14073011;

import java.util.ArrayList;

public interface SinhVien_interface {
	ArrayList<SinhVien> getAllTableSinhVien();
	ArrayList<SinhVien> getSinhVienByMaLop(String maLop);
	public boolean crateSinhVien(SinhVien sinhVien);
	public boolean deleteSinhVien(SinhVien sinhVien) ;

}
