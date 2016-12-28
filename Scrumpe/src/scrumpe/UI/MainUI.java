/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrumpe.UI;

import Container.Content.Component.ContentHeader;
import Container.Content.Component.HeaderContent;
import java.awt.BorderLayout;
import java.awt.Component;
import Utils.CustomComponents;

/**
 *
 * @author Max Verhoeven
 */
public abstract class MainUI extends UIComponent {

    private String screenName;
    private boolean hasHeader;
    public MainUI(String headerName, boolean hasHeader) {
        setBackground(AppStyle.TRANSLUCENT);
        this.screenName = headerName;
        this.hasHeader= hasHeader;
    }


    @Override
    public void add(Component comp, Object constraints) {
        super.add(comp, constraints); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initCustomComponents(){
        if(getLayout() instanceof BorderLayout){
           if(hasHeader){
            super.add(new ContentHeader(screenName),BorderLayout.NORTH);
           }
        }
    };

}
