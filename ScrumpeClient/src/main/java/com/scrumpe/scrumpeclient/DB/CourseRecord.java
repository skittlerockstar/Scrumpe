/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.DB;
import java.io.Serializable;
import java.util.List;


/**
 *
 * @author Max Verhoeven
 */

public class CourseRecord implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private List<Answer> givenAnswers;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CourseRecord)) {
            return false;
        }
        CourseRecord other = (CourseRecord) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataComponents.CourseRecord[ id=" + id + " ]";
    }
    
}
