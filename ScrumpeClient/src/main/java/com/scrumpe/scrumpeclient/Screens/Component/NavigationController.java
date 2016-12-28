/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screens.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import com.scrumpe.scrumpeclient.ComponentController;
import com.scrumpe.scrumpeclient.ScreenManager;
import static com.scrumpe.scrumpeclient.ScreenManager.MainScreen;
/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class NavigationController extends ComponentController {

    List<Button> navigation = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    public void setNavItems(HashMap<MainScreen,String> navigateTo){
        for (Map.Entry<MainScreen, String> entry : navigateTo.entrySet()) {
            MainScreen key = entry.getKey();
            String value = entry.getValue();
            createNewNavItem(key,value);
        }
    }

    private void createNewNavItem(MainScreen key, String value) {
        Button b = new Button(value);
        b.setOnAction((ActionEvent event) -> {
            ScreenManager.getInstance().loadScreen(key);
        });
        
        componentRoot
                .getChildren()
                .add(b);
    }

    @Override
    public void setupLayout() {
    }
}
