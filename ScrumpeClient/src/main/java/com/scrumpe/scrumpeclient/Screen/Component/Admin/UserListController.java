/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.Component.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import com.scrumpe.scrumpeclient.Screen.Base.ComponentBase;
import com.scrumpe.scrumpeclient.Screen.Utils.ComponentFactory;
import static com.scrumpe.scrumpeclient.Screen.Utils.ComponentFactory.ComponentType;
/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class UserListController extends ComponentBase {

    @FXML
    private ListView userList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createUserList();
    }

    @Override
    public void setupLayout() {
        HBox.setHgrow(componentRoot, Priority.ALWAYS);
        HBox.setMargin(userList, new Insets(5));
    }

    private void createUserList() {
       ObservableList<Node> items = FXCollections.observableArrayList();
        for (int i = 0; i < 50; i++) {
                FXMLLoader l = ComponentFactory.createComponent(this,ComponentType.UserListItem,true);
                items.add(l.getRoot());
        }
        userList.setItems(items);
    }

}
