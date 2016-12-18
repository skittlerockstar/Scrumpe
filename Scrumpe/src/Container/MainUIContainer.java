/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Container;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import scrumpe.UI.AppStyle;
import scrumpe.UI.UIComponent;

/**
 * The container in the mainframe which contains the header, main and footer container.
 * @author MJ. Verhoeven
 */
public class MainUIContainer extends UIComponent {

    private UIComponent UIHeader,UIMain,UIFooter;
    private JPanel spaceHolderL,spaceHolderR;
    public MainUIContainer() {
        super(new BorderLayout());
        createContent();
        setSpaceHolder();
        super.setBackground(AppStyle.BG_COLOR_DARK);
    }
    
    /**
     * add 3 main components : header, main and footer.
     */
    private void createContent() {
        UIHeader = new HeaderContainer();
        UIMain = new MainContainer();
        UIFooter = new FooterContainer();

        add(UIHeader, BorderLayout.PAGE_START);
        add(UIMain, BorderLayout.CENTER);
        add(UIFooter, BorderLayout.PAGE_END);
    }

    /**
     * sidebars to compensate space.
     * TODO need to make this responsive
     */
    private void setSpaceHolder() {
        spaceHolderL = new JPanel();
        spaceHolderR = new JPanel();

        spaceHolderL.setPreferredSize(new Dimension(100, getPreferredSize().height));
        spaceHolderR.setPreferredSize(new Dimension(100, getPreferredSize().height));

        add(spaceHolderL, BorderLayout.LINE_START);
        add(spaceHolderR, BorderLayout.LINE_END);

        spaceHolderL.setBackground(AppStyle.TRANSLUCENT);
        spaceHolderR.setBackground(AppStyle.TRANSLUCENT);
    }

    @Override
    public void applyCustomStyle() {
    }
}
