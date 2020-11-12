/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Tran Nguyen Anh
 */


@Entity
@Table(name = "TaiKhoan")
public class TaiKhoan {

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

    /**
     * @return the matKhau
     */
    public String getMatKhau() {
        return matKhau;
    }

    /**
     * @param matKhau the matKhau to set
     */
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    /**
     * @return the quyenTC
     */
    public boolean isQuyenTC() {
        return quyenTC;
    }

    /**
     * @param quyenTC the quyenTC to set
     */
    public void setQuyenTC(boolean quyenTC) {
        this.quyenTC = quyenTC;
    }
    @Id
    @Column(name = "tentk")
    private String tenTK;
    
    @Column(name = "matkhau", length = 20, nullable = false)
    private String matKhau;
    
    @Column(name = "quyen")
    private boolean quyenTC;
    @Column(name = "salt")
    private String saltMK;
    
    public TaiKhoan(){}
    public TaiKhoan(String ten,String mk, boolean quyen, String salt){
        this.tenTK = ten;
        this.matKhau = mk;
        this.quyenTC = quyen;
        this.saltMK = salt;
    }
    /**
     * @return the saltMK
     */
    public String getSaltMK() {
        return saltMK;
    }

    /**
     * @param saltMK the saltMK to set
     */
    public void setSaltMK(String saltMK) {
        this.saltMK = saltMK;
    }
    
}
