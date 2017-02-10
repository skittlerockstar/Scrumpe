/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.OverlayScreen;

import com.scrumpe.scrumpeclient.Screen.Base.OverlayBase;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class PopUpNotificationController  extends OverlayBase{
    @FXML 
    private Label errorText;
    @FXML
    private Button dismiss,confirmYes,confirmNo;
    @FXML
    private HBox dismissBox,confirmBox;
    private Stage stage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      dismiss.setOnAction((ActionEvent event) -> {
          componentRoot.setVisible(false);
      });
      confirmYes.setOnAction((ActionEvent event) -> {
          componentRoot.setVisible(false);
      });
      confirmNo.setOnAction((ActionEvent event) -> {
          componentRoot.setVisible(false);
      });
    }    
    public void setMessage(String message){
        errorText.setText(message);
    }
    @Override
    public void setupLayout() {
       AnchorPane.setBottomAnchor(componentRoot, 0.0);
       AnchorPane.setTopAnchor(componentRoot, 0.0);
       AnchorPane.setRightAnchor(componentRoot, 0.0);
       AnchorPane.setLeftAnchor(componentRoot, 0.0);
       componentRoot.setVisible(false);
    }

    @Override
    public void onChanged() {
    }

}
