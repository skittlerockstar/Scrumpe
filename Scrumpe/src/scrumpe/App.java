/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scrumpe;

import Container.MainFrame;
import Container.MainUIContainer;

/**
 *
 * @author MJ. Verhoeven
 */
public class App {
    private static App instance;
    public static MainFrame mainFrame;
    
    private App() {
        mainFrame = MainFrame.getInstance();
    }
    
    public static App getInstance(){
        if(instance == null) instance = new App();
        return instance;
    }

}
