/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.Component.Admin;

import com.scrumpe.scrumpeclient.DB.DAO.CourseDAO;
import com.scrumpe.scrumpeclient.DB.DAO.Callback.DAOCallBack;
import com.scrumpe.scrumpeclient.DB.DAO.QuestionDAO;
import com.scrumpe.scrumpeclient.DB.DBManager;
import com.scrumpe.scrumpeclient.DB.Entity.Course;
import com.scrumpe.scrumpeclient.DB.Entity.Question;
import com.scrumpe.scrumpeclient.MainApp;
import com.scrumpe.scrumpeclient.Screen.Base.ComponentBase;
import com.scrumpe.scrumpeclient.Screen.Utils.ComponentFactory;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager;
import com.scrumpe.scrumpeclient.Utils.ExcelUploader;
import com.scrumpe.scrumpeclient.Utils.RGX;
import java.awt.Desktop;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import org.bson.types.ObjectId;

/**
 * FXML Controller class
 *
 * @author Max Verhoeven
 */
public class CourseEditorController extends ComponentBase implements DAOCallBack<Course> {

    @FXML
    private TextField courseTitle, minimumScore, searchQuestion;
    @FXML
    private TextArea courseDescription;
    @FXML
    private Label questionCount, percentage;
    @FXML
    private Button addQuestionBtn, deleteAllQBtn, uploadExcelBtn, saveCourse, discardCourse;
    @FXML
    private Accordion questionContainer;
    final FileChooser fileChooser = new FileChooser();
    CEQuestionController controller;
    List<Question> questions = new ArrayList<>();
    List<Question> preparedQuestions = new ArrayList<>();
    public List<Question> qUpForDeletion = new ArrayList<>();
    Course currentCourse;
    private boolean isEditing = false;
    public void setCurrentCourse(Course currentCourse) {
        CourseDAO cd = DBManager.getInstance().getDAO(CourseDAO.class);
        cd.getCourse((o) -> {
            this.currentCourse = (Course) o;
        }, null, currentCourse.getId());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FileChooser.ExtensionFilter excelFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(excelFilter);
        currentCourse = new Course();
        setHandlers();
        searchQuestion.setManaged(false);
        searchQuestion.setVisible(false);
    }

    @Override
    public void setupLayout() {
        AnchorPane.setBottomAnchor(componentRoot, 0.0);
        AnchorPane.setTopAnchor(componentRoot, 0.0);
        AnchorPane.setLeftAnchor(componentRoot, 0.0);
        AnchorPane.setRightAnchor(componentRoot, 0.0);
    }

    private void addQuestionField(Question data) {
        ObservableList<TitledPane> panes = questionContainer.getPanes();
        FXMLLoader CEQI = ComponentFactory.createComponent(this, ComponentFactory.ComponentType.CEQuestionItem, true);
        TitledPane s = (TitledPane) CEQI.getRoot();
        controller = CEQI.getController();
        controller.setCec(this);
        s.setUserData(controller);
        panes.add(s);
        controller.setExistingData(data);
        controller.setData(panes);
        updateQuestionCount();
        handleScoreInput();
    }

    private void updateQuestionCount() {
        questionCount.setText(questionContainer.getPanes().size() + "");
    }

    private void setMinScorePercentage(int minNr, int maxNr) {
        float max = (float) maxNr;
        float min = (float) minNr;
        if (min != 0 && max != 0) {
            if ((int) min > (int) max) {
                min = max;
                minimumScore.setText((int) min + "");
            }
            float ans = max / min;
            ans = 100 / ans;
            percentage.setText("(" + ans + "%)");
            percentage.setOpacity(0.5);
        }else{
            percentage.setText("(0%)");
        }
    }

