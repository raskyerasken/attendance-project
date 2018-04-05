/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.Student;
import DAL.DataBaseHiddenStudent;
import javafx.collections.ObservableList;

/**
 *
 * @author jacob
 */
public class BLLManagerHiddenStudent {
DataBaseHiddenStudent DALHiddenStudent= new DataBaseHiddenStudent();
    

    public void addHiddenStudent(Student hiddenStudent) {
        DALHiddenStudent.add(hiddenStudent);
    }

    public ObservableList<Student> getHiddenStudent() {
    return DALHiddenStudent.getHiddenStudent();
            }


    public void unHide(Student unHideStudent) {
    DALHiddenStudent.unHideStudent(unHideStudent);}
    
}
