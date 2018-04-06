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
import BLL.BLLManagerHiddenStudent;
import BLL.BLLManagerStudent;
import java.sql.SQLException;
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
    BLLManagerHiddenStudent bllHiddenStudent
             = new  BLLManagerHiddenStudent();
 
    private final ObservableList<Student> students
            = FXCollections.observableArrayList();

    private final ObservableList<PresentDate> attendance
            = FXCollections.observableArrayList();
    private final ObservableList<PresentDate> attendanceByStudent
            = FXCollections.observableArrayList();
    private final  ObservableList<Student> hiddenStudent
            = FXCollections.observableArrayList();
 
     private final  ObservableList<Courses> courses
            = FXCollections.observableArrayList();
     
       private final  ObservableList<Student> StudentInCourses
            = FXCollections.observableArrayList();
       
    public void add(Student studd) 
    {
        students.add(studd);
    }
    
    ObservableList<Student> getAttendance() 
    {
        return students;
    }
    
    ObservableList<PresentDate> getAttendanceDay() 
    {attendance.clear();
        attendance.addAll(presentDate.getDate());
       
        return attendance;
    }
      ObservableList<PresentDate> getAttendanceDayByStudent(Student stud) 
    {
       getAttendanceDay();
        for (PresentDate presentDate1 : attendance) {
            
            if(presentDate1.getStudentID()==stud.getStudentID())
            {
            attendanceByStudent.add(presentDate1);
            }
        }
        return attendanceByStudent;
    }

    public void delete(int index) 
    {
       attendance.remove(index);
    }

    public  void addAttendance(PresentDate pd) throws SQLServerException 
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

    void hideStudent(Student hiddenStudent) 
    {
        students.remove(hiddenStudent);
        bllHiddenStudent.addHiddenStudent(hiddenStudent);
        bllStudent.removeStudent(hiddenStudent);
    }

    ObservableList<Student> getHiddenstudent() 
    {
        hiddenStudent.addAll(bllHiddenStudent.getHiddenStudent());
        return hiddenStudent;
    }

    void unHideStudent(Student unHideStudent) throws SQLException 
    {
        hiddenStudent.remove(unHideStudent);
        bllHiddenStudent.unHide(unHideStudent);
        bllStudent.addStudent(unHideStudent);
    }
}
