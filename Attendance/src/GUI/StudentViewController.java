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
import javafx.scene.control.SelectionModel;
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
    private Label studentName;
    Student student = new Student(); 

    void setModel(Model model, Student SelectStudent) 
    {
        student=SelectStudent;
        this.model= model; 
        studentName.setId("OurLabel");
        studentName.setText(SelectStudent.getName()+" " +SelectStudent.getFamilyName());
       
                    tblviewAttendence.setItems(model.getAttendanceDayByStudent(student));
        
    }
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        colDate.setCellValueFactory(
                new PropertyValueFactory("date"));
    }
    @FXML
    void editAttendance() throws IOException 
    {
        Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("EditAttedance.fxml"));
        Parent root = fxLoader.load();
        EditAttedanceController controller = fxLoader.getController();
        controller.setModel(model);
        PresentDate pd= new  PresentDate();
        pd.setStudentID(student.getStudentID());
        pd.setCourse("CS2017_b");
        controller.setPresentDate(pd);
        SelectionModel select = tblviewAttendence.getSelectionModel();
        if (!select.isEmpty()) 
        {
            controller.setStudent((PresentDate) select.getSelectedItem(), select.getSelectedIndex());
        } 
        
        else 
        {
            controller.noStudent();
        }
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.setResizable(false);
        newStage.show();
    }

    @FXML
    private void mainWindow(ActionEvent event) throws IOException 
    {
        Stage stage = (Stage) tblviewAttendence.getScene().getWindow();
        stage.close();
    }

}
