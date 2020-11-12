/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuvien;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pojo.Sach;

/**
 *
 * @author Ánh
 */
public class DialogMessenger {
    public static JFXDialog dialogThongBao(StackPane stackPane, String s){
        
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
          
            dialogLayout.setHeading(new Text("Thông báo"));
            dialogLayout.setBody(new Text(s));
     
         
          
            JFXButton btnOK = new JFXButton("OK");
            
            JFXDialog dialog = new JFXDialog(stackPane,dialogLayout,JFXDialog.DialogTransition.TOP);  
            btnOK.setOnAction((ActionEvent event) -> {
                dialog.close();
        });
            dialogLayout.setActions(btnOK);
      
        return dialog;
    }
    
    
    public static JFXDialog dialogThongBao2chon(StackPane stackPane, String s){
        
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
            System.out.println("Đăng nhập không thành công");
            dialogLayout.setHeading(new Text("Thông báo"));
            dialogLayout.setBody(new Text(s));
            
               
            JFXButton btnCancel = new JFXButton("Cancel");
            JFXButton btnOK = new JFXButton("OK");

            
            
            JFXDialog dialog = new JFXDialog(stackPane,dialogLayout,JFXDialog.DialogTransition.TOP);  
            btnOK.setOnAction((ActionEvent event) -> {
                dialog.close();
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                
            });
            
            btnCancel.setOnAction((ActionEvent event) -> {
                dialog.close();
            });
            HBox hbox = new HBox();
            hbox.getChildren().add(btnCancel);
            hbox.getChildren().add(btnOK);
            dialogLayout.setActions(hbox);
            
      
        return dialog;
    }
    
}
