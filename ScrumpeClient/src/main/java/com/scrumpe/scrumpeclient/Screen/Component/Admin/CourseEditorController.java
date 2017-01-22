/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.Component.Admin;

import com.scrumpe.scrumpeclient.DB.DAO.CourseDAO;
import com.scrumpe.scrumpeclient.DB.DAO.DAOCallBack;
import com.scrumpe.scrumpeclient.DB.Entity.Course;
import com.scrumpe.scrumpeclient.DB.Entity.Question;
import com.scrumpe.scrumpeclient.MainApp;
import com.scrumpe.scrumpeclient.Screen.Base.ComponentBase;
import com.scrumpe.scrumpeclient.Screen.Utils.ComponentFactory;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager;
import com.scrumpe.scrumpeclient.Utils.ExcelUploader;
import com.scrumpe.scrumpeclient.Utils.ExcelUploader.CourseEnum;
import com.scrumpe.scrumpeclient.Utils.RGX;
import java.awt.font.NumericShaper.Range;
import javafx.stage.FileChooser;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class CourseEditorController extends ComponentBase implements DAOCallBack<Course> {

    @FXML
    private TextField courseTitle, minimumScore;
    @FXML
    private TextArea courseDescription;
    @FXML
    private Label questionCount, percentage;
    @FXML
    private Button addQuestionBtn, deleteAllQBtn, uploadExcelBtn, saveCourse, discardCourse;
    @FXML
    private Accordion questionContainer;
    final FileChooser fileChooser = new FileChooser();
    CEQuestionItemController controller;
    List<Question> questions = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setHandlers();
    }

    @Override
    public void setupLayout() {
        AnchorPane.setBottomAnchor(componentRoot, 0.0);
        AnchorPane.setTopAnchor(componentRoot, 0.0);
        AnchorPane.setLeftAnchor(componentRoot, 0.0);
        AnchorPane.setRightAnchor(componentRoot, 0.0);
    }

    private void addQuestionField(Object data) {
        ObservableList<TitledPane> panes = questionContainer.getPanes();
        FXMLLoader l = ComponentFactory.createComponent(this, ComponentFactory.ComponentType.CEQuestionItem, true);
        TitledPane s = (TitledPane) l.getRoot();
        controller = l.getController();
        s.setUserData(controller);
        panes.add(s);
        if (data != null) {
            controller.setExistingData(data);
        }
        controller.setData(panes);
        updateQuestionCount();
        handleScoreInput();
    }

    private void updateQuestionCount() {
        questionCount.setText(questionContainer.getPanes().size() + "");
    }

    private void setMinScorePercentage(int minNr, int maxNr) {
        float max = (float)maxNr;
         float min = (float)minNr;
        if (min != 0 && max != 0) {
            if ((int) min > (int) max) {
                min = max;
                minimumScore.setText((int) min + "");
            }
            float ans = max / min;
            ans = 100 / ans;
            percentage.setText("(" + ans + "%)");
            percentage.setOpacity(0.5);
        }
    }

    private void saveCourse() {
        ObservableList<TitledPane> panes = questionContainer.getPanes();
        panes.forEach((next) -> {
            ((CEQuestionItemController) next.getUserData()).getQuestions(this);
        });
    }

    private void discardCourse() {
        componentRoot.setVisible(false);
        componentRoot.setMouseTransparent(true);
    }

    private void insertExcelData(HashMap<CourseEnum, Object> course) {
        String[] courseinfo = (String[]) course.get(CourseEnum.CourseInfo);
        courseTitle.setText(courseinfo[0]);
        courseDescription.setText(courseinfo[1]);

        List<Object> questions = (List<Object>) course.get(CourseEnum.Questions);
        for (Object question : questions) {
            addQuestionField(question);
        }
        minimumScore.setText(courseinfo[2]);
        handleScoreInput();
    }

    private void saveCourse(List<Question> results) {
        Course c = new Course();
        c.setCourseTitle(courseTitle.getText());
        c.setCourseDescription(courseDescription.getText());
        c.setMinimumScore(Integer.parseInt(minimumScore.getText()));
        c.setQuestions(results);
        CourseDAO dao = data.getDAO(CourseDAO.class);
        dao.saveCourse(this, c);
    }

    @Override
    public void dbResult(Course result) {
        if (result == null) presentNote("Sorry something went wrong... please contact the admin.");
        else{
            discardCourse();
            ScreenManager.getInstance().loadScreen(ScreenManager.MainScreen.Main, true);
        }
        ScreenManager.getInstance().showLoadingScreen(false);
    }

    @Override
    public void dbResults(List<Course> results) {

    }

    public void addQtoCourse(Question q) {
        questions.add(q);
        if (questions.size() == questionContainer.getPanes().size()) {
            saveCourse(questions);
        }
    }

    public void show(boolean show) {
        componentRoot.setVisible(show);
        componentRoot.setMouseTransparent(!show);
    }

    public void editCourse(Course c) {

    }

    private void generateCourseFromExcel() {
        File file = fileChooser.showOpenDialog(MainApp.getRootStage());
        HashMap<CourseEnum, Object> course;
        course = ((file != null) ? ExcelUploader.readExcel(file) : null);
        if (course != null) insertExcelData(course); 
        else presentNote("File is not readable");
    }

    private void deleteAllQuestions() {
        questionContainer.getPanes().clear();
        updateQuestionCount();
        handleScoreInput();
    }

    private void handleScoreInput() {
        String mTxt = minimumScore.getText(); 
        if(mTxt.equals("")) return;                    
        int qSize = questionContainer.getPanes().size();
        int minNr = ((mTxt.matches(RGX.INT)) ? Integer.parseInt(mTxt) : qSize);
        if(minNr > qSize || minNr < 0 ) minNr = qSize;
        minimumScore.setText(String.valueOf(minNr));
        setMinScorePercentage(minNr,qSize);
    }

    private void setHandlers() {
        saveCourse.setOnAction((event) -> {
            saveCourse();
        });
        discardCourse.setOnAction((event) -> {
            discardCourse();
        });
        addQuestionBtn.setOnAction((event) -> {
            addQuestionField(null);
        });
        deleteAllQBtn.setOnAction((event) -> {
            deleteAllQuestions();
        });
        uploadExcelBtn.setOnAction((event) -> {
            generateCourseFromExcel();
        });
        minimumScore.setOnKeyReleased((event) -> {
            handleScoreInput();
        });
    }

}
