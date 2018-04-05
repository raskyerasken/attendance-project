/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.PresentDate;
import BE.Student;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jacob
 */
public class CalculateAttendenceProcent {
    Model model;
      
     private final ObservableList<Student> students
            = FXCollections.observableArrayList();
    Date toDayDate; 
      java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
           DateFormat formatter= new SimpleDateFormat("yy-MM-dd");
        java.sql.Date start ;

    public CalculateAttendenceProcent(Model model) throws ParseException  { 
        
        start = new java.sql.Date(formatter.parse("2018-02-05").getTime());
        this.model = model; 
        
     
    }
      public double schoolDaysBetween(Date d1, Date d2)
    {
       int currentday=1;
     if(d1.getDay()==0)
     {
         d1.setDate(d1.getDate()+1);
       currentday=0;
     }
     else if(d1.getDay()==6)
     {
         d1.setDate(d1.getDate()+2);
         currentday=0;
     }
     return (double)((((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24))+1)/7)*5+(d2.getDay()-d1.getDay())%6+currentday;
    
     }
      
        void setAttendenceProcent(Date d1,Date d2)
    {
        students.clear();
       
        for (Student stud : model.getAttence()) {
            
            double countDaysPresent=0;
            for (PresentDate dateOfPresent : model.getAttenceDay()) {
                
                if(dateOfPresent.getDate().after(d1)&&dateOfPresent.getDate().before(d2))
                {
                    
                if(stud.getStudentID()==dateOfPresent.getStudentID())
                {
                countDaysPresent++;                                                    
                }
                }
            }
            double studAttendProcent=(countDaysPresent/schoolDaysBetween(d1,d2)*100);
           
            String stringFormat=String.format("%.2f",studAttendProcent).replace(",", ".");
            double formatProcent = Double.parseDouble(stringFormat);
            stud.setAttendance(formatProcent);
            students.add(stud);
        }
        model.addAll(students);
    }
        String returnMostSkippedDay(Date d1, Date D2)
        {
        String days=null;
        int day=0;
        int dat=0;
           int other=0;
            int[] count={0,0,0,0,0};
            for (PresentDate dateOfPresent : model.getAttenceDay()) {
                count[dateOfPresent.getDate().getDay()-1]++;
            }
            for (int i : count) {
                
                if (dat>i||other==0) {
                    day=other;
                    dat=i;
                }
                other++;
            }
           switch (day) {
                case 0: days = "Monday";
                     break;
                     case 1: days = "Tuesday";
                     break;
                     case 2: days = "Wednesday";
                     break;
                     case 3: days = "Thursday";
                     break;
                     case 4: days = "Friday";
                     break;
            }
                    
        return days;
        }

      
    
}
