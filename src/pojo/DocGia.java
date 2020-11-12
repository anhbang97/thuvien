/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "docgia")
public class DocGia implements Serializable {

    /**
     * @return the soThe
     */
    public int getSoThe() {
        return soThe;
    }

    /**
     * @param soThe the soThe to set
     */
    public void setSoThe(int soThe) {
        this.soThe = soThe;
    }

    @Id
    @Column(name = "madocgia")
    private int maDG;
    @Column(name = "tendocgia")
    private String tenDG;
    @Column(name = "gioitinh")
    private String gioiTinh;
    @Column(name = "namsinh")
    private Date namSinh;
    @Column(name = "diachi")
    private String diaChi;
//    @OneToOne(fetch = FetchType.LAZY)
//    @PrimaryKeyJoinColumn(name = "sothe")
    @Column(name = "sothe")
    private int soThe;
    
    
    public DocGia(){};    
    public DocGia(int maDG,String tenDG, String gioiTinh, 
            Date namSinh, String diaChi, int soThe){
        this.maDG = maDG;
        this.tenDG = tenDG;
        this.gioiTinh = gioiTinh;
        this.namSinh = namSinh;
        this.diaChi = diaChi;
        this.soThe = soThe;
    }
    
    public DocGia(String tenDG, String gioiTinh, 
            Date namSinh, String diaChi, int soThe){
        this.maDG = maDG;
        this.tenDG = tenDG;
        this.gioiTinh = gioiTinh;
        this.namSinh = namSinh;
        this.diaChi = diaChi;
        this.soThe = soThe;
    }

    /**
     * @return the maDG
     */
    public int getMaDG() {
        return maDG;
    }

    /**
     * @param maDG the maDG to set
     */
    public void setMaDG(int maDG) {
        this.maDG = maDG;
    }

    /**
     * @return the tenDG
     */
    public String getTenDG() {
        return tenDG;
    }

    /**
     * @param tenDG the tenDG to set
     */
    public void setTenDG(String tenDG) {
        this.tenDG = tenDG;
    }

    /**
     * @return the gioiTinh
     */
    public String getGioiTinh() {
        return gioiTinh;
    }

    /**
     * @param gioiTinh the gioiTinh to set
     */
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    /**
     * @return the namSinh
     */
    public Date getNamSinh() {
        return namSinh;
    }

    /**
     * @param namSinh the namSinh to set
     */
    public void setNamSinh(Date namSinh) {
        this.namSinh = namSinh;
    }

    /**
     * @return the diaChi
     */
    public String getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     */
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

//    /**
//     * @return the the
//     */
//    public TheDocGia getThe() {
//        return the;
//    }
//
//    /**
//     * @param the the the to set
////     */
////    public void setThe(TheDocGia the) {
////        this.the = the;
////    }
//
//    /**
//     * @return the the
//     */
//    public int getThe() {
//        return getSoThe();
//    }
//
//    /**
//     * @param the the the to set
//     */
//    public void setThe(int the) {
//        this.setSoThe(the);
//    }

}
