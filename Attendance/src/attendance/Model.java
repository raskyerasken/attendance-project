/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance;

import Classes.Students;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jacob
 */
public class Model {
    private final ObservableList<Students> attence
            = FXCollections.observableArrayList();

    void add(Students studd) {
     attence.add(studd);
    }

    ObservableList<Students> getAttence() {
     return attence;
     
    }

    void delete(int index) {
    attence.remove(index);
    }

    void delete() {
        for (Students students : attence) {
            
        }
}
}
