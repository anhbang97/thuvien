/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "nhanvien")
public class NhanVien{

    /**
     * @return the gioiTinhNV
     */
    public String getGioiTinhNV() {
        return gioiTinhNV;
    }

    /**
     * @param gioiTinhNV the gioiTinhNV to set
     */
    public void setGioiTinhNV(String gioiTinhNV) {
        this.gioiTinhNV = gioiTinhNV;
    }

    /**
     * @return the namSinhNV
     */
    public Date getNamSinhNV() {
        return namSinhNV;
    }

    /**
     * @param namSinhNV the namSinhNV to set
     */
    public void setNamSinhNV(Date namSinhNV) {
        this.namSinhNV = namSinhNV;
    }

    /**
     * @return the diaChiNV
     */
    public String getDiaChiNV() {
        return diaChiNV;
    }

    /**
     * @param diaChiNV the diaChiNV to set
     */
    public void setDiaChiNV(String diaChiNV) {
        this.diaChiNV = diaChiNV;
    }

    /**
     * @return the tenTK
     */
    public String getTenTK() {
        return tenTK;
    }

    /**
     * @param tenTK the tenTK to set
     */
    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }
    @Id
    @Column(name = "manhanvien")
    private int maNV;
    @Column(name = "hoten")
    private String tenNV    ;
    @Column(name = "gioitinh")
    private String gioiTinhNV;
    @Column(name = "namsinh")
    private Date namSinhNV;
    @Column (name = "diachi")
    private String diaChiNV;
    @Column (name = "tentk")
    private String tenTK;

    public NhanVien(){};
    public NhanVien(int maNV,String tenNV, String gioiTinh, 
            Date d, String diaChi, String tk){
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gioiTinhNV = gioiTinh;
        this.namSinhNV = d;
        this.diaChiNV = diaChi;
        this.tenTK = tk;
        
        
    }


    /**
     * @return the maNV
     */
    public int getMaNV() {
        return maNV;
    }

    /**
     * @param maNV the maNV to set
     */
    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    /**
     * @return the tenNV
     */
    public String getTenNV() {
        return tenNV;
    }

    /**
     * @param tenNV the tenNV to set
     */
    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }


    
    
    
}
