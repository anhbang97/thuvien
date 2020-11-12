/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table (name = "matsach")
public class MatSach {
    @Id
    @Column (name = "mamatsach")
    private int maMatSach;
    @Column (name = "maphieumuon")
    private int maPhieuMuon;
    @Column (name = "masach")
    private String maSachMat;
    @Column (name = "tensach")
    private String tenSachMat;
    @Column (name = "sothe")
    private int soTheMat;
    @Column (name = "ngayghinhan")
    private Date ngayGhiNhan; 
public MatSach(){
    
}
public MatSach(int maMuon,String maS,String tenS,int the,Date ngay){
   
    this.maPhieuMuon = maMuon;
    this.maSachMat = maS;
    this.tenSachMat = tenS;
    this.soTheMat = the;
    this.ngayGhiNhan = ngay;
}
    /**
     * @return the maMatSach
     */
    public int getMaMatSach() {
        return maMatSach;
    }

    /**
     * @param maMatSach the maMatSach to set
     */
    public void setMaMatSach(int maMatSach) {
        this.maMatSach = maMatSach;
    }

    /**
     * @return the maPhieuMuon
     */
    public int getMaPhieuMuon() {
        return maPhieuMuon;
    }

    /**
     * @param maPhieuMuon the maPhieuMuon to set
     */
    public void setMaPhieuMuon(int maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    /**
     * @return the maSachMat
     */
    public String getMaSachMat() {
        return maSachMat;
    }

    /**
     * @param maSachMat the maSachMat to set
     */
    public void setMaSachMat(String maSachMat) {
        this.maSachMat = maSachMat;
    }

    /**
     * @return the tenSachMat
     */
    public String getTenSachMat() {
        return tenSachMat;
    }

    /**
     * @param tenSachMat the tenSachMat to set
     */
    public void setTenSachMat(String tenSachMat) {
        this.tenSachMat = tenSachMat;
    }

    /**
     * @return the soTheMat
     */
    public int getSoTheMat() {
        return soTheMat;
    }

    /**
     * @param soTheMat the soTheMat to set
     */
    public void setSoTheMat(int soTheMat) {
        this.soTheMat = soTheMat;
    }

    /**
     * @return the ngayGhiNhan
     */
    public Date getNgayGhiNhan() {
        return ngayGhiNhan;
    }

    /**
     * @param ngayGhiNhan the ngayGhiNhan to set
     */
    public void setNgayGhiNhan(Date ngayGhiNhan) {
        this.ngayGhiNhan = ngayGhiNhan;
    }
}
