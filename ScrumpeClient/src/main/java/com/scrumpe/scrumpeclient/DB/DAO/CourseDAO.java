/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.DB.DAO;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.scrumpe.scrumpeclient.DB.DAO.Callback.DAOCallBack;
import com.mongodb.MongoClient;
import com.scrumpe.scrumpeclient.DB.DBManager;
import com.scrumpe.scrumpeclient.DB.Entity.Course;
import com.scrumpe.scrumpeclient.DB.Entity.Question;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;

/**
 *
 * @author Max Verhoeven
 */
public class CourseDAO extends DAO<Course,String> {
    
    public CourseDAO(Class<Course> entityClass, MongoClient mongoClient, Morphia morphia, String dbName) {
        super(entityClass, mongoClient, morphia, dbName);
    }
    public void getCourse(DAOCallBack<Course> callback,String key,Object value){
      accessDB(callback,task(() -> {
          Course findOne = super.findOne("_id",value);
            return findOne;
        }));
    }
    public void getCourses(DAOCallBack<List<Course>> callback){
        accessDB(callback,taskList(() -> {
            List<Course> courseTitleAndIDS = find().asList();
            return courseTitleAndIDS;
        }));
    }
    public void getCourses(DAOCallBack<List<Course>> callback,String...excludes){
        accessDB(callback,taskList(() -> {
            Query q = createQuery();
            for (String  s : excludes) {
                q = q.project(s,false);
            }
             QueryResults<Course> find = find(q);
            List<Course> asList = find.asList();
            return asList;
        }));
    }
    public void deleteCourse(DAOCallBack<Course> callback,Course course){
        this.getCourse((o)->{
            QuestionDAO ad = DBManager.getInstance().getDAO(QuestionDAO.class);
               ad.deleteQuestions((x) -> {
                   accessDB(callback, task(()->delete(course)));
               }, o.getQuestions());
        }, "", course.getId());
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
    public void getQuestionCount(DAOCallBack<Integer> cb,ObjectId courseId){
        accessDB((o) -> {
            cb.dbResult((Integer) o);
        }, task(()->{
            Query<Course> project = createQuery().filter("_id", courseId).project("questions",true);
            DBCursor cursor = project.fetch().getCursor();
            while (cursor.hasNext()) {
                DBObject next = cursor.next();
                DBObject get = (DBObject) next.get("questions");
                System.err.println(get);
               return get.toMap().size();
            }
            return 0;
        }));
    }
        public void getQuestionIDs(DAOCallBack<List<ObjectId>> cb,ObjectId courseId){
        accessDB((o) -> {
            cb.dbResult((List<ObjectId>) o);
        }, task(()->{
            Query<Course> project = createQuery().filter("_id", courseId).project("questions",true);
            DBCursor cursor = project.fetch().getCursor();
            while (cursor.hasNext()) {
                DBObject next = cursor.next();
                DBObject get = (DBObject) next.get("questions");
                Collection<DBRef> values = get.toMap().values();
                List<ObjectId> ids = new ArrayList<>();
                for (DBRef value : values) {
                    ids.add((ObjectId) value.getId());
                }
                return ids;
            }
            return null;
        }));
    }
}
