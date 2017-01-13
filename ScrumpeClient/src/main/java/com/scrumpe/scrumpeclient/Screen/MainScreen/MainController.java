/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.MainScreen;

import com.scrumpe.scrumpeclient.DB.DAO.CourseDAO;
import com.scrumpe.scrumpeclient.DB.DAO.UserDAO;
import com.scrumpe.scrumpeclient.DB.DBManager;
import com.scrumpe.scrumpeclient.DB.Entity.Course;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import com.scrumpe.scrumpeclient.Screen.Utils.ComponentFactory;
import com.scrumpe.scrumpeclient.Screen.Base.ScreenBase;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager.MainScreen;
import com.scrumpe.scrumpeclient.Screen.Component.CourseListItemController;
import com.scrumpe.scrumpeclient.Screen.Base.UIComponent;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager;
import com.scrumpe.scrumpeclient.Utils.Log;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import org.mongodb.morphia.query.QueryResults;

/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class MainController extends ScreenBase {

    @FXML
    private FlowPane courseContainer;
    @FXML
    HBox centerContent;
    private List<FXMLLoader> courseListItems = new ArrayList<>();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @Override
    public void setNavigation() {
        Button home = new Button("Home");
        Button logOut = new Button("LogOut");
        logOut.setOnAction((ActionEvent event) -> {
            UserDAO.logOut();
            ContainerController cc = ScreenManager.getInstance().getRootLoader().getController();
            cc.loggedInUser.setText("Not Logged in");
        });
        addNavItem(MainScreen.Main, home,false);
        addNavItem(MainScreen.Login, logOut,false);
    }

    @Override
    public void setDescription() {
        super.description = "Welcome to Version 0.1 of Scrump! There's not very much to except to take courses at the moment. \n simply select a course to start.";
    }

    @Override
    public void setupLayout() {
        try {
            HBox.setHgrow(componentRoot, Priority.ALWAYS);
            HBox.setMargin(componentRoot, new Insets(10, 0, 0, 0));
           if(!init){ addCourses();}
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addCourses() throws IOException {
        List<Course> courseTitleAndIDS = data.getDatastore().find(Course.class).project("title", true).asList();
        for (Course course : courseTitleAndIDS) {
        FXMLLoader n = ComponentFactory.createComponent(this, ComponentFactory.ComponentType.CourseListItem, true);
            courseListItems.add(n);
            UIComponent u = n.getController();
            u.setup(n.getRoot());
            CourseListItemController c  = n.getController();
            c.setCourse(course);
            courseContainer.getChildren().add(n.getRoot());
        }
    }
    @Override
    public void setAdminComponents() {
        FXMLLoader x = ComponentFactory.createComponent(this, ComponentFactory.ComponentType.UserList, true);
        UIComponent u = x.getController();
        u.setup(x.getRoot());
        centerContent.getChildren().add(x.getRoot());
    }

    @Override
    public void setTitle() {
        title = "Course Selection";
    }

}
