/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.Base;

import com.scrumpe.scrumpeclient.DB.DAO.UserDAO;
import com.scrumpe.scrumpeclient.DB.DBManager;
import com.scrumpe.scrumpeclient.Screen.Utils.ComponentFactory;
import com.scrumpe.scrumpeclient.Screen.Base.UIComponent;
import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager.MainScreen;
import com.scrumpe.scrumpeclient.Screen.Component.MainDescriptionController;
import com.scrumpe.scrumpeclient.Screen.Component.NavigationController;
import com.scrumpe.scrumpeclient.Utils.Log;
import java.util.logging.Level;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 *
 * @author Max Verhoeven
 */
public abstract class ScreenBase extends UIComponent {
    protected String description;
    protected String title;
    protected boolean init = false;
    protected HashMap<MainScreen,Button> navigation= new HashMap<>();
    private HBox headerRoot = new HBox();
    public abstract void setNavigation();
    public abstract void setDescription();
    public abstract void setTitle();
    public abstract void setAdminComponents();
    
    public void loadTitle(){
        if(title !=null){
           FXMLLoader loader = ComponentFactory.createComponent(this, ComponentFactory.ComponentType.ScreenTitle, true);
           Label headerTitle = (Label) ((Pane)loader.getRoot()).getChildren().get(0);
           headerTitle.setText(title);
           HBox.setHgrow(loader.getRoot(), Priority.ALWAYS);
           headerRoot.getChildren().add(0,loader.getRoot());
        }
    }
    public void loadNavigation(){
        if(navigation.size() > 0){
            FXMLLoader loader = ComponentFactory.createComponent(this, ComponentFactory.ComponentType.Navigation, true);
           HBox.setHgrow(loader.getRoot(), Priority.SOMETIMES);
            headerRoot.getChildren().add(headerRoot.getChildren().size(),loader.getRoot());
            NavigationController navigationController = loader.getController();
            navigationController.setNavItems(navigation);
        }
    }
    
    public void loadDescription(){
        FXMLLoader loader = ComponentFactory.createComponent(this, ComponentFactory.ComponentType.MainDescription, true);
        MainDescriptionController descriptionController = loader.getController();
        descriptionController.setDescription(description);
        ((Pane)componentRoot).getChildren().add(0,loader.getRoot());
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
        
        if(description !=null){
            loadDescription();
        }
        loadHeader();
        loadTitle();
        loadNavigation();
        if(UserDAO.getLoggedInUser() !=null){
            if(UserDAO.getLoggedInUser().isIsAdmin()){
            setAdminComponents();
            }
        }
    }

    private void loadHeader() {
        if(title !=null || navigation.size() > 0){
        VBox.setVgrow(headerRoot, Priority.NEVER);
        VBox.setMargin(headerRoot, new Insets(0,0,10,0));
        componentRoot.getChildren().add(0,headerRoot);
        }
    }
    
}
