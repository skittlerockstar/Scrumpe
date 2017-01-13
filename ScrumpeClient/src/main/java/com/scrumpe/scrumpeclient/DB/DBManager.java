/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.DB;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

/**
 *
 * @author Max Verhoeven
 */
public class DBManager {

    private final boolean DEBUG = false;

    private static DBManager instance;
    private final MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private final Morphia morphia;
    private final Datastore datastore;

    private static final String CON_HOST_DEB = "localhost",
            CON_HOST = "ds157078.mlab.com",
            CON_U = "scrumpe",
            CON_U_DEB = "",
            CON_P = "scrumpe",
            CON_P_DEB = "",
            DATABASE = "scrumpe",
            DATABASE_DEB = "Scrumpe",
            MAP_PACKAGE = "com.scrumpe.scrumpeclient.DB",
            MAP_PACKAGE_DEB = "com.scrumpe.scrumpeclient.DB.Entity";

    private static final int CON_PORT_DEB = 27017,
                             CON_PORT  = 57078;

    public Datastore getDatastore() {
        return datastore;
    }

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() {
        if (DEBUG) {
            mongoClient = new MongoClient("localhost", 27017);
            morphia = new Morphia();
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
            });
            morphia = new Morphia();

            morphia.mapPackage(MAP_PACKAGE);
            datastore = morphia.createDatastore(mongoClient, DATABASE);
        }
    }

    public <T extends BasicDAO> Object getDAO(final Class<T> daoClass) {
        Constructor<?> ctor;
        Class<?> x = (Class<?>) ((ParameterizedType) daoClass.getGenericSuperclass()).getActualTypeArguments()[0];
        Object object = null;
        try {
            ctor = daoClass.getConstructor(Class.class, MongoClient.class, Morphia.class, String.class);
            object = ctor.newInstance(new Object[]{x, mongoClient, morphia, DATABASE});
        } catch (Exception ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return object;
    }

    public void close() {

    }
}
