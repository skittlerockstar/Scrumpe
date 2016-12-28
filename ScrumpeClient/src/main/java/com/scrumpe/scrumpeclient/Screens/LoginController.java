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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import com.scrumpe.scrumpeclient.MainScreenController;
import com.scrumpe.scrumpeclient.ScreenManager;

/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class LoginController extends MainScreenController   {

    private Node root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
    ScreenManager sm = ScreenManager.getInstance();
    sm.loadScreen(ScreenManager.MainScreen.Main);
    }

    @Override
    public void setNavigation() {
    }

    @Override
    public void setDescription() {
    }

    @Override
    public void setupLayout() {
        HBox.setHgrow(componentRoot, Priority.NEVER);
    }

    @Override
    public void setAdminComponents() {
        
    }
}
