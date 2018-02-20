/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance;

import Classes.Student;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jacob
 */
public class MainWindowController implements Initializable 
{
    @FXML
    private AnchorPane hey;

    @FXML
    private Label label;
    @FXML
    private TextField txtCPR;
    @FXML
    private TableView<Student> tblviewStudens;
    @FXML
    private TableColumn<Student, String> clSurname;
    @FXML
    private TableColumn<Student, String> clFamilyName;
    Student stud = new Student();
    private final ObservableList<Student> studentsList
            = FXCollections.observableArrayList();
    @FXML
    private Label nameLabel;
    @FXML
    private Label ageLabel;
    @FXML
    private Label attendanceLabel;
    @FXML
    private ImageView studentPhotoView;
    Model model = new Model();
    @FXML
    private Label studentName;
    @FXML
    private ImageView picture;
    @FXML
    private TextField pwTeacher;

    /**
     * Initializes the controller class.
     */
    @Override
<<<<<<< HEAD
    public void initialize(URL url, ResourceBundle rb) {
=======
    public void initialize(URL url, ResourceBundle rb) 
    {
>>>>>>> 82ecec56469744ea2547dc80f8abca4f866d4115
        tblviewStudens.setItems(model.getAttence());

        clSurname.setCellValueFactory(
                new PropertyValueFactory("name"));

        clFamilyName.setCellValueFactory(
                new PropertyValueFactory("familyName"));
<<<<<<< HEAD
        MockStudents ms= new MockStudents();
        ms.addStudents(model);
        stud.setFamilyName("Hansen");
        stud.setName("Jeppe");
        stud.setStudPic("/Image/surprised.png");
        studentsList.add(stud);

        Student stud2 = new Student();
        stud2.setFamilyName("Jensen");
        stud2.setName("Karl");
        stud2.setStudPic("/Image/happy.png");
        studentsList.add(stud2);

        Student stud3 = new Student();
        stud3.setFamilyName("Søresen");
        stud3.setName("Sofie");
        stud3.setStudPic("/Image/angry.png");
        studentsList.add(stud3);

        
        Student stud4 = new Student();
        stud4.setFamilyName("Rask");
        stud4.setName("Kasper");
        stud4.setStudPic("/Image/kasper.jpg");
        studentsList.add(stud4);
        
        Student stud5 = new Student();
        stud5.setFamilyName("Søresen");
        stud5.setName("Jacob");
        stud5.setStudPic("/Image/happy.png");
        studentsList.add(stud5);
        
        Student stud6 = new Student();
        stud6.setFamilyName("Andersen");
        stud6.setName("Kristofer");
        stud6.setStudPic("/Image/surprised.png");
        studentsList.add(stud6);
        
        Student stud7 = new Student();
        stud7.setFamilyName("Moniz");
        stud7.setName("Fabio");
        stud7.setStudPic("/Image/happy.png");
        studentsList.add(stud7);
        
        Student stud8 = new Student();
        stud8.setFamilyName("Sim");
        stud8.setName("Skomandas");
        stud8.setStudPic("/Image/tired.png");
        studentsList.add(stud8);
        
        Student stud9 = new Student();
        stud9.setFamilyName("Armando");
        stud9.setName("Gionathan");
        stud9.setStudPic("/Image/scared.png");
        studentsList.add(stud9);
        
=======
        
        MockData md = new MockData();
        md.add(model);
        


>>>>>>> 82ecec56469744ea2547dc80f8abca4f866d4115
        textChangeCPR();
        textChangerTeacher();
    }

    private void textChangerTeacher() {
        pwTeacher.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable e) {
                if (pwTeacher.getText().length() == 10) {
                    pwTeacher.setStyle("-fx-text-fill: green");
                } else {
                    pwTeacher.setStyle("-fx-text-fill: red");
                }
            }
        });
    }
    
    

    private void textChangeCPR() {
        txtCPR.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable e) {
                if (txtCPR.getText().length() == 10) {
                    txtCPR.setStyle("-fx-text-fill: green");
                } else {
                    txtCPR.setStyle("-fx-text-fill: red");
                }
            }
        });
    }

    private void setit(ActionEvent event) {
        Stage stage = (Stage) hey.getScene().getWindow();
        stage.setMinWidth(400);
        stage.setMinHeight(400);
    }

    @FXML
    private void btnCPR(ActionEvent event) {
        try {
            //int rate = Integer.parseInt(txtCPR.getText());
            Integer rate = 10;
            if (txtCPR.getText().length() == rate && !tblviewStudens.getSelectionModel().isEmpty()) {
                System.out.println("10numbers ");
            } else {
                showErrorDialog("Not a cpr", null, "Input a danish CPR number");
            }
        } catch (NumberFormatException e) {
            showErrorDialog("Not a cpr or not selected a person", null, "Input a danish CPR number and select person");
        }
    }

    @FXML
<<<<<<< HEAD
    private void openTeacher(ActionEvent event) throws IOException {
      
        if (pwTeacher.getText().length() == 10) 
        {
            System.out.println("Password Correct ");

=======
    private void openTeacher(ActionEvent event) throws IOException 
    {
        if (pwTeacher.getText().length() == 10) 
        {
            System.out.println("Password Correct ");
>>>>>>> 82ecec56469744ea2547dc80f8abca4f866d4115
            teacherWindow();
        } else {
            showErrorDialog("Not a cpr", null, "Input a danish CPR number");
        }
    }

    private void showErrorDialog(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    void teacherWindow() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("TeacherView.fxml"));
        Parent root = fxLoader.load();
        TeacherViewController controller = fxLoader.getController();
        controller.setModel(model);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
        Stage stage = (Stage) label.getScene().getWindow();
        stage.close();
    }

    private void newImageStudent(String pic) {
        Image image = new Image(getClass().getResourceAsStream(pic));
        picture.setImage(image);
    }
    
<<<<<<< HEAD
     @FXML
     private void changeNAme(MouseEvent event) throws IOException {
        Student student = tblviewStudens.getSelectionModel().getSelectedItem();
        if (!student.equals(null)) {
            nameLabel.setText(student.getName()
            + " " + student.getFamilyName());
     newImageStudent(student.getStudPic());
=======
    

    @FXML
    private void changeNAme(MouseEvent event) throws IOException 
    {      
        Students student = tblviewStudens.getSelectionModel().getSelectedItem();
        
        if(!student.equals(null))
        {
            nameLabel.setText(student.getName() 
                    + " " + student.getFamilyName());
            newImageStudent(student.getStudPic());
>>>>>>> 82ecec56469744ea2547dc80f8abca4f866d4115
        }
    }

    void setModel(Model model) 
    {
        this.model = model;
    }
}
