/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Container;

import Container.Content.Component.Logo;
import Container.Content.HeaderContent;
import java.awt.BorderLayout;
import scrumpe.UI.UIComponent;

/**
 * Header in the UIContainer
 * @author MJ. Verhoeven
 */
class HeaderContainer extends UIComponent {

    private HeaderContent content;
    public HeaderContainer() {
        super(new BorderLayout());
        content = new HeaderContent();
        Logo l = new Logo();
        
        add(content,content.UIPos);
    }
    
    
}
