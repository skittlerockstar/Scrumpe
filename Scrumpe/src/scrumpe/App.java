/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scrumpe;

import Container.MainFrame;
import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import java.awt.Button;
import java.awt.Color;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.LookAndFeel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import scrumpe.UI.TestClassForTheme;
/**
 * Entry point for Main Application. Singleton.
 * @author MJ. Verhoeven
 */
public class App {
    private static App instance; //this
    private static MainFrame mainFrame; // main frame instance
    
    private App() {
        //new MainFrame singleton
        TestClassForTheme.applyTheme();
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
    