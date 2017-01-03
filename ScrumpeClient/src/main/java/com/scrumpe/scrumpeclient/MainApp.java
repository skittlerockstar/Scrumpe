/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient;
import com.mongodb.connection.QueryResult;
import com.scrumpe.scrumpeclient.DB.DAO.AnswerDAO;
import com.scrumpe.scrumpeclient.DB.DAO.CourseDAO;
import com.scrumpe.scrumpeclient.DB.DAO.QuestionDAO;
import com.scrumpe.scrumpeclient.DB.DBManager;
import com.scrumpe.scrumpeclient.DB.Entity.Answer;
import com.scrumpe.scrumpeclient.DB.Entity.Course;
import com.scrumpe.scrumpeclient.DB.Entity.Question;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager;
import com.scrumpe.scrumpeclient.Utils.Log;
import java.util.ArrayList;
import java.util.logging.Level;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.QueryResults;

/**
 *
 * @author Max Verhoeven
 */
public class MainApp extends Application {
    private static Stage rootStage;
    public static Stage getRootStage() {
        return rootStage;
    }
    private static final int SCREEN_MIN_WIDTH = 800;
    private static final int SCREEN_MIN_HEIGHT = 600;
    @Override
    public void start(Stage stage) throws Exception {
        rootStage = stage;
        ScreenManager sm = ScreenManager.getInstance();
        Parent root = (Parent) sm.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMinWidth(SCREEN_MIN_WIDTH);
        stage.setMinHeight(SCREEN_MIN_HEIGHT);
        stage.setMaximized(true);
        sm.loadScreen(ScreenManager.MainScreen.Login);
        stage.show();
        doTestCourse();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void doTestCourse() {
//        CourseDAO cd =(CourseDAO) DBManager.getInstance().getDAO(CourseDAO.class);
//        QuestionDAO qd =(QuestionDAO) DBManager.getInstance().getDAO(QuestionDAO.class);
//        AnswerDAO ad =(AnswerDAO) DBManager.getInstance().getDAO(AnswerDAO.class);
//        Answer a1 = new Answer();
//        a1.setAnswer("This is an answer");
//        Key<Answer> key = ad.save(a1);
//        Question q1 = new Question();
//        q1.setAnswers(new ArrayList<Answer>(){{add(a1);}});
//        q1.setCorrectAnswerIds(new ObjectId[]{(ObjectId)key.getId()});
//        q1.setQuestion("What is this question");
//        qd.save(q1);
//        Course c1 = new Course();
//        c1.setQuestions(new ArrayList<Question>(){{add(q1);}});
//        cd.save(c1);
//        
//        QueryResults<Course> result = cd.find();
//        for (Course course : result) {
//            Log.log(MainApp.class,Level.SEVERE,course.getQuestions().get(0).getQuestion());
//        }
    }
    
}
