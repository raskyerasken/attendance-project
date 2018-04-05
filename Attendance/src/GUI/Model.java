/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.Courses;
import BE.DateOfPresent;
import BE.Student;
import BLL.BLLManagerCourses;
import BLL.BLLManagerHiddenStudent;
import BLL.BLLManagerStudent;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jacob
 */
public class Model 
{
   BLLManagerCourses bllCourses = new BLLManagerCourses();
 BLLManagerStudent bllStudent= new  BLLManagerStudent();
 BLLManagerHiddenStudent bllHiddenStudent= new  BLLManagerHiddenStudent();
    private final  ObservableList<Student> students
            = FXCollections.observableArrayList();
 private final  ObservableList<Student> hiddenStudent
            = FXCollections.observableArrayList();
    private final  ObservableList<DateOfPresent> attence
            = FXCollections.observableArrayList();
    
     private final  ObservableList<Courses> courses
            = FXCollections.observableArrayList();
       private final  ObservableList<Student> StudentInCourses
            = FXCollections.observableArrayList();
    public void add(Student studd) 
    {
        students.add(studd);
    }
   
   

    ObservableList<Student> getAttence() 
    {
     return students;
     
    }
    ObservableList<DateOfPresent> getAttenceDay() 
    {
        return attence;
    }

    public void delete(int index) 
    {
       attence.remove(index);
    }

    public  void addAttence(DateOfPresent dop) 
    {
        attence.add(dop);
    }

    void addAll(ObservableList<Student> student) 
    {
        students.clear();
        System.out.println("sdf");
        students.setAll(student);
    }

    public void addStudentInCourses(Courses course) {
        courses.add(course);
    }
    public ObservableList<Courses>  getClasses()
    {
    return  bllCourses.getAllClasses(); 
        
    }

    ObservableList<Student> getStudentInClass(Courses selectedItem) {
        StudentInCourses.clear();
        for (Courses course : courses) {
            if(course.getCourse()==selectedItem.getCourse())
            {
                for (Student student : students) {
                    if(student.getStudentID()==course.getStudentID())
                    {
                    StudentInCourses.add(student);
                    }
                    
                }
            
            }
            
        }
        return StudentInCourses;
    }

    List<Student> getAllStudent() {
       students.addAll(bllStudent.getAllStudent());
    return students;
            }

    void hideStudent(Student hiddenStudent) {
    students.remove(hiddenStudent);
    bllHiddenStudent.addHiddenStudent(hiddenStudent);
    bllStudent.removeStudent(hiddenStudent);
    }

    ObservableList<Student> getHiddenstudent() {
        hiddenStudent.addAll(bllHiddenStudent.getHiddenStudent());
    return hiddenStudent;
            }

    void unHideStudent(Student unHideStudent) throws SQLException {
        hiddenStudent.remove(unHideStudent);
        bllHiddenStudent.unHide(unHideStudent);
        bllStudent.addStudent(unHideStudent);
    
    }
}