    private void prepareCourseForDB() {
        if(courseTitle.getText().length() < 4){
            presentNote("You don't have a title for this course");
            return;
        }
        if(currentCourse == null) currentCourse =new Course();
        currentCourse.getQuestions().clear();
        if(currentCourse.getId()==null) removeDeletedQuestions();
        ObservableList<TitledPane> panes = questionContainer.getPanes();
        boolean isValid = true;
        for (TitledPane pane : panes) {
           if(!((CEQuestionController)pane.getUserData()).checkRequirements()){
               isValid = false;
           }
        }
        if(isValid){
            panes.stream().forEach((cc)-> ((CEQuestionController)cc.getUserData()).prepareQuestionForDB(this));
        }
    }
    public void saveQuestions(){
        
    }
    private void discardCourse() {
        throwConfirmError("Are you sure you want to discard changes?", (event) -> {
            show(false);
            isEditing = false;
            deleteAllQuestions();
        }, (event) ->{});
    }

    private void insertData(Course course) {
        if(isEditing) currentCourse.getQuestions().addAll(course.getQuestions());
        else currentCourse = course;
        courseTitle.setText(course.getCourseTitle());
        courseDescription.setText(course.getCourseDescription());
        List<Question> posQ = ((course.getQuestions() != null) ? course.getQuestions() : new ArrayList<>());
        questions = posQ;
        for (Question question : questions) {
            addQuestionField(question);
        }
        minimumScore.setText(course.getMinimumScore() + "");
        handleScoreInput();
    }



    @Override
    public void dbResult(Course result) {
        if (result == null) {
            presentNote("Sorry something went wrong... please contact the admin.");
        } else {
            clearCE();
            show(false);
            ScreenManager.getInstance().loadScreen(ScreenManager.MainScreen.Main, true);
        }
        ScreenManager.getInstance().showLoadingScreen(false);
    }
    public void editCourse(Course c) {
        qUpForDeletion.clear();
        questionContainer.getPanes().clear();
        CourseDAO cd = DBManager.getInstance().getDAO(CourseDAO.class);
        cd.getCourse((o) -> {
            this.currentCourse = (Course) o;
            insertData((Course) o);
        isEditing = true;
        show(true);
        }, null, c.getId());
        
    }

