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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
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
    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    java.sql.Date toDayDate = sqlDate;
    String startSDate = "2015-11-12";
    String endSDate = sqlDate.toString();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateFormat formattersql = new SimpleDateFormat("yy-MM-dd");
    CalculateAttendenceProcent cal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        LocalDate startDate = LocalDate.parse(startSDate, formatter);
        System.out.println("");
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
                    for (DateOfPresent dateOfPresent : model.getAttenceDay()) {

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

    void mainWindow() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        Parent root = fxLoader.load();
        MainWindowController controller = fxLoader.getController();
        controller.setModel(model);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
      
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

    void setModel(Model model) {
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
             tblStudents.getItems().sort((o1, o2) -> {
            return o1.getName().compareTo(o2.getName()); //To change body of generated lambdas, choose Tools | Templates.
        });

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
            controller.setStudent((DateOfPresent) select.getSelectedItem(), select.getSelectedIndex());
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

}
