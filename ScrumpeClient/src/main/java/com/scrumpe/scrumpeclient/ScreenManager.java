/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Max Verhoeven
 */
public class ScreenManager {
    
    private FXMLLoader root;
    private Node rootMainContainer;
    
    public enum MainScreen{ Login, Main}
    private MainScreen currentScreen;
    private HashMap<MainScreen,FXMLLoader> screenList;
    
    private List<Node> components = new ArrayList<>();
    
    private static ScreenManager screenManager;
    private static final String SCREEN_ROOT_DIR = "/fxml/";
    public Node getRoot(){return root.getRoot();}
    private ScreenManager(){
        try {
            setRoot();
            initScreens();
        } catch (Exception ex) {
            Logger.getLogger(ScreenManager.class.getName()).log(Level.SEVERE, getClass().getResource("/fxml/Container.fxml").toString(), ex);
        }
    }
    public static ScreenManager getInstance(){
        if(screenManager == null){
            screenManager = new ScreenManager();
        } return screenManager;
    }
    public void initScreens() throws Exception{
        screenList = new HashMap<>();
        currentScreen = MainScreen.Main;
         screenList.put(MainScreen.Login, new FXMLLoader(getClass().getResource("/fxml/"+"Login.fxml")));
         screenList.put(MainScreen.Main, new FXMLLoader(getClass().getResource(SCREEN_ROOT_DIR+"Main.fxml")));
         for (Map.Entry<MainScreen, FXMLLoader> entry : screenList.entrySet()) {
            FXMLLoader value = entry.getValue();
            value.load();
        }
       
    }
    private void setRoot() throws Exception{
        root = new FXMLLoader(getClass().getResource(SCREEN_ROOT_DIR+"Container.fxml"));
        rootMainContainer = ((BorderPane)root.load()).getCenter();
    }
    public void loadScreen(MainScreen screen) {
        try{
        Pane rootCenterPane = ((Pane)rootMainContainer);
        rootCenterPane.getChildren().clear();
        FXMLLoader toLoad = screenList.get(screen);
        Pane node = toLoad.getRoot();
        rootCenterPane.getChildren().add(node);
        currentScreen = screen;
        MainScreenController controller = toLoad.getController();
        controller.setup(node);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
