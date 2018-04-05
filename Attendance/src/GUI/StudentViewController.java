/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.PresentDate;
import BE.Student;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Skomantas
 */
public class StudentViewController implements Initializable 
{
    
    Model model; 


    @FXML
    private TableView<PresentDate> tblviewAttendence;
    @FXML
    private TableColumn<PresentDate, Date> colDate;
    @FXML
    private TableColumn<PresentDate, String> colClass;
    @FXML
    private Label studentName;


    void setModel(Model model, Student SelectStudent) 
    {
        this.model= model; 
        studentName.setId("OurLabel");
        studentName.setText(SelectStudent.getName()+" " +SelectStudent.getFamilyName());
        for (PresentDate dateOfPresent : model.getAttendanceDay()) 
        {
            if(dateOfPresent.getStudentID()== SelectStudent.getStudentID() )
                    tblviewAttendence.getItems().add(dateOfPresent);
        }
    }
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        colDate.setCellValueFactory(
                new PropertyValueFactory("date"));
        colClass.setCellValueFactory(
                new PropertyValueFactory("course"));
    }    

    @FXML
    private void mainWindow(ActionEvent event) throws IOException 
    {
        Stage stage = (Stage) tblviewAttendence.getScene().getWindow();
        stage.close();
    }

}
