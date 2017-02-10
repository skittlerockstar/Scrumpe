/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.DB.Entity;

import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Transient;

/**
 *
 * @author Max Verhoeven
 */

public class Answer extends BaseEntity{

    @Property("answer")
    private String answer = "";
    @Transient
    public boolean isCorrectForExcel = false;
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Answer{" + "answer=" + answer + ", isCorrectForExcel=" + isCorrectForExcel + '}';
    }
 
}
