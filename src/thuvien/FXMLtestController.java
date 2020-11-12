package thuvien;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;
import javafx.util.StringConverter;
import javafx.util.converter.FormatStringConverter;

/**
 * FXML Controller class
 *
 * @author Ánh
 */
public class FXMLtestController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    JFXDatePicker datePicker;
    @FXML
    ImageView img;
    @FXML
    Button bt;
    @FXML
    AnchorPane a;
    
    @FXML
    AnchorPane b;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
        datePicker.setValue(LocalDate.now().plusDays(6));

        
        // Converter
        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                      DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };   
        datePicker.setConverter(converter);
        datePicker.setPromptText("dd/MM/yyyy");
        
        
        
        TranslateTransition t = new TranslateTransition();
        t.setDuration(javafx.util.Duration.seconds(4));
        t.setNode(datePicker);
        t.setToX(200);
        t.play();
        
        PieChart p = new PieChart();
        p.getData().clear();
       PieChart.Data slice1 = new PieChart.Data("Đã trả" + Utils.getTongChuaOrDaTraSach("Đã trả: "), Utils.getTongChuaOrDaTraSach("Đã trả"));
       PieChart.Data slice2 = new PieChart.Data("Chưa trả" +  Utils.getTongChuaOrDaTraSach("Chưa trả: "), Utils.getTongChuaOrDaTraSach("Chưa trả"));
       PieChart.Data slice3 = new PieChart.Data("Mất sách" +  Utils.getTongChuaOrDaTraSach("Đã mất: "), Utils.getTongChuaOrDaTraSach("Đã mất"));
       
       p.getData().add(slice1);
       p.getData().add(slice2);
       p.getData().add(slice3);
       final Label caption = new Label("");
        caption.setTextFill(Color.WHITE);
        caption.setStyle("-fx-font: 20 arial;");
 
        for (final PieChart.Data data : p.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
 
                    caption.setText(String.valueOf(data.getPieValue()));
                }
            });
        }
  
        b.getChildren().addAll(p,caption);
        
    }    
    
}
