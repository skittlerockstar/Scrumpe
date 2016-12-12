/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scrumpe.UI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
/**
 *  Base class for all UI Components in app
 * @author MJ. Verhoeven
 */
public abstract class UIComponent extends JPanel implements ApplyStyle{

    public String UIPos = BorderLayout.CENTER;
    private final int PTOP=5,PLEFT=5,PBOTTOM=5,PRIGHT=5;
    
    public UIComponent() {
        super( new BorderLayout());
        setBorder(new EmptyBorder(PTOP, PLEFT, PBOTTOM, PRIGHT));
    }
    
    public UIComponent(LayoutManager layout) {
        super(layout);
    }

    @Override
    public void add(Component comp, Object constraints, int index) {
        applyStyle(comp);
        super.add(comp, constraints, index); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Component comp, Object constraints) {
        applyStyle(comp);
        super.add(comp, constraints); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Component add(Component comp, int index) {
        applyStyle(comp);
        return super.add(comp, index); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Component add(String name, Component comp) {
        applyStyle(comp);
        return super.add(name, comp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Component add(Component comp) {
        applyStyle(comp);
        return super.add(comp); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param c
     */
    @Override
    public void applyStyle(Component c) {
           ThemeHandler.gI().applyStyleIn(c);
           //super.add(c);
    }

    /**
     * returns position of the childclass to fit the container
     * @return
     */
    public Object getUIPos() {
        if(getLayout() instanceof GridBagLayout == false){
            return UIPos;
        }else{
        return new GridBagConstraints();
        }
    }

    /**
     * returns the layout used by the child class to fit the container
     * @return
     */
    public LayoutManager getNewLayout(){
        try {
            return getLayout().getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            return null;
        }
    }
    
    
    
    
    
}
