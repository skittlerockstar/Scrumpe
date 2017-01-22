/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Max Verhoeven
 */
public class Lang {
    public static String currentLang = "en";
    public static final String LANG_BUNDLE = "strings.lang";
    
    public static ResourceBundle getLangSet(){
        return getLangSet(currentLang);
    }

    private static ResourceBundle getLangSet(String lang) {
        currentLang = lang;
        return ResourceBundle.getBundle(LANG_BUNDLE, new Locale(lang));
    }
}
