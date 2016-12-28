/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Max Verhoeven
 */
public class MainApp extends Application {
    private static final int SCREEN_MIN_WIDTH = 800;
    private static final int SCREEN_MIN_HEIGHT = 600;
    @Override
    public void start(Stage stage) throws Exception {
        ScreenManager sm = ScreenManager.getInstance();
        Parent root = (Parent) sm.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMinWidth(SCREEN_MIN_WIDTH);
        stage.setMinHeight(SCREEN_MIN_HEIGHT);
        stage.setMaximized(true);
        sm.loadScreen(ScreenManager.MainScreen.Login);
        stage.show();
     
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
