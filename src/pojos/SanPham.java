package pojos;
// Generated Oct 21, 2012 10:34:07 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SanPham generated by hbm2java
 */
public class SanPham  implements java.io.Serializable {
     private int id;
     private DoiTuong doiTuong;
     private NhaSanXuat nhaSanXuat;
     private LoaiSanPham loaiSanPham;
     private String tenSanPham;
     private String moTa;
     private Float giaBanDau;
     private Float giaHienTai;
     private Integer tinhTrang;
     private Date ngayCapNhat;
     private Integer soLuotXem;
     private Integer soLuongTon;
     private Integer soLuongDaBan;
     private Set chiTietGioHangs = new HashSet(0);
     private Set hinhAnhSanPhams = new HashSet(0);
     private Set tuKhoaSanPhams = new HashSet(0);
     private Set binhLuans = new HashSet(0);

    public SanPham() {
    }

	
    public SanPham(int id) {
        this.id = id;
    }
    public SanPham(int id, DoiTuong doiTuong, NhaSanXuat nhaSanXuat, LoaiSanPham loaiSanPham, String tenSanPham, String moTa, Float giaBanDau, Float giaHienTai, Integer tinhTrang, Date ngayCapNhat, Integer soLuotXem, Integer soLuongTon, Integer soLuongDaBan, Set chiTietGioHangs, Set hinhAnhSanPhams, Set tuKhoaSanPhams, Set binhLuans) {
       this.id = id;
       this.doiTuong = doiTuong;
       this.nhaSanXuat = nhaSanXuat;
       this.loaiSanPham = loaiSanPham;
       this.tenSanPham = tenSanPham;
       this.moTa = moTa;
       this.giaBanDau = giaBanDau;
       this.giaHienTai = giaHienTai;
       this.tinhTrang = tinhTrang;
       this.ngayCapNhat = ngayCapNhat;
       this.soLuotXem = soLuotXem;
       this.soLuongTon = soLuongTon;
       this.soLuongDaBan = soLuongDaBan;
       this.chiTietGioHangs = chiTietGioHangs;
       this.hinhAnhSanPhams = hinhAnhSanPhams;
       this.tuKhoaSanPhams = tuKhoaSanPhams;
       this.binhLuans = binhLuans;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public DoiTuong getDoiTuong() {
        return this.doiTuong;
    }
    
    public void setDoiTuong(DoiTuong doiTuong) {
        this.doiTuong = doiTuong;
    }
    public NhaSanXuat getNhaSanXuat() {
        return this.nhaSanXuat;
    }
    
    public void setNhaSanXuat(NhaSanXuat nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }
    public LoaiSanPham getLoaiSanPham() {
        return this.loaiSanPham;
    }
    
    public void setLoaiSanPham(LoaiSanPham loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }
    public String getTenSanPham() {
        return this.tenSanPham;
    }
    
    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }
    public String getMoTa() {
        return this.moTa;
    }
    
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    public Float getGiaBanDau() {
        return this.giaBanDau;
    }
    
    public void setGiaBanDau(Float giaBanDau) {
        this.giaBanDau = giaBanDau;
    }
    public Float getGiaHienTai() {
        return this.giaHienTai;
    }
    
    public void setGiaHienTai(Float giaHienTai) {
        this.giaHienTai = giaHienTai;
    }
    public Integer getTinhTrang() {
        return this.tinhTrang;
    }
    
    public void setTinhTrang(Integer tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
    public Date getNgayCapNhat() {
        return this.ngayCapNhat;
    }
    
    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }
    public Integer getSoLuotXem() {
        return this.soLuotXem;
    }
    
    public void setSoLuotXem(Integer soLuotXem) {
        this.soLuotXem = soLuotXem;
    }
    public Integer getSoLuongTon() {
        return this.soLuongTon;
    }
    
    public void setSoLuongTon(Integer soLuongTon) {
        this.soLuongTon = soLuongTon;
    }
    public Integer getSoLuongDaBan() {
        return this.soLuongDaBan;
    }
    
    public void setSoLuongDaBan(Integer soLuongDaBan) {
        this.soLuongDaBan = soLuongDaBan;
    }
    public Set getChiTietGioHangs() {
        return this.chiTietGioHangs;
    }
    
    public void setChiTietGioHangs(Set chiTietGioHangs) {
        this.chiTietGioHangs = chiTietGioHangs;
    }
    public Set getHinhAnhSanPhams() {
        return this.hinhAnhSanPhams;
    }
    
    public void setHinhAnhSanPhams(Set hinhAnhSanPhams) {
        this.hinhAnhSanPhams = hinhAnhSanPhams;
    }
    public Set getTuKhoaSanPhams() {
        return this.tuKhoaSanPhams;
    }
    
    public void setTuKhoaSanPhams(Set tuKhoaSanPhams) {
        this.tuKhoaSanPhams = tuKhoaSanPhams;
    }
    public Set getBinhLuans() {
        return this.binhLuans;
    }
    
    public void setBinhLuans(Set binhLuans) {
        this.binhLuans = binhLuans;
    }




}


