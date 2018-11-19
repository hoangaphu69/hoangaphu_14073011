package HoangDinhPhu_14073011;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmSinhVien extends JFrame //implements MouseListener 
{

	private static final long serialVersionUID = -1554680235689968471L;
	private JTextField txtMaSV;
	private JTextField txtHoTen;
	private JTextField txtEmail;
	private JTextField txtDiaChi;

	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnXoa;
	private DefaultTableModel dataModel;
	private JTable table;

	private JScrollPane scroll;
	private JButton btnXemDSSV;
	private JLabel lblMalop;
	private JComboBox cbxMaLop;
	private LopHoc_Dao lopHoc_DAO;
	private SinhVien_DAO sv_dao;

	public FrmSinhVien() {
		ConnectBD.getInstance().getConnection();
		lopHoc_DAO = new LopHoc_Dao();
		sv_dao = new SinhVien_DAO();

		setTitle("ho ten sv: hoang dinh phu	Massv: 14073011  lop: dhkhmt10a");
		setSize(800, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Box b, b1, b2, b3, b4, b5, b6, b7, b8;
		add(b = Box.createVerticalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b8 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b6 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));

		b.add(b7 = Box.createHorizontalBox());

		JLabel lblTieuDe, lblMaSV, lblHoTen, lblEmail, lblDiaChi;
		b1.add(lblTieuDe = new JLabel("THONG TIN SINH VIEN", JLabel.CENTER));
		lblTieuDe.setFont(new Font("Arila", Font.BOLD, 26));

		b2.add(lblMaSV = new JLabel("Ma so sinh vien: ", JLabel.RIGHT));
		b2.add(txtMaSV = new JTextField());
		b3.add(lblHoTen = new JLabel("Ho ten: ", JLabel.RIGHT));
		b3.add(txtHoTen = new JTextField());
		b4.add(lblEmail = new JLabel("Email: ", JLabel.RIGHT));
		b4.add(txtEmail = new JTextField());
		b5.add(lblDiaChi = new JLabel("Dia chi: ", JLabel.RIGHT));
		b5.add(txtDiaChi = new JTextField());

		b8.add(lblMalop = new JLabel("Ma lop: ", JLabel.RIGHT));
		b8.add(cbxMaLop = new JComboBox());
		ArrayList<LopHoc> dsLopHoc = lopHoc_DAO.getAllLopHoc();
		for (LopHoc lopHoc : dsLopHoc) {
			cbxMaLop.addItem(lopHoc.getMaLop());
		}

		lblHoTen.setPreferredSize(lblMaSV.getPreferredSize());
		lblEmail.setPreferredSize(lblMaSV.getPreferredSize());
		lblDiaChi.setPreferredSize(lblMaSV.getPreferredSize());
		lblMalop.setPreferredSize(lblMaSV.getPreferredSize());

		b6.add(btnThem = new JButton("Them"));
		b6.add(btnLuu = new JButton("Luu"));
		b6.add(btnXoa = new JButton("Xoa"));
		b6.add(btnXemDSSV = new JButton("Xem Danh Sach Sinh Vien Cua Lop"));

		String[] tieuDe = { "Ma so", "Ho ten", "Email", "Dia chi", "Ma lop" };
		dataModel = new DefaultTableModel(tieuDe, 0);
		table = new JTable(dataModel);
		JScrollPane scroll  = new JScrollPane(table);
		b7.add(scroll);
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sach sinh vien hien tai"));
		docdulieuvaobang();
		table.addMouseListener(mouseListener);

		btnThem.addActionListener(actionListener);
		btnXoa.addActionListener(actionListener);
		btnLuu.addActionListener(actionListener);
		btnXemDSSV.addActionListener(actionListener);
	}

	private void docdulieuvaobang() {
		ArrayList<SinhVien> dssv = sv_dao.getAllTableSinhVien();
		for (SinhVien sinhVien : dssv) {
			dataModel.addRow(new Object[] {sinhVien.getMaSV(), sinhVien.getHoTenSV(),sinhVien.getEmail(),
					sinhVien.getDiaChi(),sinhVien.getMaLop()});
		}
	}
	public void cleantextfield() {
		txtMaSV.setText("");
		txtHoTen.setText("");
		txtEmail.setText("");
		txtDiaChi.setText("");
		txtMaSV.requestFocus();
	}
	public void xoaBangModel() {

		dataModel.getDataVector().removeAllElements();
		revalidate();
	}
	public void xoaDongDuocChon() {
		int rowCount = dataModel.getRowCount();
		int n = table.getSelectedRow();
		dataModel.removeRow(n);
	}
	ActionListener actionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if(o.equals(btnThem)) {
				cleantextfield();
			}
			if(o.equals(btnLuu)) {
				int maSV = Integer.parseInt(txtMaSV.getText().toString());
				String hoTenSV = txtHoTen.getText().toString();
				String email = txtEmail.getText().toString();
				String diaChi = txtDiaChi.getText().toString();
				String maLop = (String) cbxMaLop.getSelectedItem();
				String regextMaSV = "[0-9]{5}";
				String regexHoTen = "^[a-zA-Z\\s]+$";
				String regexEmail = "\\S+@\\S+\\.\\S+";

				if(txtMaSV.getText().matches(regextMaSV)) {
					if (hoTenSV.matches(regexHoTen)) {
						if (diaChi.matches(regexHoTen)) {
							if (email.matches(regexEmail)) {
								SinhVien sinhVien = new SinhVien(maSV, hoTenSV, email, diaChi, maLop);				
								txtMaSV.requestFocus();
								try {
									sv_dao.crateSinhVien(sinhVien);
									xoaBangModel();
									docdulieuvaobang();
								} catch (Exception e2) {
									JOptionPane.showMessageDialog(null, "trung mat tiu");
								}
							} else {
								JOptionPane.showMessageDialog(null, "ban nhap sai dia chi email");
							}
						} else {
							JOptionPane.showMessageDialog(null, "dia chi sai \n ban vui long nhap lai dia chi");
						}
					} else {
						JOptionPane.showMessageDialog(null, "ten sai ban vui long nhap lai");
					}
				}else {
					JOptionPane.showMessageDialog(null, "error txtMaSV nhap theo dinh dang \n 5 chu so viet lien");
				}
			}
			if(o.equals(btnXoa)) {
				int maSV = Integer.parseInt(txtMaSV.getText());
				SinhVien sinhVien = new SinhVien(maSV);

				sv_dao.deleteSinhVien(sinhVien);
				System.out.println("dung");
				xoaBangModel();
				docdulieuvaobang();
				//				xoaDongDuocChon();
			}
			if(o.equals(btnXemDSSV)) {
				if(cbxMaLop.getSelectedIndex()> -1) {
					String maLop = String.valueOf(cbxMaLop.getSelectedItem());
					xoaBangModel();
					ArrayList<SinhVien> dssv = sv_dao.getSinhVienByMaLop(maLop);
					for (SinhVien sinhVien : dssv) {
						dataModel.addRow(new Object[] {sinhVien.getMaSV(),sinhVien.getHoTenSV(),sinhVien.getEmail(),
								sinhVien.getDiaChi(),maLop});
					}
				}else {
					JOptionPane.showMessageDialog(null, "chua cho ma lop: ");
				}

			}

		}
	};

	MouseListener mouseListener = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = table.getSelectedRow();
			txtMaSV.setText(dataModel.getValueAt(row, 0).toString());
			txtHoTen.setText(dataModel.getValueAt(row, 1).toString());
			txtEmail.setText(dataModel.getValueAt(row, 2).toString());
			txtDiaChi.setText(dataModel.getValueAt(row, 3).toString());
			cbxMaLop.setSelectedItem(dataModel.getValueAt(row, 4).toString());

		}
	};
	public static void main(String[] args) {
		FrmSinhVien frmSinhVien = new FrmSinhVien();
		frmSinhVien.setVisible(true);

	}
}