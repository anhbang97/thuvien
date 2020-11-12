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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table (name = "muontra")
public class MuonTra {
    @Id
    @Column(name = "mamuon")
    private int maMuon;
    
    @ManyToOne
    @JoinColumn(name = "sothemuon")
    private TheDocGia the;
    
    @ManyToOne
    @JoinColumn(name = "masachmuon")
    private Sach maSachMuon;
    
    @Column(name ="tensachmuon")
    private String tenSachMuon;
    @Column(name = "ngaymuon")
    private Date ngayMuon;
    @Column(name = "ngaytra")
    private Date ngayTra;
    @Column(name = "ngayhethan")
    private Date ngayHetHan;
    @Column(name = "tienphat")
    private int tienPhat;
    @Column(name ="ghichu")
    private String ghiChu;
    
    @Column(name ="tennhanvien")
    private String tenNhanVien;
    public MuonTra(){
    }
    public MuonTra(TheDocGia sothe,Sach s, Date ngaymuon,Date ngayhethan,String nv){
        
        this.the = sothe;
        this.maSachMuon = s;
        this.tenSachMuon = s.getTenSach();
        this.ngayMuon = ngaymuon;
        this.ngayHetHan = ngayhethan;
        this.tenNhanVien = nv;
    }
    
    
    /**
     * @return the maMuon
     */
    public int getMaMuon() {
        return maMuon;
    }

    /**
     * @param maMuon the maMuon to set
     */
    public void setMaMuon(int maMuon) {
        this.maMuon = maMuon;
    }

   

   

    /**
     * @return the tenSach
     */
    public String getTenSach() {
        return tenSachMuon;
    }

    /**
     * @param tenSach the tenSach to set
     */
    public void setTenSach(String tenSach) {
        this.tenSachMuon = tenSach;
    }

    /**
     * @return the ngayMuon
     */
    public Date getNgayMuon() {
        return ngayMuon;
    }

    /**
     * @param ngayMuon the ngayMuon to set
     */
    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    /**
     * @return the ngayTra
     */
    public Date getNgayTra() {
        return ngayTra;
    }

    /**
     * @param ngayTra the ngayTra to set
     */
    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    /**
     * @return the ngayHetHan
     */
    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    /**
     * @param ngayHetHan the ngayHetHan to set
     */
    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    /**
     * @return the tienPhat
     */
    public int getTienPhat() {
        return tienPhat;
    }

    /**
     * @param tienPhat the tienPhat to set
     */
    public void setTienPhat(int tienPhat) {
        this.tienPhat = tienPhat;
    }

    /**
     * @return the ghiChu
     */
    public String getGhiChu() {
        return ghiChu;
    }

    /**
     * @param ghiChu the ghiChu to set
     */
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    /**
     * @return the soThe
     */
    public TheDocGia getSoThe() {
        return the;
    }

    /**
     * @param soThe the soThe to set
     */
    public void setSoThe(TheDocGia soThe) {
        this.the = soThe;
    }

    /**
     * @return the maSach
     */
    public Sach getMaSach() {
        return maSachMuon;
    }

    /**
     * @param maSach the maSach to set
     */
    public void setMaSach(Sach maSach) {
        this.maSachMuon = maSach;
    }

    /**
     * @return the tenNhanVien
     */
    public String getTenNhanVien() {
        return tenNhanVien;
    }

    /**
     * @param tenNhanVien the tenNhanVien to set
     */
    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    
}
