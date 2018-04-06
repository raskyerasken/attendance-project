/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.PresentDate;
import BE.Student;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.naming.spi.DirStateFactory.Result;

/**
 *
 * @author ander
 */
public class DataBasePresentDate 
{
    private ConnectionManagerAttendance cm = new ConnectionManagerAttendance();
    
    public void addPresentDate(PresentDate presentDate) throws SQLServerException
    {
        try(Connection con = cm.getConnection())
        {
            String sql 
                    = "INSERT INTO PresentDate"
                    + "(Date, StudentID)"
                    + "VALUES (?,?)";
            
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setDate(1, (Date) presentDate.getDate());
            pstmt.setInt(2, presentDate.getStudentID());
            
            int affected = pstmt.executeUpdate();
            if (affected < 1) 
            {
                throw new SQLException("Date could not be added");
            } 
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBasePresentDate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<PresentDate> getDates()
    {
        
        ObservableList<PresentDate> allDates 
                = FXCollections.observableArrayList();
        
        try(Connection con = cm.getConnection())
        {
            PreparedStatement pstmt 
                    = con.prepareStatement("SELECT * FROM PresentDate");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                PresentDate presentDate = new PresentDate();
                presentDate.setDate(rs.getDate("Date"));
                presentDate.setStudentID(rs.getInt("StudentId"));
                if(!allDates.contains(presentDate)){
                allDates.add(presentDate);}
            }
        } 
        
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBasePresentDate.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return (ObservableList<PresentDate>) allDates;
    }
    

    public void removeall() {
      try (Connection con = cm.getConnection()) 
        {
            String sql = "DELETE FROM PresentDate WHERE StudentID=?";
            for (int i = 0; i < 13; i++) {
                
           
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, i);
            pstmt.execute(); }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        } }
}
