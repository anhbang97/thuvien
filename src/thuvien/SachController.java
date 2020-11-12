/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuvien;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import static java.awt.PageAttributes.MediaType.E;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.E;
import static java.lang.StrictMath.E;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import static javax.print.attribute.standard.MediaSize.Engineering.E;
import static jdk.nashorn.internal.objects.NativeMath.E;
import org.controlsfx.control.textfield.TextFields;
import pojo.DocGia;
import pojo.MatSach;
import pojo.MuonTra;
import pojo.NhanVien;
import pojo.Sach;
import pojo.TaiKhoan;
import pojo.TheDocGia;
import pojo.ThongKe;

/**
 * FXML Controller class
 *
 * @author Ánh
 */
public class SachController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    private int chucNang = 0;
    private int themSua = 0;
    private int luuThe = 0;
    private int luuTaiKhoan = 0;
    @FXML
    private JFXTextField txtMaNVDangNhap;
    @FXML
    private JFXTextField txtHTDangNhap;
    @FXML
    private JFXTextField txtGTDangNhap;
    @FXML
    private JFXTextField txtDCDangNhap;
    @FXML
    private JFXTextField txtCVDangNhap;
    @FXML
    private JFXTextField txtTKDangNhap;
    @FXML
    private JFXTextField txtNSDangNhap;
    @FXML
    private JFXButton btnDangXuat;
    @FXML
    private JFXButton btnXemThem;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boolean xe = false;
        TranslateTransition t = new TranslateTransition();
        t.setDuration(javafx.util.Duration.seconds(3));
        t.setCycleCount(10000);
        t.setNode(imgdong);
        t.setToX(500);

        t.play();
