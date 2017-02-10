/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.DB.DAO.Callback;

import java.util.List;

/**
 *
 * @author Max Verhoeven
 */
public interface DAOCallBack<T extends Object>{
    void dbResult(T o);
}
