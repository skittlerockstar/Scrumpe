/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.DB.Entity;
import java.io.Serializable;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;


/**
 *
 * @author Max Verhoeven
 */

public class Question extends BaseEntity {

    @Property("question")
    private String question;
    @Property
    private String explanation;
    @Reference
    private List<Answer> answers;
    
    @Property("correctAnswers")
    private ObjectId[] correctAnswerIds;

    public ObjectId[] getCorrectAnswerIds() {
        return correctAnswerIds;
    }

    public void setCorrectAnswerIds(ObjectId[] correctAnswerIds) {
        this.correctAnswerIds = correctAnswerIds;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
    
}
