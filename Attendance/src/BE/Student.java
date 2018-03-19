/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

import java.util.Date;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jacob
 */
public class Student 
{
    

    private final StringProperty studPic = new SimpleStringProperty();
    private final DoubleProperty Attendance = new SimpleDoubleProperty();

    public Double getAttendance() {
        return Attendance.get();
    }

    public void setAttendance(Double value) {
        Attendance.set(value);
    }

    public DoubleProperty AttendanceProperty() {
        return Attendance;
    }
    
    public Student() 
    {
        setStudPic("/Image/sadface.png");
    }
    
    public String getStudPic()
    {
        return studPic.get();
    }

    public void setStudPic(String value) 
    {
        studPic.set(value);
    }

    public StringProperty studPicProperty() 
    {
        return studPic;
    }
    
    
    private final StringProperty familyName = new SimpleStringProperty();
    private Date attence;
    
    public Date getAttence() 
    {
        return attence;
    }
    
    public void setAttence(Date attence) 
    {
        this.attence = attence;
    }

    public String getFamilyName() 
    {
        return familyName.get();
    }

    public void setFamilyName(String value) 
    {
        familyName.set(value);
    }

    public StringProperty familyNameProperty() 
    {
        return familyName;
    }

    private final StringProperty name = new SimpleStringProperty();

    public String getName() 
    {
        return name.get();
    }

    public void setName(String value) 
    {
        name.set(value);
    }

    public StringProperty nameProperty() 
    {
        return name;
    }
}
