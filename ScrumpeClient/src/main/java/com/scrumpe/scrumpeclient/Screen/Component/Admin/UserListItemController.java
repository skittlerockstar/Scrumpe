/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.Component.Admin;

import com.scrumpe.scrumpeclient.DB.Entity.User;
import java.net.URL;
import java.util.ResourceBundle;
import com.scrumpe.scrumpeclient.Screen.Base.ComponentBase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class UserListItemController extends ComponentBase{

    @FXML
    private Label firstName,lastName;
    @FXML
    private Button editBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setupLayout() {
        
    }
    public void setUserData(User u){
        firstName.setText(u.getFirstName());
        lastName.setText(u.getLastName());
       // email.setText(u.getEmail());
        editBtn.setUserData(u);
    }
}
