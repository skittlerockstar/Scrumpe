/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.DB;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.scrumpe.scrumpeclient.DB.User;
import com.scrumpe.scrumpeclient.DB.DAO.UserDAO;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;


/**
 *
 * @author Max Verhoeven
 */
public class DBManager {
    public static DBManager instance;
    public MongoClient mongoClient;
    public MongoDatabase mongoDatabase;
    public Morphia morphia ;
    public Datastore datastore;
    public static final String CON_STRING = "mongodb://localhost";
    public static final String CON_DEBUG_STRING = "mongodb://localhost";
    public static final String DATABASE = "Scrumpe";
    public static final String MAP_PACKAGE ="com.scrumpe.scrumpeclient.DB";
    
    public static DBManager getInstance(){
        if(instance == null){
            instance = new DBManager();
        } 
        return instance;
    }
    
    private DBManager(){
       mongoClient = new MongoClient("localhost", 27017);
       morphia = new Morphia();
       morphia.mapPackage(MAP_PACKAGE);
       datastore = morphia.createDatastore(mongoClient, "Scrumpe");
    }

    public <T extends BasicDAO> Object getDAO(final Class<T> daoClass) {
        Constructor<?> ctor; 
       Class<?> x  = (Class<?>) ((ParameterizedType)daoClass.getGenericSuperclass()).getActualTypeArguments()[0];
      Object object = null;
       try {
            ctor = daoClass.getConstructor(Class.class,MongoClient.class,Morphia.class,String.class);
             object = ctor.newInstance(new Object[] {x,mongoClient,morphia,DATABASE });
        } catch (Exception ex) {
              Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        } 
        return object;
    }
    
}