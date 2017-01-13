/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.MainScreen;

import com.scrumpe.scrumpeclient.DB.DAO.UserDAO;
import com.scrumpe.scrumpeclient.DB.DBManager;
import com.scrumpe.scrumpeclient.DB.Entity.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import com.scrumpe.scrumpeclient.Screen.Base.ScreenBase;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager;
import com.scrumpe.scrumpeclient.Utils.Escurity;
import com.scrumpe.scrumpeclient.Utils.LessRT;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class LoginController extends ScreenBase implements EventHandler<WorkerStateEvent> {

    @FXML
    private Node root;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passField;
    private Task loginTask;
    @FXML
    private AnchorPane forgotPassContainer;

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
        email = Escurity.cleanString(email);
        emailField.setText(email);
        User u = userdao.tryLogin(email, password);
        return u;
    }

    @Override
    public void handle(WorkerStateEvent event) {
        User u = (User) loginTask.getValue();
        if (u != null) {
            ScreenManager sm = ScreenManager.getInstance();
            sm.loadScreen(ScreenManager.MainScreen.Main);
            ContainerController cc = sm.getRootLoader().getController();
            cc.loggedInUser.setText("Welcome " + u.getFirstName() + " " + u.getLastName());
        } else {
            throwError("Wrong Credentials");
        }
        ScreenManager.getInstance().showLoadingScreen(false);
        DBManager.getInstance().close();
    }

    @Override
    public void setTitle() {
    }

    @FXML
    private void forgotPassword(MouseEvent event) {
            //TODO remove if password reminder is implemented

        throwError("Sorry, this functionality is not ready yet... please contact the administrator.");
        //
        //Then uncomment this
//        if (forgotPassContainer.mouseTransparentProperty().get()) {
//            forgotPassContainer.setDisable(false);
//            forgotPassContainer.setOpacity(1.0);
//            forgotPassContainer.setMouseTransparent(false);
//        } else {
//            forgotPassContainer.setDisable(true);
//            forgotPassContainer.setOpacity(0.0);
//            forgotPassContainer.setMouseTransparent(true);
//        }
    }

    @FXML
    private void sendPassReminder(ActionEvent event) {
        throwError("A new password has been sent to");
        forgotPassword(null);
    }
}
