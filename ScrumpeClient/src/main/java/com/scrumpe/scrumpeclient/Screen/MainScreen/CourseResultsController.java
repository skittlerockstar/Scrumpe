/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.MainScreen;

import com.scrumpe.scrumpeclient.DB.Entity.Answer;
import com.scrumpe.scrumpeclient.DB.Entity.Course;
import com.scrumpe.scrumpeclient.DB.Entity.Question;
import com.scrumpe.scrumpeclient.MainApp;
import com.scrumpe.scrumpeclient.Screen.Base.ScreenBase;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager;
import com.scrumpe.scrumpeclient.Utils.Log;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.bson.types.ObjectId;

/**
 * FXML Controller class
 *
 * @author IMXNotASPider
 */
public class CourseResultsController extends ScreenBase {

    private Course takenCourse;
    private List<List<ObjectId>> givenAnswers;
    @FXML
    private Label score, requiredScore, resultQuestion, explanation,timePassed;
    @FXML
    private VBox resultSummaryList, resultAnswersContainer,qrescont;
    @FXML

    private ListView questionList;
    private Label yourAnswer = new Label("Your Answer");
    private Label correctAnswer = new Label("Correct Answer");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        yourAnswer.getStyleClass().add("yourAnswer");
        correctAnswer.getStyleClass().add("correctAnswer");
       
    }

    @Override
    public void setNavigation() {
        addNavItem(ScreenManager.MainScreen.Main, new Button("Return"), false);
    }

    @Override
    public void setDescription() {
        screenDescription = "These are your results";
    }

    @Override
    public void setTitle() {
        title = "Results";
    }

    @Override
    public void setAdminComponents() {
    }

    @Override
    public void setupLayout() {
        HBox.setHgrow(componentRoot, Priority.ALWAYS);
         Scene scene = MainApp.getRootStage().getScene();
        scene.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            qrescont.setPrefWidth(newValue.floatValue()*0.6);
        });
        scene.heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
