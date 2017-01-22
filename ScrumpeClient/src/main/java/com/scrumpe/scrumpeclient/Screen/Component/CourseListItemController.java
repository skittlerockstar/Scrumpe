/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.Component;

import com.scrumpe.scrumpeclient.DB.DAO.AnswerDAO;
import com.scrumpe.scrumpeclient.DB.DAO.CourseDAO;
import com.scrumpe.scrumpeclient.DB.DAO.DAOCallBack;
import com.scrumpe.scrumpeclient.DB.DAO.QuestionDAO;
import com.scrumpe.scrumpeclient.DB.Entity.Course;
import com.scrumpe.scrumpeclient.DB.Entity.Question;
import com.scrumpe.scrumpeclient.Screen.Base.AdminComponents;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import com.scrumpe.scrumpeclient.Screen.Base.ComponentBase;
import com.scrumpe.scrumpeclient.Screen.Component.Admin.CourseEditorController;
import com.scrumpe.scrumpeclient.Screen.MainScreen.CourseActiveController;
import com.scrumpe.scrumpeclient.Screen.MainScreen.MainController;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class CourseListItemController extends ComponentBase implements AdminComponents {

    @FXML
    private Node startCourse;
    private Course course;
    @FXML
    private Label courseTitle, courseDescription, questionCount, minimumScore;
    @FXML
    private HBox controls;
    private Button editCourse, deleteCourse;

    public void setCourse(Course i) {
        course = i;
        courseTitle.setText(i.getCourseTitle());
        questionCount.setText(i.getQuestions().size() + "");
        minimumScore.setText(i.getMinimumScore() + "");
        courseDescription.setText(i.getCourseDescription());
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
    private void startC(ActionEvent event) {
        ScreenManager.getInstance().showLoadingScreen(true);
        CourseActiveController ac = (CourseActiveController) ScreenManager.getInstance().loadScreen(ScreenManager.MainScreen.ActiveCourse);
        ac.setCurrentCourse(course);
    }

    @Override
    public void setAdminParts() {
        setupCourseControlls();
    }

    private void setupCourseControlls() {
        editCourse = new Button("edit");
        deleteCourse = new Button("delete");
        controls.getChildren().add(editCourse);
        controls.getChildren().add(deleteCourse);
        editCourse.setOnAction((event) -> {
            CourseEditorController courseEditorScreen = MainController.getCourseEditorScreen();
            courseEditorScreen.editCourse(course);
        });
        deleteCourse.setOnAction((event) -> {
            super.throwConfirmError(
                    "Are you sure you want to delete " + course.getCourseTitle(),
                    (onConfirm) -> {
                      deleteCourse();
                    }, 
                    (onCancel) -> {
                        
                    });
        });
    }

    private void deleteCourse() {
        CourseDAO cDao = data.getDAO(CourseDAO.class);
        QuestionDAO qDao = data.getDAO(QuestionDAO.class);
        AnswerDAO aDao = data.getDAO(AnswerDAO.class);
        course.getQuestions().forEach((q) -> {
            aDao.deleteAnswers(null, q.getAnswers());
        });
        qDao.deleteQuestions(null, course.getQuestions());
        cDao.deleteCourse(new DAOCallBack<Course>(){
            @Override
            public void dbResult(Course result) {
                ScreenManager.getInstance().loadScreen(ScreenManager.MainScreen.Main,true);
                ScreenManager.getInstance().showLoadingScreen(true);
                presentNote("Course Deleted!");
            }

            @Override
            public void dbResults(List<Course> results) {
            }
        }, course);
    }

}
