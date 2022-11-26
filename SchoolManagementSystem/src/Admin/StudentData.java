package Admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentData {
  
  private final StringProperty id;
  private final StringProperty firstname;
  private final StringProperty lastname;
  private final StringProperty password;
  private final StringProperty division;

  public StudentData(String id, String firstname, String lastname, String password,String division){
    this.id = new SimpleStringProperty(id);
    this.firstname = new SimpleStringProperty(firstname);
    this.lastname = new SimpleStringProperty(lastname);
    this.password = new SimpleStringProperty(password);
    this.division = new SimpleStringProperty(division);
  }


  // Setter/Getter
  public void setID(String id){
    this.id.set(id);
  }
  public StringProperty idProperty(){
    return id;
  }
  // ------------------------------------------------------------//
  public void setFirstname(String firstname){
    this.firstname.set(firstname);
  }
  public StringProperty firstnameProperty(){
    return firstname;
  }
  // ------------------------------------------------------------//
  public void setLastname(String lastname){
    this.lastname.set(lastname);
  }
  public StringProperty lastnameProperty(){
    return lastname;
  }
  // ------------------------------------------------------------//
  public void setPassword(String password){
    this.password.set(password);
  }
  public StringProperty passwordProperty(){
    return password;
  }
  // ------------------------------------------------------------//
  public void setDivision(String division){
    this. division.set(division);
  }
  public StringProperty  divisionProperty(){
    return division;
  }
}
