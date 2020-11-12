/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "thedocgia")
public class TheDocGia{

    /**
     * @param soThe the soThe to set
     */
    public void setSoThe(int soThe) {
        this.soThe = soThe;
    }
    @Id
    @Column(name = "sothe")
    private int soThe;
//    @Column(name = "ngaybatdau")
//    private Date nbd;
    @Column(name = "ngayketthuc")
    private Date nkt;
    
    @OneToMany(mappedBy = "the")
    private List<MuonTra> dsMuon;
//    @OneToOne(mappedBy = "the")
//    private DocGia dg;
    
    /**
     * @return the soThe
     */
    
    
    public TheDocGia(){};
    public TheDocGia(int so,Date Ngay){
        this.soThe = so;
        this.nkt = Ngay;
    }
    public int getSoThe() {
        return soThe;
    }

//    /**
//     * @return the nbd
//     */
//    public Date getNbd() {
//        return nbd;
//    }
//
//    /**
//     * @param nbd the nbd to set
//     */
//    public void setNbd(Date nbd) {
//        this.nbd = nbd;
//    }

    /**
     * @return the nkt
     */
    public Date getNkt() {
        return nkt;
    }

    /**
     * @param nkt the nkt to set
     */
    public void setNkt(Date nkt) {
        this.nkt = nkt;
    }

//    /**
//     * @return the dg
//     */
//    public DocGia getDg() {
//        return dg;
//    }
//
//    /**
//     * @param dg the dg to set
//     */
//    public void setDg(DocGia dg) {
//        this.dg = dg;
//    }

    /**
     * @return the dsMuon
     */
    public List<MuonTra> getDsMuon() {
        return dsMuon;
    }

    /**
     * @param dsMuon the dsMuon to set
     */
    public void setDsMuon(List<MuonTra> dsMuon) {
        this.dsMuon = dsMuon;
    }

    @Override
    public String toString() {
        String m = String.valueOf(soThe);
        return m; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
