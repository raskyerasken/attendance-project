/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance;

import Classes.Students;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jacob
 */
public class EditAttedenceController implements Initializable {
Model model;
Students studd= new Students();
 @FXML
    private TextField lblName;
    @FXML
    private TextField lblLastName;
    @FXML
    private TextField lblDate;
    boolean newAttendence =false;
    int index;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setModel(Model model) {
    this.model= model;
    }

    void setStudent(Students selectedItem, int index) {
      lblName.setText(selectedItem.getName());
        lblLastName.setText(selectedItem.getFamilyName());
        lblDate.setText(""+selectedItem.getAttence());
        newAttendence= true;
        this.index=index;
    }

    @FXML
    private void saveAttence(ActionEvent event) throws ParseException {
        getStudent();
        model.add(studd);
        System.out.println(model.getAttence());
        if(newAttendence)
        {
        model.delete(index);
        }
         Stage stage = (Stage) lblName.getScene().getWindow();
                        stage.close();
 }
   void getStudent() throws ParseException
    {
     studd.setFamilyName(lblLastName.getText());
        studd.setName(lblName.getText()); String str_date=lblDate.getText();
        DateFormat formatter ; 
        Date date ; 
        formatter = new SimpleDateFormat("yy-MM-dd");
        date = formatter.parse(str_date);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        studd.setAttence(sqlDate);
        
    }

    @FXML
    private void deleteAttendence(ActionEvent event) throws ParseException {
      if(newAttendence)
        {
        model.delete(index);
        }
    }

    void noStudent() {
    newAttendence=false;
    
            }
    
}
