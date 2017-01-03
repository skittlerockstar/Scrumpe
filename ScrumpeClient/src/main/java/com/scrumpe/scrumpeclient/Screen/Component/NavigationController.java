/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import com.scrumpe.scrumpeclient.Screen.Base.ComponentBase;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager;
import static com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager.MainScreen;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class NavigationController extends ComponentBase {

    List<Button> navigation = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    public void setNavItems(HashMap<MainScreen,Button> navigateTo){
        for (Map.Entry<MainScreen, Button> entry : navigateTo.entrySet()) {
            MainScreen key = entry.getKey();
            createNewNavItem(key,entry.getValue());
        }
    }

    private void createNewNavItem(MainScreen key, Button value) {
        value.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                 ScreenManager.getInstance().loadScreen(key);
            }
        });
        
        ((Pane)componentRoot.getChildren().get(0)).getChildren().add(value);
    }

    @Override
    public void setupLayout() {
    }
}
