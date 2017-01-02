/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screens;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import com.scrumpe.scrumpeclient.ScreenManager;

/**
 *
 * @author Max Verhoeven
 */
public class ContainerController implements Initializable {
    
    @FXML
    public Label loggedInUser;
    @FXML
    private Label label;
    @FXML
    private BorderPane root;
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
    ScreenManager sm = ScreenManager.getInstance();
    sm.loadScreen(ScreenManager.MainScreen.Login);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
