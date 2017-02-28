/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Utils;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.lesscss.deps.org.apache.commons.io.IOUtils;

/**
 *
 * @author Max Verhoeven
 */
public class IPLookup {

    public static boolean isLocal() {
        Runtime rt = Runtime.getRuntime();
        String hostname = tryGetHostName(rt);
        String hasWifi = tryGetSSID(rt);
        if(hostname.contains("SRV-TS01")){
            return true;
        }
        else if (hasWifi.toLowerCase().contains("connected") && hasWifi.toLowerCase().contains("squerist")) {
            return true;
        }else{
            return false;
        }
            
    }

    private static String tryGetHostName(Runtime rt) {
        try {
            Process exec = rt.exec(new String[]{"cmd.exe", "/c", "hostname"});
            InputStream inputStream = exec.getInputStream();
            String inputStreamString = new Scanner(inputStream,"UTF-8").useDelimiter("\\A").next();
            return inputStreamString;
        } catch (IOException e) {}
       return "";
    }

    private static String tryGetSSID(Runtime rt) {
    try {
            Process exec = rt.exec(new String[]{"cmd.exe", "/c", "netsh wlan show interfaces"});
            InputStream inputStream = exec.getInputStream();
            String toString = IOUtils.toString(inputStream);
            return toString;
        } catch (IOException e) {}
        finally{
            return "";
        }
    }

}
