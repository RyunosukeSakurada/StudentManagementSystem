package student;

import java.net.URL;
import java.util.ResourceBundle;

import Admin.StudentData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentController implements Initializable{

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
  private TableColumn<StudentData, String> divisionColumn; 
  @FXML
  private Button addBtn;
  @FXML
  private Button clearBtn;

  Dialog<ButtonType> dialog = null;
  Alert alert = new Alert(AlertType.NONE);
  //instantiate a model
  StudentModel studentModel = null;



  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.studentModel = new StudentModel();
    this.loadStudentData();  
  }


  //load
  @FXML
  public void loadStudentData(){
  
      this.idColumn.setCellValueFactory( new PropertyValueFactory<StudentData, String>("id"));
      this.firstNameColumn.setCellValueFactory( new PropertyValueFactory<StudentData, String>("firstname"));
      this.lastNameColumn.setCellValueFactory( new PropertyValueFactory<StudentData, String>("lastname"));
      this.divisionColumn.setCellValueFactory( new PropertyValueFactory<StudentData, String>("division"));
  
      this.studentDataTableView.setItems(studentModel.getStudents());
  }
  
  //add
  @FXML
  private void addStudent(ActionEvent event){
      studentModel.addStudent(this.firstname.getText(), this.lastname.getText(), this.password.getText(),this.division.getText());
      this.loadStudentData();
      this.clearFields(null);
  }
  
  //clear fields
  @FXML
  private void clearFields(ActionEvent event){
      this.firstname.setText("");
      this.lastname.setText("");
      this.password.setText("");
      this.division.setText("");
  }
  

}
