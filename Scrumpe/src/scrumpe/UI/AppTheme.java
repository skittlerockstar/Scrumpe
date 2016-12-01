/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrumpe.UI;

import Utils.TypeHandler;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.RenderingHints;
import java.awt.color.ColorSpace;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.w3c.dom.events.MouseEvent;
import static scrumpe.Scrumpe.logAllComponents;

/**
 *
 * @author MJ. Verhoeven
 */
class AppTheme implements MouseListener {

    public enum CLAZZ {

        JPanel, JTabbedPane, JSplitPane, JScrollPane, JToolBar, JDesktopPane, JInternalFrame, JLayeredPane, JLabel, JButton, JToggleButton, JCheckBox, JRadioButton, ButtonGroup, JComboBox, JList, JTextField, JTextArea, JScrollBar, JSlider, JProgressBar, JFormattedTextField, JPasswordField, JSpinner, JSeparator, JTextPane, JEditorPane, JTree, JTable, JMenuBar, JMenu, JMenuItem, JCheckBoxMenuItem, JRadioButtonMenuItem, JPopupMenu, JDialog, JFrame, JColorChooser, JFileChooser, JOptionPane
    }
    private static AppTheme instance;

    public static AppTheme gI() {
        if (instance == null) {
            instance = new AppTheme();
        }
        return instance;
    }

    private AppTheme() {

    }

    public void applyStyle(Component c) {
        if (c instanceof JPanel) {
            Container cont = (Container) c;
            System.out.println(c.toString());
            for (Component x : cont.getComponents()) {
                applyStyle(x);
            }
        } else {
            applyCompStyle(c);
        }
    }

    private void applyCompStyle(Component c) {
        CLAZZ type = CLAZZ.valueOf(c.getClass().getSimpleName());
        switch (type) {
            case JButton:
                styleButton((JButton)c);
                c.addMouseListener(this);
            default:
                setDefaults(c);
                break;
        }
    }

    private void setButtonStyle(Component c) {
    
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
        c.setBackground(AppStyle.BUTTON_BG);
        c.setBorder(AppStyle.BUTTON_BORDER);
        c.setFocusPainted(false);
    }

    private void setFont(Component c) {
        int size = c.getFont().getSize();
        if(size > 20){
            c.setForeground(AppStyle.MAIN_COLOR_DARK);
        }
        c.setFont(new Font(AppStyle.TEXT_FONT, AppStyle.FONT_STYLE, size));
    }

    private void setDefaults(Component c) {
        setFont(c);
    }

}
