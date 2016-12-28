/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Container;

import Container.Content.Component.FooterContent;
import java.awt.BorderLayout;
import scrumpe.UI.UIComponent;

/**
 * Footer in the UIContainer 
 * @author MJ. Verhoeven
 */
class FooterContainer extends UIComponent {
    private FooterContent content;
    public FooterContainer() {
        super(new BorderLayout());
        content = new FooterContent();
        add(content,content.UIPos);
    }

    @Override
    public void initCustomComponents() {
    }
    
}
