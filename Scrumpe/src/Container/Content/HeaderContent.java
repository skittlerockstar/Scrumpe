/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Container.Content;

import Container.Content.Component.Logo;
import java.awt.Color;
import scrumpe.UI.UIComponent;

/**
 * Content of the footer - Swing design file
 * @author MJ. Verhoeven
 */
public class HeaderContent extends UIComponent {

    private Logo logo; // used for logo
    public HeaderContent() {
        super();
        logo = new Logo(Logo.Location.CENTER);
        initComponents();
        jLabel2.setForeground(new Color(99, 159, 255));
        System.out.println(jLabel1.getClass().getSimpleName());
        setPos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel2.setText("Scrumpe");
        add(jLabel2, new java.awt.GridBagConstraints());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("A Scrum Practice Exam Application");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(jLabel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

    private void setPos() {
    }

    @Override
    public void applyCustomStyle() {
    }

}
