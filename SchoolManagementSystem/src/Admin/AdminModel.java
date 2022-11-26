package Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class AdminModel {

Connection conn = null;
private ObservableList<StudentData> studentData;

public AdminModel(){
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

        // id | createAt | first name | last name | password |  division

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
    String query = "INSERT INTO  login_tbl (firstname, lastname, password, division) VALUES (?, ?, ?, ?)";
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

//edit function
public void editStudent(String id, String firstname, String lastname, String password, String division){
    String sql = 
    "UPDATE login_tbl SET firstname = ?,  lastname= ? , password= ?, division = ? WHERE id = ?";
    PreparedStatement statement = null;

    try {
        Connection conn = dbConnection.getConnection();
        statement = conn.prepareStatement(sql);

        statement.setString(1, firstname);
        statement.setString(2, lastname);
        statement.setString(3, password);
        statement.setString(4, division);
        statement.setInt(5, Integer.parseInt(id));

        statement.execute();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally{
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



    //delete function
    public void deleteStudent(String id){
        String sql = "DELETE FROM  login_tbl WHERE id = ?";
        PreparedStatement statement = null;

        try {
            Connection conn = dbConnection.getConnection();
            statement = conn.prepareStatement(sql);

            statement.setInt(1, Integer.parseInt(id));

            statement.execute();
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
