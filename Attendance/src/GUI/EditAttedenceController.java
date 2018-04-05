/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.PresentDate;
import BE.Student;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jacob
 */
public class EditAttedenceController implements Initializable {
Model model;
Student studd= new Student();
    PresentDate dop= new PresentDate();
    private TextField lblName;
    private TextField lblLastName;
    @FXML
    private TextField lblDate;
    boolean newAttendence =false;
    int index;
    @FXML
    private ComboBox<String> student;
    @FXML
    private TextField txtClass;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        lblDate.setText(""+sqlDate);
        // TODO
        
    }    

    void setModel(Model model) {
    this.model= model;
        for (Student student1 : model.getAttendance()) {
           student.getItems().add(student1.getName()+" "+student1.getFamilyName());
        }
    
    }

    void setStudent(PresentDate selectedItem, int index) {
        student.getSelectionModel().select(selectedItem.getStudentID());
        lblDate.setText(""+selectedItem.getDate());
        newAttendence= true;
        this.index=index;
    }

    @FXML
    private void saveAttence(ActionEvent event) throws ParseException, SQLServerException {
        getStudent();
        model.addAttendance(dop);
        System.out.println(model.getAttendance());
        if(newAttendence)
        {
        model.delete(index);
        }
         Stage stage = (Stage) student.getScene().getWindow();
                        stage.close();
 }
   void getStudent() throws ParseException
    {
       
        String str_date=lblDate.getText();
        DateFormat formatter ; 
        Date date ; 
        formatter = new SimpleDateFormat("yy-MM-dd");
        date = formatter.parse(str_date);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        dop.setDate(sqlDate);
        dop.setCourse(txtClass.getText());
        
    }

    @FXML
    private void deleteAttendence(ActionEvent event) throws ParseException {
      if(newAttendence)
        {
        model.delete(index);
        }
      Stage stage = (Stage) student.getScene().getWindow();
                        stage.close();
    }

    void noStudent() {
    newAttendence=false;
    
            }
    
}
