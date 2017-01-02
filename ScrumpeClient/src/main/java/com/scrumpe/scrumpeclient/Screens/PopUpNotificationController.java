/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screens;

import com.scrumpe.scrumpeclient.ComponentController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class PopUpNotificationController extends ComponentController {

    @FXML 
    private Label errorText;
    
    @FXML
    private Button dismiss;
    private Stage stage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      dismiss.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
             stage.close();
          }
      });
    }    
    public void setMessage(String message){
        errorText.setText(message);
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    @Override
    public void setupLayout() {
    }
}
