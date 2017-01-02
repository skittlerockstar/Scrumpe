/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screens;

import com.scrumpe.scrumpeclient.ComponentFactory;
import com.scrumpe.scrumpeclient.DB.DAO.UserDAO;
import com.scrumpe.scrumpeclient.DB.DBManager;
import com.scrumpe.scrumpeclient.DB.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import com.scrumpe.scrumpeclient.MainScreenController;
import com.scrumpe.scrumpeclient.ScreenManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class LoginController extends MainScreenController implements EventHandler<WorkerStateEvent>{

    @FXML
    private Node root;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passField;
    private Task loginTask;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
        ScreenManager.getInstance().showLoadingScreen(true);
        loginTask = new Task() {
            @Override
            protected Object call() throws Exception {
                return tryLogin();
            }
        };
        loginTask.setOnSucceeded(this);
        Thread th = new Thread(loginTask);
        th.setDaemon(true);
        th.start();
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

    private User tryLogin() {
        UserDAO userdao = (UserDAO) DBManager.getInstance().getDAO(UserDAO.class);
        String email = emailField.getText();
        String password = passField.getText();
        User u = userdao.tryLogin(email, password);
        if (u == null) {
            throwError("Wrong Credentials");
            return u;
        } else {
            return u;
        }
    }

    @Override
    public void handle(WorkerStateEvent event) {
        User u = (User) loginTask.getValue();
        if(u != null){
        ScreenManager sm = ScreenManager.getInstance();
        sm.loadScreen(ScreenManager.MainScreen.Main);
        ContainerController cc = sm.getRootLoader().getController();
        cc.loggedInUser.setText("Welcome " + u.getFirstName() + " " + u.getLastName());
        }
        ScreenManager.getInstance().showLoadingScreen(false);
    }
}
