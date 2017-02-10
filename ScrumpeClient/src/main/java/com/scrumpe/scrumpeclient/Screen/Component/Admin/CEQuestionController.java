/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.Component.Admin;

import com.scrumpe.scrumpeclient.DB.DAO.AnswerDAO;
import com.scrumpe.scrumpeclient.DB.DAO.Callback.DAOCallBack;
import com.scrumpe.scrumpeclient.DB.DAO.QuestionDAO;
import com.scrumpe.scrumpeclient.DB.DBManager;
import com.scrumpe.scrumpeclient.DB.Entity.Answer;
import com.scrumpe.scrumpeclient.DB.Entity.Question;
import com.scrumpe.scrumpeclient.Screen.Base.ComponentBase;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
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
public class CEQuestionController extends ComponentBase {

    ObservableList<TitledPane> panes;
    boolean isEditing = false;
    boolean isDBRunning = false;
    @FXML
    private Button deleteQBtn, addAnswer, deleteAllAnswers;
    @FXML
    private Label questionLabel;
    @FXML
    private TextArea questionTextField,explanationField;
    @FXML
    private VBox answerContainer;
    CourseEditorController cec;
    Question question = new Question();
    public Question getQuestion() { return question; }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          deleteQBtn.setOnAction((event) -> panes.remove(componentRootAsTitlePane));
          addAnswer.setOnAction((e) ->addAnswer(new Answer()));
          deleteAllAnswers.setOnAction((e)->answerContainer.getChildren().clear());
          questionTextField.textProperty().addListener((ob, ov, nv) ->questionLabel.setText(nv));
    }

    @Override
    public void setupLayout() {}
    @Override
    public void onChanged() {}

    public void setExistingData(Question data) { 
        this.question = data;
        questionTextField.setText(data.getQuestion());
        explanationField.setText(data.getExplanation());
        data.getAnswers().stream().forEach(this::addAnswer);
    }

    public void setData(ObservableList<TitledPane> panes) {
        this.panes = panes;
    }

    private void addAnswer(Answer ans){
        TextField answerfield = new TextField(ans.getAnswer());
        Button deleteAnswer = new Button("Delete");
        boolean ansCorrect = (ans.getId() !=null  ? isCorrectAnswer(ans) :ans.isCorrectForExcel);
        ToggleButton isCorrect = new ToggleButton((ansCorrect? "Correct":"Incorrect"));
        HBox answer = new HBox(5, answerfield,isCorrect,deleteAnswer);
        
        HBox.setHgrow(answerfield, Priority.ALWAYS);
        answer.setUserData(ans);
        answer.setMaxWidth(Double.MAX_VALUE);
        answerfield.textProperty().addListener((ob,ov,nv)->editAnswer(answer,nv));
        
        isCorrect.setOnAction((e)->changeCorrect(answer));
        isCorrect.setSelected(ansCorrect);
        deleteAnswer.setOnAction((e)->deleteAnswer(answer));
        answerContainer.getChildren().add(answer);
    }

    private void deleteAnswer(HBox ans) {
        answerContainer.getChildren().remove(ans);
    }
    private void editAnswer(HBox answer, String nv) {
    }
    private void changeCorrect(HBox answer) {
        ToggleButton get = (ToggleButton) answer.getChildren().get(1);
        get.setText((get.isSelected()? "Correct":"Incorrect"));
    }

    private boolean isCorrectAnswer(Answer ans) {
        List<ObjectId> correctAnswerIds = question.getCorrectAnswerIds();
        return correctAnswerIds.stream().anyMatch((obj)-> obj.equals(ans.getId()));
    }

    public void prepareQuestionForDB(CourseEditorController cec) {
        if(checkRequirements()){
        this.cec = cec;
        this.question.getCorrectAnswerIds().clear();
        this.question.getAnswers().clear();
        ObservableList<Node> children = answerContainer.getChildren();
        for (Node node : children) {
            Answer a = (Answer) node.getUserData();
            a.setAnswer(((TextField)((HBox)node).getChildren().get(0)).getText());
            Node get = ((HBox)node).getChildren().get(1);
            saveAnswer(a,((ToggleButton)get).isSelected());
        }
        }else{
            componentRootAsTitlePane.setExpanded(true);
            questionTextField.requestFocus();
        }
    }

    public void saveAnswer(Answer a,boolean isCorrectA) {
        AnswerDAO dao = DBManager.getInstance().getDAO(AnswerDAO.class);
        final boolean corr = isCorrectA;
        dao.createAnswer((o) -> {
            question.getAnswers().add(o);
            if(corr) {
                question.getCorrectAnswerIds().add(o.getId());
                System.out.println(question.getCorrectAnswerIds());
            }
            if(question.getAnswers().size() == answerContainer.getChildren().size()){
                String text = questionTextField.getText();
                question.setQuestion(text);
                String text1 = explanationField.getText();
                question.setExplanation(text1);
                saveQuestion();
            }
        }, a);
    }

    private void updateAnswer(Answer a, boolean isCorrectA) {
       
    }

    private void deleteRemovedAnswers(ObservableList<Node> children) {
        List<Answer> answers = question.getAnswers();
        answers.stream().filter((a)->{
            boolean mustDelete = false;
            for (Node n : children) {
                Answer ans = (Answer) n.getUserData();
                if(!ans.getId().equals(a.getId()) && ans.getId() !=null){
                    mustDelete = true;
                }
            }
            return mustDelete;
        }).forEach(System.out::println);
    }

    private void saveQuestion() {
        QuestionDAO dao = DBManager.getInstance().getDAO(QuestionDAO.class);
        dao.saveQuestion((o) -> {
            cec.callback(o);
        }, question);
    }

    private boolean checkRequirements() {
        componentRootAsTitlePane.getStyleClass().remove("err");
        
        if(questionTextField.getText().equals("")){
             presentNote("One of your questions... has no question.");
             componentRootAsTitlePane.getStyleClass().add("err");
            return false;
        }
        if(answerContainer.getChildren().size() <= 1) {
            presentNote("You don't have at least 2 answers for question:\n"+questionTextField.getText());
            componentRootAsTitlePane.getStyleClass().add("err");
            return false;
        }
        ObservableList<Node> children = answerContainer.getChildren();
        boolean hasCorrectAnswer = false;
        for (Node x : children) {
            ToggleButton get = (ToggleButton) ((HBox)x).getChildren().get(1);
            TextField get2 = (TextField) ((HBox)x).getChildren().get(0);
            if(get.isSelected()) hasCorrectAnswer = true;
            if(get2.getText().equals("")) {
                presentNote("You don't have an answer for question:\n"+question.getQuestion());
         componentRootAsTitlePane.getStyleClass().add("err");
                return false;
            }
        }
        if(!hasCorrectAnswer){
            componentRootAsTitlePane.getStyleClass().add("err");
            presentNote("You don't have at least 1 correct answers for question:\n"+question.getQuestion());
        }
        return hasCorrectAnswer;
    }



    
}

