/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Container;

import java.awt.BorderLayout;
import scrumpe.UI.UIContainer;

/**
 *
 * @author MJ. Verhoeven
 */
public class MainUIContainer extends UIContainer{
    
    public UIContainer UIHeader,UIMain,UIFooter;
    
    public MainUIContainer() {
        super(new BorderLayout());
        createContent();
    }

    private void createContent() {
        UIHeader = new HeaderContainer();
        UIMain = new MainContainer();
        UIFooter = new FooterContainer();
        
        add(UIHeader,BorderLayout.PAGE_START);
        add(UIMain,BorderLayout.CENTER);
        add(UIFooter,BorderLayout.PAGE_END);
    }
    
}
