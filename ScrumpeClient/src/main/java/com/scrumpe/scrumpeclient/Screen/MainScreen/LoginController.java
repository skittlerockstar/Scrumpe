/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.MainScreen;

import com.scrumpe.scrumpeclient.DB.DAO.Callback.DAOCallBack;
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
import java.util.List;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class LoginController extends ScreenBase implements DAOCallBack<User> {

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
        emailField.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                try {
                    login();
                } catch (Exception e) {
                }
            }
        });
        passField.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                try {
                    login();
                } catch (Exception e) {
                }
            }
        });
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
        login();
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

    @Override
    public void setTitle() {
    }

    @FXML
    private void forgotPassword(MouseEvent event) {
        //TODO remove if password reminder is implemented
        presentNote("Sorry, this functionality is not ready yet... please contact the administrator.");
    }

    @FXML
    private void sendPassReminder(ActionEvent event) {
        presentNote("A new password has been sent to");
        forgotPassword(null);
    }

    @Override
    public void dbResult(User result) {
        if (result != null) {
            ScreenManager sm = ScreenManager.getInstance();
            sm.loadScreen(ScreenManager.MainScreen.Main, true);
            ContainerController cc = sm.getRootLoader().getController();
            System.err.println(result.toString());
            cc.loggedInUser.setText("Welcome " + result.getFirstName() + " " + result.getLastName());
        } else {
            presentNote("Wrong Credentials");
        }
    }

    private void login() throws Exception {
        UserDAO uDAO = data.getDAO(UserDAO.class);
        uDAO.tryLogin(this, emailField.getText(), passField.getText());
    }
}
