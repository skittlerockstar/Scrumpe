/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 * Settings for the application e.g. anti-aliasing
 * @author MJ. Verhoeven
 */
public class SystemSettings {
    /**
     * Turn on anti-aliasing for fonts
     * TODO create more settings
     */
    public static void setSettings() {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
    }
}
