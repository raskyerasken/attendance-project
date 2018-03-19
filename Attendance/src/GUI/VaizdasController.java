/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.DateOfPresent;
import BE.Student;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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


    void setModel(Model model, Student SelectStudent) 
    {
        this.model= model; 
        for (DateOfPresent dateOfPresent : model.getAttenceDay()) {
            if(dateOfPresent.getFirstName().equals(SelectStudent.getName()) && 
                   dateOfPresent.getLastName().equals(SelectStudent.getFamilyName()))
                    tblviewAttendence.getItems().add(dateOfPresent);
       }
        
      //tblviewAttendence.setItems(model.getAttenceDay());
    }
  
   void backToMain() throws IOException
   {
        Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        Parent root = fxLoader.load();
        MainWindowController controller= fxLoader.getController();
        controller.setModel(model);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
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

    @FXML
    private void mainWindow(ActionEvent event) throws IOException 
    {
        backToMain();
        Stage stage = (Stage) tblviewAttendence.getScene().getWindow();
        stage.close();
    }

}
