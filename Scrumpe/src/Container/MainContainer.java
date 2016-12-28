/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Container;
import scrumpe.UI.MainUI;
import Container.Content.*;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.util.HashMap;
import scrumpe.App;
import scrumpe.UI.AppStyle;
import scrumpe.UI.UIComponent;

/**
 * The main container in the UIContainer
 * @author MJ. Verhoeven
 */
public class MainContainer extends UIComponent {

    public static UIComponent currentScreen;
    public static MainContainer mainContainer;

    @Override
    public void initCustomComponents() {
    }
    public enum MainScreens {
        Login, Main, Course, Results, Records, Help, AdminHelp, CourseEditor, ActiveCourse
    };

    public static HashMap<Enum<MainScreens>, MainUI> mainScreens;

    public MainContainer() {
        super(new GridBagLayout());
        setAttr();
        loadContainer(MainScreens.Main);
        setBackground(AppStyle.BG_COLOR_DARK);
        mainContainer = this;
    }

    private void setAttr() {
        mainScreens = new HashMap<>();
        mainScreens.put(MainScreens.Login, new LoginScreen());
        mainScreens.put(MainScreens.Main, new MainScreen());
        mainScreens.put(MainScreens.Course, new CourseIntroScreen());
        mainScreens.put(MainScreens.Results, new ResultsScreen());
        mainScreens.put(MainScreens.Records, new RecordsScreen());
        mainScreens.put(MainScreens.ActiveCourse, new CourseScreen());
        mainScreens.put(MainScreens.Help, new HelpScreen());
    }

    private void loadContainer(MainScreens screen) {
       mainContainer.setVisible(false);
        removeAll();
         repaint();
         
        currentScreen = mainScreens.get(screen);
        setLayout(currentScreen.getNewLayout());
        add(currentScreen,currentScreen.getUIPos());
        mainContainer.setVisible(true);
    }
    public static void changeScreen(String screen){
        mainContainer.loadContainer(MainScreens.valueOf(screen));
       
    }
     public static void changeScreen(MainScreens screen){
       // mainContainer.setVisible(false);
        mainContainer.loadContainer(screen);
       // mainContainer.setVisible(true);
    }
}
