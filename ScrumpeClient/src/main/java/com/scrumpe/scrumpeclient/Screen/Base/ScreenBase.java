/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.Base;

import com.scrumpe.scrumpeclient.DB.DAO.UserDAO;
import com.scrumpe.scrumpeclient.Screen.Utils.ComponentFactory;
import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager.MainScreen;
import com.scrumpe.scrumpeclient.Screen.Component.MainDescriptionController;
import com.scrumpe.scrumpeclient.Screen.Component.NavigationController;
import com.scrumpe.scrumpeclient.Screen.Utils.ComponentFactory.ComponentType;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Max Verhoeven
 */
public abstract class ScreenBase extends UIComponent {
    protected String screenDescription;
    protected String title;
    private Label screenTitle;
    protected boolean init = false;
    private final HashMap<MainScreen,Button> navigation= new HashMap<>();
    
    public abstract void setNavigation();
    public abstract void setDescription();
    public abstract void setTitle();
    public abstract void setAdminComponents();
    @Override
    public void onChanged(){
        loadTitle();
    }
    public void loadTitle(){
         Pane base =  (Pane)ScreenManager.getInstance().getRoot();
            BorderPane bp = (BorderPane)base.getChildren().get(0);
            AnchorPane ap = ((AnchorPane)bp.getTop());
            if(ap.getChildren().get(0).getStyleClass().contains("screenTitle")){
                ap.getChildren().remove(0);
            }
        if(title != null){
           // if(screenTitle == null){
           FXMLLoader loader = ComponentFactory.createComponent(this, ComponentType.ScreenTitle, true);
           screenTitle = (Label) ((Pane)loader.getRoot()).getChildren().get(0);
           screenTitle.setText(title);
             HBox root = loader.getRoot();
             root.setMaxWidth(300);
           ap.getChildren().add(0,loader.getRoot());
//            }else{
                screenTitle.setText(title);
                screenTitle.setWrapText(false);
//            }
        }
    }
    public void loadNavigation(){
        if(navigation.size() > 0){
            FXMLLoader loader = ComponentFactory.createComponent(this, ComponentType.Navigation, true);
            HBox.setHgrow(loader.getRoot(), Priority.SOMETIMES);
            componentRoot.getChildren().add(0,loader.getRoot());
            NavigationController navigationController = loader.getController();
            navigationController.setNavItems(navigation);
        }
    }
    public void addNavItem(MainScreen destination,Button b,boolean cancelDefault){
        b.setUserData(cancelDefault);
        navigation.put(destination, b);
    }
    
    public void loadDescription(){
        if(screenDescription!=null){
        FXMLLoader loader = ComponentFactory.createComponent(this, ComponentType.MainDescription, true);
        MainDescriptionController descriptionController = loader.getController();
        descriptionController.setDescription(screenDescription);
        ((Pane)componentRoot).getChildren().add(0,loader.getRoot());
        }
    }

    @Override
    public void setup(Node current) {
       super.setup(current); //To change body of generated methods, choose Tools | Templates.
       if(!init){ 
           loadComponents();
           init = true;
       }
    }

    private void loadComponents() {
        setDescription();
        setTitle();
        setNavigation();
        loadHeader();
        loadTitle();
        loadNavigation();
        if(UserDAO.getLoggedInUser() !=null){
           // if(!UserDAO.getLoggedInUser().isIsAdmin()){
                setAdminComponents();
          //  }
        }
    }

    private void loadHeader() {
        if(title !=null || navigation.size() > 0){
//        Pane base =  (Pane)ScreenManager.getInstance().getRoot();
//        BorderPane bp = (BorderPane)base.getChildren().get(0);
//        ((AnchorPane)bp.getTop()).getChildren().add(0,headerRoot);
        }
    }
    
}
