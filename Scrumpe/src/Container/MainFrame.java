/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Container;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import scrumpe.UI.AppStyle;
import scrumpe.UI.AppUIBase;

/**
 *
 * @author MJ. Verhoeven
 */
public class MainFrame extends AppUIBase {

    private static MainFrame instance;
    private MainUIContainer mainContainer;

    private MainFrame() {
        setMainContainer();
        
        setFrameDesign();
        setFrameProperties();
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    private void setFrameProperties() {
        // only used for fullscreen undecorated frame. makes sure the taskbar isn't overlapped.
        //        GraphicsConfiguration config = super.getGraphicsConfiguration();
        //        Rectangle usableBounds = SunGraphicsEnvironment.getUsableBounds(config.getDevice());
        //        super.setUndecorated(true);
        //        super.setMaximizedBounds(usableBounds);

        super.setExtendedState(MAXIMIZED_BOTH);
        super.pack();
        super.setVisible(true);
        super.setBackground(Color.RED);
    }

    private void setFrameDesign() {
        //TODO Frame Design
    }

    private void setMainContainer() {
        mainContainer = new MainUIContainer();
        add(mainContainer, mainContainer.UIPos);
    }


}
