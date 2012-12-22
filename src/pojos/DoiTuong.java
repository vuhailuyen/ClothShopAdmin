package pojos;
// Generated Oct 21, 2012 10:34:07 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DoiTuong generated by hbm2java
 */
public class DoiTuong  implements java.io.Serializable {
     private int id;
     private String moTa;
     private Date ngayCapNhat;
     private Integer tinhTrang;
     private Set sanPhams = new HashSet(0);

    public DoiTuong() {
    }

	
    public DoiTuong(int id) {
        this.id = id;
    }
    public DoiTuong(int id, String moTa, Date ngayCapNhat, Integer tinhTrang, Set sanPhams) {
       this.id = id;
       this.moTa = moTa;
       this.ngayCapNhat = ngayCapNhat;
       this.tinhTrang = tinhTrang;
       this.sanPhams = sanPhams;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getMoTa() {
        return this.moTa;
    }
    
    public void setMoTa(String moTa) {
        this.moTa = moTa;
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
    public Set getSanPhams() {
        return this.sanPhams;
    }
    
    public void setSanPhams(Set sanPhams) {
        this.sanPhams = sanPhams;
    }




}

