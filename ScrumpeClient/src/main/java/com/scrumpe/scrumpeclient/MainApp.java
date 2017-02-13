/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager;
import com.scrumpe.scrumpeclient.Utils.LessRT;
import com.scrumpe.scrumpeclient.Utils.Resizer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Max Verhoeven
 */
public class MainApp extends Application implements EventHandler<KeyEvent>{
    private static Stage rootStage;
    public static Stage getRootStage() {
        return rootStage;
    }
    //TODO move to settings class;
    private static final int SCREEN_MIN_WIDTH = 1280;
    private static final int SCREEN_MIN_HEIGHT = 800;
    //end
    
    @Override
    public void start(Stage stage) throws Exception {
         stage.initStyle(StageStyle.UNDECORATED);
        LessRT.Setup(MainApp.class);
        rootStage = stage;
        LessRT.compileShit();
        ScreenManager sm = ScreenManager.getInstance();
        Parent root = (Parent) sm.getRoot();
        
        Scene scene = new Scene(root);
       

        stage.setScene(scene);  
        stage.setMinWidth(SCREEN_MIN_WIDTH);
        stage.setMinHeight(SCREEN_MIN_HEIGHT);
        stage.setMaximized(true);
        sm.loadScreen(ScreenManager.MainScreen.Login);
        Resizer.addResizeListener(stage);
        stage.show();
        root.addEventHandler(KeyEvent.KEY_PRESSED, this);
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(KeyEvent event) {
        KeyCode key = event.getCode();
        if(key == KeyCode.CONTROL){
            LessRT.compileShit();
        }
    }
    
}
