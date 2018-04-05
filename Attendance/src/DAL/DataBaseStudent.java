/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.Student;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jacob
 */
public class DataBaseStudent 
{
     private ConnectionManagerAttendance cm = new ConnectionManagerAttendance();

   public void add (Student student) throws SQLException
    {
        try (Connection con = cm.getConnection())  
        {
            String sql 
                = "INSERT INTO Student"
                + "(Name,  StudPicture, LastName) "
                + "VALUES(?,?,?)";
           PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getStudPic());
            pstmt.setString(3, student.getFamilyName());
           
            
            int affected = pstmt.executeUpdate();
            if (affected<1){
                    throw new SQLException("Song could not be added");}
            
            
              ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                student.setStudentID(rs.getInt(1));
           }
                 }
    catch (SQLException ex) 
    {
        Logger.getLogger(DataBaseStudent.class.getName()).log(Level.SEVERE, null, ex);
    }     
  }

    public List<Student> getAllStudent() 
    {
        List<Student> allStudent
                = new ArrayList();

        try (Connection con = cm.getConnection()) 
        {
            PreparedStatement stmt
                    = con.prepareStatement("SELECT * FROM Student");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) 
            {
                Student stud = new Student();
                stud.setFamilyName(rs.getString("LastName"));
                stud.setName(rs.getString("Name"));
                stud.setStudPic(rs.getString("StudPicture"));
                stud.setStudentID(rs.getInt("StudentId"));
              
                allStudent.add(stud);
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBaseStudent.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allStudent;
    }

    public void removeStudent(Student hiddenStudent) {
       try (Connection con = cm.getConnection()) 
        {
            String sql = "DELETE FROM Student WHERE name=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, hiddenStudent.getStudentID());
            pstmt.execute();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        } }
}
