/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.Courses;
import BE.PresentDate;
import BE.Student;
import BLL.BLLManagerCourses;
import BLL.BLLManagerDate;
import BLL.BLLManagerStudent;
import com.microsoft.sqlserver.jdbc.SQLServerException;
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
    BLLManagerDate presentDate = new BLLManagerDate();
    
    private final ObservableList<Student> students
            = FXCollections.observableArrayList();

    private final ObservableList<PresentDate> attendance
            = FXCollections.observableArrayList();
    
     private final ObservableList<Courses> courses
            = FXCollections.observableArrayList();
     
       private final ObservableList<Student> StudentInCourses
            = FXCollections.observableArrayList();
       
    public void add(Student studd) 
    {
        students.add(studd);
    }
    
    ObservableList<Student> getAttence() 
    {
        return students;
    }
    
    ObservableList<PresentDate> getAttenceDay() 
    {
        attendance.addAll(presentDate.getDate());
        return attendance;
    }

    public void delete(int index) 
    {
       attendance.remove(index);
    }

    public  void addAttence(PresentDate pd) throws SQLServerException 
    {
        presentDate.addDate(pd);
        attendance.add(pd);
    }

    void addAll(ObservableList<Student> student) 
    {
        students.clear();
        System.out.println("sdf");
        students.setAll(student);
    }

    public void addStudentInCourses(Courses course) 
    {
        courses.add(course);
    }
    
    public ObservableList<Courses>  getClasses()
    {
        return  bllCourses.getAllClasses();  
    }

    ObservableList<Student> getStudentInClass(Courses selectedItem) 
    {
        StudentInCourses.clear();
        for (Courses course : courses) 
        {
            if(course.getCourse()==selectedItem.getCourse())
            {
                for (Student student : students) 
                {
                    if(student.getStudentID()==course.getStudentID())
                    {
                        StudentInCourses.add(student);
                    }
                    
                }
            
            }
            
        }
        return StudentInCourses;
    }
    
    
    List<Student> getAllStudent() 
    {
        students.addAll(bllStudent.getAllStudent());
        return students;
    }
}
