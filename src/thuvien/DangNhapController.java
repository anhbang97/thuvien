/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuvien;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pojo.TaiKhoan;

/**
 * FXML Controller class
 *
 * @author Tran Nguyen Anh
 */
public class DangNhapController implements Initializable {
    
   private static final SessionFactory factory = HibernateUtil.getSessionFactory(); 
        
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    @FXML
    private JFXTextField txtTaiKhoan;
    @FXML
    private JFXPasswordField txtMatKhau;
    @FXML
    private JFXButton btnDangNhap;
    @FXML
    private VBox vboxDangNhap;
    @FXML
    private VBox vboxDangNhapThanhCong;
    @FXML
    private Text txtDangNhap;
    @FXML
    private JFXButton btnSach;
    @FXML
    private JFXButton btnDocGia;
    @FXML
    private JFXButton btnMuonTra;
    @FXML
    private JFXButton btnMatSach;
    @FXML
    private JFXButton btnTheDocGia;
    @FXML
    private JFXButton btnNhanVien;
    @FXML
    private JFXButton btnThongKe;
    @FXML
    private VBox vboxMain;
    @FXML
    private StackPane stackPane;
    public static String luu;
    public static boolean quyen;
    @FXML
    public void checkDangNhapHandler(ActionEvent event) throws IOException, NoSuchAlgorithmException{
     
            
        
    }
    @FXML
    public void chuyenSach(ActionEvent event) throws IOException, NoSuchAlgorithmException{
        boolean kq = this.checkDangNhap(this.txtTaiKhoan.getText(), 
                                        this.txtMatKhau.getText());
        if(kq == true){
        luu = txtTaiKhoan.getText();
        
     
        String mess = " Đăng nhập thành công!!!";
        System.out.println(mess);
        
            //Chuyển form
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLSach.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
            
        
        }
        else{
            String mess = "Thông tài khoản hoặc mật khẩu không hợp lệ !!!";
            System.out.println("Đăng nhập không thành công !!!");
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();
        }
            
    }
    
    
    
    @FXML
    public void thoatHandler(MouseEvent event){
        String mess = "Bạn có chắc chắn muốn thoát không ?";
        DialogMessenger.dialogThongBao2chon(this.stackPane,mess).show();

    }
    
    @FXML
    public void minHadler(MouseEvent event){
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setIconified(true);
    }
    
    @FXML
    
    
    
    //checkDangNhap
    public boolean checkDangNhap(String tk, String mk) throws NoSuchAlgorithmException{
        boolean kq = false;
        Session session = factory.openSession();
        String hql = "FROM TaiKhoan WHERE tenTK = :tenTK and "
                        + "matKhau = :matKhau and saltMK = :saltMK";
        Query q = session.createQuery(hql);
        q.setParameter("tenTK", tk);
        q.setParameter("matKhau", mk);
        q.setParameter("saltMK", this.convertHashToString(mk));
//        System.out.println(this.convertHashToString(mk));
        
        List<TaiKhoan> rs = q.list();
        session.close();
        if(rs.size() == 1){
            quyen = rs.get(0).isQuyenTC();
            kq = true;
        }
        
        
        return kq;
    }
    
    //Băm mật khẩu 32bit
    public static String convertHashToString(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(text.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
    
}
