/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lesscss.LessCompiler;
import org.lesscss.LessException;
import org.lesscss.deps.org.apache.commons.io.FileUtils;

/**
 *
 * @author IMXNotASPider
 */
public class LessRT {

    private static String ROOT;
    private static final String LESS_SOURCE = "styles\\less\\";
    private static final String CSS_SOURCE = "styles\\css\\";

    public static void LoadLess(Class source) {
       LessCompiler less = new LessCompiler();
       URL l =source.getResource("");
       Log.log(source, Level.SEVERE, l.getPath());

    }
}
