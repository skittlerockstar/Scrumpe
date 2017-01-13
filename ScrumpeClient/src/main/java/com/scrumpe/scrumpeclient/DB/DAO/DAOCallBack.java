/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.DB.DAO;

import java.util.List;

/**
 *
 * @author Max Verhoeven
 */
public interface DAOCallBack<T> {
    
    void dbResult(T result);
    void dbResults(List<T> results);
    
}
