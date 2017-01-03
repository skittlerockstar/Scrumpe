/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.DB.Entity;
import java.io.Serializable;
import java.util.List;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author Max Verhoeven
 */

public class Course extends BaseEntity {

    @Property("title")
    private String courseTitle;
    @Reference
    private List<Question> questions;
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
}
