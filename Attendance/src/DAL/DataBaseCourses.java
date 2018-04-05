/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.Courses;
import BE.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jacob
 */
public class DataBaseCourses 
{

    private ConnectionManagerAttendance cm = new ConnectionManagerAttendance();
    public void addCourse(Courses course) 
    {
        try (Connection con = cm.getConnection())  
        {
            String sql 
                = "INSERT INTO Courses"
                + "(CourseName,  StudentId) "
                + "VALUES(?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, course.getCourse());
            pstmt.setInt(2, course.getStudentID());
           
            int affected = pstmt.executeUpdate();
            if (affected<1)
            {
                throw new SQLException("Song could not be added");
            }
        }
        catch (SQLException ex) 
        {
             Logger.getLogger(DataBaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    

    public ObservableList<Courses> getALlClasses() 
    {
        ObservableList<Courses> allCourses
                = FXCollections.observableArrayList();

        try (Connection con = cm.getConnection()) 
        {
            PreparedStatement stmt
                    = con.prepareStatement("SELECT * FROM Courses");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) 
            {
                Courses course = new Courses();
                course.setCourse(rs.getString("CourseName"));
                course.setStudentID(rs.getInt("StudentId"));
              
                allCourses.add(course);
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBaseStudent.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return (ObservableList<Courses>) allCourses;
    }
    
}
