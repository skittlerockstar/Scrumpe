/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumpe.scrumpeclient.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    public enum CourseEnum{
        CourseInfo,Questions,Question,Description,Explanation,Answers,
    }
    public static HashMap<CourseEnum,Object> readExcel(File file) {
        String[] courseInfo = new String[3];
        HashMap<CourseEnum, Object> course = new HashMap<>();
        try {

            FileInputStream excelFile = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            courseInfo[0] = datatypeSheet.getRow(1).getCell(0).getStringCellValue();
            courseInfo[1] = datatypeSheet.getRow(1).getCell(1).getStringCellValue();
            courseInfo[2] = ((int)datatypeSheet.getRow(1).getCell(2).getNumericCellValue()) + "";
            course.put(CourseEnum.CourseInfo, courseInfo);
            course.put(CourseEnum.Questions, getQuestions(datatypeSheet));
            return course;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object getQuestions(Sheet datatypeSheet) {
        List<Object> questions = new ArrayList<>();
        Iterator i = datatypeSheet.iterator();
        while (i.hasNext()) {
            Row r = (Row) i.next();
            if (r.getRowNum() > 3) {
                if (r.getRowNum() % 2 == 0) {
                    if(r.getCell(0).getCellTypeEnum() == CellType.BLANK){
                        break;
                    }
                    HashMap<CourseEnum, Object> question = new HashMap<>();
                    question.put(CourseEnum.Question, r.getCell(0).getStringCellValue());
                    question.put(CourseEnum.Description, r.getCell(1).getStringCellValue());
                    question.put(CourseEnum.Answers, getAnswers(r, datatypeSheet.getRow(r.getRowNum()+1)));
                    questions.add(question);
                }
            }
        }
        
        return questions;
    }

    private static Object getAnswers(Row r, Row row) {
          HashMap<String,Boolean> answers = new HashMap<>();
          Iterator anss = r.iterator();
          while(anss.hasNext()){
              Cell c = (Cell) anss.next();
              Cell c2 = row.getCell(c.getColumnIndex());
              if(c.getColumnIndex() > 1){
                  String val = "";
                  Boolean isCorrect = false;
                  if(c.getCellTypeEnum() == CellType.STRING){
                      val = c.getStringCellValue();
                  }else if ( c.getCellTypeEnum() == CellType.NUMERIC){
                      int n = (int) c.getNumericCellValue();
                      val = n+"";
                  }else if(c.getCellTypeEnum() == CellType.BLANK){
                      break;
                  }
                 
                  if(c2.getCellTypeEnum() == CellType.STRING){
                      isCorrect = true;
                  }else if ( c2.getCellTypeEnum() == CellType.NUMERIC){
                      
                      isCorrect = true;
                  }else if ( c2.getCellTypeEnum() == CellType.BLANK){
                      
                  }
                  answers.put(val, isCorrect);
              }
          }
          return answers;
    }

    private static void printThatShit(HashMap<String, Object> course) {
        String[] strings = (String[]) course.get("courseInfo");
        System.err.println(strings[0]+" "+strings[1]+" "+strings[2]);
        List<Object> qlist = (ArrayList) course.get("questions");
        for (Object object : qlist) {
            HashMap<String,Object> qs = (HashMap<String,Object>) object;
            System.err.println(qs.get("question"));
            System.err.println(qs.get("description"));
            HashMap<String,Boolean> answers = (HashMap<String,Boolean>) qs.get("answers");
            for (Map.Entry<String, Boolean> entry : answers.entrySet()) {
                String key = entry.getKey();
                Boolean value = entry.getValue();
                System.err.println(key+" = "+ value.toString());
            }
        }
        
    }

}
