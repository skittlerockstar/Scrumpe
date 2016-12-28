/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 *
 * @author Max Verhoeven
 */
public abstract class UIComponent implements Initializable{
     protected Pane componentRoot;
    public abstract void setupLayout();
    public void setup(Node current){
        componentRoot = (Pane) current;
        setupLayout();
    }
}
