/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.DateOfPresent;
import BE.Student;
import GUI.Model;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author mr.Andersen
 */
public class MockData 
{

    
public void add(Model stud) throws ParseException 
    {
        Student stud1 = new Student();
        stud1.setStudentID(1);
        stud1.setFamilyName("Trump");
        stud1.setName("Donald");
        stud1.setStudPic("/Image/poop.png");
        stud1.setAttendance(58.8);
        stud.add(stud1);
        
        
        Student stud2 = new Student();
        stud2.setStudentID(2);
        stud2.setFamilyName("Nikolov");
        stud2.setName("Marin");
        stud2.setAttendance(52.8);
        stud2.setStudPic("/Image/sadface.png");
        stud.add(stud2);

        Student stud3 = new Student();
        stud3.setStudentID(3);
        stud3.setFamilyName("Enevoldsen");
        stud3.setName("Anni");
        stud3.setAttendance(12.8);
        stud3.setStudPic("/Image/girl.png");
        stud.add(stud3);
        
        Student stud4 = new Student();
        stud4.setStudentID(4);
        stud4.setFamilyName("Rask");
        stud4.setName("Kasper");
        stud4.setAttendance(13.8);
        stud4.setStudPic("/Image/kasper.jpg");
        stud.add(stud4);
        
        Student stud5 = new Student();
        stud5.setStudentID(5);
        stud5.setFamilyName("Sørensen");
        stud5.setName("Jacob");
        stud5.setAttendance(100.8);
        stud5.setStudPic("/Image/devil.png");
        stud.add(stud5);
        
        Student stud6 = new Student();
        stud6.setStudentID(6);
        stud6.setFamilyName("Andersen");
        stud6.setName("Kristofer");
        stud6.setAttendance(0.8);
        stud6.setStudPic("/Image/cool.png");
        stud.add(stud6);
        
        Student stud7 = new Student();
        stud7.setStudentID(7);
        stud7.setFamilyName("Moniz");
        stud7.setName("Fabio");
        stud7.setAttendance(0.8);
        stud7.setStudPic("/Image/happy.png");
        stud.add(stud7);
        
        Student stud8 = new Student();
        stud8.setStudentID(8);
        stud8.setFamilyName("Sim");
        stud8.setName("Skomandas");
        stud8.setAttendance(78.8);
        stud8.setStudPic("/Image/tired.png");
        stud.add(stud8);
        
        Student stud9 = new Student();
        stud9.setStudentID(9);
        stud9.setFamilyName("Armando");
        stud9.setName("Gionathan");
        stud9.setAttendance(28.8);
        stud9.setStudPic("/Image/abu.png");
        stud.add(stud9);
        
        Student stud10 = new Student();
        stud10.setStudentID(10);
        stud10.setFamilyName("Hansen");
        stud10.setName("Jeppe");
        stud10.setAttendance(23.8);
        stud10.setStudPic("/Image/surprised.png");
        stud.add(stud10);
        addAttence(stud);
        
        Student stud11 = new Student();
        stud11.setStudentID(10);
        stud11.setFamilyName("Petersen");
        stud11.setName("Björn");
        stud11.setAttendance(23.8);
        stud11.setStudPic("/Image/confused.jpg");
        stud.add(stud11);
        addAttence(stud);
    }
    public void addAttence(Model model) throws ParseException 
    {
        DateOfPresent date = new DateOfPresent();
        date.setCourse("SDE");
        date.setStudentID(1);
        DateFormat formatter= new SimpleDateFormat("yy-MM-dd");
        java.sql.Date sqlDate = new java.sql.Date(formatter.parse("2015-11-11").getTime());
        date.setDate(sqlDate);
        model.addAttence(date);
        
        DateOfPresent date2 = new DateOfPresent();
        date2.setCourse("SDE");
        date2.setStudentID(5);
        DateFormat formatter2= new SimpleDateFormat("yy-MM-dd");
        java.sql.Date  sqlDate2 = new java.sql.Date(formatter2.parse("2015-11-12").getTime());
        date2.setDate(sqlDate2);
        model.addAttence(date2);
        
        DateOfPresent date3 = new DateOfPresent();
        date3.setCourse("SDO");
        date3.setStudentID(1);
        DateFormat formatter3= new SimpleDateFormat("yy-MM-dd");
        java.sql.Date  sqlDate3 = new java.sql.Date(formatter3.parse("2015-11-13").getTime());
        date3.setDate(sqlDate3);
        model.addAttence(date3);
        
    
    }
   
    
}
