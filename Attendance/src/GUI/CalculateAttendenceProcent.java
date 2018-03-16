/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
        

    public CalculateAttendenceProcent(Model model) throws ParseException { 
        DateFormat formatter= new SimpleDateFormat("yy-MM-dd");
        java.sql.Date start = new java.sql.Date(formatter.parse("2018-03-11").getTime());
        this.model = model;
        
        System.out.println(daysBetween(start, utilDate));
    }
      public int daysBetween(Date d1, Date d2)
    {
        return (int)((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24)); 
    }
    
    
}
