/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.DB.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
    private String question = "";
    public String getQuestion() {return question;}
    public void setQuestion(String question) {this.question = question;}
    
    @Property("description")
    private String explanation = "";
    public String getExplanation() {return explanation;}
    public void setExplanation(String explanation) {this.explanation = explanation;}

    @Reference
    private List<Answer> answers = new ArrayList<>();
    public List<Answer> getAnswers() {return answers;}
    public void setAnswers(List<Answer> answers) {this.answers = answers;}
   
    @Property("correctAnswers")
    private List<ObjectId> correctAnswerIds = new ArrayList<>();
    public List<ObjectId> getCorrectAnswerIds() {return correctAnswerIds;}
    public void setCorrectAnswerIds(List<ObjectId> correctAnswerIds) { this.correctAnswerIds = correctAnswerIds; }
    
    @Override
    public String toString() {
        return "Question{" +getId()+ "question=" + question + ", explanation=" + explanation + ", answers=" + answers + ", correctAnswerIds=" + '}';
    }
    
}
