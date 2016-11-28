/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrumpe;

import Container.MainUIContainer;
import java.awt.Component;
import java.awt.Container;
import javax.swing.SwingUtilities;

/**
 *
 * @author Ruud
 */
public class Scrumpe implements Runnable{

    /**
     * @param args the command line arguments
     */
     private static App app;
    public static void main(String[] args) {
       
           Scrumpe example = new Scrumpe();
        // schedule this for the event dispatch thread (edt)
        SwingUtilities.invokeLater(example);
    }
     public void run()
    {
        app = App.getInstance();
//        logAllComponents(App.mainFrame);
    }
     public static void logAllComponents(Container x){
        for(Component c: x.getComponents()){
            System.out.println(c.toString());
            if(c instanceof Container){
               logAllComponents((Container)c);
            }
        }
    }
    
}
