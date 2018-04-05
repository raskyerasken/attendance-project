/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.Courses;
import BE.PresentDate;
import BE.Student;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jacob
 */
public class TeacherViewController implements Initializable {

    boolean showHiddenStudent=false; 
    @FXML
    private AnchorPane lol;
    @FXML
    private Label skippedDayLabel;
    @FXML
    private ComboBox<Courses> classPicker;
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
    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    java.sql.Date toDayDate = sqlDate;
    String startSDate = "2018-02-12";
    String endSDate = sqlDate.toString();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateFormat formattersql = new SimpleDateFormat("yy-MM-dd");
    CalculateAttendenceProcent cal;
    @FXML
    private JFXButton showStudent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        LocalDate startDate = LocalDate.parse(startSDate, formatter);
        LocalDate endDate = LocalDate.parse(endSDate, formatter);
        this.startDate.setValue(startDate);
        this.endDate.setValue(endDate);

        // TODO
        colFirstName.setCellValueFactory(
                new PropertyValueFactory("Name"));

        colLastName.setCellValueFactory(
                new PropertyValueFactory("familyName"));

        colAttence.setCellValueFactory(
                new PropertyValueFactory("Attendance"));
        

        tblStudents.setRowFactory((param) -> new TableRow<Student>() {

            protected void updateItem(Student item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setStyle("");
                } else {
                    Student person = getTableView().getItems().get(getIndex());
                    for (PresentDate dateOfPresent : model.getAttenceDay()) {

                        if (dateOfPresent.getStudentID() == person.getStudentID()) {
                            int datePresent= (int) (dateOfPresent.getDate().getTime()/ (1000 * 60 * 60 * 24));
                            int dateOfTheDate= (int) (toDayDate.getTime()/ (1000 * 60 * 60 * 24));
                            
                            if (datePresent==dateOfTheDate){
                                setStyle("-fx-background-color: green;");
                            } else {
                                setStyle("");
                            }
                        }
                    }
                }
            }
        });
  
    }

    void mainWindow() throws IOException 
    {
        Stage stage = (Stage) lol.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void returnStudentView(ActionEvent event) throws IOException {
        mainWindow();
        Stage stage = (Stage) tblStudents.getScene().getWindow();
        stage.close();
    }

    private void skippedDayLabel() {
        skippedDayLabel.setText("Monday");
    }

    void setModel(Model model) throws ParseException {
        this.model = model;
        try {
            cal = new CalculateAttendenceProcent(model);
        } catch (ParseException ex) {
            Logger.getLogger(TeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cal.setAttendenceProcent(new java.sql.Date(formattersql.parse(startSDate).getTime()),
                     sqlDate);
        } catch (ParseException ex) {
            Logger.getLogger(TeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tblStudents.setItems(model.getAttence());
           sortTableViewByName();
             classPicker.getItems().clear();
        for (Courses classe : model.getClasses()) {
            int first = 0; 
           
            for (Courses item : classPicker.getItems()) {
                if(item.getCourse()==classe.getCourse())
                {
                first =1 ;
                }
            }
             if (first==0)
            {
            classPicker.getItems().add(classe);}
        
            }
        DateFormat formatter = new SimpleDateFormat("yy-MM-dd");
        String start = startDate.getValue().format(DateTimeFormatter.ISO_DATE);
        java.sql.Date startsDate = new java.sql.Date(formatter.parse(start).getTime());
        String end = endDate.getValue().format(DateTimeFormatter.ISO_DATE);
        java.sql.Date endsDate = new java.sql.Date(formatter.parse(end).getTime());
        skippedDayLabel.setText(cal.returnMostSkippedDay(startsDate, endsDate));
    
    }
           
        


    

    @FXML
    private void ChangeAttence(ActionEvent event) throws IOException {

        editAttence();
    }

    void editAttence() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("EditAttedence.fxml"));
        Parent root = fxLoader.load();
        EditAttedenceController controller = fxLoader.getController();
        controller.setModel(model);
        SelectionModel select = tblStudents.getSelectionModel();
        if (!select.isEmpty()) {
            controller.setStudent((PresentDate) select.getSelectedItem(), select.getSelectedIndex());
        } else {
            controller.noStudent();
        }
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    private void StartDate(ActionEvent event) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yy-MM-dd");
        String start = startDate.getValue().format(DateTimeFormatter.ISO_DATE);
        java.sql.Date startsDate = new java.sql.Date(formatter.parse(start).getTime());
        String end = endDate.getValue().format(DateTimeFormatter.ISO_DATE);
        java.sql.Date endsDate = new java.sql.Date(formatter.parse(end).getTime());
        cal.setAttendenceProcent(startsDate, endsDate);
    }

    @FXML
    private void endDate(ActionEvent event) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yy-MM-dd");
        String start = startDate.getValue().format(DateTimeFormatter.ISO_DATE);
        java.sql.Date startsDate = new java.sql.Date(formatter.parse(start).getTime());
        String end = endDate.getValue().format(DateTimeFormatter.ISO_DATE);
        java.sql.Date endsDate = new java.sql.Date(formatter.parse(end).getTime());
        cal.setAttendenceProcent(startsDate, endsDate);
    }

    

    @FXML
    private void StudentFromThisCourse(ActionEvent event) { 
    
        tblStudents.setItems(
                model.getStudentInClass((Courses) classPicker.getSelectionModel().getSelectedItem()));
    }

    @FXML
    private void hideStudent(ActionEvent event) {
        if(tblStudents.getSelectionModel().getSelectedItem()!=null
                && "Show hidden student".equals(showStudent.getText()))
        {
           model.hideStudent(tblStudents.getSelectionModel().getSelectedItem());
        }
        else
        {
        showErrorDialog("select student",null,"select Student");
        }
    }
     private void showErrorDialog(String title, String header, String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void changeShowAttendence(ActionEvent event) {
        if(!showHiddenStudent){
        tblStudents.getItems().clear();
        tblStudents.setItems(model.getHiddenstudent());
        showHiddenStudent=true;
        showStudent.setText("Show student");
        sortTableViewByName();
        
        }
        else
        {
            tblStudents.getItems().clear();
        tblStudents.setItems((ObservableList<Student>) model.getAllStudent());
        showHiddenStudent=false;
        showStudent.setText("Show hidden student");
        sortTableViewByName();
            
        }
               
    }

    @FXML
    private void unHideStudent(ActionEvent event) throws SQLException {
       if(tblStudents.getSelectionModel().getSelectedItem()!=null
               && "Show student".equals(showStudent.getText()))
        {
           model.unHideStudent(tblStudents.getSelectionModel().getSelectedItem());
        }
        else
        {
        showErrorDialog("select student",null,"select Student");
        }
    }

    private void sortTableViewByName() {
      tblStudents.getItems().sort((o1, o2) -> {
            return o1.getName().compareTo(o2.getName()); //To change body of generated lambdas, choose Tools | Templates.
        });}

}
