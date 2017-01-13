/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.DB.DAO;

import com.mongodb.MongoClient;
import com.scrumpe.scrumpeclient.DB.Entity.Course;
import java.util.List;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author Max Verhoeven
 */
public class CourseDAO extends DAO<Course,String> {
    
    public CourseDAO(Class<Course> entityClass, MongoClient mongoClient, Morphia morphia, String dbName) {
        super(entityClass, mongoClient, morphia, dbName);
    }
    public Course getCourse(){
        Datastore dataStore = super.getDatastore();
        Query<Course> query = dataStore.find(Course.class);
                      query = query.project("title",true);
        List<Course> courseTitleAndIDS = query.asList();
        return null;
    }
    public void getCourses(DAOCallBack<Course> callback){
        accessDB(callback,taskList(() -> {
            Query<Course> query = super.createQuery();
                          query = query.project("title",true);
            List<Course> courseTitleAndIDS = query.asList();
            return courseTitleAndIDS;
        }));
    }
}
