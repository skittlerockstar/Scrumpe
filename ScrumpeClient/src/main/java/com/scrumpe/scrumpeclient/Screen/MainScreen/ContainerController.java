/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.MainScreen;

import com.scrumpe.scrumpeclient.MainApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Max Verhoeven
 */
public class ContainerController implements Initializable {

    @FXML
    public Label loggedInUser;
    @FXML
    private Label label;
    @FXML
    private BorderPane root;
    @FXML
    private AnchorPane windowBar;
    @FXML
    private Button suspendBtn, resizeBtn, exitBtn;
    @FXML
    private ImageView resizeImg;

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
        ScreenManager sm = ScreenManager.getInstance();
        sm.loadScreen(ScreenManager.MainScreen.Login);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Stage rootStage = MainApp.getRootStage();
        final Delta dragDelta = new Delta();
        windowBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta.x = rootStage.getX() - mouseEvent.getScreenX();
                dragDelta.y = rootStage.getY() - mouseEvent.getScreenY();
            }
        });
        windowBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rootStage.setX(mouseEvent.getScreenX() + dragDelta.x);
                rootStage.setY(mouseEvent.getScreenY() + dragDelta.y);
            }
        });
        suspendBtn.setOnAction((x)-> iconify(rootStage));
            resizeBtn.setOnAction((x) -> resize(rootStage));
        exitBtn.setOnAction((x) -> exit());
    }

    private void exit() {
        Platform.exit();
    }

    private void resize(Stage rootStage) {
        boolean state = rootStage.isMaximized();
        String root = "/img/";
        URL resource = getClass().getResource(root+(state?"Resize1.png":"Resize2.png"));
        resizeImg.setImage(new Image(resource.toString()));
        rootStage.setMaximized(!state);
        if(!state) rootStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        rootStage.setX(0);
        rootStage.setY(0);
    }

    private void iconify(Stage rootStage) {
        rootStage.setIconified(true);
    }

    class Delta {

        double x, y;
    }
}
