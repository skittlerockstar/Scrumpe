/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Container.Content;

import Container.Content.Component.ContentHeader;
import java.awt.BorderLayout;
import static java.awt.SystemColor.text;
import scrumpe.UI.AppStyle;
import scrumpe.UI.UIComponent;
import scrumpe.UI.WrapLayout;
import sun.misc.Contended;

/**
 *
 * @author Max Verhoeven
 */
public class Course extends UIComponent {

    private String descriptionText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum a tempus urna. Mauris faucibus, arcu eu pellentesque sodales, odio tortor ullamcorper enim, sed sollicitudin ligula mauris quis nisi. Aenean feugiat neque leo, quis tempus massa pretium eu. Vivamus felis sapien, scelerisque ac massa maximus, laoreet cursus ex. Ut posuere nibh vel pellentesque tempus. Cras non iaculis odio. Vestibulum vel accumsan enim, a rutrum massa.";
    /**
     * Creates new form Course
     */
    public Course() {
        initComponents();
        addContentHeader();
        setBackground(AppStyle.TRANSLUCENT);
        description.setText(String.format("<html><div style=\"width:500px; padding:40px\">%s</div><html>", descriptionText));
        //descriptionContainer.setLayout(new WrapLayout(WrapLayout.CENTER));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bodyContainer = new javax.swing.JPanel();
        descriptionContainer = new javax.swing.JPanel();
        description = new javax.swing.JLabel();
        courseContainer = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        bodyContainer.setBackground(AppStyle.BG_COLOR_LIGHT);
        bodyContainer.setLayout(new java.awt.BorderLayout());

        descriptionContainer.setMaximumSize(new java.awt.Dimension(500, 500));
        descriptionContainer.setMinimumSize(new java.awt.Dimension(500, 500));
        descriptionContainer.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 30, 5));

        description.setText("jLable");
        descriptionContainer.add(description);

        bodyContainer.add(descriptionContainer, java.awt.BorderLayout.NORTH);

        jButton1.setText("Start Course");
        jButton1.setAlignmentX(0.5F);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        courseContainer.add(jButton1);

        bodyContainer.add(courseContainer, java.awt.BorderLayout.CENTER);

        add(bodyContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyContainer;
    private javax.swing.JPanel courseContainer;
    private javax.swing.JLabel description;
    private javax.swing.JPanel descriptionContainer;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void applyCustomStyle() {
    }

    private void addContentHeader() {
        add(new ContentHeader("Course X"),BorderLayout.NORTH);
    }
}