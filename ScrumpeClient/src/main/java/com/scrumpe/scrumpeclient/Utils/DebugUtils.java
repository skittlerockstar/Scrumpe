/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Max Verhoeven
 */
public class DebugUtils {
    public static void log(String message){ 
        Logger.getLogger(DebugUtils.class.getName()).log(Level.SEVERE, message);
    }
}
