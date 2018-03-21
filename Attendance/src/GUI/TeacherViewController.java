/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.DateOfPresent;
import BE.Student;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jacob
 */
public class TeacherViewController implements Initializable {

    @FXML
    private AnchorPane lol;
    @FXML
    private Label skippedDayLabel;
    @FXML
    private ChoiceBox<?> classPicker;
    @FXML
    private TableView<Student> tblStudents;
    @FXML
    private TableColumn<Student, String> colFirstName;
    @FXML
    private TableColumn<Student, String> colLastName;
    @FXML
    private TableColumn<Student, Double> colAttence;
 Model model;
    private JFXDatePicker hey;
    @FXML
    private JFXDatePicker endDate;
    @FXML
    private JFXDatePicker startDate;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        // TODO
        colFirstName.setCellValueFactory(
        new PropertyValueFactory("Name"));
                colFirstName.setCellFactory(param -> new TableCell<Student, String>(){
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setStyle("");
        } else {
            setText(item);
            Student person = getTableView().getItems().get(getIndex());
            if(person.getAttendance()<50) {
                setStyle("-fx-background-color: red;");
            } else {
                setStyle("");
            }
        }
    }
});
        
        colLastName.setCellValueFactory(
        new PropertyValueFactory("familyName"));
        
       
        colAttence.setCellValueFactory(
        new PropertyValueFactory("Attendance"));
     
             colAttence.setCellFactory(param -> new TableCell<Student, Double>(){
    @Override
    protected void updateItem(Double item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setStyle("");
        } else {
            setText(item+"");
            Student person = getTableView().getItems().get(getIndex());
            if(person.getAttendance()<50) {
                setStyle("-fx-background-color: red;");
            } else {
                setStyle("");
            }
        }
    }
}); 
             
        colLastName.setCellFactory(param -> new TableCell<Student, String>(){
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setStyle("");
        } else {
            setText(item);
            Student person = getTableView().getItems().get(getIndex());
            if(person.getAttendance()<50) {
                setStyle("-fx-background-color: red;");
            } else {
                setStyle("");
            }
        }
    }
});
      
    }
        
      
    
    
    void mainWindow() throws IOException
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

    @FXML
    private void returnStudentView(ActionEvent event) throws IOException 
    {
        mainWindow();
        Stage stage = (Stage) tblStudents.getScene().getWindow();
        stage.close();
    }
    
    
    private void skippedDayLabel()
    {
        skippedDayLabel.setText("Monday");
    }
    
    
    
    void setModel(Model model) 
    {
        this.model=model;
        
        tblStudents.setItems(model.getAttence());
       
    }

    @FXML
    private void ChangeAttence(ActionEvent event) throws IOException 
    {
        
        editAttence();
    }
    
    void editAttence() throws IOException
    {
        Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("EditAttedence.fxml"));
        Parent root = fxLoader.load();
        EditAttedenceController controller= fxLoader.getController();
        controller.setModel(model);
        SelectionModel select = tblStudents.getSelectionModel();
        if (!select.isEmpty())
       controller.setStudent((DateOfPresent) select.getSelectedItem(),select.getSelectedIndex() );
        else
        controller.noStudent();
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    private void StartDate(ActionEvent event) {
    }

    @FXML
    private void endDate(ActionEvent event) throws ParseException {
       CalculateAttendenceProcent calatpro= new CalculateAttendenceProcent(model);
         DateFormat formatter= new SimpleDateFormat("yy-MM-dd");
         String hallo = startDate.getValue().format(DateTimeFormatter.ISO_DATE);
        java.sql.Date start = new java.sql.Date(formatter.parse(hallo).getTime());
       String hallo2 = endDate.getValue().format(DateTimeFormatter.ISO_DATE);
        java.sql.Date end = new java.sql.Date(formatter.parse(hallo2).getTime());
        System.out.println(calatpro.schoolDaysBetween(start, end));
    }
    
    
}
