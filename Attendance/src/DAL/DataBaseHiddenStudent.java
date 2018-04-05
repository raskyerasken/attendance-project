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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jacob
 */
public class DataBaseHiddenStudent {
private ConnectionManagerMyTunes cm = new ConnectionManagerMyTunes();
    public void add(Student hiddenStudent) {
      try (Connection con = cm.getConnection())  {

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
            if (affected<1){
                    throw new SQLException("Song could not be added");}
            
           
                 }
    catch (SQLException ex) {
        Logger.getLogger(DataBaseHiddenStudent.class.getName()).log(Level.SEVERE, null, ex);
    }     
    
}
}
