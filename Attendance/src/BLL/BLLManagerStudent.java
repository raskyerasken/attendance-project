/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.Student;
import DAL.DataBaseStudent;
import DAL.MockData;
import GUI.Model;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author jacob
 */
public class BLLManagerStudent {
    DataBaseStudent DALStudent = new DataBaseStudent();
    MockData mockData=new MockData();
    public void add(Model model) throws ParseException {
        mockData.add(model);
     }

    public void addStudent(Student student) throws SQLException {
    DALStudent.add(student);
    }

    public List<Student> getAllStudent() {
  return DALStudent.getAllStudent();
           }

    public void removeStudent(Student hiddenStudent) {
    DALStudent.removeStudent(hiddenStudent);
    }

   
    
}
