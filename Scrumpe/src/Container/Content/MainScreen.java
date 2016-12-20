
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Container.Content;

import Container.Content.Component.Admin.UserList;
import Container.Content.Component.ContentHeader;
import Container.Content.Component.Course;
import Container.Content.Component.IContentHeader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ScrollPaneConstants;
import scrumpe.UI.AppStyle;
import scrumpe.UI.UIComponent;
import scrumpe.UI.WrapLayout;
/**
 * The mainscreen after the login page
 * @author MJ. Verhoeven
 */
public class MainScreen extends UIComponent implements IContentHeader{

    List<Course> courses = new ArrayList<>(); 
    int courseCollumns = 4;
    public MainScreen() {
        super();
        initComponents();
        jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        addContentHeader();
        addCourses();
        jScrollPane1.setViewportBorder(AppStyle.createPadding(0, 5, 0, 5));
        jScrollPane1.setBorder(AppStyle.createPadding());
        addAdminPanel();
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
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        courseContainer = new javax.swing.JPanel();

        setBackground(AppStyle.TRANSLUCENT);
        setLayout(new java.awt.BorderLayout());

        bodyContainer.setBackground(AppStyle.BG_COLOR_LIGHT);
        bodyContainer.setLayout(new java.awt.BorderLayout());

        descriptionContainer.setLayout(new javax.swing.BoxLayout(descriptionContainer, javax.swing.BoxLayout.LINE_AXIS));

        jLabel2.setText("<html>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum a tempus urna. Mauris faucibus, arcu eu pellentesque sodales, odio tortor ullamcorper enim, sed sollicitudin ligula mauris quis nisi. Aenean feugiat neque leo, quis tempus massa pretium eu. Vivamus felis sapien, scelerisque ac massa maximus, laoreet cursus ex. Ut posuere nibh vel pellentesque tempus. Cras non iaculis odio. Vestibulum vel accumsan enim, a rutrum massa.</html>");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jLabel2.setName("test"); // NOI18N
        descriptionContainer.add(jLabel2);
        jLabel2.getAccessibleContext().setAccessibleDescription("");

        bodyContainer.add(descriptionContainer, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setBorder(null);
        jScrollPane1.setViewportView(courseContainer);

        bodyContainer.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(bodyContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyContainer;
    private javax.swing.JPanel courseContainer;
    private javax.swing.JPanel descriptionContainer;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private void addCourses() {
        for (int i = 0; i < 40; i++) {
            Course c = new Course("Random course");
            courses.add(c);
        }
        courseContainer.setLayout(new WrapLayout());
        for(Course x:courses){
            x.setLayout(new WrapLayout());
            courseContainer.add(x);
            super.applyStyle(x);
        }
    }

    private void addAdminPanel() {
        bodyContainer.add(new UserList(),BorderLayout.LINE_END);
    }

    @Override
    public void applyCustomStyle() {
        
        descriptionContainer.setBorder(AppStyle.createThemeBorder(AppStyle.BorderPos.INSIDE));
        jLabel2.setOpaque(true);
        jLabel2.setBackground(Color.white);
        System.out.println(jLabel2.toString());
    }

    @Override
    public void addContentHeader() {
          add(new ContentHeader("Home"),BorderLayout.PAGE_START);
    }

    
}
