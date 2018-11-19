package HoangDinhPhu_14073011;

public class SinhVien {
	private int maSV;
	private String hoTenSV;
	private String email;
	private String diaChi;
	private String maLop;
	public SinhVien(int maSV, String hoTenSV, String email, String diaChi, String maLop) {
		super();
		this.maSV = maSV;
		this.hoTenSV = hoTenSV;
		this.email = email;
		this.diaChi = diaChi;
		this.maLop = maLop;
	}
	public SinhVien(int maSV) {
		super();
		this.maSV = maSV;
	}
	public SinhVien(String maLop) {
		super();
		this.maLop = maLop;
	}
	
	public SinhVien() {
		
	}
	public int getMaSV() {
		return maSV;
	}
	public void setMaSV(int maSV) {
		this.maSV = maSV;
	}
	public String getHoTenSV() {
		return hoTenSV;
	}
	public void setHoTenSV(String hoTenSV) {
		this.hoTenSV = hoTenSV;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getMaLop() {
		return maLop;
	}
	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}
	
}
