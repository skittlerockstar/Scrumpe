/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scrumpe.UI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javafx.scene.paint.Color;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author MJ. Verhoeven
 */
public abstract class UIComponent extends JPanel implements MouseListener, ApplyStyle{

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

    
    
    @Override
    public void applyStyle(Component c) {
           AppTheme.gI().applyStyle(c);
           //super.add(c);
    }


    @Override
    public void mouseClicked(MouseEvent e){
        e.getComponent().setBackground(java.awt.Color.DARK_GRAY);
        System.out.println(e.getComponent().toString());
    }

    @Override
    public void mousePressed(MouseEvent e) {
      System.out.println(e.getComponent().toString());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       System.out.println(e.getComponent().toString());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      System.out.println(e.getComponent().toString());
    }

    @Override
    public void mouseExited(MouseEvent e) {
       System.out.println(e.getComponent().toString());
    }
    
    
    
    
    
}
