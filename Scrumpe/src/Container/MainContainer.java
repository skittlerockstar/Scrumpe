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
        Login, Main, Course, Results, Records, Help, AdminHelp, CourseEditor, ActiveCourse
    };

    private HashMap<Enum<MainScreens>, UIComponent> mainScreens;

    public MainContainer() {
        super(new GridBagLayout());
        setAttr();
        loadContainer(MainScreens.ActiveCourse);
        setBackground(AppStyle.TRANSLUCENT);
    }

    private void setAttr() {
        mainScreens = new HashMap<>();
        mainScreens.put(MainScreens.Login, new LoginScreen());
        mainScreens.put(MainScreens.Main, new MainScreen());
        mainScreens.put(MainScreens.Course, new Course());
        mainScreens.put(MainScreens.Results, new ResultsScreen());
        mainScreens.put(MainScreens.Records, new Records());
        mainScreens.put(MainScreens.ActiveCourse, new ActiveCourse());
    }

    private void loadContainer(MainScreens screen) {
        removeAll();
        currentScreen = mainScreens.get(screen);
        setLayout(currentScreen.getNewLayout());
        add(currentScreen,currentScreen.getUIPos());
    }

}
