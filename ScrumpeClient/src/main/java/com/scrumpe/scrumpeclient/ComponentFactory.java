/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient;

import com.scrumpe.scrumpeclient.Screens.LoadingScreenController;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author Max Verhoeven
 */
public class ComponentFactory {
    public enum ComponentType{
        CourseListItem,MainDescription,Navigation,UserList,UserListItem
    }
    private static final String XML_ROOT_DIR = "/fxml/Component/";
    private static final String XML_ADMIN_DIR = "/fxml/Component/Admin/";
    private static HashMap<ComponentType,String> components = new HashMap<ComponentType,String>();
    static{
        components.put(ComponentType.CourseListItem, XML_ROOT_DIR+"CourseListItem.fxml");
        components.put(ComponentType.MainDescription, XML_ROOT_DIR+"MainDescription.fxml");
        components.put(ComponentType.Navigation, XML_ROOT_DIR+"NavPanel.fxml");
        components.put(ComponentType.UserList, XML_ADMIN_DIR+"UserList.fxml");
        components.put(ComponentType.UserListItem, XML_ADMIN_DIR+"UserListItem.fxml");
    }
    public static FXMLLoader createComponent(Object context,ComponentType type){
        return createComponent(context,type,false);
    }
    public static FXMLLoader createComponent(Object context,ComponentType type,boolean preload){
        FXMLLoader load = new FXMLLoader(context.getClass().getResource(components.get(type)));
        if(preload){
            try {
                load.load();
                ComponentController controller = load.getController();
                controller.setup(load.getRoot());
            } catch (IOException ex) {
                System.err.println(ex.toString());
                Logger.getLogger(ComponentFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return load;
    } 
}