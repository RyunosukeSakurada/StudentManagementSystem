package login;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController implements Initializable{

    LoginModel loginModel = new LoginModel();

    @FXML
    private Label dbStatus;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<option> combobox;
    @FXML
    private Button loginBtn;
    @FXML
    private Label loginStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        if(loginModel.isDatabaseConnected()){
            //inject a text to dbStatus that db is connected
            this.dbStatus.setText("Connected");
        }else{
            this.dbStatus.setText("Not Connected");
        }
        this.combobox.setItems(FXCollections.observableArrayList(option.values()));
    }

    //Login
    @FXML
    public void Login(ActionEvent event){
        try {
            if(this.loginModel.isLogin(this.firstname.getText(), this.lastname.getText(),this.password.getText(), ((option)this.combobox.getValue()).toString())){
                Stage stage = (Stage)this.loginBtn.getScene().getWindow();
                stage.close();
                switch(((option)this.combobox.getValue()).toString()){
                    case "Admin":
                        adminLogin();
                        break;
                    case "Student":
                        studentLogin();
                        break;
                    }
                }else{
                    this.loginStatus.setText("Wrong Credential");
                }
        }catch (Exception localException) {
        }
    }

    
    //as admin
    public void adminLogin(){
        try{
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminroot = (Pane)adminLoader.load(getClass().getResource("/Admin/Admin.fxml").openStream());
            adminLoader.getController();
            
            Scene scene = new Scene(adminroot);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.setResizable(false);
            adminStage.show();
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    //as student
    public void studentLogin(){
        try{
            Stage userstage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/student/student.fxml").openStream());
            loader.getController();

            Scene scene = new Scene(root);
            userstage.setScene(scene);
            userstage.setTitle("Student Dashboard");
            userstage.setResizable(false);
            userstage.show();

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
