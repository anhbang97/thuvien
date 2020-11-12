/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuvien;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import pojo.DocGia;
import pojo.MatSach;
import pojo.MuonTra;
import pojo.NhanVien;
import pojo.Sach;
import pojo.TaiKhoan;
import pojo.TheDocGia;
import pojo.ThongKe;

/**
 *
 * @author Tran Nguyen Anh
 */
public class Utils {
    
    private static final SessionFactory factory = HibernateUtil.getSessionFactory();
    public static List<Sach> getSachKW(String keyword){
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Sach.class);
        String f = String.format("%%%s%%", keyword);
        Criterion a = Restrictions.ilike("tenSach", f);
        Criterion b = Restrictions.ilike("maSach", f);
        Criterion c = Restrictions.ilike("tacGia", f);
        Criterion d = Restrictions.ilike("nhaXuatBan", f);
        Criterion e = Restrictions.ilike("theLoai", f);
        
        cr.add(Restrictions.or(a,b,c,d,e));
        List s = cr.list();
        session.close();
          return s;
    }
    public static List<Sach> getSachCB(String thuoctinh,String keyword){
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Sach.class);
        String f = String.format("%%%s%%", keyword);
       cr.add(Restrictions.ilike(thuoctinh, f));
       List s = cr.list();
        session.close();
          return s;
    }
    public static List<Sach> getSach(){        
        Session session = factory.openSession();
        String hql = "FROM Sach";
        Query q = session.createQuery(hql);

//        System.out.println(this.convertHashToString(mk));

        List<Sach> rs = q.list();
        session.close();

        return rs;
    }
    
    public static List<String> getAnhSach(String maSach){        
        Session session = factory.openSession();
        String hql = "select img FROM Sach WHERE maSach = :maSach";
        Query q = session.createQuery(hql);
        q.setParameter("maSach", maSach);

//        System.out.println(this.convertHashToString(mk));

        List<String> rs = q.list();
        session.close();

        return rs;
    }
    
//    public static List<String> getSach(){
//        Session session = factory.openSession();
//        String hql = "select maSach FROM Sach WHERE maSach = :maSach";
//        Query q = session.createQuery(hql);
//        q.setParameter("maSach", "TN001");
////        System.out.println(this.convertHashToString(mk));
//
//        List<String> rs = q.list();
//        session.close();
//        return rs;
//    }
    
    
    public static boolean addSach(Sach sach) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction trans = null;
        try {
            trans = session.beginTransaction();    
            session.save(sach);
            trans.commit();
        } catch (Exception ex) {
            if (trans != null)
                trans.rollback();
            return false;
        } finally {
            session.close();
        }
        
        return true;
    }

    public static boolean deleteSach(Sach sach) {
       Session session = factory.openSession();
       try {
           session.beginTransaction();
            session.delete(sach);
            session.getTransaction().commit();
            
            return true;
       } catch (Exception ex) {
           session.getTransaction().rollback();
           return false;
       }
   }
    
    public static List<DocGia> getDocGia(){        
        Session session = factory.openSession();
        String hql = "FROM DocGia";
        Query q = session.createQuery(hql);
        List<DocGia> rs = q.list();
        session.close();

        return rs;
    }
