/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.MainScreen;

import com.scrumpe.scrumpeclient.DB.Entity.Answer;
import com.scrumpe.scrumpeclient.DB.Entity.Course;
import com.scrumpe.scrumpeclient.DB.Entity.Question;
import com.scrumpe.scrumpeclient.Screen.Base.ScreenBase;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager;
import com.scrumpe.scrumpeclient.Utils.Log;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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
    private List<ObjectId[]> givenAnswers;
    @FXML
    private Label score, requiredScore, resultQuestion, explanation;
    @FXML
    private VBox resultSummaryList, resultAnswersContainer;
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
    }

    public void setResults(Course takenCourse, List<ObjectId[]> givenAnswers) {
        this.takenCourse = takenCourse;
        this.givenAnswers = givenAnswers;
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
                            setUserData(t.getUserData());
                            setStyle(t.getStyle());
                            setOnMouseClicked(changeQuestionResult);
                            setPrefHeight(USE_COMPUTED_SIZE);
                            setOnMousePressed((MouseEvent event) -> {
                                change();
                            });
                            setWidth(p.getWidth());
                        }
                    }

                    public void change() {
                        for (Node n : p.getChildrenUnmodifiable()) {
                            n.setDisable(false);
                        }
                        setDisabled(true);
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
        for (int i = 0; i < qs.size(); i++) {
            Question q = qs.get(i);
            ObjectId[] corAns = q.getCorrectAnswerIds();
            ObjectId[] givAns = givenAnswers.get(i);
            boolean questionIsCorrect = true;
            for (Answer a : q.getAnswers()) {
                if (!checkAnswer(a, corAns, givAns)) {
                    questionIsCorrect = false;
                }
            }
            if (!questionIsCorrect) {

                Label l = (Label) listItems.get(i);
                l.setStyle("-fx-background-color:#ff0000");
                percentage -= step;
            } else {
                Label l = (Label) listItems.get(i);
                l.setStyle("-fx-background-color:#00ff00");
            }
        }
        score.setText(percentage + "%");
        requiredScore.setText(takenCourse.getMinimumScore()*step+"%");
    }

    private boolean checkAnswer(Answer a, ObjectId[] corAns, ObjectId[] givAns) {
        boolean answerIsCorrect = false;
        boolean answerIsGiven = false;
        ObjectId ansId = a.getId();
        for (ObjectId corAn : corAns) {
            Log.log(getClass(), Level.SEVERE, corAn.toString());
            if (corAn.toString().equals(ansId.toString())) {
                answerIsCorrect = true;
            }
        }
        for (ObjectId givAn : givAns) {
            Log.log(getClass(), Level.SEVERE, givAn.toString());
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
        resultAnswersContainer.getChildren().clear();
        List<Answer> q = question.getAnswers();
        Log.log(getClass(), Level.SEVERE, givenAnswers.size() + "");
        int indexOfQ = takenCourse.getQuestions().indexOf(question);
        resultQuestion.setText(question.getQuestion());
        ObjectId[] oIds = givenAnswers.get(indexOfQ);
        ObjectId[] cIds = question.getCorrectAnswerIds();
        resultAnswersContainer.getChildren().clear();
        explanation.setText(question.getExplanation());
        for (Answer a : q) {
            HBox answerContainer = new HBox();
            answerContainer.getStyleClass().add("resultAnswers");
            answerContainer.getChildren().add(new Label(a.getAnswer()));
            for (ObjectId cid : cIds) {
                if (cid.equals(a.getId())) {
                    answerContainer.getChildren().add(correctAnswer);
                }
            }
            for (ObjectId oid : oIds) {
                if (oid.equals(a.getId())) {
                    answerContainer.getChildren().add(yourAnswer);
                }
            }
            resultAnswersContainer.getChildren().add(answerContainer);
        }

    }

}