//        System.out.println(Utils.thongKeTraTheoThangNam(1,2019));

        System.out.println("Quyền truy cập(true:admin, false:nhanvien)" + DangNhapController.quyen);
        xemThongKe();
        if ((DangNhapController.quyen) == false) {
            btnNhanVien.setDisable(true);
            btnThongKe.setDisable(true);
            btnTaiKhoan.setDisable(true);
            txtCVDangNhap.setText("Nhân viên");
        } else {
            txtCVDangNhap.setText("Quản lý");
        }
        NhanVien dn = Utils.getThongTinDangNhap(DangNhapController.luu).get(0);
        txtHTDangNhap.setText(dn.getTenNV());
        txtDCDangNhap.setText(dn.getDiaChiNV());
        txtMaNVDangNhap.setText(String.valueOf(dn.getMaNV()));
        txtTKDangNhap.setText(DangNhapController.luu);
        txtGTDangNhap.setText(dn.getGioiTinhNV());
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String d = f.format(dn.getNamSinhNV());
        txtNSDangNhap.setText(d);

        try {
            loadTheDocGia(Utils.getTheDocGia());
            loadTaiKhoan(Utils.getTaiKhoan2());
            loadSach(Utils.getSach());
            loadSachMuon(Utils.getSach());
            loadMuonTra(Utils.getMuonTra());
            loadMatSach(Utils.getMatSach());
            goiY();
            //tim tất cả
            this.txtTim.textProperty().addListener(e -> {
                List<Sach> s = Utils.getSachKW(this.txtTim.getText());
                reloadTbSach(s);
            });

        } catch (SQLException ex) {
            Logger.getLogger(SachController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //TimSach
        loadDocGia(Utils.getDocGia());
        loadNhanVien(Utils.getNhanVien());
        this.txtTimDocGia.textProperty().addListener(et -> {
            List<DocGia> dg = Utils.getDocGiaKW(this.txtTimDocGia.getText());
            reloadTbDocGia(dg);
        });
        this.txtTimSachMuon.textProperty().addListener(et -> {
            List<Sach> sach = Utils.getSachKW(this.txtTimSachMuon.getText());
            reloadTbSachMuon(sach);
        });
        this.txtTimNhanVien.textProperty().addListener(et -> {
            List<NhanVien> nv = Utils.getNhanVienKW(this.txtTimNhanVien.getText());
            reloadTbNhanVien(nv);
        });
        this.txtTimSachTra.textProperty().addListener(et -> {
            List<MuonTra> n = Utils.getMuonTraKW2(this.txtTimSachTra.getText());
            reloadTbMuonTra(n);
        });
        this.txtTimTheDocGia.textProperty().addListener(et -> {
            List<TheDocGia> tg = Utils.getTheDocGiaKW(this.txtTimTheDocGia.getText());
            reloadTbTheDocGia(tg);
        });
        this.txtTimTaiKhoan.textProperty().addListener(et -> {
            List<TaiKhoan> tg = Utils.getTaiKhoanKW(this.txtTimTaiKhoan.getText());
            reloadTbTaiKhoan(tg);
        });
        this.list = FXCollections.observableArrayList("---- Tất cả ----", "Mã Sách",
                "Tên Sách", "Tác Giả", "Thể Loại", "Nhà Xuất Bản");

        this.cbTim.setItems(list);
        this.cbTim.setValue("---- Tất cả ----");

        EventHandler<ActionEvent> event
                = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent et) {
                try {
                    timSach();
                } catch (SQLException ex) {
                    Logger.getLogger(SachController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        };
        btnXemThem.setOnMouseClicked(et -> {
            vboxSach.setVisible(true);
            vboxHome.setVisible(false);
            btnDangXuat.setVisible(false);
        });
        btnSach.setOnMouseClicked(et -> {
            vboxSach.setVisible(true);
            vboxDocGia.setVisible(false);
            vboxNhanVien.setVisible(false);
            vboxMuon.setVisible(false);
            vboxTra.setVisible(false);
            vboxTheDocGia.setVisible(false);
            chucNang = 1;
            hboxSach.setDisable(true);
            hboxMuonTra.setVisible(false);
            hboxThemXoaSua.setVisible(true);
            btnThem.setVisible(true);
            btnSua.setVisible(false);
            btnXoa.setVisible(false);
            vboxTaiKhoan.setVisible(false);
            vboxMatSach.setVisible(false);
            vboxThongKe.setVisible(false);
            btnDangXuat.setVisible(false);
            vboxHome.setVisible(false);

        });

        btnDocGia.setOnMouseClicked(et -> {
            vboxDocGia.setVisible(true);
            vboxSach.setVisible(false);
            vboxNhanVien.setVisible(false);
            vboxMuon.setVisible(false);
            vboxTra.setVisible(false);
            chucNang = 2;
            hboxDocGia.setDisable(true);
            hboxMuonTra.setVisible(false);
            hboxThemXoaSua.setVisible(true);
            btnThem.setVisible(true);
            btnSua.setVisible(false);
            btnXoa.setVisible(false);
            vboxTheDocGia.setVisible(false);
            vboxTaiKhoan.setVisible(false);
            vboxMatSach.setVisible(false);
            vboxThongKe.setVisible(false);
            btnDangXuat.setVisible(false);
            vboxHome.setVisible(false);
        });

        btnNhanVien.setOnMouseClicked(et -> {
            vboxDocGia.setVisible(false);
            vboxSach.setVisible(false);
            vboxNhanVien.setVisible(true);
            vboxMuon.setVisible(false);
            vboxTra.setVisible(false);
            chucNang = 3;
            hboxNhanVien.setDisable(true);
            hboxMuonTra.setVisible(false);
            hboxThemXoaSua.setVisible(true);
            btnThem.setVisible(true);
            btnSua.setVisible(false);
            btnXoa.setVisible(false);
            vboxTheDocGia.setVisible(false);
            vboxTaiKhoan.setVisible(false);
            vboxMatSach.setVisible(false);
            vboxThongKe.setVisible(false);
            btnDangXuat.setVisible(false);
            vboxHome.setVisible(false);
        });

        btnMuonTra.setOnMouseClicked(et -> {
            vboxDocGia.setVisible(false);
            vboxSach.setVisible(false);
            vboxNhanVien.setVisible(false);
            vboxMuon.setVisible(true);
            vboxTra.setVisible(false);
            chucNang = 4;
            hboxMuonTra.setVisible(true);
            hboxThemXoaSua.setVisible(false);
            vboxTheDocGia.setVisible(false);
            vboxTaiKhoan.setVisible(false);
            vboxMatSach.setVisible(false);
            vboxThongKe.setVisible(false);
            btnDangXuat.setVisible(false);
            vboxHome.setVisible(false);

            goiY();

            try {
                loadSachMuon(Utils.getSach());
            } catch (SQLException ex) {
                Logger.getLogger(SachController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnCNMuon.setOnMouseClicked(et -> {
            vboxDocGia.setVisible(false);
            vboxSach.setVisible(false);
            vboxNhanVien.setVisible(false);
            vboxMuon.setVisible(true);
            vboxTra.setVisible(false);
            chucNang = 4;
            hboxMuonTra.setVisible(true);
            hboxThemXoaSua.setVisible(false);
            vboxTheDocGia.setVisible(false);
            vboxTaiKhoan.setVisible(false);
            vboxMatSach.setVisible(false);
            vboxThongKe.setVisible(false);
            btnDangXuat.setVisible(false);
            vboxHome.setVisible(false);

        });
        btnCNTra.setOnMouseClicked(et -> {
            vboxDocGia.setVisible(false);
            vboxSach.setVisible(false);
            vboxNhanVien.setVisible(false);
            vboxMuon.setVisible(false);
            vboxTra.setVisible(true);
            chucNang = 4;
            hboxMuonTra.setVisible(true);
            hboxThemXoaSua.setVisible(false);
            vboxTheDocGia.setVisible(false);
            vboxTaiKhoan.setVisible(false);
            vboxMatSach.setVisible(false);
            vboxThongKe.setVisible(false);
            btnDangXuat.setVisible(false);
            vboxHome.setVisible(false);
        });
        btnTheDocGia.setOnMouseClicked(et -> {
            vboxTheDocGia.setVisible(true);
            vboxDocGia.setVisible(false);
            vboxSach.setVisible(false);
            vboxNhanVien.setVisible(false);
            vboxMuon.setVisible(false);
            vboxTra.setVisible(false);
            hboxMuonTra.setVisible(false);
            hboxThemXoaSua.setVisible(false);
            vboxTaiKhoan.setVisible(false);
            vboxTheDocGia.setVisible(true);
            btnGiaHan.setVisible(false);
            vboxMatSach.setVisible(false);
            vboxThongKe.setVisible(false);
            btnDangXuat.setVisible(false);
            vboxHome.setVisible(false);
            this.txtSoTheDocGia.setDisable(true);
            this.txtNgaykt.setDisable(true);
            

        });
        btnTaiKhoan.setOnMouseClicked(et -> {
            vboxTaiKhoan.setVisible(true);
            vboxTheDocGia.setVisible(false);
            vboxDocGia.setVisible(false);
            vboxSach.setVisible(false);
            vboxNhanVien.setVisible(false);
            vboxMuon.setVisible(false);
            vboxTra.setVisible(false);
            vboxTheDocGia.setVisible(false);
            btnDoitk.setVisible(false);
            vboxMatSach.setVisible(false);
            hboxMuonTra.setVisible(false);
            hboxThemXoaSua.setVisible(false);
            vboxThongKe.setVisible(false);
            btnDangXuat.setVisible(false);
            vboxHome.setVisible(false);
            txtTaiKhoan2.setDisable(true);
            txtMatKhau2.setDisable(true);
            rdQL.setDisable(true);
            rdNV.setDisable(true);
        });
        btnMatSach.setOnMouseClicked(et -> {
            vboxTaiKhoan.setVisible(false);
            vboxTheDocGia.setVisible(false);
            vboxDocGia.setVisible(false);
            vboxSach.setVisible(false);
            vboxNhanVien.setVisible(false);
            vboxMuon.setVisible(false);
            vboxTra.setVisible(false);
            vboxTheDocGia.setVisible(false);
            btnDoitk.setVisible(false);
            vboxMatSach.setVisible(true);
            hboxMuonTra.setVisible(false);
            hboxThemXoaSua.setVisible(false);
            vboxThongKe.setVisible(false);
            btnDangXuat.setVisible(false);
            vboxHome.setVisible(false);
        });

        btnThongKe.setOnMouseClicked(et -> {
            vboxTaiKhoan.setVisible(false);
            vboxTheDocGia.setVisible(false);
            vboxDocGia.setVisible(false);
            vboxSach.setVisible(false);
            vboxNhanVien.setVisible(false);
            vboxMuon.setVisible(false);
            vboxTra.setVisible(false);
            vboxTheDocGia.setVisible(false);
            btnDoitk.setVisible(false);
            vboxMatSach.setVisible(false);
            hboxMuonTra.setVisible(false);
            hboxThemXoaSua.setVisible(false);
            vboxThongKe.setVisible(true);
            thongKe();
            xemThongKe();
            btnDangXuat.setVisible(false);
            vboxHome.setVisible(false);
        });
        this.cbTim.setOnAction(event);
        List<Integer> the = Utils.getTheTrong();
        this.txtTheDocGia.getItems().addAll(the);
        List<String> tk = Utils.getTKTrong();
        this.txtTaiKhoan.getItems().addAll(tk);
        List<String> tl = Utils.getTheLoai();
        this.txtTheLoai.getItems().addAll(tl);
        btnSua.setOnMouseClicked(et -> {
            themSua = 2;
            switch (chucNang) {
                case 1:
                    hboxSach.setDisable(false);
                    break;
                case 2:
                    hboxDocGia.setDisable(false);
                    break;
                case 3:
                    hboxNhanVien.setDisable(false);
                    break;
            }
        });
        btnThem.setOnMouseClicked((MouseEvent et) -> {
            themSua = 1;
            switch (chucNang) {
                case 1:
                    hboxSach.setDisable(false);
                    txtMaSach.setText(null);
                    txtTenSach.setText(null);
                    txtTheLoai.setValue(null);
                    txtNhaXuatBan.setText(null);
                    txtTacGia.setText(null);
                    txtSoLuong.setText(null);
                    imgAnh.setImage(null);
                    break;
                case 2:
                    hboxDocGia.setDisable(false);
                    txtMaDocGia.setText(null);
                    txtTenDocGia.setText(null);
                    txtNgaySinh.setValue(null);
                    txtDiaChi.setText(null);
                    this.listMaThe = FXCollections.observableArrayList(Utils.getTheTrong());
                    this.txtTheDocGia.setItems(listMaThe);

                    break;
                default:
                    hboxNhanVien.setDisable(false);
                    txtMaNhanVien.setText(null);
                    txtTenNhanVien.setText(null);
                    txtNgaySinhNV.setValue(null);
                    txtDiaChiNV.setText(null);
                    this.listTaiKhoan = FXCollections.observableArrayList(Utils.getTKTrong());
                    this.txtTaiKhoan.setItems(listTaiKhoan);

                    break;
            }
        });

        tgbtTra.setOnMouseClicked(et -> {
            if (tgbtTra.isSelected()) {
                loadMuonTra(Utils.getMuonTraChuaTra());
                tgbtTra.setText("Chưa Trả");
            } else {
                loadMuonTra(Utils.getMuonTra());
                tgbtTra.setText("Tất Cả  ");
            }
        });
        btnThemThe.setOnMouseClicked((MouseEvent et) -> {
            luuThe = 1;
            this.txtSoTheDocGia.setText(null);
            this.txtNgaykt.setValue(null);
            this.txtSoTheDocGia.setDisable(false);
            this.txtNgaykt.setDisable(false);
            btnLuuThe.setDisable(false);
        });
        btnGiaHan.setOnMouseClicked((MouseEvent et) -> {
            luuThe = 2;
            this.txtSoTheDocGia.setDisable(false);
            this.txtNgaykt.setDisable(false);
            btnLuuThe.setDisable(false);
        });
        btnTaotk.setOnMouseClicked((MouseEvent et) -> {
            luuTaiKhoan = 1;
            this.txtTaiKhoan2.setText(null);
            this.txtMatKhau2.setText(null);
            this.rdQL.setSelected(true);
            this.txtTaiKhoan2.setDisable(false);
            this.txtMatKhau2.setDisable(false);
            this.rdQL.setDisable(false);
            this.rdNV.setDisable(false);// cần cho ngày hiện tại không ta? ngày kết thúc mà !! ý là mượn trả chỉnh lại chưa ? !!!!
        });
        btnDoitk.setOnMouseClicked((MouseEvent et) -> {
            luuTaiKhoan = 2;
            this.rdQL.setSelected(true);
            this.txtTaiKhoan2.setDisable(false);
            this.txtMatKhau2.setDisable(false);
            this.rdQL.setDisable(false);
            this.rdNV.setDisable(false);
        });

        lbSLSach.setText(String.valueOf(Utils.getDemSach()));
        lbTheLoai.setText(String.valueOf(Utils.getDemTheLoai()));

    }
    @FXML
    private VBox vboxThongKe;
    @FXML
    private Label lbSLSach;
    @FXML
    private Label lbTheLoai;
    @FXML
    private JFXToggleButton tgbtTra;
    @FXML
    private JFXTextField txtTimSachTra;
    @FXML
    private HBox hboxThemXoaSua;
    @FXML
    private HBox hboxMuonTra;
    @FXML
    private JFXTextField txtTimSachMuon;
    @FXML
    private JFXButton btnXoa;
    @FXML
    private JFXButton btnThem;
    @FXML
    private JFXButton btnSua;
    @FXML
    private VBox vboxSach;
    @FXML
    private VBox vboxDocGia;
    @FXML
    TableView<Sach> tbvSach;
    @FXML
    private TableColumn<Sach, String> tbcMaSach;
    @FXML
    private TableColumn<Sach, String> tbcTenSach;
    @FXML
    private TableColumn<Sach, String> tbcTacGia;
    @FXML
    private TableColumn<Sach, String> tbcTheLoai;
    @FXML
    private TableColumn<Sach, String> tbcNxb;
    @FXML
    private TableColumn<Sach, Integer> tbcSoLuong;

    @FXML
    private VBox vboxNhanVien;
    @FXML
    private VBox vboxMuon;
    @FXML
    private VBox vboxTra;
    @FXML
    TableView<DocGia> tbvDocGia;
    @FXML
    private TableColumn<DocGia, String> tbcMaDocGia;
    @FXML
    private TableColumn<DocGia, String> tbcTenDocGia;
    @FXML
    private TableColumn<DocGia, String> tbcDiaChi;
    @FXML
    private TableColumn<DocGia, Date> tbcNgaySinh;
    @FXML
    private TableColumn<DocGia, String> tbcGioiTinh;
    @FXML
    private TableColumn<DocGia, Integer> tbcTheDocGia;

    @FXML
    private JFXButton btnSach;
    @FXML
    private JFXButton btnDocGia;
    @FXML
    private JFXButton btnNhanVien;
    @FXML
    private JFXButton btnMatSach;
    @FXML
    private JFXButton btnThongKe;
    @FXML
    private JFXButton btnTheDocGia;
    @FXML
    private JFXButton btnMuonTra;

    @FXML
    private JFXButton btnThemAnh;
    @FXML
    private ImageView imgAnh;
    @FXML
    private JFXTextField txtMaSach;
    @FXML
    private JFXTextField txtTenSach;
    @FXML
    private JFXTextField txtTacGia;
    @FXML
    private JFXComboBox<String> txtTheLoai;
    @FXML
    private JFXTextField txtNhaXuatBan;
    @FXML
    private JFXTextField txtSoLuong;
    @FXML
    private JFXTextField txtTim;

    @FXML
    private JFXTextField txtMaDocGia;
    @FXML
    private JFXTextField txtTenDocGia;
    @FXML
    private JFXTextField txtDiaChi;
    @FXML
    private JFXComboBox<Integer> txtTheDocGia;
    @FXML
    private JFXRadioButton rdNam;
    @FXML
    private JFXRadioButton rdNu;
    @FXML
    private JFXDatePicker txtNgaySinh;
    @FXML
    private JFXTextField txtTimDocGia;
    @FXML
    private JFXComboBox cbDocGia;
    @FXML
    private JFXTextField txtMaNhanVien;
    @FXML
    private JFXTextField txtTenNhanVien;
    @FXML
    private JFXTextField txtDiaChiNV;
    @FXML
    private JFXComboBox txtTaiKhoan;
    @FXML
    private JFXRadioButton rdNamNV;
    @FXML
    private JFXRadioButton rdNuNV;
    @FXML
    private JFXDatePicker txtNgaySinhNV;
    @FXML
    private JFXTextField txtTimNhanVien;
    @FXML
    private JFXComboBox cbNhanVien;
    @FXML
    private JFXButton btnLuuThemNV;
    @FXML
    private JFXButton btnLuu;
    @FXML
    private JFXButton btnCNMuon;
    @FXML
    private JFXButton btnCNTra;
    @FXML
    private StackPane stackPane;
    @FXML
    private JFXComboBox<String> cbTim;

    ObservableList<String> list;
    ObservableList<Integer> listMaThe;
    ObservableList<String> listTaiKhoan;
    String imgSach = "";
    @FXML
    private ImageView imgdong;

    @FXML
    public void themAnhHadler(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            this.imgSach = selectedFile.getAbsolutePath();
            Image img = new Image("file:" + imgSach);
            this.imgAnh.setImage(img);
//            this.btnThemAnh.setVisible(false);
            this.imgAnh.setVisible(true);

        }

    }

    public void timSach() throws SQLException {

        switch (this.cbTim.getValue()) {
            case "Mã Sách":
                this.txtTim.textProperty().addListener(e -> {
                    List<Sach> s = Utils.getSachCB("maSach", this.txtTim.getText());
                    reloadTbSach(s);
                });
                break;
            case "Tên Sách":
                this.txtTim.textProperty().addListener(e -> {
                    List<Sach> s = Utils.getSachCB("tenSach", this.txtTim.getText());
                    reloadTbSach(s);
                });
                break;
            case "Tác Giả":
                this.txtTim.textProperty().addListener(e -> {
                    List<Sach> s = Utils.getSachCB("tacGia", this.txtTim.getText());
                    reloadTbSach(s);
                });
                break;
            case "Nhà Xuất Bản":
                this.txtTim.textProperty().addListener(e -> {
                    List<Sach> s = Utils.getSachCB("nhaXuatBan", this.txtTim.getText());
                    reloadTbSach(s);
                });
                break;
            case "Thể Loại":
                this.txtTim.textProperty().addListener(e -> {
                    List<Sach> s = Utils.getSachCB("theLoai", this.txtTim.getText());
                    reloadTbSach(s);
                });
                break;
        }
    }

    public void loadSach(List a) {

        tbcMaSach.setCellValueFactory(new PropertyValueFactory<>("maSach"));

        tbcTenSach.setCellValueFactory(new PropertyValueFactory<>("tenSach"));

        tbcTacGia.setCellValueFactory(new PropertyValueFactory<>("tacGia"));

        tbcNxb.setCellValueFactory(new PropertyValueFactory<>("nhaXuatBan"));

        tbcTheLoai.setCellValueFactory(new PropertyValueFactory<>("theLoai"));
//
        tbcSoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));

        this.tbvSach.setItems(FXCollections.observableArrayList(a));

    }
    @FXML
    private VBox vboxHome;

    @FXML
    public void thoatHandler(MouseEvent event) throws IOException {
        String mess = "Bạn có chắc chắn muốn đăng xuất không ?";
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        ;
        dialogLayout.setHeading(new Text("Thông báo"));
        dialogLayout.setBody(new Text(mess));

        JFXButton btnCancel = new JFXButton("Cancel");
        JFXButton btnOK = new JFXButton("OK");

        JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {
            try {
                dialog.close();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("FXMLDangNhap.fxml"));
                Parent parent = loader.load();
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SachController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {
            dialog.close();
        });
        HBox hbox = new HBox();
        hbox.getChildren().add(btnCancel);
        hbox.getChildren().add(btnOK);
        dialogLayout.setActions(hbox);
        dialog.show();

    }

    @FXML
    public void homeHandler(MouseEvent event) {
        btnDangXuat.setVisible(true);
        vboxHome.setVisible(true);
        vboxTaiKhoan.setVisible(false);
        vboxTheDocGia.setVisible(false);
        vboxDocGia.setVisible(false);
        vboxSach.setVisible(false);
        vboxNhanVien.setVisible(false);
        vboxMuon.setVisible(false);
        vboxTra.setVisible(false);
        vboxTheDocGia.setVisible(false);
        btnDoitk.setVisible(false);
        vboxMatSach.setVisible(false);
        hboxMuonTra.setVisible(false);
        hboxThemXoaSua.setVisible(false);
        vboxThongKe.setVisible(false);
    }

    @FXML
    public void minHadler(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setIconified(true);
    }

    public void chonSachHadler(MouseEvent event) {
        this.txtMaSach.setText(tbvSach.getSelectionModel().getSelectedItem().getMaSach());
        this.txtTenSach.setText(tbvSach.getSelectionModel().getSelectedItem().getTenSach());
        this.txtTacGia.setText(tbvSach.getSelectionModel().getSelectedItem().getTacGia());

        this.txtNhaXuatBan.setText(tbvSach.getSelectionModel().getSelectedItem().getNhaXuatBan());
        this.txtSoLuong.setText(String.valueOf(tbvSach.getSelectionModel().getSelectedItem().getSoLuong()));
        List<String> s1 = Utils.getAnhSach(txtMaSach.getText());
//        List <String> tl = Utils.getTheLoai();
        this.list = FXCollections.observableArrayList(Utils.getTheLoai());
        this.txtTheLoai.setItems(list);
        this.txtTheLoai.setValue(tbvSach.getSelectionModel().getSelectedItem().getTheLoai());

        btnSua.setVisible(true);
        btnXoa.setVisible(true);

        String a = "";
        for (String i : s1) {
            a = i;
        }
        if (!a.equals("")) {
            String replaceString = a.replace("\\", "/");
            System.out.println(replaceString);
            System.out.println(replaceString.indexOf("AnhSach"));
//        System.out.println(replaceString.indexOf("."));
            Image img;
            img = new Image(replaceString.substring(replaceString.indexOf("AnhSach")));
            this.imgAnh.setImage(img);
        }
        this.btnThemAnh.setVisible(false);
        this.imgAnh.setVisible(true);
        hboxSach.setDisable(true);
//        this.txtMaSach.setDisable(true);
//        this.txtTenSach.setDisable(true);
//        this.txtTacGia.setDisable(true);
//        this.txtNhaXuatBan.setDisable(true);
//        this.txtTheLoai.setDisable(true);
//        this.txtSoLuong.setDisable(true);
        hboxThemXoaSua.setDisable(false);
    }

    public void luuThemSachHadler(ActionEvent event) throws SQLException {
        try {

            if (this.txtMaSach.getText() == null || this.txtTenSach.getText() == null
                    || this.txtTacGia.getText() == null || this.txtNhaXuatBan.getText() == null
                    || this.txtSoLuong.getText() == null) {
                String mess = "Dữ liệu nhập trống!";
                DialogMessenger.dialogThongBao(this.stackPane, mess).show();

            } else {
                if (this.txtMaSach.getText().equals("") || this.txtTenSach.getText().equals("")
                        || this.txtTacGia.getText().equals("") || this.txtNhaXuatBan.getText().equals("")
                        || this.txtSoLuong.getText().equals("") || Integer.parseInt(this.txtSoLuong.getText()) < 0) {
                    String mess = "Dữ liệu nhập không hợp lệ!";
                    DialogMessenger.dialogThongBao(this.stackPane, mess).show();
                } else {
                    if (this.txtMaSach.getText().length() > 45 || this.txtTenSach.getText().length() > 100
                            || this.txtTacGia.getText().length() > 45 || this.txtNhaXuatBan.getText().length() > 45
                            || this.txtSoLuong.getText().length() > 11) {

                    } else {
                        if (themSua == 1) {
                            themSachHadler();
                            lbSLSach.setText(String.valueOf(Utils.getDemSach()));
                            lbTheLoai.setText(String.valueOf(Utils.getDemTheLoai()));
                        } else {
                            String s = "Bạn có chắc chắn sửa sách này không?";
                            JFXDialogLayout dialogLayout = new JFXDialogLayout();
                            dialogLayout.setHeading(new Text("Thông báo"));
                            dialogLayout.setBody(new Text(s));
                            JFXButton btnCancel = new JFXButton("Không");
//            btnCancel.setStyle("-fx-background-color: #236DE5; -fx-text-fill: #FFFFFF; :hover");
                            JFXButton btnOK = new JFXButton("Có");
                            //            btnOK.setStyle("-fx-background-color: #236DE5; -fx-text-fill: #FFFFFF; :hover");
                            btnOK.setButtonType(JFXButton.ButtonType.RAISED);

                            JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
                            btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {;
                                try {
                                    UpdateSachHandler();
                                    hboxSach.setDisable(true);
                                    String mess = "Sửa sách thành công";
                                    DialogMessenger.dialogThongBao(this.stackPane, mess).show();
                                } catch (SQLException ex) {
                                    Logger.getLogger(SachController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                dialog.close();
                            });
                            btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {;
                                dialog.close();
                            });
                            HBox hbox = new HBox();
                            hbox.getChildren().add(btnCancel);
                            hbox.getChildren().add(btnOK);
                            dialogLayout.setActions(hbox);
                            dialog.show();
                        }

                    }
                }
            }
        } catch (NumberFormatException ex) {
            DialogMessenger.dialogThongBao(stackPane, "Số lượng phải là số!").show();

        }
    }
    @FXML
    private HBox hboxSach;
    @FXML
    private HBox hboxDocGia;
    @FXML
    private HBox hboxNhanVien;

    public void themSachHadler() {

        Sach sach = new Sach(this.txtMaSach.getText(), this.txtTenSach.getText(),
                this.txtTacGia.getText(), this.txtTheLoai.getValue(),
                this.txtNhaXuatBan.getText(),
                Integer.parseInt(this.txtSoLuong.getText()), this.imgSach);

        if (Utils.addSach(sach) == true) {

            loadSach(Utils.getSach());
            System.out.println("Thêm sách thành công");
            this.imgAnh.setVisible(true);
            String mess = "Thêm sách thành công";
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();
        } else {
            String mess = "Thêm sách thất bại";
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();
            System.out.println("Thêm sách thất bại");
        }
    }

    public void xoaSachHadler() {
        Sach sach = new Sach(this.txtMaSach.getText(), this.txtTenSach.getText(),
                this.txtTacGia.getText(), this.txtTheLoai.getValue(),
                this.txtNhaXuatBan.getText(), Integer.parseInt(this.txtSoLuong.getText()), this.imgSach);

        String s = "Bạn có chắc chắn xóa sách này không?";
        JFXDialogLayout dialogLayout = new JFXDialogLayout();

        dialogLayout.setHeading(new Text("Thông báo"));
        dialogLayout.setBody(new Text(s));

        JFXButton btnCancel = new JFXButton("Không");
        JFXButton btnOK = new JFXButton("Có");
        btnOK.setButtonType(JFXButton.ButtonType.RAISED);

        JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {;
            String mess = "";
            dialog.close();

            if (Utils.deleteSach(sach) == true) {
                loadSach(Utils.getSach());

                mess = "Xóa sách thành công";
                lbSLSach.setText(String.valueOf(Utils.getDemSach()));
                lbTheLoai.setText(String.valueOf(Utils.getDemTheLoai()));
            } else {
                mess = "Lỗi xóa sách";
            }
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();

        });
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {;
            dialog.close();
        });
        HBox hbox = new HBox();
        hbox.getChildren().add(btnCancel);
        hbox.getChildren().add(btnOK);
        dialogLayout.setActions(hbox);
        dialog.show();
    }

    private void reloadTbSach(List<Sach> s) {
        this.tbvSach.getItems().clear();
        this.tbvSach.getItems().addAll(s);
    }

    public void loadDocGia(List a) {

        tbcMaDocGia.setCellValueFactory(new PropertyValueFactory<>("maDG"));

        tbcTenDocGia.setCellValueFactory(new PropertyValueFactory<>("tenDG"));

        tbcGioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));

        tbcNgaySinh.setCellValueFactory(new PropertyValueFactory<>("namSinh"));

        tbcDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));

        tbcTheDocGia.setCellValueFactory(new PropertyValueFactory<>("soThe"));
        this.tbvDocGia.setItems(FXCollections.observableArrayList(a));

    }

    public void chonDocGiaHadler(MouseEvent event) {
        this.txtMaDocGia.setText(String.valueOf(tbvDocGia.getSelectionModel().getSelectedItem().getMaDG()));
        this.txtTenDocGia.setText(tbvDocGia.getSelectionModel().getSelectedItem().getTenDG());
        String s = (tbvDocGia.getSelectionModel().getSelectedItem().getGioiTinh());
        if (s.equals("Nam")) {
            rdNam.setSelected(true);
        } else {
            rdNu.setSelected(true);
        }
        String ns = String.valueOf(tbvDocGia.getSelectionModel().getSelectedItem().getNamSinh());
        int y = Integer.parseInt(ns.substring(0, 4));
        int m = Integer.parseInt(ns.substring(5, 7));
        int d = Integer.parseInt(ns.substring(8, 10));
        System.out.println(d + " " + m + " " + y);
        this.txtNgaySinh.setValue(LocalDate.of(y, m, d));
        this.txtDiaChi.setText(tbvDocGia.getSelectionModel().getSelectedItem().getDiaChi());
        this.listMaThe = FXCollections.observableArrayList(Utils.getMaThe());
        this.txtTheDocGia.setItems(listMaThe);
        this.txtTheDocGia.setValue(tbvDocGia.getSelectionModel().getSelectedItem().getSoThe());

        hboxDocGia.setDisable(true);
        btnSua.setVisible(true);
        btnXoa.setVisible(true);
    }

    public void themDocGiaHadler() throws ParseException {

        String gt = "Nam";
        if (rdNu.isSelected()) {
            gt = "Nữ";
        }
        String stringDate = String.valueOf(this.txtNgaySinh.getValue());
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);

        DocGia docgia = new DocGia(this.txtTenDocGia.getText(), gt, d,
                this.txtDiaChi.getText(), this.txtTheDocGia.getValue());

        if (Utils.addDocGia(docgia) == true) {

            loadDocGia(Utils.getDocGia());

            this.imgAnh.setVisible(true);
            String mess = "Thêm độc giả thành công";
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();
        } else {
            String mess = "Thêm độc giả thất bại";
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();
            System.out.println("Thêm độc giả thất bại");
        }

    }

    public void luuThemDocGiaHadler(ActionEvent event) throws ParseException {
        if (this.txtTenDocGia.getText() == null
                || this.txtDiaChi.getText() == null || this.txtNgaySinh.getValue() == null) {
            String mess = "Dữ liệu trống!";
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();
        } else {
            if (this.txtTenDocGia.getText().equals("")
                    || this.txtDiaChi.getText().equals("") || this.txtNgaySinh.getValue().compareTo(LocalDate.now()) > 0) {
                String mess = "Dữ liệu nhập không hợp lệ!";
                DialogMessenger.dialogThongBao(this.stackPane, mess).show();
            } else {
                if (this.txtTenDocGia.getText().length() > 45
                        || this.txtDiaChi.getText().length() > 45) {
                    String mess = "Dữ liệu nhập không hợp lệ!";
                    DialogMessenger.dialogThongBao(this.stackPane, mess).show();
                } else {
                    if (themSua == 1) {
                        themDocGiaHadler();
                    } else {
                        String s = "Bạn có chắc chắn sửa thông độc giả này không?";
                        JFXDialogLayout dialogLayout = new JFXDialogLayout();
                        dialogLayout.setHeading(new Text("Thông báo"));
                        dialogLayout.setBody(new Text(s));

                        JFXButton btnCancel = new JFXButton("Không");
                        JFXButton btnOK = new JFXButton("Có");
                        btnOK.setButtonType(JFXButton.ButtonType.RAISED);

                        JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
                        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {;
                            try {
                                UpdateDocGiaHandler();
                                hboxDocGia.setDisable(true);
                                String mess = "Sửa độc giả thành công";
                                DialogMessenger.dialogThongBao(this.stackPane, mess).show();
                            } catch (SQLException ex) {
                                Logger.getLogger(SachController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ParseException ex) {
                                Logger.getLogger(SachController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            dialog.close();
                        });
                        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {;
                            dialog.close();
                        });
                        HBox hbox = new HBox();
                        hbox.getChildren().add(btnCancel);
                        hbox.getChildren().add(btnOK);
                        dialogLayout.setActions(hbox);
                        dialog.show();

                    }

                }
            }
        }
    }

    public void xoaDocGiaHadler() throws SQLException, ParseException {
        String gt = "Nam";
        if (rdNu.isSelected()) {
            gt = "Nữ";
        }
        String stringDate = String.valueOf(this.txtNgaySinh.getValue());
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
        DocGia docgia = new DocGia(Integer.parseInt(this.txtMaDocGia.getText()), this.txtTenDocGia.getText(), gt, d,
                this.txtDiaChi.getText(), this.txtTheDocGia.getValue());

        String s = "Bạn có chắc chắn xóa độc giả này không?";
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text("Thông báo"));
        dialogLayout.setBody(new Text(s));
        JFXButton btnCancel = new JFXButton("Không");
        JFXButton btnOK = new JFXButton("Có");
        btnOK.setButtonType(JFXButton.ButtonType.RAISED);

        JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {;
            String mess = "";
            if (Utils.deleteDocGia(docgia) == true) {
                loadDocGia(Utils.getDocGia());
                dialog.close();
                mess = "Xóa độc giả thành công";
            } else {
                mess = "Lỗi xóa độc giả";
            }
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();
        });
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {;
            dialog.close();
        });
        HBox hbox = new HBox();
        hbox.getChildren().add(btnCancel);
        hbox.getChildren().add(btnOK);
        dialogLayout.setActions(hbox);
        dialog.show();
    }

    public void xoaHadler(ActionEvent event) throws SQLException, ParseException {
        if (chucNang == 1) {
            xoaSachHadler();
        } else if (chucNang == 2) {
            xoaDocGiaHadler();
        } else {
            xoaNhanVienHadler();
        }
    }

    @FXML
    TableView<NhanVien> tbvNhanVien;
    @FXML
    private TableColumn<NhanVien, Integer> tbcMaNhanVien;
    @FXML
    private TableColumn<NhanVien, String> tbcTenNhanVien;
    @FXML
    private TableColumn<NhanVien, String> tbcDiaChiNV;
    @FXML
    private TableColumn<NhanVien, Date> tbcNgaySinhNV;
    @FXML
    private TableColumn<NhanVien, String> tbcGioiTinhNV;
    @FXML
    private TableColumn<NhanVien, String> tbcTaiKhoan;

    public void loadNhanVien(List a) {
        tbcMaNhanVien.setCellValueFactory(new PropertyValueFactory<>("maNV"));

        tbcTenNhanVien.setCellValueFactory(new PropertyValueFactory<>("tenNV"));

        tbcGioiTinhNV.setCellValueFactory(new PropertyValueFactory<>("gioiTinhNV"));

        tbcNgaySinhNV.setCellValueFactory(new PropertyValueFactory<>("namSinhNV"));

        tbcDiaChiNV.setCellValueFactory(new PropertyValueFactory<>("diaChiNV"));

        tbcTaiKhoan.setCellValueFactory(new PropertyValueFactory<>("tenTK"));

        this.tbvNhanVien.setItems(FXCollections.observableArrayList(a));
    }

    public void chonNhanVienHadler(MouseEvent event) {
        this.txtMaNhanVien.setText(String.valueOf(tbvNhanVien.getSelectionModel().getSelectedItem().getMaNV()));
        this.txtTenNhanVien.setText(tbvNhanVien.getSelectionModel().getSelectedItem().getTenNV());
        String s = (tbvNhanVien.getSelectionModel().getSelectedItem().getGioiTinhNV());
        System.out.println(s);
        if (s.equals("Nam")) {
            rdNamNV.setSelected(true);
        } else {
            rdNuNV.setSelected(true);
        }
        String ns = String.valueOf(tbvNhanVien.getSelectionModel().getSelectedItem().getNamSinhNV());
        int y = Integer.parseInt(ns.substring(0, 4));
        int m = Integer.parseInt(ns.substring(5, 7));
        int d = Integer.parseInt(ns.substring(8, 10));
        System.out.println(d + " " + m + " " + y);
        this.txtNgaySinhNV.setValue(LocalDate.of(y, m, d));
        this.txtDiaChiNV.setText(tbvNhanVien.getSelectionModel().getSelectedItem().getDiaChiNV());
        this.listTaiKhoan = FXCollections.observableArrayList(Utils.getTaiKhoan());
        this.txtTaiKhoan.setItems(listTaiKhoan);
        this.txtTaiKhoan.setValue(tbvNhanVien.getSelectionModel().getSelectedItem().getTenTK());
        hboxNhanVien.setDisable(true);
        btnSua.setVisible(true);
        btnXoa.setVisible(true);
    }

    public void themNhanVienHadler() throws ParseException {

        String gt = "Nam";
        if (rdNuNV.isSelected()) {
            gt = "Nữ";
        }
        String stringDate = String.valueOf(this.txtNgaySinhNV.getValue());
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);

        this.listTaiKhoan = FXCollections.observableArrayList(Utils.getTKTrong());
        this.txtTaiKhoan.setItems(listTaiKhoan);
        NhanVien nhanvien = new NhanVien(Integer.parseInt(this.txtMaNhanVien.getText()),
                this.txtTenNhanVien.getText(), gt, d,
                this.txtDiaChiNV.getText(), String.valueOf(this.txtTaiKhoan.getValue()));

        if (Utils.addNhanVien(nhanvien) == true) {

            loadNhanVien(Utils.getNhanVien());
            System.out.println("Thêm nhân viên thành công");
            String mess = "Thêm nhân viên thành công";
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();
        } else {
            String mess = "Thêm nhân viên thất bại";
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();
            System.out.println("Thêm nhân viên thất bại");
        }
    }

    public void luuThemNhanVienHadler(ActionEvent event) throws SQLException, ParseException {
        if (this.txtTenNhanVien.getText() == null
                || this.txtDiaChiNV.getText() == null || this.txtNgaySinhNV.getValue() == null) {
            String mess = "Dữ liệu trống!";
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();
        } else {
            if (this.txtTenNhanVien.getText().equals("")
                    || this.txtDiaChiNV.getText().equals("")
                    || this.txtNgaySinhNV.getValue().compareTo(LocalDate.now()) > 0) {
                String mess = "Dữ liệu nhập không hợp lệ!";
                DialogMessenger.dialogThongBao(this.stackPane, mess).show();
            } else {
                if (this.txtTenNhanVien.getText().length() > 45
                        || this.txtDiaChiNV.getText().length() > 45) {
                    String mess = "Dữ liệu nhập không hợp lệ!";
                    DialogMessenger.dialogThongBao(this.stackPane, mess).show();
                } else {
                    if (themSua == 1) {
                        themNhanVienHadler();
                    } else {
                        JFXDialogLayout dialogLayout = new JFXDialogLayout();
                        dialogLayout.setHeading(new Text("Thông báo"));
                        dialogLayout.setBody(new Text("Thêm nhân viên thành công"));

                        JFXButton btnCancel = new JFXButton("Không");

                        JFXButton btnOK = new JFXButton("Có");

                        btnOK.setButtonType(JFXButton.ButtonType.RAISED);

                        JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
                        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {;
                            try {
                                UpdateNhanVienHandler();
                                hboxNhanVien.setDisable(true);
                            } catch (SQLException ex) {
                                Logger.getLogger(SachController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ParseException ex) {
                                Logger.getLogger(SachController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            dialog.close();
                        });
                        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {;
                            dialog.close();
                        });
                        HBox hbox = new HBox();
                        hbox.getChildren().add(btnCancel);
                        hbox.getChildren().add(btnOK);
                        dialogLayout.setActions(hbox);
                        dialog.show();

                    }

                }
            }
        }
    }

    public void xoaNhanVienHadler() throws SQLException, ParseException {
        String gt = "Nam";
        if (rdNuNV.isSelected()) {
            gt = "Nữ";
        }
        String stringDate = String.valueOf(this.txtNgaySinhNV.getValue());
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);

        NhanVien nhanvien = new NhanVien(Integer.parseInt(this.txtMaNhanVien.getText()),
                this.txtTenNhanVien.getText(), "Nữ", d,
                this.txtDiaChiNV.getText(), String.valueOf(this.txtTaiKhoan.getValue()));

        String s = "Bạn có chắc chắn xóa nhân viên này không?";
        JFXDialogLayout dialogLayout = new JFXDialogLayout();

        dialogLayout.setHeading(new Text("Thông báo"));
        dialogLayout.setBody(new Text(s));

        JFXButton btnCancel = new JFXButton("Không");
//            btnCancel.setStyle("-fx-background-color: #236DE5; -fx-text-fill: #FFFFFF; :hover");
        JFXButton btnOK = new JFXButton("Có");
//            btnOK.setStyle("-fx-background-color: #236DE5; -fx-text-fill: #FFFFFF; :hover");
        btnOK.setButtonType(JFXButton.ButtonType.RAISED);

        JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {
            String mess = "";
            if (Utils.deleteNhanVien(nhanvien) == true) {
                loadNhanVien(Utils.getNhanVien());
                dialog.close();
                mess = "Xóa thành công";
            } else {
                mess = "Lỗi xóa nhân viên ";
            }
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();
        });
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {;
            dialog.close();
        });
        HBox hbox = new HBox();
        hbox.getChildren().add(btnCancel);
        hbox.getChildren().add(btnOK);
        dialogLayout.setActions(hbox);
        dialog.show();
    }

    public void UpdateSachHandler() throws SQLException {
        Sach s = this.tbvSach.getSelectionModel().getSelectedItem();

        s.setMaSach(this.txtMaSach.getText());
        s.setTenSach(this.txtTenSach.getText());
        s.setTacGia(this.txtTacGia.getText());
        s.setNhaXuatBan(this.txtNhaXuatBan.getText());
        s.setTheLoai(this.txtTheLoai.getValue());
        s.setSoLuong(Integer.parseInt(this.txtSoLuong.getText()));

        Utils.updateSach(s);
        reloadTbSach(Utils.getSach());

    }

    public void UpdateNhanVienHandler() throws SQLException, ParseException {
        NhanVien nv = this.tbvNhanVien.getSelectionModel().getSelectedItem();
        String gt = "Nam";
        String stringDate = String.valueOf(this.txtNgaySinhNV.getValue());
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
        nv.setMaNV(Integer.parseInt(this.txtMaNhanVien.getText()));
        nv.setTenNV(this.txtTenNhanVien.getText());
        if (rdNuNV.isSelected()) {
            gt = "Nữ";
        }
        nv.setGioiTinhNV(gt);
        nv.setDiaChiNV(this.txtDiaChiNV.getText());
        nv.setNamSinhNV(d);

        Utils.updateNhanVien(nv);
        reloadTbNhanVien(Utils.getNhanVien());

    }

    public void UpdateDocGiaHandler() throws SQLException, ParseException {
        DocGia dg = this.tbvDocGia.getSelectionModel().getSelectedItem();
        String gt = "Nam";
        String stringDate = String.valueOf(this.txtNgaySinh.getValue());
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
        dg.setMaDG(Integer.parseInt(this.txtMaDocGia.getText()));
        dg.setTenDG(this.txtTenDocGia.getText());
        if (rdNu.isSelected()) {
            gt = "Nữ";
        }
        dg.setGioiTinh(gt);
        dg.setDiaChi(this.txtDiaChi.getText());
        dg.setNamSinh(d);

        Utils.updateDocGia(dg);
        reloadTbDocGia(Utils.getDocGia());

    }

    public void reloadTbNhanVien(List<NhanVien> nv) {
        this.tbvNhanVien.getItems().clear();
        this.tbvNhanVien.getItems().addAll(nv);

    }

    public void reloadTbMuonTra(List<MuonTra> m) {
        this.tbvMuonTra.getItems().clear();
        this.tbvMuonTra.getItems().addAll(m);

    }

    public void reloadTbSachMuon(List<Sach> sach) {
        this.tbvSachMuon.getItems().clear();
        this.tbvSachMuon.getItems().addAll(sach);

    }

    public void reloadTbDocGia(List<DocGia> dg) {
        this.tbvDocGia.getItems().clear();
        this.tbvDocGia.getItems().addAll(dg);
    }

    public void reloadTbTheDocGia(List<TheDocGia> tg) {
        this.tbvTheDocGia.getItems().clear();
        this.tbvTheDocGia.getItems().addAll(tg);
    }

    public void reloadTbTaiKhoan(List<TaiKhoan> tk) {
        this.tbvTaiKhoan.getItems().clear();
        this.tbvTaiKhoan.getItems().addAll(tk);
    }
    @FXML
    TableView<Sach> tbvSachMuon;
    @FXML
    private TableColumn<Sach, String> tbcMaSachMuon;
    @FXML
    private TableColumn<Sach, String> tbcTenSachMuon;
    @FXML
    private TableColumn<Sach, String> tbcTacGiaMuon;
    @FXML
    private TableColumn<Sach, String> tbcTheLoaiMuon;
    @FXML
    private TableColumn<Sach, String> tbcNxbMuon;
    @FXML
    private TableColumn<Sach, Integer> tbcSoLuongMuon;

    public void loadSachMuon(List a) throws SQLException {

        tbcMaSachMuon.setCellValueFactory(new PropertyValueFactory<>("maSach"));

        tbcTenSachMuon.setCellValueFactory(new PropertyValueFactory<>("tenSach"));

        tbcTacGiaMuon.setCellValueFactory(new PropertyValueFactory<>("tacGia"));

        tbcNxbMuon.setCellValueFactory(new PropertyValueFactory<>("nhaXuatBan"));

        tbcTheLoaiMuon.setCellValueFactory(new PropertyValueFactory<>("theLoai"));
//
        tbcSoLuongMuon.setCellValueFactory(new PropertyValueFactory<>("soLuong"));

        this.tbvSachMuon.setItems(FXCollections.observableArrayList(a));

    }
    @FXML
    private ImageView imgAnhMuon;

    @FXML
    private JFXTextField txtMaDocGiaMuon;
    @FXML
    private HBox hboxMuon;
    @FXML
    private JFXTextField txtMaSachMuon;
    @FXML
    private JFXDatePicker dateNgayMuon;
    @FXML
    private JFXDatePicker dateNgayHetHan;

    public void goiY() {
        List<Integer> ds = Utils.getMaThe();
        TextFields.bindAutoCompletion(txtMaDocGiaMuon, ds);
    }
    @FXML
    private Label txtTenSachMuon;

    public void chonSachMuonHadler(MouseEvent event) {
        dateNgayMuon.setValue(LocalDate.now());
        dateNgayMuon.setDisable(true);
        dateNgayHetHan.setValue(LocalDate.now().plusDays(30));
        dateNgayHetHan.setDisable(true);
        txtMaSachMuon.setText((tbvSachMuon.getSelectionModel().getSelectedItem().getMaSach()));
        txtTenSachMuon.setText(tbvSachMuon.getSelectionModel().getSelectedItem().getTenSach());
        List<String> s1 = Utils.getAnhSach(txtMaSachMuon.getText());

        String a = "";
        for (String i : s1) {
            a = i;
        }
        if (!a.equals("")) {
            String replaceString = a.replace("\\", "/");
            System.out.println(replaceString);
            System.out.println(replaceString.indexOf("AnhSach"));
//        System.out.println(replaceString.indexOf("."));
            Image img;
            img = new Image(replaceString.substring(replaceString.indexOf("AnhSach")));
            this.imgAnhMuon.setImage(img);
        }

    }

    public void muonHandler(ActionEvent event) throws ParseException {
        try {
            String stringDateMuon = String.valueOf(this.dateNgayMuon.getValue());
            String stringDateHetHan = String.valueOf(this.dateNgayHetHan.getValue());
            Date d = new SimpleDateFormat("yyyy-MM-dd").parse(stringDateMuon);
            Date f = new SimpleDateFormat("yyyy-MM-dd").parse(stringDateHetHan);

            long ngay = System.currentTimeMillis();
            Date ngaykt = new Date(ngay);
            List<Integer> ktmt = Utils.laySoTheTheoNgayKetThuc(ngaykt);

            if (this.txtMaDocGiaMuon.getText().equals("")) {
                // ô số thẻ trống
                String mess = "Vui lòng nhập số thẻ độc giả";
                DialogMessenger.dialogThongBao(this.stackPane, mess).show();
            }
            if (ktmt.contains(Integer.parseInt(this.txtMaDocGiaMuon.getText()))) {
                String mess = "Thẻ độc giả đã hết hạn!";
                DialogMessenger.dialogThongBao(this.stackPane, mess).show();
            }
            if (!Utils.getTheMuon().contains(Integer.parseInt(this.txtMaDocGiaMuon.getText()))) {
                String mess = "Thẻ độc giả chưa có người sở hữu!";
                DialogMessenger.dialogThongBao(this.stackPane, mess).show();
            } else {
                TheDocGia tg = new TheDocGia();
                tg.setSoThe(Integer.parseInt(this.txtMaDocGiaMuon.getText()));

                Sach s = new Sach();
                s.setMaSach(this.txtMaSachMuon.getText());
                s.setTenSach(Utils.getTenSach(this.txtMaSachMuon.getText()));
                String tenNV = Utils.getNhanVienTK(DangNhapController.luu);
                System.out.println(tenNV);
                if (Utils.getSoLan(Integer.parseInt(this.txtMaDocGiaMuon.getText())) < 5) {
                    MuonTra m = new MuonTra(tg, s, d, f, tenNV);
                    m.setGhiChu("Chưa trả");
                    if (Utils.muon(m) == true) {

                        loadMuonTra(Utils.getMuonTra());
                        thongKe();
                        String mess = "Mượn thành công";
                        DialogMessenger.dialogThongBao(this.stackPane, mess).show();
                    } else {
                        String mess = "Không mượn được";
                        DialogMessenger.dialogThongBao(this.stackPane, mess).show();

                    }
                } else {
                    String mess = "Không được mượn sách tối đa quá 5 lần chưa trả!";
                    DialogMessenger.dialogThongBao(this.stackPane, mess).show();
                    System.out.println("Không mượn được");
                }

            }
        } catch (NumberFormatException ex) {
            DialogMessenger.dialogThongBao(stackPane, "Số thẻ độc giả phải là số!").show();
        }

    }

    @FXML
    TableView<MuonTra> tbvMuonTra;
    @FXML
    private TableColumn<MuonTra, Integer> tbcMaMuon;
    @FXML
    private TableColumn<MuonTra, Integer> tbcSoTheMuon;
    @FXML
    private TableColumn<MuonTra, String> tbcMaSachTra;
    @FXML
    private TableColumn<MuonTra, String> tbcTenSachTra;
    @FXML
    private TableColumn<MuonTra, Date> tbcNgayMuon;
    @FXML
    private TableColumn<MuonTra, Date> tbcNgayTra;
    @FXML
    private TableColumn<MuonTra, Date> tbcNgayHetHan;
    @FXML
    private TableColumn<MuonTra, Integer> tbcTienPhat;
    @FXML
    private TableColumn<MuonTra, String> tbcGhiChu;
    @FXML
    private TableColumn<MuonTra, String> tbcTenNV;
    @FXML
    private JFXButton btnMuon;

    public void loadMuonTra(List a) {
        tbcMaMuon.setCellValueFactory(new PropertyValueFactory<>("maMuon"));
        //
        tbcSoTheMuon.setCellValueFactory(new PropertyValueFactory<>("soThe"));

        tbcMaSachTra.setCellValueFactory(new PropertyValueFactory<>("maSach"));
        //
        tbcTenSachTra.setCellValueFactory(new PropertyValueFactory<>("tenSach"));

        tbcNgayMuon.setCellValueFactory(new PropertyValueFactory<>("ngayMuon"));

        tbcNgayTra.setCellValueFactory(new PropertyValueFactory<>("ngayTra"));

        tbcNgayHetHan.setCellValueFactory(new PropertyValueFactory<>("ngayHetHan"));

        tbcTienPhat.setCellValueFactory(new PropertyValueFactory<>("tienPhat"));

        tbcGhiChu.setCellValueFactory(new PropertyValueFactory<>("ghiChu"));

        tbcTenNV.setCellValueFactory(new PropertyValueFactory<>("tenNhanVien"));

        this.tbvMuonTra.setItems(FXCollections.observableArrayList(a));
    }

    @FXML
    private JFXButton btnTra;

    public void chon(MouseEvent event) {
        btnTra.setVisible(true);
        btnTra.setDisable(false);
        btnBaoMatSach.setVisible(true);

    }

    public void chonTraHandler(ActionEvent event) throws ParseException {

        MuonTra m = tbvMuonTra.getSelectionModel().getSelectedItem();

        String stringDate = String.valueOf(LocalDate.now());

        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);

        if (m.getNgayTra() != null) {
            String mess = "Sách đã được trả";
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();
        } else if (m.getGhiChu().equals("Đã mất")) {
            String mess = "Sách đã được báo mất";
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();
        } else {
            m.setNgayTra(d);

            if (m.getNgayTra().compareTo(m.getNgayHetHan()) > 0) {
                long soNgayTre = (m.getNgayTra().getTime() - m.getNgayHetHan().getTime()) / (24 * 3600 * 1000);
                m.setTienPhat((int) (soNgayTre * 5000));

            }
            m.setGhiChu("Đã trả");
            Utils.updateMuon(m);

            String mess = "Trả sách thành công";

            loadMuonTra(Utils.getMuonTra());
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();
        }

    }
    @FXML
    private JFXButton btnBaoMatSach;

    public void baoMatSachHandler(ActionEvent Event) throws ParseException {

        String stringDate = String.valueOf(LocalDate.now());
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
        MuonTra m = this.tbvMuonTra.getSelectionModel().getSelectedItem();
        System.out.println(m.getGhiChu());
        if (m.getGhiChu().equals("Đã trả") || m.getGhiChu().equals("Đã mất")) {
            String mess = "Không thể báo mất sách";
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();

        } else {
            MatSach ms = new MatSach(m.getMaMuon(), m.getMaSach().getMaSach(), m.getTenSach(), m.getSoThe().getSoThe(), d);

            String s = "Bạn có chắc chắn là sách đã bị mất?";
            JFXDialogLayout dialogLayout = new JFXDialogLayout();
            dialogLayout.setHeading(new Text("Thông báo"));
            dialogLayout.setBody(new Text(s));

            JFXButton btnCancel = new JFXButton("Không");
            JFXButton btnOK = new JFXButton("Có");
            btnOK.setButtonType(JFXButton.ButtonType.RAISED);

            JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
            btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {
                if (Utils.baoMatSach(ms) == true) {
                    m.setGhiChu("Đã mất");
                    Utils.updateMuon(m);
                    loadMatSach(Utils.getMatSach());
                    loadMuonTra(Utils.getMuonTra());
                    String mess = "Đã báo mất sách";
                    DialogMessenger.dialogThongBao(this.stackPane, mess).show();
                } else {
                    String mess = "Báo mất sách thất bại";
                    DialogMessenger.dialogThongBao(this.stackPane, mess).show();
                }
                dialog.close();
            });
            btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {;
                dialog.close();
            });
            HBox hbox = new HBox();
            hbox.getChildren().add(btnCancel);
            hbox.getChildren().add(btnOK);
            dialogLayout.setActions(hbox);
            dialog.show();
        }
    }
    @FXML
    private VBox vboxTheDocGia;
    @FXML
    TableView<TheDocGia> tbvTheDocGia;
    @FXML
    private TableColumn<TheDocGia, Integer> tbcMaTheDocGia;
    @FXML
    private TableColumn<TheDocGia, Date> tbcHSDTheDocGia;
    @FXML
    private JFXButton btnThemThe;
    @FXML
    private JFXButton btnGiaHan;
    @FXML
    private JFXButton btnLuuThe;
    @FXML
    private JFXTextField txtSoTheDocGia;
    @FXML
    private JFXDatePicker txtNgaykt;

    public void loadTheDocGia(List a) {
        tbcMaTheDocGia.setCellValueFactory(new PropertyValueFactory<>("soThe"));
        //
        tbcHSDTheDocGia.setCellValueFactory(new PropertyValueFactory<>("nkt"));
        this.tbvTheDocGia.setItems(FXCollections.observableArrayList(a));

    }

    public void themTheDocGiaHadler() throws ParseException {

        String stringDate = String.valueOf(this.txtNgaykt.getValue());
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
        TheDocGia tg = new TheDocGia(Integer.parseInt(this.txtSoTheDocGia.getText()), d);

        if (Utils.addTheDocGia(tg) == true) {

            loadTheDocGia(Utils.getTheDocGia());
            String mess = "Thêm thẻ độc giả thành công";
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();
        } else {
            String mess = "Thêm thẻ độc giả thất bại";
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();
        }

    }

    public void giaHanTheDocGiaHandler() throws ParseException {
        TheDocGia tg = this.tbvTheDocGia.getSelectionModel().getSelectedItem();

        String stringDate = String.valueOf(this.txtNgaykt.getValue());
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
        tg.setSoThe(Integer.parseInt(this.txtSoTheDocGia.getText()));
        tg.setNkt(d);

        Utils.updateThe(tg);

        reloadTbTheDocGia(Utils.getTheDocGia());

    }

    public void luuTheHadler(ActionEvent event) throws ParseException {
        try {
            if (this.txtSoTheDocGia.getText() == null) {
                String mess = "Dữ liệu trống";
                DialogMessenger.dialogThongBao(this.stackPane, mess).show();
            } else if (this.txtSoTheDocGia.getText().equals("") || this.txtNgaykt.getValue() == null) {
                String mess = "Dữ liệu trống";
                DialogMessenger.dialogThongBao(this.stackPane, mess).show();
            } else if (Integer.parseInt(this.txtSoTheDocGia.getText()) < 0) {
                String mess = "Số thẻ không hợp lệ";
                DialogMessenger.dialogThongBao(this.stackPane, mess).show();
            } else if (this.txtSoTheDocGia.getText().length() > 11) {
                String mess = "Số thẻ quá dài";
                DialogMessenger.dialogThongBao(this.stackPane, mess).show();
            } else if (luuThe == 1) {
                themTheDocGiaHadler();
            } else {
                String s = "Bạn có chắc chắn gia hạn thẻ này không?";
                JFXDialogLayout dialogLayout = new JFXDialogLayout();
                dialogLayout.setHeading(new Text("Thông báo"));
                dialogLayout.setBody(new Text(s));
                JFXButton btnCancel = new JFXButton("Không");
                JFXButton btnOK = new JFXButton("Có");
                btnOK.setButtonType(JFXButton.ButtonType.RAISED);
                JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
                btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {;
                    try {
                        giaHanTheDocGiaHandler();

                        String mess = "Gia hạn thành công";
                        DialogMessenger.dialogThongBao(this.stackPane, mess).show();
                    } catch (ParseException ex) {
                        Logger.getLogger(SachController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dialog.close();
                });
                btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {;
                    dialog.close();
                });
                HBox hbox = new HBox();
                hbox.getChildren().add(btnCancel);
                hbox.getChildren().add(btnOK);
                dialogLayout.setActions(hbox);
                dialog.show();
            }
        } catch (NumberFormatException ex) {
            String mess = "Số thẻ không hợp lệ";
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();
        }

    }
    @FXML
    private VBox vboxTaiKhoan;
    @FXML
    TableView<TaiKhoan> tbvTaiKhoan;
    @FXML
    private TableColumn<TaiKhoan, String> tbcTentk;
    @FXML
    private TableColumn<TaiKhoan, String> tbcMatKhau;
    @FXML
    private TableColumn<TaiKhoan, String> tbcSalt;
    @FXML
    private TableColumn<TaiKhoan, Boolean> tbcQTC;
    @FXML
    private JFXButton btnTaotk;
    @FXML
    private JFXButton btnDoitk;
    @FXML
    private JFXButton btnLuutk;
    @FXML
    private JFXTextField txtMatKhau2;
    @FXML
    private JFXTextField txtTaiKhoan2;
    @FXML
    private RadioButton rdQL;
    @FXML
    private RadioButton rdNV;
    @FXML
    private JFXButton btnTaiKhoan;
    @FXML
    private JFXTextField txtTimTheDocGia;
    @FXML
    private JFXTextField txtTimTaiKhoan;

    public void loadTaiKhoan(List a) {
        tbcTentk.setCellValueFactory(new PropertyValueFactory<>("tenTK"));
        //
        tbcMatKhau.setCellValueFactory(new PropertyValueFactory<>("matKhau"));
        tbcSalt.setCellValueFactory(new PropertyValueFactory<>("saltMK"));
        tbcQTC.setCellValueFactory(new PropertyValueFactory<>("quyenTC"));
        this.tbvTaiKhoan.setItems(FXCollections.observableArrayList(a));

    }

    public void taoTKHandler() throws NoSuchAlgorithmException {
        boolean ktQuyen = false;
        if (this.rdQL.isSelected()) {
            ktQuyen = true;
        }
        TaiKhoan tk = new TaiKhoan(this.txtTaiKhoan2.getText(), this.txtMatKhau2.getText(),
                ktQuyen, DangNhapController.convertHashToString(this.txtMatKhau2.getText()));

        if (Utils.addTaiKhoan(tk) == true) {
            loadTaiKhoan(Utils.getTaiKhoan2());
            String mess = "Tạo tài khoản thành công";
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();
        } else {
            String mess = "Tạo tài khoản thất bại";
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();
        }

    }

    public void updateTaiKhoanHandler() throws ParseException, NoSuchAlgorithmException {
        boolean ktQuyen = false;
        if (this.rdQL.isSelected()) {
            ktQuyen = true;
        }
        TaiKhoan tk = this.tbvTaiKhoan.getSelectionModel().getSelectedItem();
        tk.setTenTK(this.txtTaiKhoan2.getText());
        tk.setMatKhau(this.txtMatKhau2.getText());
        tk.setSaltMK(DangNhapController.convertHashToString(this.txtMatKhau2.getText()));
        tk.setQuyenTC(ktQuyen);
        Utils.updateTK(tk);

        reloadTbTaiKhoan(Utils.getTaiKhoan2());

    }

    public void luuTaiKhoanHadler(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
        if (this.txtTaiKhoan2.getText() == null || this.txtMatKhau2.getText() == null) {
            String mess = "Dữ liệu trống";
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();
        } else if (this.txtTaiKhoan2.getText().equals("") || this.txtMatKhau2.getText().equals("")) {
            String mess = "Dữ liệu trống";
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();
        } else if (this.txtTaiKhoan2.getText().length() > 45 || this.txtMatKhau2.getText().length() > 45) {
            String mess = "Tài khoản hoặc mật khẩu không hợp lệ";
            DialogMessenger.dialogThongBao(this.stackPane, mess).show();
        } else if (luuTaiKhoan == 1) {
            taoTKHandler();
        } else {
            String s = "Bạn có chắc chắn thay dổi thông tin tài khoản này không?";
            JFXDialogLayout dialogLayout = new JFXDialogLayout();
            dialogLayout.setHeading(new Text("Thông báo"));
            dialogLayout.setBody(new Text(s));
            JFXButton btnCancel = new JFXButton("Không");
            JFXButton btnOK = new JFXButton("Có");
            btnOK.setButtonType(JFXButton.ButtonType.RAISED);
            JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
            btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {;
                try {
                    updateTaiKhoanHandler();

                    String mess = "Thay đổi tài khoản thành công";
                    DialogMessenger.dialogThongBao(this.stackPane, mess).show();
                } catch (ParseException ex) {
                    Logger.getLogger(SachController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(SachController.class.getName()).log(Level.SEVERE, null, ex);
                }
                dialog.close();
            });
            btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {;
                dialog.close();
            });
            HBox hbox = new HBox();
            hbox.getChildren().add(btnCancel);
            hbox.getChildren().add(btnOK);
            dialogLayout.setActions(hbox);
            dialog.show();
        }
    }

    public void chonTaiKhoanHandler(MouseEvent event) {
        this.txtTaiKhoan2.setDisable(true);
        this.txtMatKhau2.setDisable(true);
        this.rdQL.setDisable(true);
        this.rdNV.setDisable(true);
        this.txtTaiKhoan2.setText(tbvTaiKhoan.getSelectionModel().getSelectedItem().getTenTK());
        this.txtMatKhau2.setText(tbvTaiKhoan.getSelectionModel().getSelectedItem().getMatKhau());
        boolean s = true;
        s = (tbvTaiKhoan.getSelectionModel().getSelectedItem().isQuyenTC());
        System.out.println(s);
        if (s == false) {
            rdNV.setSelected(true);
        } else {
            rdQL.setSelected(true);
        }
        btnDoitk.setVisible(true);

    }

    public void chonTheDocGiaHandler(MouseEvent event) {
        this.txtSoTheDocGia.setDisable(true);
        this.txtNgaykt.setDisable(true);
        btnLuuThe.setDisable(true);
        this.txtSoTheDocGia.setText(String.valueOf(tbvTheDocGia.getSelectionModel().getSelectedItem().getSoThe()));
        String ns = String.valueOf(tbvTheDocGia.getSelectionModel().getSelectedItem().getNkt());
        int y = Integer.parseInt(ns.substring(0, 4));
        int m = Integer.parseInt(ns.substring(5, 7));
        int d = Integer.parseInt(ns.substring(8, 10));
        System.out.println(d + " " + m + " " + y);
        this.txtNgaykt.setValue(LocalDate.of(y, m, d));

        btnGiaHan.setVisible(true);

    }

    @FXML
    private VBox vboxMatSach;
    @FXML
    TableView<MatSach> tbvMatSach;
    @FXML
    private TableColumn<MatSach, Integer> tbcMaMatSach;
    @FXML
    private TableColumn<MatSach, Integer> tbcMaPhieuMuon;
    @FXML
    private TableColumn<MatSach, String> tbcMaSachMat;
    @FXML
    private TableColumn<MatSach, String> tbcTenSachMat;
    @FXML
    private TableColumn<MatSach, Date> tbcNgayGhiNhan;
    @FXML
    private TableColumn<MatSach, Integer> tbcMaTheMatSach;

    public void loadMatSach(List a) {
        tbcMaMatSach.setCellValueFactory(new PropertyValueFactory<>("maMatSach"));
        tbcMaPhieuMuon.setCellValueFactory(new PropertyValueFactory<>("maPhieuMuon"));

        tbcMaSachMat.setCellValueFactory(new PropertyValueFactory<>("maSachMat"));
        tbcTenSachMat.setCellValueFactory(new PropertyValueFactory<>("tenSachMat"));
        tbcMaTheMatSach.setCellValueFactory(new PropertyValueFactory<>("soTheMat"));
        tbcNgayGhiNhan.setCellValueFactory(new PropertyValueFactory<>("ngayGhiNhan"));
        this.tbvMatSach.setItems(FXCollections.observableArrayList(a));

    }
    @FXML
    LineChart chart;
