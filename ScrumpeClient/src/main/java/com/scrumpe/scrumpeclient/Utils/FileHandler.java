/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Max Verhoeven
 */
public class FileHandler {

    public static void convertStreamToFile(InputStream is, File file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
        String line = null;
        while ((line = reader.readLine()) != null) {
            fileWriter.write(line + "\n");
        }
        fileWriter.flush();
        fileWriter.close();
        is.close();

    }
}
