/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Container;

import Container.Content.HeaderContent;
import java.awt.BorderLayout;
import java.awt.Color;
import scrumpe.UI.UIContainer;

/**
 *
 * @author MJ. Verhoeven
 */
class HeaderContainer extends UIContainer {

    private HeaderContent content;
    public HeaderContainer() {
        super(new BorderLayout());
        content = new HeaderContent();
        add(content,content.UIPos);
    }
    
}
