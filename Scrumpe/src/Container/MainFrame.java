/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Container;

import javax.swing.UIManager;
import scrumpe.UI.AppUIBase;

/**
 * Main JFrame. Used to create the JFrame and setup the rest of UI. Singleton.
 *
 * @author MJ. Verhoeven
 */
public class MainFrame extends AppUIBase {

    private MainUIContainer mainContainer;

    private MainFrame() {
        setMainContainer();
        setFrameDesign();
        setFrameProperties();
    }

    /**
     * get singleton instance
     *
     * @return
     */
    public static MainFrame getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {

        public static final MainFrame INSTANCE = new MainFrame();
    }

    /**
     * Used to set the Frame fullscreen and visible
     */
    private void setFrameProperties() {
        // only used for fullscreen undecorated frame. makes sure the taskbar isn't overlapped.
        //        GraphicsConfiguration config = super.getGraphicsConfiguration();
        //        Rectangle usableBounds = SunGraphicsEnvironment.getUsableBounds(config.getDevice());
        //        super.setUndecorated(true);
        //        super.setMaximizedBounds(usableBounds);

        super.setExtendedState(MAXIMIZED_BOTH);
        super.pack();
        super.setVisible(true);
    }

    private void setFrameDesign() {
        //TODO Frame Design
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
        }
    }

    /**
     * creates and sets the uicontainer panel in the main JFrame
     */
    private void setMainContainer() {
        mainContainer = new MainUIContainer();
        add(mainContainer, mainContainer.UIPos);
    }

}