//           ((HBox) qrescont.getParent()).setMinHeight(newValue.floatValue()*0.6);
        });
    }

    public void setResults(Course takenCourse, List<List<ObjectId>> givenAnswers,Label time) {
        this.takenCourse = takenCourse;
        this.givenAnswers = givenAnswers;
        this.timePassed.setText(time.getText());
    }

    public void showResults() {
        generateQuestionList();

        showQuestionResult(takenCourse.getQuestions().get(0));
        //resultsContainer.getChildren().add(new Label())
    }

    private void generateQuestionList() {
        ObservableList<Node> items = FXCollections.observableArrayList();
        for (Question q : takenCourse.getQuestions()) {
            Label l = new Label(q.getQuestion());
            l.setUserData(q);
            items.add(l);

        }
        questionList.setItems(items);
        generateScore();
        questionList.setCellFactory(new Callback<ListView<Label>, ListCell<Label>>() {

            @Override
            public ListCell<Label> call(ListView<Label> p) {

                ListCell<Label> cell = new ListCell<Label>() {

                    @Override
                    protected void updateItem(Label t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getText());
                            setPrefWidth(300);
                            setUserData(t.getUserData());
                            getStyleClass().addAll(t.getStyleClass());
                            setOnMouseClicked(changeQuestionResult);
                            setPrefHeight(USE_COMPUTED_SIZE);
                            setOnMousePressed((MouseEvent event) -> {
                                change();
                            });
                            //setWidth(p.getWidth());
                        }
                    }

                    public void change() {
                    }
                };

                return cell;
            }
        });

    }

    private void generateScore() {
        ObservableList<Node> listItems = questionList.getItems();
        List<Question> qs = takenCourse.getQuestions();
        float percentage = 100;
        float step = 100 / qs.size();
        int correctQ = 0;
        for (int i = 0; i < qs.size(); i++) {
            Question q = qs.get(i);
            List<ObjectId> corAns = q.getCorrectAnswerIds();
            List<ObjectId> givAns = givenAnswers.get(i);
            boolean questionIsCorrect = true;
            for (Answer a : q.getAnswers()) {
                if (!checkAnswer(a, corAns, givAns)) {
                    questionIsCorrect = false;
                }
            }
            if (!questionIsCorrect) {
                Label l = (Label) listItems.get(i);
                l.getStyleClass().add("gotQuestionWrong");
                percentage -= step;
            } else {
                correctQ++;
                Label l = (Label) listItems.get(i);
                l.getStyleClass().add("gotQuestionCorrect");
            }
        }
        int minScore = takenCourse.getMinimumScore();
        score.setText(correctQ+" ("+percentage + "%)");
        if(correctQ >= minScore){
            score.getStyleClass().add("gotQuestionCorrect");
        }else{
            score.getStyleClass().add("gotQuestionWrong");
        }
        requiredScore.setText(minScore+" ("+minScore*step+"%)");
    }

    private boolean checkAnswer(Answer a, List<ObjectId> corAns, List<ObjectId> givAns) {
        boolean answerIsCorrect = false;
        boolean answerIsGiven = false;
        ObjectId ansId = a.getId();
        for (ObjectId corAn : corAns) {
            if (corAn.toString().equals(ansId.toString())) {
                answerIsCorrect = true;
            }
        }
        for (ObjectId givAn : givAns) {
            if (givAn.toString().equals(ansId.toString())) {
                answerIsGiven = true;
            }
        }
        if (!answerIsCorrect && !answerIsGiven) {
            return true;
        } else if (answerIsCorrect && answerIsGiven) {
            return true;
        }
        return false;
    }

    EventHandler changeQuestionResult = (EventHandler<MouseEvent>) (MouseEvent event) -> {
        Node source = (Node) event.getSource();
        showQuestionResult((Question) source.getUserData());
    };

    private void showQuestionResult(Question question) {
        qrescont.setPrefWidth(MainApp.getRootStage().getWidth()*0.6);
        resultAnswersContainer.getChildren().clear();
        List<Answer> q = question.getAnswers();
        Log.log(getClass(), Level.SEVERE, givenAnswers.size() + "");
        int indexOfQ = takenCourse.getQuestions().indexOf(question);
        resultQuestion.setText(question.getQuestion());
        List<ObjectId> oIds = givenAnswers.get(indexOfQ);
        List<ObjectId> cIds = question.getCorrectAnswerIds();
        resultAnswersContainer.getChildren().clear();
        VBox.setVgrow(explanation, Priority.ALWAYS);
        explanation.setWrapText(true);
        explanation.setText(question.getExplanation());
        explanation.setMaxWidth(500);
        for (Answer a : q) {
            VBox answerContainer = new VBox();
            answerContainer.getStyleClass().add("resultAnswers");
            Label ans = new Label(a.getAnswer());
            ans.setWrapText(true);
            answerContainer.setMaxWidth(500);
            VBox.setVgrow(ans, Priority.ALWAYS);
            ans.setPrefHeight(Region.USE_COMPUTED_SIZE);
            answerContainer.getChildren().add(ans);
             resultAnswersContainer.getChildren().add(answerContainer);
             HBox givenResults = new HBox();
             boolean isTrueA = false;
            for (ObjectId oid : cIds) {
                if (oid.equals(a.getId())) {
                   Label trueIndicator = new Label("Correct Answer");
                    trueIndicator.getStyleClass().add("trueIndicator");
                    givenResults.getChildren().add(0,trueIndicator);
                    isTrueA = true;
                }
            }
            for (ObjectId cid : oIds) {
                if (cid.equals(a.getId())) {
                    Label correctIndicator = new Label("Your Answer");
                    String y = (isTrueA?"yes":"no");
                    correctIndicator.getStyleClass().add("correctIndicator");
                    correctIndicator.getStyleClass().add(y);
                    givenResults.getChildren().add(correctIndicator);
                }
            }
           answerContainer.getChildren().add(0,givenResults);
        }

    }

}
