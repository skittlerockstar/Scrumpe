/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.Base;

import com.scrumpe.scrumpeclient.Screen.OverlayScreen.PopUpNotificationController;
import com.sun.istack.internal.logging.Logger;
import java.util.Iterator;
import java.util.logging.Level;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Max Verhoeven
 */
public abstract class UIComponent implements Initializable{
    protected Pane componentRoot;
    final private Stage popUp = new Stage();
    private Scene popUpScene;
    public abstract void setupLayout();
    public void setup(Node current){
        componentRoot = (Pane) current;
        setupLayout();        
    }
    public void throwError(String message){
//       Stage root =  MainApp.getRootStage();
//       PopUpNotificationController pnc = f.getController();
//       pnc.setMessage(message);
//       if(popUpScene == null){
//        pnc.setStage(popUp);
//        popUp.initStyle(StageStyle.UNDECORATED);
//        popUp.initStyle(StageStyle.TRANSPARENT);
//        popUp.initModality(Modality.APPLICATION_MODAL);
//        popUp.initOwner(MainApp.getRootStage());   
//        popUpScene = new Scene(f.getRoot(),root.getWidth() , root.getHeight());
//        popUpScene.setFill(null);
//        popUp.setScene(popUpScene);
//       }else{
//           popUp.setWidth(root.getWidth());
//           popUp.setHeight(root.getHeight());
//       }
//       popUp.show();
    }
    
}
