/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.DB.DAO;

import com.mongodb.MongoClient;
import com.scrumpe.scrumpeclient.DB.Entity.Answer;
import com.scrumpe.scrumpeclient.DB.Entity.Question;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author Max Verhoeven
 */
public class AnswerDAO extends DAO<Answer,String> {
  
    public AnswerDAO(Class<Answer> entityClass, MongoClient mongoClient, Morphia morphia, String dbName) {
        super(entityClass, mongoClient, morphia, dbName);
    }
    public void getAnswers(DAOCallBack callback,String key,Object value){
      accessDB(callback,taskList(() -> {
            return super.findOneId(key,value);
        }));
    }
     public void deleteAnswers(DAOCallBack<Question> callback,List<Answer> answers){
        accessDB(callback, taskList(() -> {
            for (Answer answer : answers) {
                delete(answer);
            }
            return answers; //To change body of generated lambdas, choose Tools | Templates.
        }));
    }
    public void getAnswers(DAOCallBack<Answer> callback){
        accessDB(callback,taskList(() -> {
            Query<Answer> query = super.createQuery();
            List<Answer> answers = query.asList();
            return answers;
        }));
    }
    public void createAnswer(DAOCallBack<Answer> callback,Answer ans){
        accessDB(callback,task(() -> {
            Key<Answer> save = save(ans);
            ans.setId((ObjectId) save.getId());
            return  ans;
        }));
    }
     public void createAnswers(DAOCallBack<Answer> callback,List<Answer> anss){
        accessDB(callback,task(() -> {
            anss.forEach((ans) -> {
                Key<Answer> save = save(ans);
                ans.setId((ObjectId) save.getId());
            });
            return  anss;
        }));
    }
}
