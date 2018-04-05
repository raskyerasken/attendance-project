/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jacob
 */
public class PresentDate 
{
    private Date date;
    private final StringProperty course = new SimpleStringProperty();
    private final IntegerProperty StudentID = new SimpleIntegerProperty();

    public int getStudentID() 
    {
        return StudentID.get();
    }

    public void setStudentID(int value) 
    {
        StudentID.set(value);
    }

    public IntegerProperty StudentIDProperty() 
    {
        return StudentID;
    }
    
    public Date getDate() 
    {
        return date;
    }
    
    public void setDate(Date date) 
    {
        this.date = date;
    }
    
    public String getCourse() 
    {
        return course.get();
    }

    public void setCourse(String value) 
    {
        course.set(value);
    }

    public StringProperty courseProperty() 
    {
        return course;
    }
    
}
