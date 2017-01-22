/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Utils;

import com.scrumpe.scrumpeclient.MainApp;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lesscss.LessCompiler;
import org.lesscss.LessException;

/**
 *
 * @author IMXNotASPider
 */
public class LessRT {

    private static String ROOT = "../../../";
    private static final String LESS_SOURCE = "styles/less/";
    private static final String CSS_SOURCE = "styles/css/";
    static File lessFile;
    static File cssFile;

    public static void Setup(Class source) {
        try {
            URL l = source.getResource(ROOT + LESS_SOURCE + "mainStyle.less");
            URL l2 = source.getResource(ROOT + CSS_SOURCE + "mainStyle.css");
            URI f = l.toURI();
            URI f2 = l2.toURI();
            lessFile = new File(f.getPath());
            cssFile = new File(f2.getPath());
           
        } catch (URISyntaxException ex) {
            Logger.getLogger(LessRT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void compileShit(){
         try {
             LessCompiler less = new LessCompiler();
                less.compile(lessFile, cssFile);
                MainApp.getRootStage().hide();
                MainApp.getRootStage().show();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            } catch (LessException ex) {
                System.out.println(ex.toString());
            }
    }
    public static void replaceShit() throws IOException {
        Path path = Paths.get(lessFile.getPath());
        Charset charset = StandardCharsets.UTF_8;

        String content = new String(Files.readAllBytes(path), charset);
        content = content.replaceAll("red", "blue");
        Files.write(path, content.getBytes(charset));
        compileShit();
    }

}
