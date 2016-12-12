/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

/**
 * class for handling assets. get it? Ass Handler. haha. no? I'll just let myself out.
 * @author MJ. Verhoeven
 */
public class AssHandler {
    public static final String ASSET_DIR = "assets\\";

    /**
     *
     * @return
     */
    public static String getRootDir(){
       return System.getProperty("user.dir")+"\\src\\";
    }
}
