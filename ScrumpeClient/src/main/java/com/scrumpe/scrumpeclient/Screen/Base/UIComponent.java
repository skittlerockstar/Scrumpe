/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.Base;

import com.scrumpe.scrumpeclient.DB.DBManager;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import com.scrumpe.scrumpeclient.Screen.Utils.ComponentFactory.ComponentType;
/**
 *
 * @author Max Verhoeven
 */
public abstract class UIComponent implements Initializable{
    protected Pane componentRoot;
    protected DBManager data;
    
    public abstract void setupLayout();
    public void setup(Node current){
        data = DBManager.getInstance();
        componentRoot = (Pane) current;
        setupLayout();        
    }
    public void throwError(String message){
        ScreenManager.getInstance().showNotification(message, true);
    }
    public void throwConfirmError(String message,EventHandler yes,EventHandler no ){
        ScreenManager.getInstance().showConfirmNotification(message, true, yes,no);
    }
    public void closePopUp(){
        ScreenManager.getInstance().closeNotification();
    }
    
}
