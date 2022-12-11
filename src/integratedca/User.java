package integratedca;
public class User {

  private String firstName; // attributes
  private String lastName;
  private String password;
  private int id;

  public User(String userRecord) { // Default constructor 

    this.firstName = userRecord; // change the fields user table removing (user_name => name) and so on. 
    this.lastName = userRecord;
    this.password = userRecord;

    //...
    //  this.showMenu()// create menu user that shows the options as per requirements in the Project
    // each menu option, creates a method (update name); replace new userName; updatae user record for userName; return true =updated;
    //STEP 4:         

  };

  //STEP 5:
  //PUBLIC SHOW MENU
  public String getUserFirstName() {
    return firstName;
  }

  public void setUserFirstName(String userFName) {
    this.firstName = userFName;
  }

  public String getUserLastName() {
    return lastName;
  }

  public void setUserLastName(String userLName) {
    this.lastName = userLName;
  }

  public String getPassWord() {
    return password;
  }

  public void setPassWord(String pass) {
    this.password = pass;
  }

  public int getId() { // only get id as it is auto increment
    return id;
  }

}