//    Series s;
//    XYChart.Series dataSeries;

    public void xemThongKe() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Tháng");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Tổng mượn sách");
//        s = this.dataSeries;
//        s.getData().re
        chart.getData().clear();

        long ngay = System.currentTimeMillis();
        Date ngaythongke = new Date(ngay);
        SimpleDateFormat f = new SimpleDateFormat("MM");
        SimpleDateFormat l = new SimpleDateFormat("yyyy");
        String namtk = l.format(ngaythongke);
        int nam = Integer.parseInt(namtk);
//        chart.setCreateSymbols(true);

        for (int t : Utils.getNamThongKe()) {
            XYChart.Series dataSeries = new XYChart.Series();
            dataSeries.setName("Mượn sách trong nam " + t);
            for (ThongKe tk : Utils.getThongKeMuonTheoThang(t)) {
                dataSeries.getData().add(new XYChart.Data(String.valueOf(tk.getThang()),
                         tk.getTongThang()));

            }
            chart.getData().addAll(dataSeries);
        }

    }
    @FXML
    PieChart pieChart;
    @FXML
    AnchorPane a;
    @FXML
    ProgressIndicator progressIndicator;
    @FXML
    Label label;
    @FXML
    ProgressIndicator progressIndicator1;
    @FXML
    Label label1;
    @FXML
    ProgressIndicator progressIndicator3;
    @FXML
    Label label3;
    doWork task;
    @FXML
    JFXProgressBar Sach1;
    @FXML
    Label lbSach1;
    @FXML
    Label lbTiLeSach1;
    @FXML
    JFXProgressBar Sach2;
    @FXML
    Label lbSach2;
    @FXML
    Label lbTiLeSach2;

    @FXML
    JFXProgressBar Sach3;
    @FXML
    Label lbSach3;
    @FXML
    Label lbTiLeSach3;
    
    public void thongKe() {
        long ngay = System.currentTimeMillis();
        Date ngaythongke = new Date(ngay);
        SimpleDateFormat f = new SimpleDateFormat("MM");
        SimpleDateFormat l = new SimpleDateFormat("yyyy");
        String thangtk = f.format(ngaythongke);
        String stringDateHetHan = String.valueOf(this.dateNgayHetHan.getValue());

        int thang = Integer.parseInt(thangtk);
        int quy = 0;
        if (thang == 1 || thang == 2 || thang == 3) {
            quy = 1;
        } else if (thang == 4 || thang == 5 || thang == 6) {
            quy = 2;
        } else if (thang == 7 || thang == 8 || thang == 9) {
            quy = 3;
        } else {
            quy = 4;
        }
        String namtk = l.format(ngaythongke);
        int nam = Integer.parseInt(namtk);
        ThongKe z = new ThongKe(thang, nam, Utils.thongKeMuonTheoThangNam(thang, nam), Utils.thongKeMuonTheoQuyNam(quy, nam), Utils.thongKeTheoNam(nam));
        if (Utils.kiemtraThongKe(thang, nam) < 1) {
            if (Utils.addThongKe(z)) {
                System.out.println("Thông kê thành công");
            } else {
                System.out.println("Thông kê thất bại");
            }
        } else {
            z.setMaThongKe(Utils.getThongKe(nam, thang).get(0).getMaThongKe());
            Utils.updateThongKe(z);
            System.out.println("thoongkee sua");
        }

        //piechart
        
        pieChart.getData().clear();
        int tong = Utils.getTongChuaOrDaTraSach("Đã trả")
                + Utils.getTongChuaOrDaTraSach("Chưa trả") + Utils.getTongChuaOrDaTraSach("Đã mất");
        int datra = Utils.getTongChuaOrDaTraSach("Đã trả") * 100 / tong;
        int chuatra = Utils.getTongChuaOrDaTraSach("Chưa trả") * 100 / tong;
        int matsach = 100 - datra - chuatra;
        PieChart.Data slice1 = new PieChart.Data("Đã trả: (" + datra + "%)", Utils.getTongChuaOrDaTraSach("Đã trả"));
        PieChart.Data slice2 = new PieChart.Data("Chưa trả: (" + chuatra + "%)", Utils.getTongChuaOrDaTraSach("Chưa trả"));
        PieChart.Data slice3 = new PieChart.Data("Mất sách: (" + matsach + "%)", Utils.getTongChuaOrDaTraSach("Đã mất"));

        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
        pieChart.getData().add(slice3);
        final Label caption = new Label("");
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 30 arial;");

        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());

                    caption.setText(String.valueOf(data.getPieValue()));
                }
            });
        }
        a.getChildren().add(caption);

        int tuThang = Utils.getThongKe(nam, thang).get(0).getTongThang() * 100
                / Utils.getThongKe(nam, thang).get(0).getTongNam();

        System.out.println(tuThang);
        task = new doWork(tuThang, 100);
        progressIndicator.progressProperty().bind(task.progressProperty());
        label.textProperty().bind(task.messageProperty());

        new Thread(task).start();

        int tuQuy = (Utils.getThongKe(nam, thang).get(0).getTongQuy() * 100)
                / Utils.getThongKe(nam, thang).get(0).getTongNam();

        System.out.println(tuQuy);
        task = new doWork(tuQuy, 100);
        progressIndicator1.progressProperty().bind(task.progressProperty());
        label1.textProperty().bind(task.messageProperty());

        new Thread(task).start();

        int tuNgay = (Utils.getThongKe(nam, thang).get(0).getTongQuy() * 100)
                / Utils.getThongKe(nam, thang).get(0).getTongNam();

        System.out.println(tuNgay);
        task = new doWork(tuNgay, 100);
        progressIndicator3.progressProperty().bind(task.progressProperty());
        label3.textProperty().bind(task.messageProperty());

        new Thread(task).start();

        lbSach1.setText(Utils.getMuonTraTang().get(0).getTenSach());
        int a = Integer.parseInt(String.valueOf(Utils.getDemMuonTraTang().get(0)));
        int thichSach1 = a * 100 / Utils.getDemTongTraTang();
        System.out.println(thichSach1);
        task = new doWork(thichSach1, 100);
        Sach1.progressProperty().bind(task.progressProperty());
        lbTiLeSach1.textProperty().bind(task.messageProperty());
        new Thread(task).start();

        lbSach2.setText(Utils.getMuonTraTang().get(1).getTenSach());
        int a1 = Integer.parseInt(String.valueOf(Utils.getDemMuonTraTang().get(1)));
        int thichSach2 = a1 * 100 / Utils.getDemTongTraTang();
        System.out.println(thichSach2);
        task = new doWork(thichSach2, 100);
        Sach2.progressProperty().bind(task.progressProperty());
        lbTiLeSach2.textProperty().bind(task.messageProperty());
        new Thread(task).start();

        lbSach3.setText(Utils.getMuonTraTang().get(2).getTenSach());
        int a2 = Integer.parseInt(String.valueOf(Utils.getDemMuonTraTang().get(2)));
        int thichSach3 = a2 * 100 / Utils.getDemTongTraTang();
        System.out.println(thichSach3);
        task = new doWork(thichSach3, 100);
        Sach3.progressProperty().bind(task.progressProperty());
        lbTiLeSach3.textProperty().bind(task.messageProperty());
        new Thread(task).start();

    }

}
