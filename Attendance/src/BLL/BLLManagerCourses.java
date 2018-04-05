/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.Courses;
import DAL.DataBaseCourses;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author jacob
 */
public class BLLManagerCourses 
{
    DataBaseCourses DALCourse= new DataBaseCourses(); 
    public void addCourse(Courses course) 
    {
        DALCourse.addCourse(course);
    }

    public ObservableList<Courses> getAllClasses() 
    {
        return DALCourse.getALlClasses();
    }
    
}
