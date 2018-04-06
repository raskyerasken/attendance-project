/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.PresentDate;
import BE.Student;
import com.jfoenix.controls.JFXDatePicker;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class EditAttedanceController implements Initializable 
{
    Model model;
    Student studd= new Student();
    PresentDate dop= new PresentDate();
    private TextField lblName;
    private TextField lblLastName;
    private TextField lblDate;
    boolean newAttendence =false;
    int index;
    private TextField txtClass;
    @FXML
    private JFXDatePicker editAttendance;
    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    java.sql.Date toDayDate = sqlDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateFormat formattersql = new SimpleDateFormat("yy-MM-dd");
    String editDate = sqlDate.toString();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        LocalDate startDate = LocalDate.parse(editDate, formatter);
        editAttendance.setValue(startDate);
        
        //lblDate.setText(""+sqlDate);
    }    

    void setModel(Model model) 
    {
        this.model= model;
    }



    @FXML
    private void saveAttence(ActionEvent event) throws ParseException, SQLServerException 
    {
        
         //String str_date=lblDate.getText();
        DateFormat formatter = new SimpleDateFormat("yy-MM-dd");
        String start = editAttendance.getValue().format(DateTimeFormatter.ISO_DATE);
        java.sql.Date startsDate = new java.sql.Date(formatter.parse(start).getTime());
        //String end = endDate.getValue().format(DateTimeFormatter.ISO_DATE);
        //java.sql.Date endsDate = new java.sql.Date(formatter.parse(end).getTime());
        //cal.setAttendenceProcent(startsDate, endsDate);
        dop.setDate(startsDate);
        model.addAttendance(dop);
        Stage stage = (Stage) editAttendance.getScene().getWindow();
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
    private void deleteAttendence(ActionEvent event) throws ParseException 
    {
        if(newAttendence)
        {
            model.delete(index);
        }
        Stage stage = (Stage) editAttendance.getScene().getWindow();
                        stage.close();
    }

    void noStudent() 
    {
        newAttendence=false;
    }

    void setPresentDate(PresentDate pd) 
    {
        dop=pd;
    }

    @FXML
    private void editAttendance(ActionEvent event) 
    {
        
    }
    
}
