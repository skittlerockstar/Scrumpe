/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.DB.DAO;

import com.scrumpe.scrumpeclient.DB.DAO.Callback.DAOCallBack;
import com.mongodb.MongoClient;
import com.scrumpe.scrumpeclient.DB.Entity.Course;
import com.scrumpe.scrumpeclient.DB.Entity.User;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager;
import com.scrumpe.scrumpeclient.Utils.Escurity;
import java.util.List;
import javafx.concurrent.WorkerStateEvent;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author Max Verhoeven
 */
public class UserDAO extends DAO<User,String>{

        private static User loggedInUser;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public UserDAO(Class<User> entityClass, MongoClient mongoClient, Morphia morphia, String dbName) {
        super(entityClass, mongoClient, morphia, dbName);
    }
     public void getUsers(DAOCallBack<List<User>> callback){
        accessDB(callback,taskList(() -> {
            Query<User> query = super.createQuery();
            List<User> courseTitleAndIDS = query.asList();
            return courseTitleAndIDS;
        }));
    }
    public void tryLogin(DAOCallBack source,String email, String password) {
        accessDB(source,task(() -> {
            Query<User> q = super.createQuery();
            q.filter("email = ", email.toLowerCase());
            q.filter("password = ", Escurity.hash(password));
            loggedInUser = findOne(q);
            return loggedInUser;
        }));
    }
    public static void logOut() {
        loggedInUser = null;
    }

    
}
