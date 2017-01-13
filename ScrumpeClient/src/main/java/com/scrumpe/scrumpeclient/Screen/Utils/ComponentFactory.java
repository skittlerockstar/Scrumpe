/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.Utils;

import com.scrumpe.scrumpeclient.Screen.Base.ComponentBase;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author Max Verhoeven
 */
public class ComponentFactory {
    public static enum ComponentType{
        CourseListItem,MainDescription,Navigation,ScreenTitle,UserList,UserListItem
    }
    private static final String XML_ROOT_DIR = "/fxml/Component/";
    private static final String XML_ADMIN_DIR = "/fxml/Component/Admin/";
    private static HashMap<ComponentType,String> components = new HashMap<ComponentType,String>();
    static{
        components.put(ComponentType.CourseListItem, XML_ROOT_DIR+"CourseListItem.fxml");
        components.put(ComponentType.MainDescription, XML_ROOT_DIR+"MainDescription.fxml");
        components.put(ComponentType.Navigation, XML_ROOT_DIR+"NavPanel.fxml");
        components.put(ComponentType.ScreenTitle, XML_ROOT_DIR+"ScreenTitle.fxml");
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
                ComponentBase controller = load.getController();
                if(controller !=null){
                    controller.setup(load.getRoot());
                }
            } catch (IOException ex) {
                System.err.println(ex.toString());
                Logger.getLogger(ComponentFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return load;
    } 
}
