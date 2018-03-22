/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.Courses;
import BE.DateOfPresent;
import BE.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jacob
 */
public class Model 
{
    private final ObservableList<Student> students
            = FXCollections.observableArrayList();

    private final ObservableList<DateOfPresent> attence
            = FXCollections.observableArrayList();
    
     private final ObservableList<Courses> courses
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
    return courses;
    }
}
