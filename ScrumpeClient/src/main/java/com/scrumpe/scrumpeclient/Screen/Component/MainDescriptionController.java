/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.Component;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import com.scrumpe.scrumpeclient.Screen.Base.ComponentBase;

/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class MainDescriptionController extends ComponentBase {

    @FXML
    private Label description;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setupLayout() {
        VBox.setVgrow(componentRoot, Priority.NEVER);
    }
    
    public void setDescription(String description){
        this.description.setText(description);
    }

    @Override
    public void onChanged() {
    }
}
