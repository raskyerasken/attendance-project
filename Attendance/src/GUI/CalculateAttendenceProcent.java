/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.DateOfPresent;
import BE.Student;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jacob
 */
public class CalculateAttendenceProcent {
    Model model;
      
     
    Date toDayDate; 
      java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
           DateFormat formatter= new SimpleDateFormat("yy-MM-dd");
        java.sql.Date start ;

    public CalculateAttendenceProcent(Model model) throws ParseException  { 
        
//        start = new java.sql.Date(formatter.parse("2018-03-05").getTime());
        this.model = model;
        setAttendenceProcent();
      
    }
      public int schoolDaysBetween(Date d1, Date d2)
    {
       
     if(d1.getDay()==0)
     {
         d1.setDate(d1.getDate()+1);
       
     }
     else if(d1.getDay()==6)
     {
         d1.setDate(d1.getDate()+2);
     }
     return (int)((((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24))+1)/7)*5+d1.getDay()%6;
    
     }
      
        void setAttendenceProcent()
    {
        System.out.println("hey");
        for (Student stud : model.getAttence()) {
            float countDaysPresent=0;
            for (DateOfPresent dateOfPresent : model.getAttenceDay()) {
                if(stud.getStudentID()==dateOfPresent.getStudentID())
                {
                countDaysPresent++;
                }
            }
            System.out.println((double)(countDaysPresent/schoolDaysBetween(start,utilDate))*100);
            stud.setAttendance((double)(countDaysPresent/schoolDaysBetween(start,utilDate))*100);
            
        }
    }

      
    
}
