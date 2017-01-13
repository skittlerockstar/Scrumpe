/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.MainScreen;

import com.scrumpe.scrumpeclient.DB.DAO.DAOCallBack;
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

    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
        UserDAO uDAO = data.getDAO(UserDAO.class);
        uDAO.tryLogin(this,emailField.getText(), passField.getText());
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
        throwError("Sorry, this functionality is not ready yet... please contact the administrator.");
    }

    @FXML
    private void sendPassReminder(ActionEvent event) {
        throwError("A new password has been sent to");
        forgotPassword(null);
    }

    @Override
    public void dbResult(User result) {
       if (result != null) {
            ScreenManager sm = ScreenManager.getInstance();
            sm.loadScreen(ScreenManager.MainScreen.Main);
            ContainerController cc = sm.getRootLoader().getController();
            cc.loggedInUser.setText("Welcome " + result.getFirstName() + " " + result.getLastName());
        } else {
            throwError("Wrong Credentials");
        }
    }

    @Override
    public void dbResults(List<User> results) {
    }
}
