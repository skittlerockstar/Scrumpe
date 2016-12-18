/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Container;
import Container.Content.*;
import java.awt.GridBagLayout;
import java.util.HashMap;
import scrumpe.UI.AppStyle;
import scrumpe.UI.UIComponent;

/**
 * The main container in the UIContainer
 * @author MJ. Verhoeven
 */
class MainContainer extends UIComponent {

    public static UIComponent currentScreen;

    @Override
    public void applyCustomStyle() {
    }
    private enum MainScreens {
        Login, Main
    };

    private HashMap<Enum<MainScreens>, UIComponent> mainScreens;

    public MainContainer() {
        super(new GridBagLayout());
        setAttr();
        loadContainer(MainScreens.Main);
        setBackground(AppStyle.TRANSLUCENT);
    }

    private void setAttr() {
        mainScreens = new HashMap<>();
        mainScreens.put(MainScreens.Login, new LoginScreen());
        mainScreens.put(MainScreens.Main, new MainScreen());
    }

    private void loadContainer(MainScreens screen) {
        removeAll();
        currentScreen = mainScreens.get(screen);
        setLayout(currentScreen.getNewLayout());
        add(currentScreen,currentScreen.getUIPos());
    }

}
