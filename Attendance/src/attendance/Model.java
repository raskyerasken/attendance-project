/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance;

import Classes.DateOfPresent;
import Classes.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jacob
 */
public class Model {
    private final ObservableList<Student> students
            = FXCollections.observableArrayList();

  private final ObservableList<DateOfPresent> attence
            = FXCollections.observableArrayList();
    void add(Student studd) {
     students.add(studd);
    }

    ObservableList<Student> getAttence() {
     return students;
     
    }
 ObservableList<DateOfPresent> getAttenceDay() {
     return attence;
     
    }

    void delete(int index) {
       attence.remove(index);
 }

    void addAttence(DateOfPresent dop) {
         attence.add(dop);
    }
    

}
