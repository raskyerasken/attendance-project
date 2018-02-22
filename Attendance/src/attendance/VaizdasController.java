/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance;

import Classes.DateOfPresent;
import Classes.Student;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Skomantas
 */
public class VaizdasController implements Initializable {
Model model; 


    @FXML
    private TableView<DateOfPresent> tblviewAttendence;
    @FXML
    private TableColumn<DateOfPresent, String> colName;
    @FXML
    private TableColumn<DateOfPresent, Date> colDate;
    @FXML
    private TableColumn<DateOfPresent, String> colClass;
    @FXML
    private TableColumn<DateOfPresent, String> colcpr;


   void setModel(Model model, Student SelectStudent) {
      this.model= model; 
       for (DateOfPresent dateOfPresent : model.getAttenceDay()) {
           if(dateOfPresent.getFirstName().equals(SelectStudent.getName()) && 
                   dateOfPresent.getLastName().equals(SelectStudent.getFamilyName()))
           tblviewAttendence.getItems().add(dateOfPresent);
       }
      //tblviewAttendence.setItems(model.getAttenceDay());
    }


   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         colName.setCellValueFactory(
                new PropertyValueFactory("firstName"));

        colDate.setCellValueFactory(
                new PropertyValueFactory("date"));
        colClass.setCellValueFactory(
                new PropertyValueFactory("course"));
       
    }    

}