    private void generateCourseFromExcel() {
        final File file = fileChooser.showOpenDialog(MainApp.getRootStage());
        ScreenManager screenM = ScreenManager.getInstance();
        screenM.showLoadingScreen(true);
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                return ExcelUploader.readExcel(file);
            }
        };
        task.setOnSucceeded((event) -> {
            try {
                Course c = (Course) task.get();
                insertData(c);
            } catch (Exception e) {
                screenM.showNotification(e.toString(), true);
            } finally {
                screenM.showLoadingScreen(false);
            }
        });
        task.setOnFailed((event) -> {
            System.err.println(task.getException().toString());
            screenM.showNotification(task.getException().getMessage(), true);
            screenM.showLoadingScreen(false);
        });
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    private void deleteAllQuestions() {
        questionContainer.getPanes().clear();
        updateQuestionCount();
        handleScoreInput();
    }

    private void handleScoreInput() {
        String mTxt = minimumScore.getText();
        if (mTxt.equals("")) {
            return;
        }
        int qSize = questionContainer.getPanes().size();
        int minNr = ((mTxt.matches(RGX.INT)) ? Integer.parseInt(mTxt) : qSize);
        if (minNr > qSize || minNr < 0) {
            minNr = qSize;
        }
        minimumScore.setText(String.valueOf(minNr));
        setMinScorePercentage(minNr, qSize);
    }

    private void setHandlers() {
        saveCourse.setOnAction((e) -> prepareCourseForDB());
        discardCourse.setOnAction((e) -> discardCourse());
        addQuestionBtn.setOnAction((e) -> addQuestionField(new Question()));
        deleteAllQBtn.setOnAction((e) -> deleteAllQuestions());
        uploadExcelBtn.setOnAction((e) -> generateCourseFromExcel());
        minimumScore.setOnKeyReleased((e) -> handleScoreInput());
        //courseTitle.textProperty().addListener((o, old, newV) -> currentCourse.setCourseTitle(newV));
        //courseTitle.textProperty().addListener((o, old, newV) -> currentCourse.setCourseDescription(newV));
        minimumScore.textProperty().addListener((o, old, newValue) -> {
            if (newValue.matches(RGX.INT)) {
                currentCourse.setMinimumScore(Integer.parseInt(newValue));
            }
        });
        questionContainer.getPanes().addListener((ListChangeListener.Change<? extends Node> c) -> {
            handleQuestionChanges(c);
        });
        searchQuestion.textProperty().addListener((ob, o, n) -> searchForQuestion(n));
    }

    @Override
    public void onChanged() {
    }

    public void clearCE() {
        isEditing = false;
        currentCourse = new Course();
        deleteAllQuestions();
        courseDescription.clear();
        courseTitle.clear();
        minimumScore.clear();
        show(false);
    }

    private void checkCourse() {
        if (currentCourse.getQuestions() == null) {
            currentCourse.setQuestions(new ArrayList<Question>());
        }
        ObservableList<TitledPane> panes = questionContainer.getPanes();
        for (Iterator<TitledPane> iterator = panes.iterator(); iterator.hasNext();) {
            TitledPane next = iterator.next();
            CEQuestionController userData = (CEQuestionController) next.getUserData();
            Question question = userData.getQuestion();
            currentCourse.getQuestions().add(question);
        }
    }

    private void handleQuestionChanges(ListChangeListener.Change<? extends Node> c) {
        updateQuestionCount();
        handleScoreInput();
    }

    private void searchForQuestion(String x) {
        String n = x.toLowerCase();
        questionContainer.getPanes().stream()
                .peek((t) -> hidePane(t))
                .filter((t) -> ((CEQuestionController) t.getUserData()).getQuestion().getQuestion().toLowerCase().contains(n))
                .forEach((z) -> showPane(z));
    }

    private void hidePane(TitledPane t) {
        int size = questionContainer.getPanes().size();
        t.setPrefHeight(0);
        t.setMinHeight(0);
        t.setMaxHeight(0);
        t.setManaged(false);
        t.setExpanded(true);
    }

    private void showPane(TitledPane t) {
        t.setPrefHeight(Region.USE_COMPUTED_SIZE);
        t.setMinHeight(Region.USE_COMPUTED_SIZE);
        t.setMaxHeight(Region.USE_COMPUTED_SIZE);
          t.setExpanded(false);
           t.setTranslateY(10);
    }

    private void removeDeletedQuestions() {
    }
    public void saveCourse(){
         CourseDAO dao = DBManager.getInstance().getDAO(CourseDAO.class);
         QuestionDAO qdao = DBManager.getInstance().getDAO(QuestionDAO.class);
         currentCourse.setCourseTitle(courseTitle.getText());
         currentCourse.setCourseDescription(courseDescription.getText());
         currentCourse.getQuestions().removeAll(qUpForDeletion);
         qdao.deleteQuestions(null, qUpForDeletion);
        dao.saveCourse((o) -> {
            dbResult(o);
        }, currentCourse);
    }

    void callback(Question q) {
        preparedQuestions.add(q);
        ScreenManager.getInstance().showLoadingScreen(true);
        if(questionContainer.getPanes().size() == preparedQuestions.size()){
            QuestionDAO dao = DBManager.getInstance().getDAO(QuestionDAO.class);
        dao.saveQuestions((qs) -> {
            currentCourse.getQuestions().addAll(qs);
            saveCourse();
        }, preparedQuestions);
        }
    }
    @FXML
    private void downloadTemplate(){
        try {
            URL resource = getClass().getResource("/template/template.xlsx");
            File f = new File(resource.toURI());
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save file");
            fileChooser.setInitialFileName("Scrumpe_Template.xlsx");
            File savedFile = fileChooser.showSaveDialog(MainApp.getRootStage());
            try {
                if(savedFile !=null){
                    if(savedFile.exists())
                    Files.delete(savedFile.toPath());
                }
                Files.copy(f.toPath(), savedFile.toPath());
                if (Desktop.isDesktopSupported()) {
                    System.err.println(savedFile.getAbsolutePath());
                        Desktop.getDesktop().browse(savedFile.getParentFile().toURI());
                }
            } catch (IOException ex) {
                System.out.println(ex.toString());
                presentNote("Oops something went wrong. Please contact the admin");
            }
        } catch (URISyntaxException ex) {
            presentNote("Oops something went wrong. Please contact the admin");
        }
    }
}
