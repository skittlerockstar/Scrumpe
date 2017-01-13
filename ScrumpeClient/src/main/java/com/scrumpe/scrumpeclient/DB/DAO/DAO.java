/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.DB.DAO;

import com.mongodb.MongoClient;
import com.scrumpe.scrumpeclient.Screen.Utils.ScreenManager;
import java.util.List;
import java.util.concurrent.Callable;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

/**
 *
 * @author Max Verhoeven
 * 
 */
public abstract class DAO<T,K> extends BasicDAO<T,K> implements EventHandler<WorkerStateEvent>{
    DAOCallBack current;
    Task currentTask;
    public DAO(Class<T> entityClass, MongoClient mongoClient, Morphia morphia, String dbName) {
        super(entityClass, mongoClient, morphia, dbName);
    }

    @Override
    public void handle(WorkerStateEvent event) {
        ScreenManager.getInstance().showLoadingScreen(false);
        Object x = currentTask.getValue();
        if(x instanceof List){
            current.dbResults((List) currentTask.getValue());
        }else{
            current.dbResult(currentTask.getValue());
        }
    }
    public void accessDB(DAOCallBack source, Task task){
        current = source;
        currentTask = task;
        task.setOnSucceeded(this);
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }
    
    public <T> Task<T> task(Callable<T> callable) {
        return new Task<T>() {
            @Override
            public T call() throws Exception {
                return callable.call();
            }
        };
    }
    public <T> Task<List<T>> taskList(Callable<T> callable) {
        return new Task<List<T>>() {
            @Override
            public List<T> call() throws Exception {
                return (List<T>)callable.call();
            }
        };
    }
}
