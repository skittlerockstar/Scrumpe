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
import com.scrumpe.scrumpeclient.Screen.Base.ScreenBase;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.bson.types.ObjectId;

/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class CourseActiveController extends ScreenBase implements EventHandler<ActionEvent>{

    @FXML
    private Button previousQuestionBtn, nextQuestionBtn;
    
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
    
    //TODO replace this with a real record in DB
    private List<ObjectId[]> givenAnswers;
    
    private List<ObjectId> currentGivenAnswers = new ArrayList<>();

    public void setCurrentCourse(Course currentCourse) {
        CourseDAO cd = (CourseDAO) data.getDAO(CourseDAO.class);
        this.currentCourse = cd.findOne("_id", currentCourse.getId());
        initCourse();
    }

    @Override
    public void setNavigation() {
        Button help = new Button("Help");
        Button quit = new Button("Quit Course");
        quit.addEventHandler(MouseEvent.MOUSE_PRESSED, (event) -> {
            throwConfirmError("Are you sure? \n All progress will be lost.", (eventt) -> {
               ScreenManager.getInstance().loadScreen(ScreenManager.MainScreen.Main);
               closePopUp();
            },(eventtt) -> {
                closePopUp();
            });
        });
        addNavItem(ScreenManager.MainScreen.Main, help,false);
        addNavItem(ScreenManager.MainScreen.Main, quit,true);
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
        setPastAnswer();
        setButtonStates();
    }

    @FXML
    void nextQuestion(ActionEvent event) {
        saveAnswer();
        currentQuestionNumber++;
        if(currentQuestionNumber == questions.size()){
            finishCourse();
            return;
        }
        setCurrentQuestion(currentQuestionNumber);
        setPastAnswer();
        setButtonStates();
    }

    private void initCourse() {
        givenAnswers = new ArrayList<>();
        currentGivenAnswers = new ArrayList<>();
        currentQuestionNumber = 0;
        questions = currentCourse.getQuestions();
        progressPercentageStep = (1.0 / questions.size());
        setCurrentQuestion(currentQuestionNumber);
        setButtonStates();
    }

    private void setCurrentQuestion(int currentQuestionNumber) {
        currentGivenAnswers = new ArrayList<>();
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
        if (b instanceof CheckBox) {
            if (((CheckBox) b).isSelected()) {
                currentGivenAnswers.add((ObjectId) b.getUserData());
            } else {
                currentGivenAnswers.remove((ObjectId) b.getUserData());
            }
        }
        if (b instanceof RadioButton) {
            if (((RadioButton) b).isSelected()) {
                currentGivenAnswers.clear();
                currentGivenAnswers.add((ObjectId) b.getUserData());
            }
        }
        setButtonStates();
    }

    @Override
    public void setTitle() {
        // title = currentCourse.getCourseTitle();
    }

    private void saveAnswer() {
       ObjectId[] finalAnswers = currentGivenAnswers.toArray(new ObjectId[currentGivenAnswers.size()]);
       if(givenAnswers.size() > currentQuestionNumber){
        givenAnswers.set(currentQuestionNumber, finalAnswers);
       }else{
           givenAnswers.add(finalAnswers);
       }
        currentGivenAnswers.clear();
    }

    private void setPastAnswer() {
        if(givenAnswers.size() > currentQuestionNumber){
        ObjectId[] get = givenAnswers.get(currentQuestionNumber);
        currentGivenAnswers.addAll(Arrays.asList(get));
            for (Node node : answerContainer.getChildren()) {
                ButtonBase ansBtn = (ButtonBase) node;
                for (ObjectId objectId : get) {
                    if (((ObjectId) ansBtn.getUserData()).equals(objectId)) {
                        if (ansBtn instanceof CheckBox) {
                            ((CheckBox) ansBtn).setSelected(true);
                        } else {
                            ((RadioButton) ansBtn).setSelected(true);
                        }
                    }
                }

            }
        }
    }

    private void setButtonStates() {
        if(currentQuestionNumber == 0 ){previousQuestionBtn.setDisable(true);}
        else{ previousQuestionBtn.setDisable(false); }
        
        if(currentQuestionNumber == questions.size()-1){nextQuestionBtn.setText("Finnish Course");} 
        else{ nextQuestionBtn.setText("Next"); }
        
        if(currentGivenAnswers.isEmpty()){ nextQuestionBtn.setDisable(true);}
        else{nextQuestionBtn.setDisable(false);}
    }

    private void finishCourse() {
       ScreenManager.getInstance().loadScreen(ScreenManager.MainScreen.CourseResults);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}


}