//    public static List<DocGia> getDocGia2() {
//        Session session = factory.openSession();
//        
//        Criteria cr = session.createCriteria(DocGia.class);
//        
//        List<DocGia> dg = cr.list();
//        
//        session.close();
//        
//        return dg;
//    }
    public static List<Integer> getMaThe(){        
        Session session = factory.openSession();
        String hql = "select soThe FROM TheDocGia";
        Query q = session.createQuery(hql);


        List<Integer> rs = q.list();
        session.close();

        return rs;
    }
    public static List<String> getTaiKhoan(){        
        Session session = factory.openSession();
        String hql = "select tenTK FROM TaiKhoan";
        Query q = session.createQuery(hql);
        List<String> rs = q.list();
        session.close();
        return rs;
    }
    
    
    public static boolean addDocGia(DocGia docgia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction trans = null;
        try {
            trans = session.beginTransaction();    
            session.save(docgia);
            trans.commit();
        } catch (Exception ex) {
            if (trans != null)
                trans.rollback();
            return false;
        } finally {
            session.close();
        }
        
        return true;
    }
    
    
    public static boolean deleteDocGia(DocGia docgia) {
       Session session = factory.openSession();
       try {
           session.beginTransaction();
//            for (Choice c: q.getChoices())
//                session.delete(c);
            session.delete(docgia);
            session.getTransaction().commit();
            
            return true;
       } catch (Exception ex) {
           session.getTransaction().rollback();
           return false;
       }
   }
    
    
    public static List<NhanVien> getNhanVien(){        
        Session session = factory.openSession();
        String hql = "From NhanVien";
        Query q = session.createQuery(hql);
        List<NhanVien> rs = q.list();
        session.close();
        return rs;
    }
    
    
    public static boolean addNhanVien(NhanVien nv) {
        Session session = factory.openSession();
        
        Transaction trans = null;
        try {
            trans = session.beginTransaction();    
            
            session.save(nv);
            trans.commit();
        } catch (Exception ex) {
            if (trans != null){
                trans.rollback();
                System.err.println("null");
            }
                
            System.out.println(ex);
            return false;
        } finally {
            session.close();
        }
        
        return true;
    }
    
    
    public static boolean deleteNhanVien(NhanVien nhanvien) {
       Session session = factory.openSession();
       try {
           session.beginTransaction();
           session.delete(nhanvien);
           session.getTransaction().commit();
            
           return true;
       } catch (Exception ex) {
           session.getTransaction().rollback();
           return false;
       }
   }
    
    
    
   public static void updateSach(Sach s){
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        session.saveOrUpdate(s);
        trans.commit();
        session.close();
    } 
   
   public static void updateDocGia(DocGia s){
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        session.saveOrUpdate(s);
        trans.commit();
        session.close();
    } 
   public static void updateNhanVien(NhanVien s){
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        session.saveOrUpdate(s);
        trans.commit();
        session.close();
    } 
   public static List<DocGia> getDocGiaKW(String keyword){
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(DocGia.class);
        String f = String.format("%%%s%%", keyword);
        Criterion a = Restrictions.sqlRestriction("madocgia LIKE '%"+keyword+"%' ");
        Criterion b = Restrictions.ilike("tenDG", f);
        Criterion c = Restrictions.ilike("gioiTinh", f);
        Criterion d = Restrictions.sqlRestriction("namsinh LIKE '%"+keyword+"%' ");
        Criterion e = Restrictions.ilike("diaChi", f);
        Criterion g = Restrictions.sqlRestriction("sothe LIKE '%"+keyword+"%' ");
        cr.add(Restrictions.or(a,b,c,d,e,g));
        List s = cr.list();
        session.close();
          return s;
    }
   
    public static List<NhanVien> getNhanVienKW(String keyword){
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(NhanVien.class);
        String f = String.format("%%%s%%", keyword);
        Criterion a = Restrictions.sqlRestriction("manhanvien LIKE '%"+keyword+"%' ");
        Criterion b = Restrictions.ilike("tenNV", f);
        Criterion c = Restrictions.ilike("gioiTinhNV", f);
        Criterion d = Restrictions.sqlRestriction("namsinh LIKE '%"+keyword+"%' ");
        Criterion e = Restrictions.ilike("diaChiNV", f);
        Criterion g = Restrictions.ilike("tenTK", f);
        cr.add(Restrictions.or(a,b,c,d,e,g));
        List s = cr.list();
        session.close();
          return s;
    }
    public static List<Integer> getTheMuon(){        
        Session session = factory.openSession();
        String hql = "SELECT soThe FROM DocGia";
        Query q = session.createQuery(hql);
        List<Integer> rs = q.list();
        session.close();

        return rs;
    }
    public static List<Integer> laySoTheTheoNgayKetThuc(Date ngay){
        Session session = factory.openSession();
        String hql = "Select soThe FROM TheDocGia WHERE DATE(nkt) < :ngay";
        Query q = session.createQuery(hql);
        q.setParameter("ngay", ngay);
        List rs = q.list();
        session.close();
        return  rs;
    }
    public static List<MuonTra> getMuonTra(){        
        Session session = factory.openSession();
        String hql = "FROM MuonTra";
        Query q = session.createQuery(hql);
        List<MuonTra> rs = q.list();
        session.close();

        return rs;
    }
    public static boolean muon(MuonTra m) {
        Session session = factory.openSession();
        
        Transaction trans = null;
        try {
            trans = session.beginTransaction();    
            
            session.save(m);
            trans.commit();
        } catch (Exception ex) {
            if (trans != null){
                trans.rollback();
                System.err.println("null");
            }
                
            System.out.println(ex);
            return false;
        } finally {
            session.close();
        }
        
        return true;
    }
    public static void updateMuon(MuonTra m){
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        session.saveOrUpdate(m);
        trans.commit();
        session.close();
    }
    
    public static int getSoLan(int so){
        TheDocGia the = new TheDocGia();
        the.setSoThe(so);
        Session session = factory.openSession();
        String hql = "SELECT COUNT(*) FROM MuonTra WHERE ngayTra = NULL AND the= :the";
        Query q = session.createQuery(hql);
        q.setParameter("the", the);
      
        List rs = q.list();
        session.close();

        return Integer.parseInt(rs.get(0).toString()) ;
    }
    
    public static List<MuonTra> getMuonTraChuaTra(){        
        Session session = factory.openSession();
        String hql = "FROM MuonTra WHERE ngayTra = NULL";
        Query q = session.createQuery(hql);
        List<MuonTra> rs = q.list();
        session.close();

        return rs;
    }
    
    public static List<MuonTra> getMuonTraKW(String keyword){
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Sach.class);
        String f = String.format("%%%s%%", keyword);
        Criterion a = Restrictions.ilike("the", f);
        cr.add(Restrictions.or(a));
        List s = cr.list();
        session.close();
          return s;
    }
    
    public static String getTenSach(String maSach){
        Session session = factory.openSession();
        String hql = "Select tenSach FROM Sach WHERE maSach = :maSach";
        Query q = session.createQuery(hql);
        q.setParameter("maSach", maSach);
        List rs = q.list();
        session.close();
        return String.valueOf(rs.get(0));
    }
    
    public static String getNhanVienTK (String tk){
        Session session = factory.openSession();
        String hql = "Select tenNV FROM NhanVien WHERE tenTK = :tenTK";
        Query q = session.createQuery(hql);
        q.setParameter("tenTK", tk);
        List rs = q.list();
        session.close();
        return String.valueOf(rs.get(0));
    }
    
    
    
    public static List<MuonTra> getMuonTraKW2(String keyword){
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(MuonTra.class);
        String f = String.format("%%%s%%", keyword);
        Criterion a = Restrictions.sqlRestriction("mamuon LIKE '%"+keyword+"%' ");
        
        Criterion b = Restrictions.sqlRestriction("sothemuon LIKE '%"+keyword+"%' ");
        
        Criterion c = Restrictions.sqlRestriction("masach LIKE '%"+keyword+"%' ");
        
        Criterion d = Restrictions.sqlRestriction("tensach LIKE '%"+keyword+"%' ");
        
        Criterion e = Restrictions.sqlRestriction("ngaymuon LIKE '%"+keyword+"%' ");
        
        Criterion g = Restrictions.sqlRestriction("tennhanvien LIKE '%"+keyword+"%' ");
        
        cr.add(Restrictions.or(a,b,c,d,e,g));
        List s = cr.list();
        session.close();
          return s;
    }
    public static List<Integer> getTheTrong(){        
        Session session = factory.openSession();
        String hql = "SELECT soThe FROM TheDocGia WHERE soThe NOT IN (SELECT soThe FROM DocGia)";
        Query q = session.createQuery(hql);
        List<Integer> rs = q.list();
        session.close();

        return rs;
    }
    public static List<String> getTKTrong(){        
        Session session = factory.openSession();
        String hql = "SELECT tenTK FROM TaiKhoan WHERE tenTk NOT IN (SELECT tenTK FROM NhanVien)";
        Query q = session.createQuery(hql);
        List<String> rs = q.list();
        session.close();

        return rs;
    }
    public static List<String> getTheLoai(){
        Session session = factory.openSession();
        String hql = "SELECT ten FROM TheLoai";
        Query q = session.createQuery(hql);
        List<String> rs = q.list();
        session.close();

        return rs;
    }
    public static List<TheDocGia> getTheDocGia(){        
        Session session = factory.openSession();
        String hql = "FROM TheDocGia";
        Query q = session.createQuery(hql);
        List<TheDocGia> rs = q.list();
        session.close();
        return rs;
    }
    public static boolean addTheDocGia(TheDocGia tg) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction trans = null;
        try {
            trans = session.beginTransaction();    
            session.save(tg);
            trans.commit();
        } catch (Exception ex) {
            if (trans != null)
                trans.rollback();
            return false;
        } finally {
            session.close();
        }
        return true;
    }
     public static void updateThe(TheDocGia m){
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        session.saveOrUpdate(m);
        trans.commit();
        session.close();
    }
     
    public static boolean addTaiKhoan(TaiKhoan tk) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction trans = null;
        try {
            trans = session.beginTransaction();    
            session.save(tk);
            trans.commit();
        } catch (Exception ex) {
            if (trans != null)
                trans.rollback();
            return false;
        } finally {
            session.close();
        }
        return true;
    }
     public static void updateTK(TaiKhoan m){
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        session.saveOrUpdate(m);
        trans.commit();
        session.close();
    }
     public static List<TaiKhoan> getTaiKhoan2(){        
        Session session = factory.openSession();
        String hql = "FROM TaiKhoan";
        Query q = session.createQuery(hql);
        List<TaiKhoan> rs = q.list();
        session.close();
        return rs;
    }

    public static List<TheDocGia> getTheDocGiaKW(String keyword){
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(TheDocGia.class);
        String f = String.format("%%%s%%", keyword);
        
        Criterion a = Restrictions.sqlRestriction("sothe LIKE '%"+keyword+"%' ");
        
        Criterion b = Restrictions.sqlRestriction("ngayketthuc LIKE '%"+keyword+"%' ");
        cr.add(Restrictions.or(a,b));
        List s = cr.list();
        session.close();
          return s;
    }
    public static List<TaiKhoan> getTaiKhoanKW(String keyword){
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(TaiKhoan.class);
        String f = String.format("%%%s%%", keyword);
        
        Criterion a = Restrictions.sqlRestriction("tentk LIKE '%"+keyword+"%' ");
        
        Criterion b = Restrictions.sqlRestriction("matkhau LIKE '%"+keyword+"%' ");
        
        Criterion c = Restrictions.sqlRestriction("quyen LIKE '%"+keyword+"%' ");
        cr.add(Restrictions.or(a,b,c));
        List s = cr.list();
        session.close();
          return s;
    }
    
    
      public static List<MatSach> getMatSach(){        
        Session session = factory.openSession();
        String hql = "FROM MatSach";
        Query q = session.createQuery(hql);
        List<MatSach> rs = q.list();
        session.close();
        return rs;
    }
     public static boolean baoMatSach(MatSach m) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction trans = null;
        try {
            trans = session.beginTransaction();    
            session.save(m);
            trans.commit();
        } catch (Exception ex) {
            if (trans != null)
                trans.rollback();
            return false;
        } finally {
            session.close();
        }
        return true;
    }
     public static boolean deleteMuonTra(MuonTra m) {
       Session session = factory.openSession();
       try {
           session.beginTransaction();
//            for (Choice c: q.getChoices())
//                session.delete(c);
            session.delete(m);
            session.getTransaction().commit();
            
            return true;
       } catch (Exception ex) {
           session.getTransaction().rollback();
           return false;
       }
   }
     
     
    public static int getDemSach(){        
        Session session = factory.openSession();
        String hql = "Select count(*) FROM Sach";
        Query q = session.createQuery(hql);
        List rs = q.list();
        session.close();
        return Integer.parseInt(rs.get(0).toString()) ;
    }
    
    public static int getDemTheLoai(){        
        Session session = factory.openSession();
        String hql = "Select count(*) FROM TheLoai";
        Query q = session.createQuery(hql);
        List rs = q.list();
        session.close();
        return Integer.parseInt(rs.get(0).toString()) ;
    }
    
    public static int getDemTheDocGia(){        
        Session session = factory.openSession();
        String hql = "Select count(*) FROM TheLoai";
        Query q = session.createQuery(hql);
        List rs = q.list();
        session.close();
        return Integer.parseInt(rs.get(0).toString()) ;
    }
    
    public static int thongKeTheoThang(int thang){
        Session session = factory.openSession();
        String hql = "Select COUNT(*) FROM MuonTra WHERE MONTH(ngayMuon)= :thang";
        Query q = session.createQuery(hql);
        q.setParameter("thang", thang);
        List rs = q.list();
        session.close();
        return  Integer.parseInt(rs.get(0).toString());
    }
    public static int thongKeTheoNgay(Date ngay){
        Session session = factory.openSession();
        String hql = "Select COUNT(*) FROM MuonTra WHERE DATE(ngayMuon)= :ngay";
        Query q = session.createQuery(hql);
        q.setParameter("ngay", ngay);
        List rs = q.list();
        session.close();
        return  Integer.parseInt(rs.get(0).toString());
    }
    public static int thongKeTheoNam(int nam){
        Session session = factory.openSession();
        String hql = "Select COUNT(*) FROM MuonTra WHERE YEAR(ngayMuon)= :nam";
        Query q = session.createQuery(hql);
        q.setParameter("nam", nam);
        List rs = q.list();
        session.close();
        return  Integer.parseInt(rs.get(0).toString());
    }
    public static int thongKeTheoQuy(int quy){
        Session session = factory.openSession();
        String hql = "Select COUNT(*) FROM MuonTra WHERE QUARTER(ngayMuon)= :quy";
        Query q = session.createQuery(hql);
        q.setParameter("quy", quy);
        List rs = q.list();
        session.close();
        return  Integer.parseInt(rs.get(0).toString());
    }
    
    //tra
    public static int thongKeTraTheoThang(int thang){
        Session session = factory.openSession();
        String hql = "Select COUNT(*) FROM MuonTra WHERE Month(ngayTra)= :thang";
        Query q = session.createQuery(hql);
        q.setParameter("thang", thang);
        List rs = q.list();
        session.close();
        return  Integer.parseInt(rs.get(0).toString());
    }
    
    public static int thongKeMuonTheoThangNam(int thang, int nam){
        Session session = factory.openSession();
        String hql = "Select COUNT(*) FROM MuonTra WHERE Month(ngayMuon)= :thang AND YEAR(ngayMuon) = :nam";
        Query q = session.createQuery(hql);
        q.setParameter("thang", thang);
        q.setParameter("nam", nam);
        List rs = q.list();
        session.close();
        return  Integer.parseInt(rs.get(0).toString());
    }
    public static int thongKeTraTheoNgay(Date ngay){
        Session session = factory.openSession();
        String hql = "Select COUNT(*) FROM MuonTra WHERE DATE(ngayTra)= :ngay";
        Query q = session.createQuery(hql);
        q.setParameter("ngay", ngay);
        List rs = q.list();
        session.close();
        return  Integer.parseInt(rs.get(0).toString());
    }
    public static int thongKeTraTheoNam(int nam){
        Session session = factory.openSession();
        String hql = "Select COUNT(*) FROM MuonTra WHERE YEAR(ngayTra)= :nam";
        Query q = session.createQuery(hql);
        q.setParameter("nam", nam);
        List rs = q.list();
        session.close();
        return  Integer.parseInt(rs.get(0).toString());
    }
    
    public static int thongKeTraTheoQuy(int quy){
        Session session = factory.openSession();
        String hql = "Select COUNT(*) FROM MuonTra WHERE QUARTER(ngayTra)= :quy";
        Query q = session.createQuery(hql);
        q.setParameter("quy", quy);
        List rs = q.list();
        session.close();
        return  Integer.parseInt(rs.get(0).toString());
    }
    public static int thongKeMuonTheoQuyNam(int quy, int nam){
        Session session = factory.openSession();
        String hql = "Select COUNT(*) FROM MuonTra WHERE QUARTER(ngayMuon)= :quy AND YEAR(ngayMuon) = :nam";
        Query q = session.createQuery(hql);
        q.setParameter("quy", quy);
        q.setParameter("nam", nam);
        List rs = q.list();
        session.close();
        return  Integer.parseInt(rs.get(0).toString());
    }
    
    public static int thongKeMatTheoQuy(int quy){
        Session session = factory.openSession();
        String hql = "Select COUNT(*) FROM MatSach WHERE QUARTER(ngayGhiNhan)= :quy";
        Query q = session.createQuery(hql);
        q.setParameter("quy", quy);
        List rs = q.list();
        session.close();
        return  Integer.parseInt(rs.get(0).toString());
    }
    
    public static boolean addThongKe(ThongKe tk) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction trans = null;
        try {
            trans = session.beginTransaction();    
            session.save(tk);
            trans.commit();
        } catch (Exception ex) {
            if (trans != null)
                trans.rollback();
            return false;
        } finally {
            session.close();
        }
        return true;
    }
    
    public static int kiemtraThongKe(int thang, int nam){
        Session session = factory.openSession();
        String hql = "Select COUNT(*) FROM ThongKe WHERE thang= :thang AND nam= :nam";
        Query q = session.createQuery(hql);
        q.setParameter("thang", thang);
        q.setParameter("nam", nam);
        List rs = q.list();
        session.close();
        return  Integer.parseInt(rs.get(0).toString());
    }
    
    public static void updateThongKe(ThongKe m){
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        session.saveOrUpdate(m);
        trans.commit();
        session.close();
    }
    
    
    public static List<ThongKe> getThongKeMuonTheoThang(int nam){        
        Session session = factory.openSession();
        String hql = "FROM ThongKe WHERE nam=:nam";
        Query q = session.createQuery(hql);
        q.setParameter("nam", nam);
        List rs = q.list();
        session.close();
        return rs;
    }
    
    
    public static List<ThongKe> getThongKe(int nam, int thang){        
        Session session = factory.openSession();
        String hql = "FROM ThongKe WHERE nam=:nam and thang=:thang";
        Query q = session.createQuery(hql);
        q.setParameter("nam", nam);
        q.setParameter("thang", thang);
        List rs = q.list();
        session.close();
        return rs;
    }
    
    
    public static int getTongMatSach(){        
        Session session = factory.openSession();
        String hql = "FROM MatSach";
        Query q = session.createQuery(hql);
   
        List rs = q.list();
        session.close();
        return Integer.parseInt(rs.get(0).toString());
    }
    public static int getTongChuaOrDaTraSach(String s){        
        Session session = factory.openSession();
        String hql = "Select count(*) FROM MuonTra Where ghiChu =:ghiChu";
        Query q = session.createQuery(hql);
        q.setParameter("ghiChu", s);
        List rs = q.list();
        session.close();
        return Integer.parseInt(rs.get(0).toString());
    }
    
    public static List<MuonTra> demMaSach(){        
        Session session = factory.openSession();
        String hql = "SELECT maSachMuon,count(*) FROM MuonTra GROUP BY maSachMuon";
        Query q = session.createQuery(hql);
        List rs = q.list();
        session.close();
        return rs;
    }
    
    public static List<Sach> getMuonTraTang(){        
        Session session = factory.openSession();
        String hql = "SELECT maSachMuon FROM MuonTra GROUP BY maSachMuon ORDER BY count(*) DESC";
        Query q = session.createQuery(hql);
        List rs = q.list();
        session.close();

        return rs;
    }
    
    public static List<Integer> getDemMuonTraTang(){        
        Session session = factory.openSession();
        String hql = "SELECT count(*) FROM MuonTra GROUP BY maSachMuon ORDER BY count(*) DESC";
        Query q = session.createQuery(hql);
        List rs = q.list();
        session.close();

        return rs;
    }
    
    
    public static int getDemTongTraTang(){        
        Session session = factory.openSession();
        String hql = "SELECT count(*) FROM MuonTra";
        Query q = session.createQuery(hql);
        List rs = q.list();
        session.close();

        return Integer.parseInt(rs.get(0).toString());
    }
    
    
    
    public static List<NhanVien> getThongTinDangNhap(String tenTK){
        Session session = factory.openSession();
        String hql = "FROM NhanVien WHERE tenTK = :tenTK";
        Query q = session.createQuery(hql);
        q.setParameter("tenTK", tenTK);
        List rs = q.list();
        session.close();
        return rs;
    }
    
    
     public static List<Integer> getNamThongKe(){
        Session session = factory.openSession();
        String hql = "Select nam FROM ThongKe GROUP BY nam ORDER BY count(*) DESC";
        Query q = session.createQuery(hql);
        List rs = q.list();
        session.close();
        return rs;
    }
    
     public static List<Integer> getSoThangTrongNamThongKe(){
        Session session = factory.openSession();
        String hql = "Select count(*) FROM ThongKe GROUP BY nam ORDER BY count(*) DESC";
        Query q = session.createQuery(hql);
        List rs = q.list();
        session.close();
        return rs;
    }
    
     
     
    public static int  getThongKeTraTheoMuon(int thang, int nam){        
        Session session = factory.openSession();
        String hql = "Select count(*) FROM MuonTra WHERE Month(ngayMuon)=:thang AND YEAR(ngayMuon) = :nam AND ngayTra <> :ngayTra";
        Query q = session.createQuery(hql);
        q.setParameter("nam", nam);
        q.setParameter("thang", thang);
        q.setParameter("ngayTra", null);
        List rs = q.list();
        session.close();
        return Integer.parseInt(rs.get(0).toString());
    } 
     
     
}
