/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Utils;

import com.scrumpe.scrumpeclient.DB.Entity.Answer;
import com.scrumpe.scrumpeclient.DB.Entity.Course;
import com.scrumpe.scrumpeclient.DB.Entity.Question;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Max Verhoeven
 */
public class ExcelUploader {

    private static final String EXC_NO_TEMPLATE = "Excel file does not conform to the provided template.";
    private static final String EXC_NO_STRING = " is not text, Expected a cell containing text";
    private static final String EXC_NO_INTEGER = " is not a whole number, Expected a whole number";

    public static Course readExcel(File file) throws Exception {
        Course courseToLoad = new Course();
        FileInputStream excelFile = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        if (isTemplate(datatypeSheet)) {
            courseToLoad = setCourseInfo(datatypeSheet.getRow(1), courseToLoad);
            courseToLoad = setQuestionInfo(datatypeSheet, courseToLoad);
            return courseToLoad;
        } else {
            throw new Exception(EXC_NO_TEMPLATE);
        }
    }

    private static Course setCourseInfo(Row row, Course course) throws Exception {
        String courseTitle = getStringVal(row.getCell(0));
        String courseDescription = getStringVal(row.getCell(1));
        int courseMinimumScore = getIntVal(row.getCell(2));

        course.setCourseTitle(courseTitle);
        course.setCourseDescription(courseDescription);
        course.setMinimumScore(courseMinimumScore);

        return course;
    }

    private static Course setQuestionInfo(Sheet datatypeSheet, Course courseToLoad) throws Exception {
        Iterator<Row> iterator = datatypeSheet.iterator();
        List<Question> questionList = new ArrayList<>();
        while (iterator.hasNext()) {
            Row next = iterator.next();
            if (next.getRowNum() >= 4 && next.getRowNum() % 2 == 0) {
                Cell n = next.getCell(0);
                if (n == null || next.getCell(0).getCellTypeEnum() == CellType.BLANK) {
                    break;
                }
                Question question = new Question();
                String questionTitle = getStringVal(next.getCell(0));
                String questionExplanation = getStringVal(next.getCell(1));
                question.setQuestion(questionTitle);
                question.setExplanation(questionExplanation);
                question.setAnswers(setAnswerInfo(datatypeSheet.getRow(next.getRowNum())));
                questionList.add(question);
            }
        }
        courseToLoad.setQuestions(questionList);
        return courseToLoad;
    }

    private static List<Answer> setAnswerInfo(Row row) throws Exception {
        Iterator<Cell> iterator = row.iterator();
        List<Answer> answerList = new ArrayList<>();
        while (iterator.hasNext()) {
            Cell next = iterator.next();
            Answer ans = new Answer();
            if (next.getColumnIndex() > 1) {
                if (next.getCellTypeEnum() == CellType.BLANK) {
                    break;
                }
                ans.setAnswer(getStringVal(next));
                Sheet row1 = row.getSheet();
                Row row2 = row1.getRow(row.getRowNum()+1);
                if(row2!=null){
                if (row.getSheet().getRow(row.getRowNum()+1).getCell(next.getColumnIndex()).getCellTypeEnum() != CellType.BLANK) {
                    ans.isCorrectForExcel = true;
                }
                }
                answerList.add(ans);
            }
        }
        return answerList;
    }

    private static boolean isTemplate(Sheet datatypeSheet) {
        int firstRowNum = datatypeSheet.getFirstRowNum();
        Row row = datatypeSheet.getRow(firstRowNum);
        Cell cell = row.getCell(0);
        String val = cell.getStringCellValue();
        return val.equals("Course Title");
    }

    private static String getStringVal(Cell cell) throws Exception {
        try {
            if(cell == null){
                return "";
            }else
            return cell.getStringCellValue();
        } catch (Exception e) {
            try {
                return (int)cell.getNumericCellValue() + "";
            } catch (Exception ee) {
                throw new Exception(cell.getNumericCellValue() + " "
                        + "Row " + (cell.getRowIndex() + 1) + ",Cell" + cell.getColumnIndex() + EXC_NO_STRING);
            }
        }
    }

    private static int getIntVal(Cell cell) throws Exception {
        try {
            return (int) cell.getNumericCellValue();
        } catch (Exception e) {
            throw new Exception("Row " + (cell.getRowIndex() + 1) + ",Cell" + cell.getColumnIndex() + EXC_NO_INTEGER);
        }
    }
}
