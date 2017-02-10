/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.Component;

import com.scrumpe.scrumpeclient.MainApp;
import com.scrumpe.scrumpeclient.Screen.Base.AdminComponents;
import com.scrumpe.scrumpeclient.Screen.Base.ComponentBase;
import com.scrumpe.scrumpeclient.Screen.Component.Admin.CourseEditorController;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class CourseControllsController extends ComponentBase implements AdminComponents{

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField courseSearch;
    private Button addCourseBtn;
    private List<FXMLLoader> courses;
    public static CourseEditorController courseEditor;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        courseSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filterCourse(newValue);
        });
    }    

    @Override
    public void setupLayout() {
    }

    @Override
    public void setAdminParts() {
    addCourseBtn = new Button("Add Course");
    componentRoot.getChildren().add(addCourseBtn);
    addCourseBtn.setOnAction((event) -> {
        courseEditor.clearCE();
        courseEditor.show(true);
    });
    }

    @Override
    public void onChanged() {
    }

    private void filterCourse(String val) {
        String input = val.toLowerCase();
        for (FXMLLoader course : courses) {
            CourseListItemController controller = course.getController();
            String courseTitle = controller.getCourseTitle().toLowerCase();
            controller.show(courseTitle.contains(input));
        }
    }
    public void setCourseEditor(CourseEditorController ce){
        courseEditor = ce;
    }
    public void setCourses(List<FXMLLoader> courseListItems) {
        courses = courseListItems;
    }
    
}
