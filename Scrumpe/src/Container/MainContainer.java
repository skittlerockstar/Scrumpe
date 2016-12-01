/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Container;

import Container.Content.LoginScreen;
import Container.Content.MainScreen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.border.Border;
import scrumpe.UI.AppStyle;
import scrumpe.UI.UIComponent;

/**
 *
 * @author MJ. Verhoeven
 */
class MainContainer extends UIComponent {

    public MainContainer() {
        super(new GridBagLayout());
        setBackground(AppStyle.TRANSLUCENT);
        LoginScreen ls = new LoginScreen();
        add(ls,new GridBagConstraints());
        
//        setLayout(new BorderLayout());
//        MainScreen s = new MainScreen();
//        add(s,BorderLayout.CENTER);
//        s.setBackground(new Color(0.7f, 0.7f, 0.7f, 0.0f));
       
        
    }
    
}
