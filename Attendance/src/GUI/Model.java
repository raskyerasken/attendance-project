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
<<<<<<< HEAD
import java.sql.SQLException;
=======
import com.microsoft.sqlserver.jdbc.SQLServerException;
>>>>>>> 258693414e4d453e3b2f2120e499bffd0d6ed93e
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
<<<<<<< HEAD
 

=======
 private final  ObservableList<Student> hiddenStudent
            = FXCollections.observableArrayList();
    private final  ObservableList<DateOfPresent> attence
>>>>>>> fa66a75858cacc0e56a89433cb3d0cc085e5a976
            = FXCollections.observableArrayList();
    
>>>>>>> 922e3d72eb819faa06f8c9522419b88cbbab7f1b
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
    {
        attendance.addAll(presentDate.getDate());
        return attendance;
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
