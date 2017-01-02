/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Utils;
import com.scrumpe.scrumpeclient.DB.Answer;
import com.scrumpe.scrumpeclient.DB.Course;
import com.scrumpe.scrumpeclient.DB.Question;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Max Verhoeven
 */
public class TestCourseFactory {

    public static Course createTestCourse() {
        List<Answer> testAnswers = createTestAnswers();
        List<Question> testQuestions = createTestQuestions(testAnswers);
        return new Course("RandomCourse", testQuestions);
    }

    private static List<Answer> createTestAnswers() {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(1L,"Appel"));
        answers.add(new Answer(2L,"Banaan"));
        answers.add(new Answer(3L,"Peer"));
        answers.add(new Answer(4L,"Perzik"));
        return answers;
    }

    private static List<Question> createTestQuestions(List<Answer> answers) {
        List<Question> questions = new ArrayList<>();
        Long[] correctAnswers = {answers.get(0).getId()};
        questions.add(new Question(1L,"Wat voor fruit is rood?", answers,correctAnswers));
       
        Long[] correctAnswers2 = {answers.get(1).getId()};
        questions.add(new Question(2L,"Wat voor fruit is geel?", answers, correctAnswers2));
        
        Long[] correctAnswers3 = {answers.get(2).getId()};
        questions.add(new Question(3L,"Wat voor fruit is groen?", answers, correctAnswers3));
        
        Long[] correctAnswers4 = {answers.get(3).getId()};
        questions.add(new Question(4L,"Wat voor fruit is oranje?", answers, correctAnswers4));
       
        Long[] correctAnswers5 = {answers.get(0).getId(), questions.get(3).getId()};
        questions.add(new Question(5L,"Wat voor fruit is rond?", answers, correctAnswers5));
        return questions;
    }
}
