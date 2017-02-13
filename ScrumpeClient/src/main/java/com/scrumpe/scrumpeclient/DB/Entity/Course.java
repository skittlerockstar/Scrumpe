/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.DB.Entity;
import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author Max Verhoeven
 */

public class Course extends BaseEntity {

    @Property("title")
    private String courseTitle = "";
     @Property("description")
    private String courseDescription = "";
      @Property("requiredScore")
    private int minimumScore = 0;

    public int getMinimumScore() {
        return minimumScore;
    }

    public void setMinimumScore(int minimumScore) {
        this.minimumScore = minimumScore;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
    @Reference
    private List<Question> questions = new ArrayList<>();
    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Course{" + "courseTitle=" + courseTitle + ", courseDescription=" + courseDescription + ", minimumScore=" + minimumScore + ", questions=" + questions + '}';
    }
}
