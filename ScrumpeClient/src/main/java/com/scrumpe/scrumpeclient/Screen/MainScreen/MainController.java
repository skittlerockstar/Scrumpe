/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.MainScreen;

import com.scrumpe.scrumpeclient.DB.DAO.CourseDAO;
import com.scrumpe.scrumpeclient.DB.DAO.DAOCallBack;
import com.scrumpe.scrumpeclient.DB.DAO.UserDAO;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import com.scrumpe.scrumpeclient.Screen.Utils.ComponentFactory;
import com.scrumpe.scrumpeclient.Screen.Base.ScreenBase;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager.MainScreen;
import com.scrumpe.scrumpeclient.Screen.Component.CourseListItemController;
import com.scrumpe.scrumpeclient.Screen.Base.UIComponent;
import com.scrumpe.scrumpeclient.Screen.Component.Admin.CourseEditorController;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class MainController extends ScreenBase implements DAOCallBack<Course> {

    @FXML
    private FlowPane courseContainer;
    @FXML
    HBox centerContent;
    private List<FXMLLoader> courseListItems = new ArrayList<>();
    private static CourseEditorController courseEditorScreen;

    public static CourseEditorController getCourseEditorScreen() {
        return courseEditorScreen;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @Override
    public void setNavigation() {
        Button logOut = new Button("Log out");
        logOut.setOnAction((ActionEvent event) -> {
            UserDAO.logOut();
            ContainerController cc = ScreenManager.getInstance().getRootLoader().getController();
            cc.loggedInUser.setText("Not Logged in");
        });
        addNavItem(MainScreen.Login, logOut, false);
    }

    @Override
    public void setDescription() {
        super.screenDescription = "Welcome to Version 0.1 of Scrump! There's not very much to except to take courses at the moment. \n simply select a course to start.";
    }

    @Override
    public void setupLayout() {
        try {
            HBox.setHgrow(componentRoot, Priority.ALWAYS);
            HBox.setMargin(componentRoot, new Insets(10, 0, 0, 0));
            if (!init) {
                addCourses();
            }
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addCourses() throws IOException {
        CourseDAO courseDAO = data.getDAO(CourseDAO.class);
        courseDAO.getCourses(this);
    }

    @Override
    public void setAdminComponents() {
        FXMLLoader x = ComponentFactory.createComponent(this, ComponentFactory.ComponentType.UserList, true);
        UIComponent u = x.getController();
        u.setup(x.getRoot());
        centerContent.getChildren().add(x.getRoot());

        if (courseEditorScreen == null) {
            FXMLLoader courseEditor = ComponentFactory.createComponent(this, ComponentFactory.ComponentType.CourseEditor, true);
            UIComponent u2 = courseEditor.getController();
            u2.setup(courseEditor.getRoot());
            courseEditorScreen = (CourseEditorController) u2;
            ((AnchorPane) ScreenManager.getInstance().getRoot()).getChildren().add(1, courseEditor.getRoot());
        }
    }

    @Override
    public void setTitle() {
        title = "Course Selection";
    }

    @Override
    public void dbResults(List<Course> results) {
        for (Course course : results) {
            FXMLLoader n = ComponentFactory.createComponent(this, ComponentFactory.ComponentType.CourseListItem, true);
            courseListItems.add(n);
            CourseListItemController c = n.getController();
            c.setCourse(course);
            courseContainer.getChildren().add(n.getRoot());
        }
    }

    @Override
    public void dbResult(Course result) {
    }

}
