package Admin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class AdminController implements Initializable{

  TextField editFirstName = new TextField();
  TextField editLastName = new TextField();
  TextField editPassword = new TextField();
  TextField editDivision = new TextField();

  @FXML
  private TextField id;
  @FXML
  private TextField firstname;
  @FXML
  private TextField lastname;
  @FXML
  private TextField password;
  @FXML
  private TextField division;
  @FXML
  private TableView<StudentData> studentDataTableView;
  @FXML
  private TableColumn<StudentData, String> idColumn; 
  @FXML
  private TableColumn<StudentData, String> firstNameColumn; 
  @FXML
  private TableColumn<StudentData, String> lastNameColumn; 
  @FXML
  private TableColumn<StudentData, String> passwordColumn; 
  @FXML
  private TableColumn<StudentData, String> divisionColumn; 
  @FXML
  private Button addBtn;
  @FXML
  private Button clearBtn;
  @FXML
  private Button deleteBtn;
  @FXML
  private Button editBtn;

  Dialog<ButtonType> dialog = null;
  Alert alert = new Alert(AlertType.NONE);
  //instantiate a model
  AdminModel adminModel = null;

  private String editIdString;
  private String editFirstNameString;
  private String editLastNameString;
  private String editPasswordString;
  private String editDivisionString;


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.adminModel = new AdminModel();
    this.loadStudentData();  

    //disable delete and edit buttons
    editBtn.setDisable(true);
    deleteBtn.setDisable(true);
    
    studentDataTableView.setOnMouseClicked(e -> {
      StudentData selected = studentDataTableView.getSelectionModel().getSelectedItem();
      if(selected != null){
        editBtn.setDisable(false);
        deleteBtn.setDisable(false);

          editIdString = selected.idProperty().getValue();
          editFirstNameString = selected.firstnameProperty().getValue();
          editLastNameString = selected.lastnameProperty().getValue();
          editPasswordString = selected.passwordProperty().getValue();
          editDivisionString = selected.divisionProperty().getValue();
      }
    });
  }

  //load
  @FXML
  public void loadStudentData(){
      this.idColumn.setCellValueFactory( new PropertyValueFactory<StudentData, String>("id"));
      this.firstNameColumn.setCellValueFactory( new PropertyValueFactory<StudentData, String>("firstname"));
      this.lastNameColumn.setCellValueFactory( new PropertyValueFactory<StudentData, String>("lastname"));
      this.passwordColumn.setCellValueFactory( new PropertyValueFactory<StudentData, String>("password"));
      this.divisionColumn.setCellValueFactory( new PropertyValueFactory<StudentData, String>("division"));
  
      this.studentDataTableView.setItems(adminModel.getStudents());
  }
  
  //add
  @FXML
  private void addStudent(ActionEvent event){
      adminModel.addStudent(this.firstname.getText(), this.lastname.getText(), this.password.getText(),this.division.getText());
      this.loadStudentData();
      this.clearFields(null);
  }
  

  //update 
  @FXML
  private void editStudent(ActionEvent event){
      //create modal
      createModal();
      //call the modal
      dialog.showAndWait().ifPresent(response -> {
          if(response.getButtonData().equals(ButtonData.OK_DONE)){
              adminModel.editStudent(editIdString, editFirstName.getText(), editLastName.getText(),editPassword.getText(),editDivision.getText());
              this.loadStudentData();
          }
      });
  }

  //delete
  @FXML
  private void deleteStudent(ActionEvent event){
      StudentData selectedItem = studentDataTableView.getSelectionModel().getSelectedItem();
      //locally remove
      studentDataTableView.getItems().remove(selectedItem);
      //delete from DB
      adminModel.deleteStudent(selectedItem.idProperty().getValue());
  }
  

  //clear fields
  @FXML
  private void clearFields(ActionEvent event){
      this.firstname.setText("");
      this.lastname.setText(""); 
      this.password.setText(""); 
      this.division.setText("");
  }
  
  //create a modal
  private void createModal(){
    dialog = new Dialog<ButtonType>();

    dialog.setTitle("Edit a student");
    ButtonType editModalBtn = new ButtonType("Edit", ButtonData.OK_DONE);
    ButtonType cancelModalBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

    //set the content
    GridPane gridPane = new GridPane();
    gridPane.setHgap(10);   
    gridPane.setVgap(10);   
    gridPane.setPadding(new Insets(20, 10, 10, 10));

    editFirstName.setText(editFirstNameString);
    editLastName.setText(editLastNameString);
    editPassword.setText(editPasswordString);
    editDivision.setText(editDivisionString);

    
    gridPane.add(new Label("First Name"), 0, 0);
    gridPane.add(editFirstName, 1, 0);
    gridPane.add(new Label("Last Name"), 0, 1);
    gridPane.add(editLastName, 1, 1);
    gridPane.add(new Label("Password"), 0, 2);
    gridPane.add(editPassword, 1, 2);
    gridPane.add(new Label("Division"), 0, 3);
    gridPane.add(editDivision, 1, 3);

    dialog.getDialogPane().setContent(gridPane);

    dialog.getDialogPane().getButtonTypes().add(editModalBtn);
    dialog.getDialogPane().getButtonTypes().add(cancelModalBtn);
  }

}