/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance;

import Classes.Students;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private Label lblpic;
    @FXML
    private Label nameLabel;
    @FXML
    private Label ageLabel;
    @FXML
    private Label attendanceLabel;
    @FXML
    private ImageView studentPhotoView;
    Model model = new Model();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        clSurname.setCellValueFactory(
                new PropertyValueFactory("name"));

        clFamilyName.setCellValueFactory(
                new PropertyValueFactory("familyName"));
        stud.setFamilyName("Hansen");
        stud.setName("Jeppe");
        studentsList.add(stud);
        Students stud2 = new Students();
        stud2.setFamilyName("Jensen");
        stud2.setName("Karl");
        studentsList.add(stud2);
        Students stud3 = new Students();
        stud3.setFamilyName("Søresen");
        stud3.setName("Sofie");
        studentsList.add(stud3);
        tblviewStudens.setItems(studentsList);
        txtCPR.textProperty().addListener(e -> {
            if (txtCPR.getText().length() == 10) {
                txtCPR.setStyle("-fx-text-fill: green");
            } else {
                txtCPR.setStyle("-fx-text-fill: red");
            }

        });

        // TODO
    }

    private void setit(ActionEvent event) 
    {
        Stage stage = (Stage) hey.getScene().getWindow();
        stage.setMinWidth(400);
        stage.setMinHeight(400);
    }

    @FXML
    private void btnCPR(ActionEvent event) 
    {
        try {
            int rate = Integer.parseInt(txtCPR.getText());
            if (txtCPR.getText().length() == 10 && !tblviewStudens.getSelectionModel().isEmpty()) {
                System.out.println("10numbers ");
            } 
            else 
            {
                showErrorDialog("Not a cpr", null, "Input a danish CPR number");
            }
        } catch (NumberFormatException e) {
            showErrorDialog("Not a cpr or not selected a person", null, "Input a danish CPR number and select person");
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

    @FXML
    private void changeNAme(MouseEvent event) throws IOException 
    {
        lblpic.setText(tblviewStudens.getSelectionModel().getSelectedItem().getName());
    }

    @FXML
    private void openNew(ActionEvent event) throws IOException {
        // newAddGenreView();
        teacherWindow();

<<<<<<< HEAD

    void setModel(Model model) {
    this.model=model; }
}

    
    
    
=======
    }
>>>>>>> 4794fa14c725ef11008893fcd274a0375cdcc5b9

}
