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
 * @author Admin
 */
@Entity
@Table(name = "theloaisach")
public class TheLoai {
    @Id
    @Column(name = "matheloai")
    private Integer ma;
    @Column(name = "tentheloai")
    private String ten;

    /**
     * @return the ma
     */
    public Integer getMa() {
        return ma;
    }

    /**
     * @param ma the ma to set
     */
    public void setMa(Integer ma) {
        this.ma = ma;
    }

    /**
     * @return the ten
     */
    public String getTen() {
        return ten;
    }

    /**
     * @param ten the ten to set
     */
    public void setTen(String ten) {
        this.ten = ten;
    }
}
