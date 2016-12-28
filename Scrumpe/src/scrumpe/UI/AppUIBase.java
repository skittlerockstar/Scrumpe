/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scrumpe.UI;

import Container.MainFrame;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author MJ. Verhoeven
 */
public abstract class AppUIBase extends JFrame{

    /**
     * base class for jframe where new functionality can be added.
     * @see MainFrame
     */
    public static AppStyle appStyle;

    public AppUIBase() {
        super.setMinimumSize(new Dimension(800, 600));
    }
    
}
