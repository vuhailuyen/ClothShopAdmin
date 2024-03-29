package pojos;

// Generated Oct 21, 2012 10:34:07 AM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ThanhVien generated by hbm2java
 */
@SuppressWarnings("serial")
public class ThanhVien implements java.io.Serializable {

	private int id;
	private String hoVaTen;
	private Date ngaySinh;
	private String noiSong;
	private String tenDangNhap;
	private String matKhau;
	private Date ngayCapNhat;
	private Integer tinhTrang;
	private Date ngayHetHanBan;
	@SuppressWarnings("rawtypes")
	private Set hinhAnhThanhViens = new HashSet(0);
	@SuppressWarnings("rawtypes")
	private Set binhLuans = new HashSet(0);
	@SuppressWarnings("rawtypes")
	private Set gioHangs = new HashSet(0);

	public ThanhVien() {
	}

	public ThanhVien(int id) {
		this.id = id;
	}

	public ThanhVien(int id, String hoVaTen, Date ngaySinh, String noiSong,
			String tenDangNhap, String matKhau, Date ngayCapNhat,
			Integer tinhTrang, Date ngayHetHanBan, Set hinhAnhThanhViens,
			Set binhLuans, Set gioHangs) {
		this.id = id;
		this.hoVaTen = hoVaTen;
		this.ngaySinh = ngaySinh;
		this.noiSong = noiSong;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.ngayCapNhat = ngayCapNhat;
		this.tinhTrang = tinhTrang;
		this.ngayHetHanBan = ngayHetHanBan;
		this.hinhAnhThanhViens = hinhAnhThanhViens;
		this.binhLuans = binhLuans;
		this.gioHangs = gioHangs;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHoVaTen() {
		return this.hoVaTen;
	}

	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}

	public Date getNgaySinh() {
		return this.ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getNoiSong() {
		return this.noiSong;
	}

	public void setNoiSong(String noiSong) {
		this.noiSong = noiSong;
	}

	public String getTenDangNhap() {
		return this.tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return this.matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public Date getNgayCapNhat() {
		return this.ngayCapNhat;
	}

	public void setNgayCapNhat(Date ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}

	public Integer getTinhTrang() {
		return this.tinhTrang;
	}

	public void setTinhTrang(Integer tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public Date getNgayHetHanBan() {
		return this.ngayHetHanBan;
	}

	public void setNgayHetHanBan(Date ngayHetHanBan) {
		this.ngayHetHanBan = ngayHetHanBan;
	}

	public Set getHinhAnhThanhViens() {
		return this.hinhAnhThanhViens;
	}

	public void setHinhAnhThanhViens(Set hinhAnhThanhViens) {
		this.hinhAnhThanhViens = hinhAnhThanhViens;
	}

	public Set getBinhLuans() {
		return this.binhLuans;
	}

	public void setBinhLuans(Set binhLuans) {
		this.binhLuans = binhLuans;
	}

	public Set getGioHangs() {
		return this.gioHangs;
	}

	public void setGioHangs(Set gioHangs) {
		this.gioHangs = gioHangs;
	}

}
