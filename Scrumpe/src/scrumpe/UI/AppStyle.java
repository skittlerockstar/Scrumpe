/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scrumpe.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * static class that contains the app Styles and creates custom styles
 * @author MJ. Verhoeven
 */
public class AppStyle {
    
    public static final Insets NO_PAD = new Insets(0, 0, 0, 0);
    public static final Insets DEF_PAD = new Insets(5, 5, 5, 5);
    public static final Color MAIN_COLOR_LIGHT = new Color(206, 225, 255);
    public static final Color MAIN_COLOR_DARK = new Color(99, 159, 255);
    public static final Color TEXT_COLOR_LIGHT = new Color(99, 159, 255);
    public static final Color TEXT_COLOR_DARK = new Color(0.3f,0.3f,0.3f,1.0f);
     public static final Color BG_COLOR_LIGHT = new Color(234, 234, 234);
    public static final Color BG_COLOR_DARK = new Color(0.5f,0.5f,0.5f,1.0f);
    public static final Color TRANSLUCENT = new Color(0,0,0,0);
    
    public static final String TEXT_FONT = "Segoe UI";
    public static final int FONT_STYLE = Font.PLAIN;
    
    public static final Color BUTTON_BG = Color.white;
    public static final Color BUTTON_HOVER_BG = MAIN_COLOR_LIGHT;
    private static final Color BUTTON_BORDER_COLOR = MAIN_COLOR_DARK;
    
    public static final int DPLeft=5,DPRight=5,DPTop=5,DPBottom=5;
    public static enum BorderPos{INSIDE,OUTSIDE};
    public static final Border border1 = BorderFactory.createLineBorder(MAIN_COLOR_DARK);
    
    /**
     *
     * @return
     */
    public static Border createThemeBorder(){
     return createThemeBorder(BorderPos.OUTSIDE);
    }
    public static Border createThemeBorder(BorderPos borderPos){
       return createThemeBorder(borderPos, new Insets(DPTop, DPLeft, DPBottom, DPRight));
    }
    public static Border createThemeBorder(BorderPos borderPos,Insets padding){
        Color c = Color.lightGray;
        return createThemeBorder(borderPos,padding,c);
    }
     public static Border createThemeBorder(BorderPos borderPos,Insets padding,Color color){
        Border border2 = BorderFactory.createEmptyBorder(padding.top, padding.left, padding.bottom, padding.right);
        Border border1 = BorderFactory.createLineBorder(color);
        if(borderPos == borderPos.INSIDE){
            return BorderFactory.createCompoundBorder(border2, border1);
        }
        else{
            return BorderFactory.createCompoundBorder(border1, border2);
        }
    }
    public static Border createPadding(){
        return BorderFactory.createEmptyBorder(DPTop,DPLeft,DPBottom,DPRight);
    }
    public static Border createPadding(int top,int left,int bottom,int right){
        return BorderFactory.createEmptyBorder(top,left,bottom,right);
    }
}
