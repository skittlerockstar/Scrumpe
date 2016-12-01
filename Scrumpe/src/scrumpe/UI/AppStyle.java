/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scrumpe.UI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author MJ. Verhoeven
 */
public class AppStyle {
    
    public static final Color MAIN_COLOR_LIGHT = new Color(206, 225, 255);
    public static final Color MAIN_COLOR_DARK = new Color(99, 159, 255);
    public static final Color TEXT_COLOR_LIGHT = new Color(99, 159, 255);
    public static final Color TEXT_COLOR_DARK = new Color(0.5f,0.5f,0.5f,1.0f);
     public static final Color BG_COLOR_LIGHT = new Color(234, 234, 234);
    public static final Color BG_COLOR_DARK = new Color(0.5f,0.5f,0.5f,1.0f);
    public static final Color TRANSLUCENT = new Color(0,0,0,0);
    
    public static final String TEXT_FONT = "Segoe UI";
    public static final int FONT_STYLE = Font.PLAIN;
    
    public static final Color BUTTON_BG = Color.white;
    public static final Color BUTTON_HOVER_BG = MAIN_COLOR_LIGHT;
    private static final Color BUTTON_BORDER_COLOR = MAIN_COLOR_DARK;
    public static final Border BUTTON_BORDER = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(BUTTON_BORDER_COLOR),BorderFactory.createEmptyBorder(5,15,5,15));
    
  public static Border createThemeBorder(){
     return BorderFactory.createCompoundBorder( 
                BorderFactory.createLineBorder(new Color(66, 134, 244 ,200), 1, true)
                ,BorderFactory.createEmptyBorder(5,5,5,5)
            );
  }
}
