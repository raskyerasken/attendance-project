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
public class DataBaseHiddenStudent 
{
    private ConnectionManagerAttendance cm = new ConnectionManagerAttendance();
        public void add(Student hiddenStudent) 
        {
            try (Connection con = cm.getConnection())  
            {

                String sql 
                    = "INSERT INTO HiddenStudent"
                    + "(Name,  StudPicture, LastName,StudentId) "
                    + "VALUES(?,?,?,?)";
                PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, hiddenStudent.getName());
                pstmt.setString(2, hiddenStudent.getStudPic());
                pstmt.setString(3, hiddenStudent.getFamilyName());
                pstmt.setInt(4, hiddenStudent.getStudentID());


                int affected = pstmt.executeUpdate();
                if (affected<1)
                {
                        throw new SQLException("Song could not be added");
                }


            }
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBaseHiddenStudent.class.getName()).log(Level.SEVERE, null, ex);
        }     
    
}

    public ObservableList<Student> getHiddenStudent() {
    ObservableList<Student> allHiddenStudent
                = FXCollections.observableArrayList();

        try (Connection con = cm.getConnection()) 
        {
            PreparedStatement stmt
                    = con.prepareStatement("SELECT * FROM HiddenStudent");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) 
            {
                Student stud = new Student();
                stud.setFamilyName(rs.getString("LastName"));
                stud.setName(rs.getString("Name"));
                stud.setStudPic(rs.getString("StudPicture"));
                stud.setStudentID(rs.getInt("StudentId"));
              
                allHiddenStudent.add(stud);
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBaseStudent.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return (ObservableList<Student>) allHiddenStudent;
    }


    public void unHideStudent(Student unHideStudent) {
     try (Connection con = cm.getConnection()) 
        {
            String sql = "DELETE FROM HiddenStudent WHERE StudentId=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, unHideStudent.getStudentID());
            pstmt.execute();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBaseHiddenStudent.class.getName()).log(Level.SEVERE, null, ex);
        }}
}
