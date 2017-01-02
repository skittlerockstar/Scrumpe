/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screens.Component;

import java.awt.FlowLayout;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import com.scrumpe.scrumpeclient.ComponentController;
import com.scrumpe.scrumpeclient.ScreenManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class CourseListItemController extends ComponentController {

    @FXML
    private Node startCourse;
    private int id;
    public void setId(int i){
        id =i;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setupLayout() {
       
        FlowPane.setMargin(componentRoot, new Insets(5));
    }
    @FXML
    private void startC(ActionEvent event){
            ScreenManager.getInstance().loadScreen(ScreenManager.MainScreen.ActiveCourse);
    }
    
}
