/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.DB.DAO;

import com.mongodb.MongoClient;
import com.scrumpe.scrumpeclient.DB.Entity.User;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager;
import com.scrumpe.scrumpeclient.Utils.Escurity;
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

    public void tryLogin(DAOCallBack source,String email, String password) {
        ScreenManager.getInstance().showLoadingScreen(true);
        accessDB(source,task(() -> {
            Query<User> q = super.createQuery();
            q.filter("email = ", email);
            q.filter("password = ", Escurity.hash(password));
            return findOne(q);
        }));
    }
    public static void logOut() {
        loggedInUser = null;
    }

    
}
