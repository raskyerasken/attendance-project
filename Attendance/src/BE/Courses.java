/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jacob
 */
public class Courses {

    private final StringProperty Course = new SimpleStringProperty();
    private final IntegerProperty StudentID = new SimpleIntegerProperty();

    public int getStudentID() {
        return StudentID.get();
    }

    public void setStudentID(int value) {
        StudentID.set(value);
    }

    public IntegerProperty StudentIDProperty() {
        return StudentID;
    }
    

    public String getCourse() {
        return Course.get();
    }

    public void setCourse(String value) {
        Course.set(value);
    }

    public StringProperty CourseProperty() {
        return Course;
    }
    
}
