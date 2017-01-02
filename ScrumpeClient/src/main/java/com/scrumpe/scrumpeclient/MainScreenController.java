/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient;

import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import com.scrumpe.scrumpeclient.ScreenManager.MainScreen;
import com.scrumpe.scrumpeclient.Screens.Component.MainDescriptionController;
import com.scrumpe.scrumpeclient.Screens.Component.NavigationController;

/**
 *
 * @author Max Verhoeven
 */
public abstract class MainScreenController extends UIComponent {
    protected String description;
    protected boolean init = false;
    protected HashMap<MainScreen,String> navigation= new HashMap<>();
    public abstract void setNavigation();
    public abstract void setDescription();
    public abstract void setAdminComponents();
    
    public void loadNavigation(){
        if(navigation.size() > 0){
            FXMLLoader loader = ComponentFactory.createComponent(this, ComponentFactory.ComponentType.Navigation, true);
            ((Pane)componentRoot).getChildren().add(0,loader.getRoot());
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
        setNavigation();
        if(description !=null){
            loadDescription();
        }
        loadNavigation();
        if(true){
            setAdminComponents();
        }
    }
    
}
