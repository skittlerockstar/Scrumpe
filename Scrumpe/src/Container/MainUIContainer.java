/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Container;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import scrumpe.Scrumpe;
import scrumpe.UI.AppStyle;
import scrumpe.UI.UIComponent;

/**
 *
 * @author MJ. Verhoeven
 */
public class MainUIContainer extends UIComponent {

    public UIComponent UIHeader, UIMain, UIFooter;
    private JPanel spaceHolderL,spaceHolderR;
    public MainUIContainer() {
        super(new BorderLayout());
        createContent();
        setSpaceHolder();
        super.setBackground(AppStyle.BG_COLOR_DARK);
    }

    private void createContent() {
        UIHeader = new HeaderContainer();
        UIMain = new MainContainer();
        UIFooter = new FooterContainer();

        add(UIHeader, BorderLayout.PAGE_START);
        add(UIMain, BorderLayout.CENTER);
        add(UIFooter, BorderLayout.PAGE_END);
    }

    private void setSpaceHolder() {
        spaceHolderL = new JPanel();
        spaceHolderR = new JPanel();

        spaceHolderL.setPreferredSize(new Dimension(200, getPreferredSize().height));
        spaceHolderR.setPreferredSize(new Dimension(200, getPreferredSize().height));

        add(spaceHolderL, BorderLayout.LINE_START);
        add(spaceHolderR, BorderLayout.LINE_END);

        spaceHolderL.setBackground(AppStyle.TRANSLUCENT);
        spaceHolderR.setBackground(AppStyle.TRANSLUCENT);
    }
}
