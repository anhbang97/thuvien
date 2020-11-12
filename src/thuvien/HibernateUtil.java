/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuvien;

import org.hibernate.HibernateException;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import pojo.DocGia;
import pojo.MatSach;
import pojo.MuonTra;
import pojo.NhanVien;
import pojo.Sach;
import pojo.TaiKhoan;
import pojo.TheDocGia;
import pojo.TheLoai;
import pojo.ThongKe;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Tran Nguyen Anh
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            Configuration configure = new Configuration();
            configure.configure("hibernate.cfg.xml");
            configure.addAnnotatedClass(TaiKhoan.class);
            configure.addAnnotatedClass(Sach.class);
            configure.addAnnotatedClass(NhanVien.class);
            configure.addAnnotatedClass(DocGia.class);
            configure.addAnnotatedClass(TheDocGia.class);
            configure.addAnnotatedClass(MuonTra.class);
            configure.addAnnotatedClass(TheLoai.class);
            configure.addAnnotatedClass(MatSach.class);
            configure.addAnnotatedClass(ThongKe.class);
            StandardServiceRegistryBuilder builder
                    = new StandardServiceRegistryBuilder()
                            .applySettings(configure.getProperties());
            sessionFactory = configure.buildSessionFactory(builder.build());
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
//            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
