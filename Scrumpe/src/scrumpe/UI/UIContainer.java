/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scrumpe.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author MJ. Verhoeven
 */
public abstract class UIContainer extends JPanel implements MouseListener{

    public String UIPos = BorderLayout.CENTER;
    public UIContainer() {
        super();
        setBorder(new EmptyBorder(10, 10, 10, 10)); 
        addMouseListener(this);
    }
    public UIContainer(LayoutManager ui) {
        super(ui);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       e.getComponent().setBackground(Color.MAGENTA);
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