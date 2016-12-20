/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Container.Content;

import Container.Content.Component.ContentHeader;
import Container.Content.Component.IContentHeader;
import DataComponents.Answer;
import DataComponents.Course;
import DataComponents.Question;
import Utils.TestCourseFactory;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import scrumpe.UI.AppStyle;
import scrumpe.UI.UIComponent;

/**
 *
 * @author Max Verhoeven
 */
public class ActiveCourse extends UIComponent implements IContentHeader, ActionListener {

    Course activeCourse;
    Question activeQuestion;
    List<Answer> activeAnswers;
    
    int questionIndex = 1, nrOfQuestions;
    ButtonGroup activeButtonGroup;
    List<JToggleButton> activeAnswerButtons;
    ArrayList[] givenAnswers;
    List<Long> currentGivenAnswers;
    static final String RADIO_ACTION = "RadioClick", CHECK_ACTION = "CheckClick", PREVIOUS_ACTION = "PrevQuestion", NEXT_ACTION = "NextQuestion";

    /**
     * Creates new form ActiveCourse
     */
    public ActiveCourse() {
        initComponents();
        initCourse(TestCourseFactory.createTestCourse());
        initCustomComponents();
    }
    //<editor-fold desc="Custom Code">

    private void initCourse(Course createTestCourse) {
        this.activeCourse = createTestCourse;
        setActiveQuestion(1);
        this.nrOfQuestions = activeCourse.getQuestions().size();

        this.activeAnswerButtons = new ArrayList<>();
        this.currentGivenAnswers = new ArrayList<>();

        this.courseProgressBar.setMaximum(nrOfQuestions);
        this.givenAnswers = new ArrayList[nrOfQuestions];
        for (int i = 0; i < givenAnswers.length; i++) {
            givenAnswers[i] = new ArrayList<>();
        }
        this.activeButtonGroup = new ButtonGroup();

        goToQuestion(questionIndex);
    }

    private void buildQuestionField() {
       
        question.setText(activeQuestion.getQuestion());
        int correctAnswers = activeQuestion.getCorrectAnswerIds().length;
        String actionCommand = ((correctAnswers > 1) ? CHECK_ACTION : RADIO_ACTION);
        for (Answer answer : activeQuestion.getAnswers()) {
            String answerString = answer.getAnswer();
            JToggleButton button = ((correctAnswers > 1) ? new JCheckBox(answerString) : new JRadioButton(answerString));
            button.setActionCommand(actionCommand);
            button.addActionListener(this);
            activeAnswerButtons.add(button);
            if (correctAnswers == 1) {
                activeButtonGroup.add(button);
            }
            if(currentGivenAnswers.contains(answer.getId())){
                button.setSelected(true);
            }
            Answers.add(button);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case PREVIOUS_ACTION:
                goToQuestion(questionIndex - 1);
                break;
            case NEXT_ACTION:
                goToQuestion(questionIndex + 1);
                break;
            case CHECK_ACTION:
                saveAnswer((JToggleButton)e.getSource(),false);
                break;
            case RADIO_ACTION:
                saveAnswer((JToggleButton)e.getSource(),true);
                break;
            default:
                break;
        }
    }

    private void goToQuestion(int i) {
        if (i < 1)return;
        if(i > nrOfQuestions) {finishCourse(); return;}
        Answers.removeAll();
        activeAnswerButtons.clear();
        questionIndex = i;
         currentGivenAnswers = givenAnswers[questionIndex-1];
        setActiveQuestion(i);
        setProgress(i);
        buildQuestionField();
    }

    private void setProgress(int i) {
        courseProgress.setText(i + " / " + nrOfQuestions);
        courseProgressBar.setValue(i);
    }

    private void setActiveQuestion(int index) {
        this.activeQuestion = activeCourse.getQuestions().get(index - 1);
        this.activeAnswers = activeQuestion.getAnswers();
    }

    private void initCustomComponents() {
        nextQuestion.addActionListener(this);
        nextQuestion.setActionCommand(NEXT_ACTION);

        previousQuestion.addActionListener(this);
        previousQuestion.setActionCommand(PREVIOUS_ACTION);

        setBackground(AppStyle.TRANSLUCENT);
        addContentHeader();
    }

    @Override
    public void applyCustomStyle() {
    }

    @Override
    public void addContentHeader() {
        add(new ContentHeader(activeCourse.getCourseTitle()), BorderLayout.BEFORE_FIRST_LINE);
    }
    //</editor-fold>

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        question = new javax.swing.JLabel();
        Answers = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        courseProgressBar = new javax.swing.JProgressBar();
        courseProgress = new javax.swing.JLabel();
        nextQuestion = new javax.swing.JButton();
        previousQuestion = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel5.setLayout(new java.awt.GridBagLayout());

        question.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        jPanel5.add(question, gridBagConstraints);

        Answers.setLayout(new javax.swing.BoxLayout(Answers, javax.swing.BoxLayout.Y_AXIS));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        jPanel5.add(Answers, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        jPanel2.add(jPanel5, gridBagConstraints);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel4.setToolTipText("");
        jPanel4.setMaximumSize(new java.awt.Dimension(300, 2147483647));
        jPanel4.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.66;
        jPanel4.add(courseProgressBar, gridBagConstraints);

        courseProgress.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        jPanel4.add(courseProgress, gridBagConstraints);

        nextQuestion.setText("Next");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.5;
        jPanel4.add(nextQuestion, gridBagConstraints);

        previousQuestion.setText("Previous");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        jPanel4.add(previousQuestion, gridBagConstraints);

        jPanel1.add(jPanel4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        jPanel2.add(jPanel1, gridBagConstraints);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    //<editor-fold defaultstate="collapsed" desc="Generated Vars">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Answers;
    private javax.swing.JLabel courseProgress;
    private javax.swing.JProgressBar courseProgressBar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton nextQuestion;
    private javax.swing.JButton previousQuestion;
    private javax.swing.JLabel question;
    // End of variables declaration//GEN-END:variables

    private void finishCourse() {
        //saveRecord
        //gotoResults
    }


//</editor-fold>

    private void saveAnswer(JToggleButton jToggleButton,boolean singleAnswer) {
      int answerIndex = activeAnswerButtons.indexOf(jToggleButton);
      Answer answer = activeAnswers.get(answerIndex);
      if(singleAnswer) currentGivenAnswers.clear();
        if(jToggleButton.isSelected()){
            currentGivenAnswers.add(answer.getId());
        }else{
            currentGivenAnswers.remove(answer.getId());
        }
        givenAnswers[questionIndex-1] = (ArrayList)currentGivenAnswers;
        for (int i = 0; i < givenAnswers.length; i++) {
            System.err.println(givenAnswers[i].toString());
            
        }
    }
}
