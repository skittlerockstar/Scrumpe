/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Container;

import Container.Content.FooterContent;
import java.awt.BorderLayout;
import java.awt.Color;
import scrumpe.UI.UIComponent;

/**
 *
 * @author MJ. Verhoeven
 */
class FooterContainer extends UIComponent {

    private FooterContent content;
    public FooterContainer() {
        super(new BorderLayout());
        content = new FooterContent();
        add(content,content.UIPos);
    }
    
}
