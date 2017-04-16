package ebusiness2project.nixerapplication;

import java.util.Date;

public class User {
    private int userID;
    private String firstName;
    private String email;
    private String password;
    private String dob;

    //creates the task table in the database

    public User(){
    }

    public User(String firstName, String email, String password, String dob){
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.dob = dob;
        //setting entered information to this class' information
    }

    public int getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //all of these functions set and return the corresponding information

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dob=" + dob +
                '}';
    }

    //turns all the information to the necessary string layout for returning
}