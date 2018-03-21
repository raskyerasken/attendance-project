/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.DateOfPresent;
import BE.Student;
import BLL.BLLManager;
import DAL.MockData;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jacob
 */
public class MainWindowController implements Initializable 
{

    @FXML
    private Label label;
    @FXML
    private TextField txtCPR;
    private TableView<Student> tblviewStudens;
    private TableColumn<Student, String> clSurname;
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
    BLLManager bll = new BLLManager ();
    @FXML
    private GridPane gridPaneStudentPictures;
    
    String[][] board = new String[9][9];

   
    String pic;
    @FXML
    private ImageView pictureTest;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        MockData md = new MockData();
        try 
        {
            md.add(model);
        } 
        
        catch (ParseException ex) 
        {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        changeStudentPic();
    }
    
    public void setboard(String[][] board)
    {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board[i][j] = board[i][j];
            }
            String[] strings = board[i];
            
        }
    }
    
    
    private void changeStudentPic()
    {
        int studentCount = 0;
        for (Student student : model.getAttence()) 
        {
            ImageView img = (ImageView) gridPaneStudentPictures.getChildren().get(studentCount);
            Image image = new Image(getClass().getResourceAsStream(student.getStudPic()));
            img.setImage(image);
            studentCount++;
        }
    }

    private void textChangerTeacher() 
    {
        pwTeacher.textProperty().addListener(new InvalidationListener() 
        {
            @Override
            public void invalidated(Observable e) 
            {
                if (pwTeacher.getText().length() == 10) 
                {
                    pwTeacher.setStyle("-fx-text-fill: green");
                } 

                else 
                {
                    pwTeacher.setStyle("-fx-text-fill: red");
                }
            }
        });
    }
    
    private void textChangeCPR() 
    {
        
        txtCPR.textProperty().addListener(new InvalidationListener() 
        {
            @Override
            public void invalidated(Observable e) 
            {
                if (txtCPR.getText().length() == 10) 
                {
                    txtCPR.setStyle("-fx-text-fill: green");
                } 
                else 
                {
                    txtCPR.setStyle("-fx-text-fill: red");
                }
            }
        });
        
    }

    @FXML
    private void btnCPR(ActionEvent event) 
    {
        
        if (!tblviewStudens.getSelectionModel().isEmpty()) 
        {
            Student stud = tblviewStudens.getSelectionModel().getSelectedItem();
            DateOfPresent dop= new DateOfPresent();
            dop.setStudentID(stud.getStudentID());
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            dop.setDate(sqlDate);
            dop.setCourse("SDE");
            model.addAttence(dop);
        }
        else 
            showErrorDialog("Selection Error", null, "Please select a student");
    }
    
    @FXML
    private void openTeacher(ActionEvent event) throws IOException 
    {
//        if (pwTeacher.getText().length() == 10) 
//        {
            System.out.println("Password Correct ");
            teacherWindow();
//        } 
//        else 
//        {
//            showErrorDialog("Not a cpr", null, "Input a danish CPR number");
//        }
    }

    private void showErrorDialog(String title, String header, String message) 
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    void teacherWindow() throws IOException 
    {
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

    private void newImageStudent() 
    {
        Image image = new Image(getClass().getResourceAsStream(pic));
        picture.setImage(image);
    }
   
    private void changeNAme(MouseEvent event) throws IOException 
    {     
        if (!tblviewStudens.getSelectionModel().isEmpty()) 
        { 
            Student student = tblviewStudens.getSelectionModel().getSelectedItem();
        
            if(!student.equals(null))
            {
                nameLabel.setText(student.getName() 
                        + " " + student.getFamilyName());
                newImageStudent();
            }
        }
    }

    void setModel(Model model) 
    {
        this.model = model;
    }

    @FXML
    private void StudentView(ActionEvent event) throws IOException 
    {
        if (!tblviewStudens.getSelectionModel().isEmpty()) 
        {
            Stage newStage = new Stage();
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("Vaizdas.fxml"));
            Parent root = fxLoader.load();
            VaizdasController controller = fxLoader.getController();
            controller.setModel(model,tblviewStudens.getSelectionModel().getSelectedItem());
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.show();
            Stage stage = (Stage) ageLabel.getScene().getWindow();
            stage.close();
        }
        else 
            showErrorDialog("Selection Error", null, "Please select a student");
    }
}
