package ebusiness2project.nixerapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    MySQLiteHelper db = new MySQLiteHelper(this);

    //this page is the register page
    //user's will have to enter all the necessary information
    //if they dont, error message will appear

    EditText etName;
    EditText etEmail;
    EditText etPassword;
    EditText etDob;
    Button bReg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etDob = (EditText) findViewById(R.id.etDOB);
        bReg = (Button) findViewById(R.id.bReg);

        bReg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //when users click register, it will go through rigorous checking each variable

                String FName, Email, Password, DOB;
                FName = String.valueOf(etName.getText());
                Email = String.valueOf(etEmail.getText());
                Password = String.valueOf(etPassword.getText());
                DOB = String.valueOf(etDob.getText());
                //getting all the information entered

                //check if valid email
                Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
                Matcher m = p.matcher(Email);
                boolean matchFound = m.matches();

                char [] DOBtemp = DOB.toCharArray();
                char [] day1 = new char[2];
                char [] month1 = new char[2];
                char [] year1 = new char[4];
                //creating arrays that will be used to check whether the date format is correct

                if(DOBtemp.length != 10){
                    Toast.makeText(RegisterActivity.this, "Please enter valid date format", Toast.LENGTH_LONG).show();
                    //error message if the date is not long enough
                }
                else {
                    day1[0] = DOBtemp[0];
                    day1[1] = DOBtemp[1];
                    String day = new String(day1);
                    int temp = Integer.parseInt(day);
                    //assigning the day information the user entered to two char arrays so as to parse it
                    //and check it is a valid day in a month

                    month1[0] = DOBtemp[3];
                    month1[1] = DOBtemp[4];
                    String month = new String(month1);
                    int temp1 = Integer.parseInt(month);
                    //assigning the month information the user entered to two char arrays so as to parse it
                    //and check it is a valid month

                    year1[0] = DOBtemp[6];
                    year1[1] = DOBtemp[7];
                    year1[2] = DOBtemp[8];
                    year1[3] = DOBtemp[9];
                    String year = new String(year1);
                    int temp2 = Integer.parseInt(year);
                    //assigning the year information the user entered to two char arrays so as to parse it
                    //and check it is a valid year

                    //these are all error handles to make sure the necessary info is entered
                    //and the dates, emails and DOBs are valid and the right structure
                    if (FName.isEmpty() || Email.isEmpty() || Password.isEmpty() || DOB.isEmpty()) {
                        Toast.makeText(RegisterActivity.this, "Please enter the necessary information", Toast.LENGTH_LONG).show();
                    } else if (!matchFound) {
                        Toast.makeText(RegisterActivity.this, "Please enter a valid email", Toast.LENGTH_LONG).show();
                    } else if (DOBtemp[2] != '/' || DOBtemp[5] != '/' || temp < 1 || temp > 31
                            || temp1 < 1 || temp1 > 12) {
                        Toast.makeText(RegisterActivity.this, "Please enter a valid date", Toast.LENGTH_LONG).show();
                    } else if (temp2 > 1999) {
                        Toast.makeText(RegisterActivity.this, "You must be 18 to register an account", Toast.LENGTH_LONG).show();
                    } else {

                        Intent registerIntent = new Intent(RegisterActivity.this, Main.class);
                        User U1 = new User(FName, Email, Password, DOB);
                        boolean added = db.addUser(U1);
                        if (added == false) {
                            Toast.makeText(RegisterActivity.this, "Account already exists", Toast.LENGTH_LONG).show();
                            //if the account already exists, do not add and let them know
                        }
                        else {
                            RegisterActivity.this.startActivity(registerIntent);
                            Toast.makeText(RegisterActivity.this, "Registered!", Toast.LENGTH_LONG).show();
                            //if not, add them and let them know!
                        }

                    }
                }
            }
            //also need to update info in db and print to screen a success note
            //check db when logging in if account name+pword combo exist then authorise it

        });
    }
}