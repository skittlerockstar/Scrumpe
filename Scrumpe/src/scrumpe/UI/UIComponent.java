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
        super();
        setBorder(new EmptyBorder(PTOP, PLEFT, PBOTTOM, PRIGHT));
       
    }
    
    public UIComponent(LayoutManager layout) {
        super(layout);
    }

    
//    In de Abstract class
    @Override
    public Component add(Component c) {
        //applyStyle(c);
        
        return super.add(c);
    }

    @Override
    public void applyStyle(Component c) {
        //  AppTheme.applyStyle(c);
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
