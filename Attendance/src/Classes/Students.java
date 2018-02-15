/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jacob
 */
public class Students {

    private final StringProperty familyName = new SimpleStringProperty();

    private Date attence;

    /**
     * Get the value of attence
     *
     * @return the value of attence
     */
    public Date getAttence() {
        return attence;
    }

    /**
     * Set the value of attence
     *
     * @param attence new value of attence
     */
    public void setAttence(Date attence) {
        this.attence = attence;
    }

    public String getFamilyName() {
        return familyName.get();
    }

    public void setFamilyName(String value) {
        familyName.set(value);
    }

    public StringProperty familyNameProperty() {
        return familyName;
    }

    private final StringProperty name = new SimpleStringProperty();

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }
    

    
}
