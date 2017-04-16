package ebusiness2project.nixerapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Confirm_Activity extends Activity {
    MySQLiteHelper db = new MySQLiteHelper(this);

    //this class will re-display the information the user entered for a task
    //and make sure it is correct, and they can confirm it

    private TextView name;
    private TextView description;
    private TextView price;
    private TextView location;
    private TextView category;
    private Button final_confirm;
    private Intent intent;
    private Bundle bundle;

    private String str_name;
    private String str_description;
    private String str_price;
    private String str_location;
    private String str_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm);

        // Set user's data
        initData();

        name.setText("Name: " + str_name);
        description.setText("Description: " + str_description);
        price.setText("Price: " + str_price);
        location.setText("Location: " + str_location);
        category.setText("Category: " + str_category);
        final_confirm = (Button) findViewById(R.id.final_confirm1);
        //display all the users information to screen

        final_confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Confirm_Activity.this, RedirectHomepage.class);
                //when user clicks confirm, bring them back to the homepage
                float fPrice = Float.parseFloat(str_price);
                double roundOff = Math.round(fPrice * 100.0)/100.0; //make sure the user's price is a double
                fPrice = (float)roundOff;
                Task T1 = new Task(str_name, fPrice, str_description, str_location, str_category);
                db.addTask(T1);
                startActivity(intent);
                //store all the tasks information in the database
            }
        });

    }

    private void initData(){
        name = (TextView)findViewById(R.id.name);
        description = (TextView)findViewById(R.id.description);
        price = (TextView)findViewById(R.id.price);
        location = (TextView)findViewById(R.id.location);
        category = (TextView)findViewById(R.id.category);
        //initialize all the variables that will be needed

        // Get intent and information from task creation
        intent = this.getIntent();
        bundle = intent.getExtras();
        str_name = bundle.getString("name");
        str_description = bundle.getString("description");
        str_price = bundle.getString("price");
        str_location = bundle.getString("location");
        str_category = bundle.getString("category");

    }

}