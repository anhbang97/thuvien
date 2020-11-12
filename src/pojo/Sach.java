/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "sach")
public class Sach {

    /**
     * @return the img
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(String img) {
        this.img = img;
    }
    @Id
    @Column(name = "masach")
    private String maSach;
    @Column(name = "tensach")
    private String tenSach;
    @Column(name = "tacgia")
    private String tacGia;
    @Column(name = "nhaxuatban")
    private String nhaXuatBan;
    @Column(name = "theloai")
    private String theLoai;
    @Column(name = "soluong")
    private int soLuong;
    @Column(name = "img")
    private String img;
    @OneToMany(mappedBy = "maSachMuon")
    private List<MuonTra> dsSachMuon;
    public Sach(){
        
    }
    public Sach(String maSach, String tenSach, String tacGia, 
            String theLoai, String nhaXB, int soLuong, String img){
        
        this.maSach = maSach; 
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.nhaXuatBan = nhaXB;
        this.soLuong = soLuong;
        this.img = img;
    }
    
    /**
     * @return the maSach
     */
    public String getMaSach() {
        return maSach;
    }

    /**
     * @param maSach the maSach to set
     */
    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    /**
     * @return the tenSach
     */
    public String getTenSach() {
        return tenSach;
    }

    /**
     * @param tenSach the tenSach to set
     */
    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    /**
     * @return the tacGia
     */
    public String getTacGia() {
        return tacGia;
    }

    /**
     * @param tacGia the tacGia to set
     */
    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    /**
     * @return the nhaXuatBan
     */
    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    /**
     * @param nhaXuatBan the nhaXuatBan to set
     */
    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    /**
     * @return the theLoai
     */
    public String getTheLoai() {
        return theLoai;
    }

    /**
     * @param theLoai the theLoai to set
     */
    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    /**
     * @return the dsSachMuon
     */
    public List<MuonTra> getDsSachMuon() {
        return dsSachMuon;
    }

    /**
     * @param dsSachMuon the dsSachMuon to set
     */
    public void setDsSachMuon(List<MuonTra> dsSachMuon) {
        this.dsSachMuon = dsSachMuon;
    }
   @Override
    public String toString() {
        String m = String.valueOf(maSach);
        return m; //To change body of generated methods, choose Tools | Templates.
    }
    
}
