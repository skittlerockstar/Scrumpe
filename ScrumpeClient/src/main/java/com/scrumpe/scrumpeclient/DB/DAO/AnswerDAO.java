/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.DB.DAO;

import com.mongodb.MongoClient;
import com.scrumpe.scrumpeclient.DB.Entity.Answer;
import com.scrumpe.scrumpeclient.DB.Entity.User;
import com.scrumpe.scrumpeclient.Utils.Escurity;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author Max Verhoeven
 */
public class AnswerDAO extends BasicDAO<Answer, String> {

  
    public AnswerDAO(Class<Answer> entityClass, MongoClient mongoClient, Morphia morphia, String dbName) {
        super(entityClass, mongoClient, morphia, dbName);
    }
   
}
