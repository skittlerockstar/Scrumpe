/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient;
import com.scrumpe.scrumpeclient.DB.DAO.UserDAO;
import com.scrumpe.scrumpeclient.DB.DBManager;
import com.scrumpe.scrumpeclient.DB.User;
import com.scrumpe.scrumpeclient.Utils.Escurity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.mongodb.morphia.Key;

/**
 *
 * @author Max Verhoeven
 */
public class MainApp extends Application {
    private static Stage rootStage;
    public static Stage getRootStage() {
        return rootStage;
    }
    private static final int SCREEN_MIN_WIDTH = 800;
    private static final int SCREEN_MIN_HEIGHT = 600;
    @Override
    public void start(Stage stage) throws Exception {
        rootStage = stage;
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

    private void testM() {
       
    }
    
}
