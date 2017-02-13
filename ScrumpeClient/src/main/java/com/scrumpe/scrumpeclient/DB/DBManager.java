/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.DB;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.concurrent.Task;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

/**
 *
 * @author Max Verhoeven
 */
public class DBManager {
    private final boolean DEBUG = true;

    private static DBManager instance;
    private final MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private final Morphia morphia;
    private final Datastore datastore;
    static Preferences preferences = 
      Preferences.userNodeForPackage(DBManager.class);
    private static String CON_HOST_DEB ,CON_HOST ,CON_U ,CON_U_DEB ,CON_P ,
                          CON_P_DEB ,DATABASE ,DATABASE_DEB ,MAP_PACKAGE ,
                          MAP_PACKAGE_DEB;

    private static final int CON_PORT_DEB = 27017,
                             CON_PORT  = 57078;

    
    public Datastore getDatastore() {
        return datastore;
    }

    public static DBManager getInstance() {
        if (instance == null) {
            setupCredentials();
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() {
        MongoClientOptions.Builder options = MongoClientOptions.builder();
        MongoClientOptions connectTimeout = options.serverSelectionTimeout(3000).build();
        if (DEBUG) {
            DATABASE = "Scrumpe";
            ServerAddress sa = new ServerAddress("localhost", 27017);
            mongoClient = new MongoClient(new ArrayList<ServerAddress>() {
                { add(sa);}
            },connectTimeout);
            morphia = new Morphia();
            System.err.println(MAP_PACKAGE);
            morphia.mapPackage(MAP_PACKAGE);
            datastore = morphia.createDatastore(mongoClient, "Scrumpe");
        } else {
            ServerAddress sa = new ServerAddress(CON_HOST, CON_PORT);
            MongoCredential mc = MongoCredential.createCredential(CON_U, DATABASE, CON_P.toCharArray());
            mongoClient = new MongoClient(new ArrayList<ServerAddress>() {
                { add(sa);}
            },
            new ArrayList<MongoCredential>() {
                {add(mc);}
            },connectTimeout);
            
            morphia = new Morphia();
            morphia.mapPackage(MAP_PACKAGE);
            datastore = morphia.createDatastore(mongoClient, DATABASE);
        }
    }

    public <T extends BasicDAO> T getDAO(final Class<T> daoClass) {
        Constructor<?> ctor;
        Class<?> x = (Class<?>) ((ParameterizedType) daoClass.getGenericSuperclass()).getActualTypeArguments()[0];
        Object object = null;
        try {
            ctor = daoClass.getConstructor(Class.class, MongoClient.class, Morphia.class, String.class);
            object = ctor.newInstance(new Object[]{x, mongoClient, morphia, DATABASE});
        } catch (Exception ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return (T)object;
    }

    public void close() {

    }
    private static void setupCredentials() {
            CON_HOST_DEB =  preferences.get("CON_HOST_DEB", null); // localhost
            CON_HOST = preferences.get("CON_HOST", null);//"ds157078.mlab.com";
            CON_U = preferences.get("CON_U", null);//"scrumpe";
            CON_U_DEB = preferences.get("CON_U_DEB", null);// "";
            CON_P = preferences.get("CON_P", null);//"scrumpe";
            CON_P_DEB = preferences.get("CON_P_DEB", null);//"";
            DATABASE = preferences.get("DATABASE", null);//"scrumpe";
            DATABASE_DEB = preferences.get("DATABASE_DEB", null);//"Scrumpe";
            MAP_PACKAGE = preferences.get("MAP_PACKAGE", "com.scrumpe.scrumpeclient.DB");//"com.scrumpe.scrumpeclient.DB";
            MAP_PACKAGE_DEB = preferences.get("MAP_PACKAGE_DEB", null);//"com.scrumpe.scrumpeclient.DB";
    }
}
interface ConnectedCallback{
    
}