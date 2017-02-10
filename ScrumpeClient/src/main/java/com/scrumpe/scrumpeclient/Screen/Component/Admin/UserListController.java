/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.Component.Admin;

import com.scrumpe.scrumpeclient.DB.DAO.Callback.DAOCallBack;
import com.scrumpe.scrumpeclient.DB.DAO.UserDAO;
import com.scrumpe.scrumpeclient.DB.Entity.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import com.scrumpe.scrumpeclient.Screen.Base.ComponentBase;
import com.scrumpe.scrumpeclient.Screen.Utils.ComponentFactory;
import static com.scrumpe.scrumpeclient.Screen.Utils.ComponentFactory.ComponentType;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

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
    }

    @Override
    public void setupLayout() {
        HBox.setHgrow(componentRoot, Priority.ALWAYS);
        HBox.setMargin(userList, new Insets(5));
        createUserList();
    }

    private void createUserList() {
        UserDAO dao = data.getDAO(UserDAO.class);
        getUsers(dao);
    }

    @Override
    public void onChanged() {
    }

    private void getUsers(UserDAO dao) {
        dao.getUsers((results) -> {
            ObservableList<Node> userlist = FXCollections.observableArrayList();
            for (User result : results) {
                FXMLLoader l = ComponentFactory.createComponent(this, ComponentType.UserListItem, true);
                UserListItemController ulic = l.getController();
                ulic.setUserData(result);
                userlist.add(l.getRoot());
            }
            userList.setItems(userlist);
        });
    }

}
