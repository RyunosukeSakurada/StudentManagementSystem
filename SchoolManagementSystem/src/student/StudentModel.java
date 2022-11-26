package student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Admin.StudentData;
import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentModel {

    Connection conn = null;
    private ObservableList<StudentData> studentData;

    public StudentModel(){
        this.conn = dbConnection.getConnection();
        if(this.conn == null){
            System.exit(0);
        }
    }

    public ObservableList<StudentData> getStudents(){
        String query = "SELECT * FROM login_tbl";
        try {
            this.studentData = FXCollections.observableArrayList();

            ResultSet resultSet = conn.createStatement().executeQuery(query);

            // id | createAt | first name | last name | division

            while(resultSet.next()){
                this.studentData.add(new StudentData(
                    resultSet.getString(1),
                    resultSet.getString(4),
                    resultSet.getString(6),
                    resultSet.getString(3),
                    resultSet.getString(5)
                ));
            }

            return studentData;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public void addStudent(String firstname, String lastname, String password, String division){
    String query = "INSERT INTO  login_tbl (firstname, lastname, password,division) VALUES (?, ?, ?, ?)";
    PreparedStatement statement = null;

    try {
        statement = conn.prepareStatement(query);

        statement.setString(1, firstname);
        statement.setString(2, lastname);
        statement.setString(3, password);
        statement.setString(4, division);

        statement.executeQuery();

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  }
}

