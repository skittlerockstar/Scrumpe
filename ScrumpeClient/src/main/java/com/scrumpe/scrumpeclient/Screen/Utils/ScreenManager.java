/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.Utils;

import com.scrumpe.scrumpeclient.MainApp;
import com.scrumpe.scrumpeclient.Screen.Base.OverlayBase;
import com.scrumpe.scrumpeclient.Screen.Base.ScreenBase;
import com.scrumpe.scrumpeclient.Screen.Base.UIComponent;
import com.scrumpe.scrumpeclient.Utils.Lang;
import com.scrumpe.scrumpeclient.Utils.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Max Verhoeven
 */
public class ScreenManager {


    public enum MainScreen {
        Login, Main, ActiveCourse,CourseResults
    }
    public enum OverlayScreen{
        Loading,Notification
    }
    private HashMap<MainScreen, FXMLLoader> screenList = new HashMap<>();
    private HashMap<OverlayScreen, FXMLLoader> overlayScreenList = new HashMap<>();
    
    private static ScreenManager screenManager;
    private static final String SCREEN_ROOT_DIR = "/fxml/";

    private FXMLLoader root; // very root of application
    private Node rootMainContainer; // center pane of borderpane which contains main screens
    private AnchorPane popUpRoot; // sibling of borderpane which contains popupscreens;

    public Node getRoot() {
        return root.getRoot();
    }

    public FXMLLoader getRootLoader() {
        return root;
    }

    private ScreenManager() throws Exception {
        setRoot();
        setScreenResources();
        loadScreens();
        loadPopUpScreens();
    }

    public static ScreenManager getInstance() {
        if (screenManager == null) {
            try {
                screenManager = new ScreenManager();
            } catch (Exception e) {
                System.err.println(e);
                Log.log(ScreenManager.class,Level.SEVERE, e.getCause().toString());
            }
        }
        return screenManager;
    }

    public void setScreenResources() throws Exception {
        screenList.put(MainScreen.Login, new FXMLLoader(getClass().getResource(SCREEN_ROOT_DIR + "Login.fxml")));
        screenList.put(MainScreen.Main, new FXMLLoader(getClass().getResource(SCREEN_ROOT_DIR + "Main.fxml")));
        screenList.put(MainScreen.CourseResults, new FXMLLoader(getClass().getResource(SCREEN_ROOT_DIR + "CourseResult.fxml")));
        screenList.put(MainScreen.ActiveCourse, new FXMLLoader(getClass().getResource(SCREEN_ROOT_DIR + "CourseActive.fxml")));
        
        overlayScreenList.put(OverlayScreen.Loading, new FXMLLoader(getClass().getResource(SCREEN_ROOT_DIR + "LoadingScreen.fxml")));
        overlayScreenList.put(OverlayScreen.Notification, new FXMLLoader(getClass().getResource(SCREEN_ROOT_DIR + "PopUpNotification.fxml")));
    }

    private void loadScreens() {
        try {
            for (Map.Entry<MainScreen, FXMLLoader> entry : screenList.entrySet()) {
                FXMLLoader value = entry.getValue();
                value.setResources(Lang.getLangSet());
                value.load();
            }
             for (Map.Entry<OverlayScreen, FXMLLoader> entry : overlayScreenList.entrySet()) {
                FXMLLoader value = entry.getValue();
                value.setResources(Lang.getLangSet());
                value.load();
            }
        } catch (Exception e) {
            Log.log(ScreenManager.class,Level.SEVERE, e.toString());
        }
    }

    private void setRoot() throws Exception {
        root = new FXMLLoader(getClass().getResource(SCREEN_ROOT_DIR + "Container.fxml"));
        root.setResources(Lang.getLangSet());
        Pane base = root.load();
        rootMainContainer = ((BorderPane) base.getChildren().get(0)).getCenter();
        popUpRoot = (AnchorPane) base.getChildren().get(1);
        popUpRoot.setMouseTransparent(true);
    }

    private void loadPopUpScreens() {
        // Load Loading screen
        FXMLLoader loadingScreen = overlayScreenList.get(OverlayScreen.Loading);
        ((Pane)root.getRoot())
                .getChildren()
                .add(
                        loadingScreen.getRoot());
        OverlayBase controller = loadingScreen.getController();
        controller.setup(loadingScreen.getRoot());
        
        // Load Error Notification screen
        FXMLLoader errorNotification = overlayScreenList.get(OverlayScreen.Notification);
         ((Pane)root.getRoot()).getChildren().add(errorNotification.getRoot());
        ((OverlayBase) errorNotification.getController()).setup(errorNotification.getRoot());
        
    }

    public UIComponent loadScreen(MainScreen screen) {
       return loadScreen(screen,false);
    }
    public UIComponent loadScreen(MainScreen screen,boolean reload) {
        UIComponent controller = null;
        try {
            Pane rootCenterPane = ((Pane) rootMainContainer);
            rootCenterPane.getChildren().clear();
            FXMLLoader toLoad = screenList.get(screen);
            if(reload){
                toLoad = new FXMLLoader(toLoad.getLocation());
                toLoad.setResources(Lang.getLangSet());
                toLoad.load();
                screenList.replace(screen, toLoad);
            }
            Pane node = toLoad.getRoot();
            rootCenterPane.getChildren().add(node);
            controller = toLoad.getController();
            controller.setup(node);
            controller.onChanged();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        
        return controller;
    }

    public void showLoadingScreen(boolean val) {
       UIComponent loadingScreenRoot = overlayScreenList.get(OverlayScreen.Loading).getController();
       loadingScreenRoot.show(val);
    }
    public void showNotification(String message, boolean val) {
       UIComponent notificationRoot = overlayScreenList.get(OverlayScreen.Notification).getController();
       Label l = (Label) MainApp.getRootStage().getScene().lookup("#errorText");
       MainApp.getRootStage().getScene().lookup("#dismissBox").setVisible(true);
       MainApp.getRootStage().getScene().lookup("#confirmBox").setVisible(false);
       l.setText(message);
       notificationRoot.show(val);
    }
    public void showConfirmNotification(String message, boolean val, EventHandler yes,EventHandler no) {
       UIComponent notificationRoot = overlayScreenList.get(OverlayScreen.Notification).getController();
       Label l = (Label) MainApp.getRootStage().getScene().lookup("#errorText");
       MainApp.getRootStage().getScene().lookup("#dismissBox").setVisible(false);
       MainApp.getRootStage().getScene().lookup("#confirmBox").setVisible(true);
        Button yesBtn = (Button) MainApp.getRootStage().getScene().lookup("#confirmYes");
        Button noBtn = (Button) MainApp.getRootStage().getScene().lookup("#confirmNo");
        EventHandler doYes = new EventHandler() {
           @Override
           public void handle(Event event) {
               yes.handle(event);
               yesBtn.removeEventHandler(MouseEvent.MOUSE_PRESSED, this);
           }
       };
         EventHandler doNo = new EventHandler() {
           @Override
           public void handle(Event event) {
               no.handle(event);
               noBtn.removeEventHandler(MouseEvent.MOUSE_PRESSED, this);
           }
       };
        yesBtn.addEventHandler(MouseEvent.MOUSE_PRESSED,doYes);
        noBtn.addEventHandler(MouseEvent.MOUSE_PRESSED,doNo);
       l.setText(message);
       notificationRoot.show(val);
    }
    public void closeNotification(){
        showNotification("", false);
    }
    public <T extends ScreenBase> T getController(MainScreen screen){
        FXMLLoader get = screenList.get(screen);
        return get.getController();
    }
}
