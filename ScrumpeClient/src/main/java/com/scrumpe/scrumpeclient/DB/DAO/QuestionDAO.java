/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.DB.DAO;

import com.mongodb.MongoClient;
import com.scrumpe.scrumpeclient.DB.Entity.Question;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author Max Verhoeven
 */
public class QuestionDAO extends DAO<Question, String> {

    public QuestionDAO(Class<Question> entityClass, MongoClient mongoClient, Morphia morphia, String dbName) {
        super(entityClass, mongoClient, morphia, dbName);
    }
    public void deleteQuestions(DAOCallBack<Question> callback,List<Question> question){
        accessDB(callback, taskList(() -> {
            for (Question question1 : question) {
                delete(question1);
            }
            return question; //To change body of generated lambdas, choose Tools | Templates.
        }));
    }
    
    public void saveQuestion(DAOCallBack<Question> callback,Question q){
        accessDB(callback, task(() -> {
            Key<Question> save = save(q);
            q.setId((ObjectId) save.getId());
            return q; //To change body of generated lambdas, choose Tools | Templates.
        }));
    }
    public void saveQuestions(DAOCallBack<Question> callback,List<Question> qs){
        accessDB(callback, task(() -> {
            qs.forEach((q) -> {
                Key<Question> save = save(q);
                q.setId((ObjectId) save.getId());
            });
            return qs; //To change body of generated lambdas, choose Tools | Templates.
        }));
    }
    
}
