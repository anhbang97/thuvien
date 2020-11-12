/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table (name = "thongke")
public class ThongKe{
    @Id
    @Column(name = "mathongke")
    private int maThongKe;
    @Column(name = "thang")
    private int thang;
    @Column (name = "nam")
    private int nam;
    @Column(name = "tongthang")
    private int tongThang;
    @Column(name = "tongquy")
    private int tongQuy;
    @Column(name = "tongnam")
    private int tongNam;
    
    public ThongKe(){
        
    }
    public ThongKe(int ma,int thang,int nam,int tongt,int tongq,int tongn){
        this.maThongKe = ma;
        this.thang = thang;
        this.nam = nam;
        this.tongThang = tongt;
        this.tongQuy = tongq;
        this.tongNam = tongn;
    }
    public ThongKe(int thang,int nam,int tongt,int tongq,int tongn){
        this.thang = thang;
        this.nam = nam;
        this.tongThang = tongt;
        this.tongQuy = tongq;
        this.tongNam = tongn;
    }
    
    
    /**
     * @return the maThongKe
     */
    public int getMaThongKe() {
        return maThongKe;
    }

    /**
     * @param maThongKe the maThongKe to set
     */
    public void setMaThongKe(int maThongKe) {
        this.maThongKe = maThongKe;
    }

    /**
     * @return the thang
     */
    public int getThang() {
        return thang;
    }

    /**
     * @param thang the thang to set
     */
    public void setThang(int thang) {
        this.thang = thang;
    }

    /**
     * @return the nam
     */
    public int getNam() {
        return nam;
    }

    /**
     * @param nam the nam to set
     */
    public void setNam(int nam) {
        this.nam = nam;
    }

    /**
     * @return the tongThang
     */
    public int getTongThang() {
        return tongThang;
    }

    /**
     * @param tongThang the tongThang to set
     */
    public void setTongThang(int tongThang) {
        this.tongThang = tongThang;
    }

    /**
     * @return the tongQuy
     */
    public int getTongQuy() {
        return tongQuy;
    }

    /**
     * @param tongQuy the tongQuy to set
     */
    public void setTongQuy(int tongQuy) {
        this.tongQuy = tongQuy;
    }

    /**
     * @return the tongNam
     */
    public int getTongNam() {
        return tongNam;
    }

    /**
     * @param tongNam the tongNam to set
     */
    public void setTongNam(int tongNam) {
        this.tongNam = tongNam;
    }
}
