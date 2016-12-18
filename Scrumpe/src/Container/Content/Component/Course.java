/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Container.Content.Component;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.BorderFactory;
import scrumpe.UI.AppStyle;
import scrumpe.UI.UIComponent;

/**
 *
 * @author MJ. Verhoeven
 */
public class Course  extends UIComponent {

    private String courseName = "CourseTitle";
    private String finishedText = "Passed",notStartedText = "Not yet started",notFinishedText = "Not finnished";
    private String courseStatusText = "Course status:";
    /**
     * Creates new form Course
     */
    public Course(String title) {
        super(new FlowLayout(FlowLayout.CENTER, 0, 0));
        initComponents();
        courseStatus.setText(courseStatusText);
        courseScore.setText("");
        courseTitle.setText(courseName);
        didComplete.setText(notStartedText);
        //setBackground(Color.red);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        courseInnerContainer = new javax.swing.JPanel();
        courseTitleContainer = new javax.swing.JPanel();
        courseTitle = new javax.swing.JLabel();
        courseInfoContainer = new javax.swing.JPanel();
        courseStatus = new javax.swing.JLabel();
        courseScore = new javax.swing.JLabel();
        didComplete = new javax.swing.JLabel();
        CourseControlls = new javax.swing.JPanel();
        startCourse = new javax.swing.JButton();
        editCourse = new javax.swing.JButton();
        deleteCourse = new javax.swing.JButton();

        courseInnerContainer.setLayout(new javax.swing.BoxLayout(courseInnerContainer, javax.swing.BoxLayout.Y_AXIS));

        courseTitleContainer.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        courseTitle.setText("jLabel1");
        courseTitleContainer.add(courseTitle);
        courseTitle.getAccessibleContext().setAccessibleDescription("");

        courseInnerContainer.add(courseTitleContainer);

        courseInfoContainer.setLayout(new java.awt.GridLayout(2, 2));

        courseStatus.setText("jLabel1");
        courseInfoContainer.add(courseStatus);

        courseScore.setText("jLabel1");
        courseInfoContainer.add(courseScore);

        didComplete.setText("jLabel1");
        courseInfoContainer.add(didComplete);

        courseInnerContainer.add(courseInfoContainer);

        startCourse.setText("jButton1");
        CourseControlls.add(startCourse);

        editCourse.setText("jButton1");
        CourseControlls.add(editCourse);

        deleteCourse.setText("jButton1");
        CourseControlls.add(deleteCourse);

        courseInnerContainer.add(CourseControlls);

        add(courseInnerContainer);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CourseControlls;
    private javax.swing.JPanel courseInfoContainer;
    private javax.swing.JPanel courseInnerContainer;
    private javax.swing.JLabel courseScore;
    private javax.swing.JLabel courseStatus;
    private javax.swing.JLabel courseTitle;
    private javax.swing.JPanel courseTitleContainer;
    private javax.swing.JButton deleteCourse;
    private javax.swing.JLabel didComplete;
    private javax.swing.JButton editCourse;
    private javax.swing.JButton startCourse;
    // End of variables declaration//GEN-END:variables

    @Override
    public void applyCustomStyle() {
        setBackground(Color.white);
        //TODO figure out why gaps are still 5 after constructor
        setBorder(AppStyle.createThemeBorder());
        courseInfoContainer.setBorder(AppStyle.createPadding());
        courseInnerContainer.setBackground(AppStyle.TRANSLUCENT);
        courseInfoContainer.setBackground(AppStyle.TRANSLUCENT);
        courseTitleContainer.setBackground(AppStyle.TRANSLUCENT);
        CourseControlls.setBackground(AppStyle.TRANSLUCENT);
        courseTitle.setFont(new Font(AppStyle.TEXT_FONT, AppStyle.FONT_STYLE, 20));
    }
    
}
