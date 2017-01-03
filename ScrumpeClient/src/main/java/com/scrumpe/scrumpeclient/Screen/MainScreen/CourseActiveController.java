/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.MainScreen;

import com.scrumpe.scrumpeclient.DB.DAO.CourseDAO;
import com.scrumpe.scrumpeclient.DB.Entity.Answer;
import com.scrumpe.scrumpeclient.DB.Entity.Course;
import com.scrumpe.scrumpeclient.DB.Entity.Question;
import com.scrumpe.scrumpeclient.DB.Entity.Record;
import com.scrumpe.scrumpeclient.Screen.Base.ScreenBase;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager;
import java.awt.Checkbox;
import java.net.URL;
import java.util.EventListener;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class CourseActiveController extends ScreenBase implements EventHandler<ActionEvent> {

    @FXML
    private Node QuestionContainer;
    @FXML
    private ProgressBar courseProgress;
    private Double progressPercentageStep;
    @FXML
    private Label activeQuestion;
    @FXML
    private VBox answerContainer;
    private ToggleGroup currentToggleGroup;
    private Course currentCourse;
    private List<Question> questions;
    private Question currentQuestion;
    private int currentQuestionNumber = 0;
    private Record currentCourseRecord;

    public void setCurrentCourse(Course currentCourse) {
        CourseDAO cd = (CourseDAO) data.getDAO(CourseDAO.class);
        this.currentCourse = cd.findOne("_id", currentCourse.getId());
        initCourse();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @Override
    public void setNavigation() {
        Button help = new Button("Help");
        Button quit = new Button("Quit Course");
        navigation.put(ScreenManager.MainScreen.Main, help);
        navigation.put(ScreenManager.MainScreen.Main, quit);
    }

    @Override
    public void setDescription() {
       // description = " Test";
    }

    @Override
    public void setAdminComponents() {
    }

    @Override
    public void setupLayout() {
        HBox.setHgrow(componentRoot, Priority.ALWAYS);
    }

    @FXML
    private void previousQuestion(ActionEvent event) {
        currentQuestionNumber--;
        setCurrentQuestion(currentQuestionNumber);
    }

    @FXML
    void nextQuestion(ActionEvent event) {
        currentQuestionNumber++;
        setCurrentQuestion(currentQuestionNumber);
    }

    private void initCourse() {
        currentQuestionNumber = 0;
        questions = currentCourse.getQuestions();
        progressPercentageStep = (1.0 / questions.size());
        setCurrentQuestion(currentQuestionNumber);
    }

    private void setCurrentQuestion(int currentQuestionNumber) {
        activeQuestion.setText(questions.get(currentQuestionNumber).getQuestion());
        courseProgress.setProgress(progressPercentageStep * (currentQuestionNumber + 1));
        currentQuestion = questions.get(currentQuestionNumber);
        setCurrentAnswers(currentQuestion.getAnswers());
    }

    private void setCurrentAnswers(List<Answer> answers) {
        answerContainer.getChildren().clear();
        currentToggleGroup = new ToggleGroup();
        int correctAns = currentQuestion.getCorrectAnswerIds().length;
        for (Answer answer : answers) {
            String value = answer.getAnswer();
            ButtonBase tb = ((correctAns > 1) ? new CheckBox(value) : new RadioButton(value));
            if (tb instanceof RadioButton) {
                ((RadioButton) tb).setToggleGroup(currentToggleGroup);
            }
            tb.setUserData(answer.getId());
            tb.setOnAction(this);
            answerContainer.getChildren().add(tb);
        }
    }

    @Override
    public void handle(ActionEvent event) {
        ButtonBase b = (ButtonBase) event.getSource();
        //TODO save in record
    }

    @Override
    public void setTitle() {
       // title = currentCourse.getCourseTitle();
    }

}
