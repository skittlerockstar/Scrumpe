/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Screen.Base;

import com.scrumpe.scrumpeclient.DB.DAO.UserDAO;
import javafx.scene.Node;

/**
 *
 * @author Max Verhoeven
 */
public abstract class ComponentBase extends UIComponent {

    public void setAdminComps() {
        if (UserDAO.getLoggedInUser() != null) {
            if (UserDAO.getLoggedInUser().isIsAdmin()) {
                if (this instanceof AdminComponents) {
                    ((AdminComponents) this).setAdminParts();
                }
            }
        }
    }
}
