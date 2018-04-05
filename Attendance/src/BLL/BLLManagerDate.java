/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.Courses;
import BE.PresentDate;
import DAL.DataBaseCourses;
import DAL.DataBasePresentDate;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.ObservableList;

/**
 *
 * @author ander
 */
public class BLLManagerDate 
{
    DataBasePresentDate DALPresentDate= new DataBasePresentDate();
    
    public void addDate(PresentDate presentDate) throws SQLServerException 
    {
        DALPresentDate.addPresentDate(presentDate);
    }

    public ObservableList<PresentDate> getDate() 
    {
        return DALPresentDate.getDates();
    }
}
