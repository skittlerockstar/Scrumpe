/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.DB.Entity;

import java.io.Serializable;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author Max Verhoeven
 */

public class Record  extends BaseEntity  {
    
    @Reference
    private ObjectId userId;
    @Reference
    private ObjectId courseId;
    @Property("courseVersion")
    private int courseVersion;
    @Property("didFinnish")
    private boolean finished;
    @Property("minScore")
    private int minScore;
    @Property("score")
    private int score;

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getMinScore() {
        return minScore;
    }

    public void setMinScore(int minScore) {
        this.minScore = minScore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public ObjectId getCourseId() {
        return courseId;
    }

    public void setCourseId(ObjectId courseId) {
        this.courseId = courseId;
    }

    public int getCourseVersion() {
        return courseVersion;
    }

    public void setCourseVersion(int courseVersion) {
        this.courseVersion = courseVersion;
    }
    
}
