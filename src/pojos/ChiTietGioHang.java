package pojos;

// Generated Oct 21, 2012 10:34:07 AM by Hibernate Tools 3.2.1.GA

import java.util.Date;

/**
 * ChiTietGioHang generated by hbm2java
 */
public class ChiTietGioHang implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private GioHang gioHang;
	private SanPham sanPham;
	private Integer soLuong;
	private Date ngayDatHang;
	private Float tongTien;
	private Integer tinhTrang;
	private Integer daThanhToan;

	public ChiTietGioHang() {
	}

	public ChiTietGioHang(int id) {
		this.id = id;
	}

	public ChiTietGioHang(int id, GioHang gioHang, SanPham sanPham,
			Integer soLuong, Date ngayDatHang, Float tongTien,
			Integer tinhTrang, Integer daThanhToan) {
		this.id = id;
		this.gioHang = gioHang;
		this.sanPham = sanPham;
		this.soLuong = soLuong;
		this.ngayDatHang = ngayDatHang;
		this.tongTien = tongTien;
		this.tinhTrang = tinhTrang;
		this.daThanhToan = daThanhToan;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GioHang getGioHang() {
		return this.gioHang;
	}

	public void setGioHang(GioHang gioHang) {
		this.gioHang = gioHang;
	}

	public SanPham getSanPham() {
		return this.sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public Integer getSoLuong() {
		return this.soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}

	public Date getNgayDatHang() {
		return this.ngayDatHang;
	}

	public void setNgayDatHang(Date ngayDatHang) {
		this.ngayDatHang = ngayDatHang;
	}

	public Float getTongTien() {
		return this.tongTien;
	}

	public void setTongTien(Float tongTien) {
		this.tongTien = tongTien;
	}

	public Integer getTinhTrang() {
		return this.tinhTrang;
	}

	public void setTinhTrang(Integer tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public Integer getDaThanhToan() {
		return this.daThanhToan;
	}

	public void setDaThanhToan(Integer daThanhToan) {
		this.daThanhToan = daThanhToan;
	}

}
