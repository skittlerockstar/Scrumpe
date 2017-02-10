/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.DB.DAO;

import com.scrumpe.scrumpeclient.DB.DAO.Callback.DAOCallBack;
import com.mongodb.MongoClient;
import com.scrumpe.scrumpeclient.DB.Entity.Course;
import com.scrumpe.scrumpeclient.Screen.Component.Admin.CourseEditorController;
import java.util.List;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author Max Verhoeven
 */
public class CourseDAO extends DAO<Course,String> {
    
    public CourseDAO(Class<Course> entityClass, MongoClient mongoClient, Morphia morphia, String dbName) {
        super(entityClass, mongoClient, morphia, dbName);
    }
    public void getCourse(DAOCallBack callback,String key,Object value){
      accessDB(callback,taskList(() -> {
            return super.findOneId("_id",value);
        }));
    }
    public void getCourses(DAOCallBack<List<Course>> callback){
        accessDB(callback,taskList(() -> {
            List<Course> courseTitleAndIDS = find().asList();
            return courseTitleAndIDS;
        }));
    }
    public void deleteCourse(DAOCallBack<Course> callback,Course course){
        accessDB(callback, task(() -> {
            delete(course);
            
            return course; //To change body of generated lambdas, choose Tools | Templates.
        }));
    }
    public void saveCourses(DAOCallBack<List<Course>> callback,List<Course> courses){
        accessDB(callback,taskList(() -> {
            for (Course course : courses) {
                Key<Course> save = save(course);
            }
            return courses;
        }));
    }

    public void saveCourse(DAOCallBack<Course> callback, Course c) {
        accessDB(callback,task(() -> {
                Key<Course> save = save(c);
                return c;
        }));
    }
}
