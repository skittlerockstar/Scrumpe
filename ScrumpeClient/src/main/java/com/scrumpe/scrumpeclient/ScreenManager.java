/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient;

import java.awt.Color;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javax.swing.BorderFactory;

/**
 *
 * @author Max Verhoeven
 */
public class ScreenManager {
    
    private FXMLLoader root;
    private Node rootMainContainer;
    private HBox popUpRoot;
    public enum MainScreen{ Login, Main, ActiveCourse, LoadingScreen,PopUpScreen}
    private MainScreen currentScreen;
    private HashMap<MainScreen,FXMLLoader> screenList;
    
    private List<Node> components = new ArrayList<>();
    
    private static ScreenManager screenManager;
    private static final String SCREEN_ROOT_DIR = "/fxml/";
    public Node getRoot(){return root.getRoot();}
    public FXMLLoader getRootLoader(){return root;}
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
         screenList.put(MainScreen.Login, new FXMLLoader(getClass().getResource(SCREEN_ROOT_DIR+"Login.fxml")));
         screenList.put(MainScreen.Main, new FXMLLoader(getClass().getResource(SCREEN_ROOT_DIR+"Main.fxml")));
         screenList.put(MainScreen.ActiveCourse, new FXMLLoader(getClass().getResource(SCREEN_ROOT_DIR+"CourseActive.fxml")));
         screenList.put(MainScreen.LoadingScreen, new FXMLLoader(getClass().getResource(SCREEN_ROOT_DIR+"LoadingScreen.fxml")));
         screenList.put(MainScreen.PopUpScreen, new FXMLLoader(getClass().getResource(SCREEN_ROOT_DIR+"PopUpNotification.fxml")));
         for (Map.Entry<MainScreen, FXMLLoader> entry : screenList.entrySet()) {
            FXMLLoader value = entry.getValue();
            value.load();
        }
        FXMLLoader p =  screenList.get(MainScreen.LoadingScreen);
        popUpRoot.getChildren().add(p.getRoot());
        ((MainScreenController)p.getController()).setup(p.getRoot());
    }
    private void setRoot() throws Exception{
        root = new FXMLLoader(getClass().getResource(SCREEN_ROOT_DIR+"Container.fxml"));
        Pane base = root.load();
        rootMainContainer = ((BorderPane)base.getChildren().get(0)).getCenter();
        popUpRoot = (HBox)base.getChildren().get(1);
        showLoadingScreen(false);
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
    public void showLoadingScreen(boolean val){
        popUpRoot.setVisible(val);
    }
}
