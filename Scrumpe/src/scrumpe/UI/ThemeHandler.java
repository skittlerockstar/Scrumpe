/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrumpe.UI;

import Container.Content.MainScreen;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * used to apply themes to UI components
 * TODO Needs cleaning
 * @author MJ. Verhoeven
 */
class ThemeHandler implements MouseListener {

    /**
     * All UI components in SWING for checking components
     */
    private enum CLAZZ {
        JViewport,ScrollBar, JPanel, JTabbedPane, JSplitPane, JScrollPane, JToolBar, JDesktopPane, JInternalFrame, JLayeredPane, JLabel, JButton, JToggleButton, JCheckBox, JRadioButton, ButtonGroup, JComboBox, JList, JTextField, JTextArea, JScrollBar, JSlider, JProgressBar, JFormattedTextField, JPasswordField, JSpinner, JSeparator, JTextPane, JEditorPane, JTree, JTable, JMenuBar, JMenu, JMenuItem, JCheckBoxMenuItem, JRadioButtonMenuItem, JPopupMenu, JDialog, JFrame, JColorChooser, JFileChooser, JOptionPane
    }
    private static ThemeHandler instance; //singleton

    /**
     * get singleton instance
     * @return 
     */
    public static ThemeHandler gI() {
        if (instance == null) 
            instance = new ThemeHandler(); 
        return instance;
    }

    private ThemeHandler() {}

    
    /**
     * Walk through container and apply style on any UI Component
     * @param container 
     */
    public void applyStyleIn(Component container) {
//        if(container instanceof UIComponent){
//            return;
//        }
//        if (container instanceof JPanel || container instanceof JScrollPane || container instanceof JViewport) {
//            Container cont = (Container) container;
//             setPaneStyle(container);
//            for (Component x : cont.getComponents()) {
//                applyStyleIn(x);
//            }
//        } else {
//            applyCompStyle(container);
//        }
    }

    /**
     * Apply Style to any Component
     * @param compToDecorate 
     */
    private void applyCompStyle(Component compToDecorate) {
        CLAZZ type = CLAZZ.valueOf(compToDecorate.getClass().getSimpleName());
        switch (type) {
            case JButton:
                styleButton((JButton)compToDecorate);
                compToDecorate.addMouseListener(this);
                break;
            case ScrollBar:
                setScrollBarStyle((JScrollBar)compToDecorate);
                break;
            default:
                
                break;
        }
        setDefaults(compToDecorate);
    }

    private void setButtonHoverStyle(Component c) {
        c.setBackground(AppStyle.BUTTON_HOVER_BG);
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        if (e.getComponent() instanceof JButton) {
            setButtonHoverStyle(e.getComponent());
        }
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        if (e.getComponent() instanceof JButton) {
            styleButton((JButton)e.getComponent());
        }
    }

    private void styleButton(JButton c) {
       // c.setBackground(AppStyle.BUTTON_BG);
//        UIManager.put("Button.background", Color.red);
        
        c.setBorder(AppStyle.createThemeBorder(AppStyle.BorderPos.OUTSIDE,AppStyle.DEF_PAD,AppStyle.MAIN_COLOR_DARK));
        c.setFocusPainted(false);
    }

    private void setFont(Component c) {
        int size = c.getFont().getSize();
        if(size > 20){
            c.setForeground(AppStyle.MAIN_COLOR_DARK);
        }else{
                 c.setForeground(AppStyle.TEXT_COLOR_DARK);
        }
        c.setFont(new Font(AppStyle.TEXT_FONT, AppStyle.FONT_STYLE, size));
    }

    private void setDefaults(Component c) {
        setFont(c);
    }
    private void setDefaultBorder(Component c) {
        if(c instanceof JComponent){
            JComponent j = (JComponent) c;
            j.setBorder(AppStyle.createThemeBorder());
        }
    }
     private void setPaneStyle(Component container) {
    }

    private void setScrollBarStyle(JScrollBar jScrollBar) {
        jScrollBar.setBackground(Color.white);
        jScrollBar.setPreferredSize(new Dimension(15, jScrollBar.getPreferredSize().height));
        jScrollBar.setBorder(BorderFactory.createEmptyBorder());
       Component[] asdf= jScrollBar.getComponents();
       for(Component k : asdf){
           System.err.println(k.getClass().getSimpleName());
       }
        
    }

}
