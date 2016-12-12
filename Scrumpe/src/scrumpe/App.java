/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scrumpe;

import Container.MainFrame;
/**
 * Entry point for Main Application. Singleton.
 * @author MJ. Verhoeven
 */
public class App {
    private static App instance; //this
    private static MainFrame mainFrame; // main frame instance
    
    private App() {
        //new MainFrame singleton
        mainFrame = MainFrame.getInstance();
    }
    
    /**
     * Get app singleton
     * @return
     */
    public static App getInstance(){ 
        if(instance == null) instance = new App();
        return instance;
    }

}
