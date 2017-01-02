/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.OverlayScreen;

import com.scrumpe.scrumpeclient.Screen.Base.OverlayBase;
import com.scrumpe.scrumpeclient.Screen.Base.ScreenBase;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class LoadingScreenController extends OverlayBase{

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @Override
    public void setupLayout() {
       AnchorPane.setBottomAnchor(componentRoot, 0.0);
       AnchorPane.setTopAnchor(componentRoot, 0.0);
       AnchorPane.setRightAnchor(componentRoot, 0.0);
       AnchorPane.setLeftAnchor(componentRoot, 0.0);
        componentRoot.setVisible(false);
    }
    
}
