/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.DB.DAO;

import com.mongodb.MongoClient;
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
public class UserDAO extends BasicDAO<User, String> {

    private static User loggedInUser;
    public static User getLoggedInUser() {
        return loggedInUser;
    }
    
    public UserDAO(Class<User> entityClass, MongoClient mongoClient, Morphia morphia, String dbName) {
        super(entityClass, mongoClient, morphia, dbName);
    }
    public User tryLogin(String email, String password){
        User u;
       if(loggedInUser == null){
       Query<User> q = super.createQuery();
       q.filter("email = ", email);
       q.filter("password = ", Escurity.hash(password));
       u = super.findOne(q);
       if(u !=null){
           loggedInUser = u;
       }
       }else{
           return loggedInUser;
       }
       return u;
    }
   
    public static void logOut(){
        loggedInUser = null;
    }
}
