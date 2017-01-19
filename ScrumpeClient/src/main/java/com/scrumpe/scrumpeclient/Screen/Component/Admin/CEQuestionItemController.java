/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.Component.Admin;

import com.scrumpe.scrumpeclient.DB.DAO.AnswerDAO;
import com.scrumpe.scrumpeclient.DB.DAO.DAOCallBack;
import com.scrumpe.scrumpeclient.DB.DAO.QuestionDAO;
import com.scrumpe.scrumpeclient.DB.Entity.Answer;
import com.scrumpe.scrumpeclient.DB.Entity.Question;
import com.scrumpe.scrumpeclient.Screen.Base.ComponentBase;
import com.scrumpe.scrumpeclient.Utils.ExcelUploader;
import com.scrumpe.scrumpeclient.Utils.ExcelUploader.CourseEnum;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.bson.types.ObjectId;

/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class CEQuestionItemController extends ComponentBase {

    ObservableList<TitledPane> panes;
    boolean isEditing = false;
    @FXML
    private Button deleteQBtn, addAnswer, deleteAllAnswers;
    @FXML
    private Label questionLabel;
    @FXML
    private TextArea questionTextField,explanationField;
    @FXML
    private VBox answerContainer;
    CourseEditorController cec;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        deleteQBtn.setOnAction((event) -> {
            panes.remove(componentRootAsTitlePane);
        });
        addAnswer.setOnAction((event) -> {
            addAnswer();
        });
        deleteAllAnswers.setOnAction((event) -> {
            answerContainer.getChildren().clear();
        });
        questionTextField.onKeyReleasedProperty().set((event) -> {
            String s = questionTextField.getText();
            questionLabel.setText(s.replace("\n", "").replace("\r", ""));
        });
    }

    @Override
    public void setupLayout() {
    }

    void setData(ObservableList<TitledPane> panes) {
        this.panes = panes;
    }
    public void setExistingData(Object data){
        HashMap<ExcelUploader.CourseEnum, Object> question = (HashMap<ExcelUploader.CourseEnum, Object>) data;
        questionLabel.setText((String) question.get(CourseEnum.Question));
        questionTextField.setText((String) question.get(CourseEnum.Question));
        explanationField.setText((String)question.get(CourseEnum.Description));
        setExistingAnswers(question.get(CourseEnum.Answers));
    }
    private void editQuestion(boolean editing) {
    }

    private void addAnswer() {
        addAnswer("Answer",false);
    }
    private void addAnswer(String answerTitle,Boolean correct) {
        HBox answer = new HBox();
        TextField answerfield = new TextField(answerTitle);
        Button deleteAnswer = new Button("Delete");
        deleteAnswer.setOnAction((event) -> {
            answerContainer.getChildren().remove(answer);
        });
        String correctText = (correct ? "is correct" : "is not correct");
        ToggleButton isCorrect = new ToggleButton(correctText);
        String correctStyle = (correct ? "-fx-background-color:limegreen; -fx-text-fill:white" : "-fx-background-color:LightCoral; -fx-text-fill:white");
        isCorrect.setStyle(correctStyle);
         isCorrect.setSelected(correct);
        isCorrect.setPrefWidth(100.0);
        isCorrect.onMouseEnteredProperty().set((event) -> {
            isCorrect.setOpacity(0.8);
        });
        isCorrect.onMouseExitedProperty().set((event) -> {
            isCorrect.setOpacity(1.0);
        });
        isCorrect.setOnAction((event) -> {
            if (isCorrect.isSelected()) {
                isCorrect.setStyle("-fx-background-color:limegreen");
                isCorrect.setText("is correct");
            } else {
               isCorrect.setStyle("-fx-background-color:LightCoral; -fx-text-fill:white");
                isCorrect.setText("is not Correct");
            }
        });
        answer.getChildren().add(answerfield);
        answer.getChildren().add(isCorrect);
        answer.getChildren().add(deleteAnswer);
        answer.setMaxWidth(Double.MAX_VALUE);
        answer.setSpacing(5.0);
        answer.setPadding(new Insets(5,0,0,0));
        HBox.setHgrow(answerfield, Priority.ALWAYS);
        answerContainer.getChildren().add(answer);

    }

    private void setExistingAnswers(Object get) {
        HashMap<String,Boolean> answers = (HashMap<String,Boolean>) get;
        for (Map.Entry<String, Boolean> entry : answers.entrySet()) {
            String key = entry.getKey();
            Boolean value = entry.getValue();
            addAnswer(key, value);
        }
    }
    public void getQuestions(CourseEditorController cec){
        this.cec = cec;
        ObservableList<Node> children = answerContainer.getChildren();
        AnswerDAO dao = data.getDAO(AnswerDAO.class);
        List<Answer> anslist = new ArrayList<>();
        List<Answer> correctAnswers = new ArrayList<>();
        for (Iterator<Node> iterator = children.iterator(); iterator.hasNext();) {
            HBox next = (HBox) iterator.next();
            TextField ans = (TextField) next.getChildren().get(0);
            ToggleButton isCorrect = (ToggleButton) next.getChildren().get(1);
           Answer s = new Answer();
           s.setAnswer(ans.getText());
           anslist.add(s);
           if(isCorrect.isSelected()){
               correctAnswers.add(s);
           }
        }
         dao.createAnswers(new DAOCallBackImpl(this,correctAnswers), anslist);
    }
    public void saveQuestion(Question q){
        q.setQuestion(questionLabel.getText());
        q.setExplanation(explanationField.getText());
        QuestionDAO dao = data.getDAO(QuestionDAO.class);
        dao.saveQuestion(new DAOCallBackImpl1(this), q);
    }
    public void returnQuestion(Question q){
        cec.addQtoCourse(q);
    }
    private static class DAOCallBackImpl implements DAOCallBack<Answer> {

        CEQuestionItemController cb;
        private List<Answer> correct;
        public DAOCallBackImpl(CEQuestionItemController cb,List<Answer> correct) {
            this.cb = cb;
            this.correct = correct;
        }

        @Override
        public void dbResult(Answer result) {
            
        }

        @Override
        public void dbResults(List<Answer> results) {
            Question q = new Question();
            q.setAnswers(results);
            List<ObjectId> correctAns = new ArrayList<>();
            for (Answer result : results) {
                for (Answer answer : correct) {
                    if(result.getAnswer().equals(answer.getAnswer())){
                        correctAns.add(result.getId());
                    }
                }
            }
            ObjectId[] toArray = correctAns.toArray(new ObjectId[correctAns.size()]);
            q.setCorrectAnswerIds(toArray);
            cb.saveQuestion(q);
        }
    }

    private static class DAOCallBackImpl1 implements DAOCallBack<Question> {
        CEQuestionItemController cec;
        public DAOCallBackImpl1(CEQuestionItemController cec) {
        this.cec = cec;
        }

        @Override
        public void dbResult(Question result) {
            cec.returnQuestion(result);
        }

        @Override
        public void dbResults(List<Question> results) {
            
        }
    }

}
