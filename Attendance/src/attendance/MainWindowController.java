/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance;

import Classes.Students;
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
public class MainWindowController implements Initializable {

    @FXML
    private AnchorPane hey;

    @FXML
    private Label label;
    @FXML
    private TextField txtCPR;
    @FXML
    private TableView<Students> tblviewStudens;
    @FXML
    private TableColumn<Students, String> clSurname;
    @FXML
    private TableColumn<Students, String> clFamilyName;
    Students stud = new Students();
    private final ObservableList<Students> studentsList
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
    public void initialize(URL url, ResourceBundle rb) {
        tblviewStudens.setItems(studentsList);

        clSurname.setCellValueFactory(
                new PropertyValueFactory("name"));

        clFamilyName.setCellValueFactory(
                new PropertyValueFactory("familyName"));

        stud.setFamilyName("Hansen");
        stud.setName("Jeppe");
        stud.setStudPic("/Image/surprised.png");
        studentsList.add(stud);

        Students stud2 = new Students();
        stud2.setFamilyName("Jensen");
        stud2.setName("Karl");
        stud2.setStudPic("/Image/happy.png");
        studentsList.add(stud2);

        Students stud3 = new Students();
        stud3.setFamilyName("Søresen");
        stud3.setName("Sofie");
        stud3.setStudPic("/Image/angry.png");
        studentsList.add(stud3);
<<<<<<< HEAD

//        newImageStudent("/Image/happy.png");
//        if(tblviewStudens.getSelectionModel().getSelectedItem().getFamilyName().contentEquals("Hansen"))
//        {
//            newImageStudent("/Image/happy.png");
//        }
=======
        
        Students stud4 = new Students();
        stud4.setFamilyName("Rask");
        stud4.setName("Kasper");
        stud4.setStudPic("/Image/sadface.png");
        studentsList.add(stud4);
        
        Students stud5 = new Students();
        stud5.setFamilyName("Søresen");
        stud5.setName("Jacob");
        stud5.setStudPic("/Image/happy.png");
        studentsList.add(stud5);
        
        Students stud6 = new Students();
        stud6.setFamilyName("Andersen");
        stud6.setName("Kristofer");
        stud6.setStudPic("/Image/surprised.png");
        studentsList.add(stud6);
        
        Students stud7 = new Students();
        stud7.setFamilyName("Moniz");
        stud7.setName("Fabio");
        stud7.setStudPic("/Image/happy.png");
        studentsList.add(stud7);
        
        Students stud8 = new Students();
        stud8.setFamilyName("Sim");
        stud8.setName("Skomandas");
        stud8.setStudPic("/Image/tired.png");
        studentsList.add(stud8);
        
        Students stud9 = new Students();
        stud9.setFamilyName("Armando");
        stud9.setName("Gionathan");
        stud9.setStudPic("/Image/scared.png");
        studentsList.add(stud9);
        
>>>>>>> d1fc6b200c14a172c2208472c9174396300b2d75
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
        if (pwTeacher.getText().length() == 10) {
            System.out.println("10numbers ");
=======
    private void openTeacher(ActionEvent event) throws IOException 
    {
        if (pwTeacher.getText().length() == 10) 
        {
            System.out.println("Password Correct ");
>>>>>>> d1fc6b200c14a172c2208472c9174396300b2d75
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
<<<<<<< HEAD

=======
>>>>>>> d1fc6b200c14a172c2208472c9174396300b2d75
    }
    
    

    @FXML
<<<<<<< HEAD
    private void changeNAme(MouseEvent event) throws IOException {
        Students student = tblviewStudens.getSelectionModel().getSelectedItem();
        if (!student.equals(null)) {
            nameLabel.setText(student.getName()
                              + " " + student.getFamilyName());
=======
    private void changeNAme(MouseEvent event) throws IOException 
    {      
        Students student = tblviewStudens.getSelectionModel().getSelectedItem();
        
        if(!tblviewStudens.getSelectionModel().isEmpty())
        {
            nameLabel.setText(student.getName() 
                    + " " + student.getFamilyName());
>>>>>>> d1fc6b200c14a172c2208472c9174396300b2d75
            newImageStudent(student.getStudPic());
        }
    }

    void setModel(Model model) {
        this.model = model;
    }
}
