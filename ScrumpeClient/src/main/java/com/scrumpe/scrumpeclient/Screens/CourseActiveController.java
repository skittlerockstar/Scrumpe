/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screens;

import com.scrumpe.scrumpeclient.MainScreenController;
import com.scrumpe.scrumpeclient.ScreenManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class CourseActiveController extends MainScreenController {

    @FXML
    private Node QuestionContainer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    @Override
    public void setNavigation() {
        navigation.put(ScreenManager.MainScreen.Main, "Help");
        navigation.put(ScreenManager.MainScreen.Main, "Quit Course");
    }

    @Override
    public void setDescription() {
        description =" Test";
    }

    @Override
    public void setAdminComponents() {
    }

    @Override
    public void setupLayout() {
        HBox.setHgrow(componentRoot, Priority.ALWAYS);
    }
    
}
