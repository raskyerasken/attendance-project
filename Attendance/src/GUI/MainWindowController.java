/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.PresentDate;
import BE.Student;
import BLL.BLLManagerCourses;
import BLL.BLLManagerStudent;
import DAL.MockData;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jacob
 */
public class MainWindowController implements Initializable 
{
    BLLManagerCourses bllcourses= new BLLManagerCourses();
    private Label label;
    private TextField txtCPR;
    private TableView<Student> tblviewStudens;
    private TableColumn<Student, String> clSurname;
    private TableColumn<Student, String> clFamilyName;
    Student stud = new Student();
    private final ObservableList<Student> studentsList
            = FXCollections.observableArrayList();
    @FXML
    private ImageView studentPhotoView;
    Model model = new Model();
    private ImageView picture;
    private TextField pwTeacher;
    BLLManagerStudent bll = new BLLManagerStudent ();
    @FXML
    private GridPane gridPaneStudentPictures;
    String pic;
    private Label studentName;
    @FXML
    private GridPane maxPane;
    
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
        } catch (SQLServerException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try { 
            changeStudentPic();
        } catch (SQLException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try 
        {
            CalculateAttendenceProcent cal = new CalculateAttendenceProcent(model);
        } 
        
        catch (ParseException ex) 
        {
            Logger.getLogger(TeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void changeStudentPic() throws SQLException
    {
        int studentCount = 0;
        for (Student student : model.getAllStudent()) 
        {
            
            //ImageView img = (ImageView) gridPaneStudentPictures.getChildren().get(studentCount);
            Image image = new Image(getClass().getResourceAsStream(student.getStudPic()));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(175);
            imageView.setFitHeight(175);
            
            Button b = new Button();
            b.setUserData(student);
            b.setMinSize(175, 175);
            b.scaleShapeProperty();
            b.setGraphic(imageView);
            b.setId("buttonForStudentPicture");
            
            
            
            Label lbl = new Label();
            lbl.setText(student.getName() + " " + student.getFamilyName()); 
            lbl.setId("OurLabel");
            lbl.alignmentProperty().set(Pos.BOTTOM_CENTER);
//            img.setImage(image);
            b.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override
                public void handle(ActionEvent event) 
                {
                    try 
                    {
                        Student student = (Student)((Button)event.getSource()).getUserData();
                        System.out.println("Student: " + student.getName());
                        PresentDate date = new PresentDate();
                        date.setCourse("The Fun Class");
                        date.setStudentID(student.getStudentID());
                        java.util.Date utilDate = new java.util.Date();
                        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                        date.setDate(sqlDate);
                        model.addAttendance(date);
                        b.setId("buttonForStudentPictureSelected");
                        
                        
                        //Loads the StudentView window and saves the info to student
                        Stage newStage = new Stage();
                        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("StudentView.fxml"));
                        Parent root = fxLoader.load();
                        StudentViewController controller = fxLoader.getController();
                        controller.setModel(model,student);
                        Scene scene = new Scene(root);
                        newStage.setScene(scene);
                        newStage.show();
                    } 
                    
                    catch (IOException ex) 
                    {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLServerException ex) {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }
            });
            gridPaneStudentPictures.add(b, studentCount%4, studentCount/4);
            maxPane.add(lbl, studentCount%4, studentCount/4);
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
    private void btnCPR(ActionEvent event) throws SQLServerException 
    {
        
        if (!tblviewStudens.getSelectionModel().isEmpty()) 
        {
            Student stud = tblviewStudens.getSelectionModel().getSelectedItem();
            PresentDate dop= new PresentDate();
            dop.setStudentID(stud.getStudentID());
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            dop.setDate(sqlDate);
            dop.setCourse("SDE");
            model.addAttendance(dop);
        }
        
        else 
            showErrorDialog("Selection Error", null, "Please select a student");
    }
    
    @FXML
    private void openTeacher(ActionEvent event) throws IOException, ParseException 
    {
//        if (pwTeacher.getText().length() == 10) 
//        {
//            System.out.println("Password Correct ");
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

    void teacherWindow() throws IOException, ParseException 
    {
        Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("TeacherView.fxml"));
        Parent root = fxLoader.load();
        TeacherViewController controller = fxLoader.getController();
        controller.setModel(model);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }

    private void newImageStudent() 
    {
        Image image = new Image(getClass().getResourceAsStream(pic));
        picture.setImage(image);
    }
   
    private void changeNAme(MouseEvent event) throws IOException 
    {     
        
//        if (!tblviewStudens.getSelectionModel().isEmpty()) 
//        { 
//            Student student = tblviewStudens.getSelectionModel().getSelectedItem();
//        
//            if(!student.equals(null))
//            {
//                nameLabel.setText(student.getName() 
//                        + " " + student.getFamilyName());
//                newImageStudent();
//            }
//        }
    }

    void setModel(Model model) 
    {
        this.model = model;
    }

    private void StudentView(ActionEvent event) throws IOException 
    {
        if (!tblviewStudens.getSelectionModel().isEmpty()) 
        {
            Stage newStage = new Stage();
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("Vaizdas.fxml"));
            Parent root = fxLoader.load();
            StudentViewController controller = fxLoader.getController();
            controller.setModel(model,tblviewStudens.getSelectionModel().getSelectedItem());
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.show();
            Stage stage = (Stage) gridPaneStudentPictures.getScene().getWindow();
            stage.close();
        }
        else 
            showErrorDialog("Selection Error", null, "Please select a student");
    }
}
