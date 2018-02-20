/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance;

import Classes.Students;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
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
    private TableView<Students> tblStudents;
    @FXML
    private TableColumn<Students, String> colFirstName;
    @FXML
    private TableColumn<Students, String> colLastName;
    @FXML
    private TableColumn<Students, Date> colAttence;
 Model model;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
        colFirstName.setCellValueFactory(
        new PropertyValueFactory("name"));
        
        colLastName.setCellValueFactory(
        new PropertyValueFactory("familyName"));
        
        
        colAttence.setCellValueFactory(
        new PropertyValueFactory("attence"));
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
       controller.setStudent((Students) select.getSelectedItem(),select.getSelectedIndex() );
        else
        controller.noStudent();
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }
    
    
}
