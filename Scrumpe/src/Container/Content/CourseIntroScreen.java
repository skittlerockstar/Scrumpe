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
import scrumpe.UI.MainUI;
import scrumpe.UI.UIComponent;
import scrumpe.UI.WrapLayout;
import sun.misc.Contended;

/**
 *
 * @author Max Verhoeven
 */
public class CourseIntroScreen extends MainUI {

    private String descriptionText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum a tempus urna. Mauris faucibus, arcu eu pellentesque sodales, odio tortor ullamcorper enim, sed sollicitudin ligula mauris quis nisi. Aenean feugiat neque leo, quis tempus massa pretium eu. Vivamus felis sapien, scelerisque ac massa maximus, laoreet cursus ex. Ut posuere nibh vel pellentesque tempus. Cras non iaculis odio. Vestibulum vel accumsan enim, a rutrum massa.";
    /**
     * Creates new form Course
     */
    public CourseIntroScreen() {
       super("Course info",true);
        initComponents();
        super.initCustomComponents();
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

        bodyContainer = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        bodyContainer.setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(jLabel1, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        bodyContainer.add(jPanel2, gridBagConstraints);

        jButton1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jButton1.setText("Start Course!");
        jButton1.setMargin(new java.awt.Insets(40, 100, 40, 100));
        jPanel1.add(jButton1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        bodyContainer.add(jPanel1, gridBagConstraints);

        add(bodyContainer, java.awt.BorderLayout.CENTER);
        bodyContainer.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyContainer;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
