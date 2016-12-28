/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Utils;

import javafx.fxml.FXMLLoader;

/**
 *
 * @author Max Verhoeven
 */
public class NodeUtil {
    public static Object getClassFrom(FXMLLoader node){
        FXMLLoader l = new FXMLLoader(node.getLocation());
        return  l.getController();
    }
}
