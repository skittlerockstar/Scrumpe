/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.Component;

import com.scrumpe.scrumpeclient.DB.Entity.Course;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import com.scrumpe.scrumpeclient.Screen.Base.ComponentBase;
import com.scrumpe.scrumpeclient.Screen.MainScreen.CourseActiveController;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.bson.types.ObjectId;

/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class CourseListItemController extends ComponentBase {

    @FXML
    private Node startCourse;
    private Course course;
    @FXML
    private Label courseTitle;
    public void setCourse(Course i){
        course =i;
        courseTitle.setText(i.getCourseTitle());
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setupLayout() {
        FlowPane.setMargin(componentRoot, new Insets(5));
    }
    @FXML
    private void startC(ActionEvent event){
         CourseActiveController ac = (CourseActiveController) ScreenManager.getInstance().loadScreen(ScreenManager.MainScreen.ActiveCourse);
         ac.setCurrentCourse(course);
    }
    
}
