package ebusiness2project.nixerapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends AppCompatActivity {
    MySQLiteHelper db = new MySQLiteHelper(this);

    //this page is the login and register page.
    //it is the first page the user will see after the splash screen

    EditText etEmail;
    EditText etPassword;
    Button bLogin;
    Button bRegister;
    String Email, Password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bLogin = (Button) findViewById(R.id.bLogin);
        bRegister = (Button) findViewById(R.id.bRegister);
        //initialize all the buttons that the user can click

        bRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Main.this, RegisterActivity.class);
                Main.this.startActivity(registerIntent);
                //when user clicks this, it will take them to the register page
            }


        });

        bLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                etEmail = (EditText) findViewById(R.id.etEmail);
                etPassword = (EditText) findViewById(R.id.etPassword);
                //initialize the varaibles that will store user's login information

                Email = etEmail.getText().toString();
                Password = etPassword.getText().toString();
                //getting the info the user entered and converting it to a string to add to the database

                if (Email.isEmpty() && Password.isEmpty()) {
                    Toast.makeText(Main.this, "Please enter some information", Toast.LENGTH_LONG).show();
                    //error handle incase user enters no information
                }
                else if(Email.isEmpty()){
                    Toast.makeText(Main.this, "Please enter your email", Toast.LENGTH_LONG).show();
                    //error handle incase user enters no email
                }
                else if(Password.isEmpty()){
                    Toast.makeText(Main.this, "Please enter your password", Toast.LENGTH_LONG).show();
                    //error handle incase user enters no password
                }

                else {

                    if (db.userLogin(Email, Password) == true) {
                        Intent homepageIntent = new Intent(Main.this, RedirectHomepage.class);
                        Main.this.startActivity(homepageIntent);
//                        android.app.FragmentManager fragmentManager = getFragmentManager();
//                        fragmentManager.beginTransaction()
//                                .replace(R.id.activity_main
//                                        , new TaskView())
//                                .commit();
                        //check if information user entered is in the database, and if it is, bring them to the homepage
                    }
                    else {
                        Toast.makeText(Main.this, "Incorrect Email or Password", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Main.this, Main.class);
                        startActivity(intent);
                        //if the information the user entered is not in the database,
                        //display error message
                    }


                }
            }


        });

    }
}