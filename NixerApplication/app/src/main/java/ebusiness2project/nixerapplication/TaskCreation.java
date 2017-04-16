package ebusiness2project.nixerapplication;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.database.sqlite.SQLiteOpenHelper;



public class TaskCreation extends Fragment {

    private Button bt_confirm;
    private EditText name;
    private EditText description;
    private EditText price;
    private EditText location;
    private Spinner category;


    //Intent intent;
    View myView;
    Button but1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_main_task, container, false);

        // Set adapter to the category
        Spinner sp = (Spinner)myView.findViewById(R.id.category);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.category, R.layout.spinnerlayout);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sp.setAdapter(adapter);

        // Initialize all the variables that we will need to store user info
        initData();
        // get the users info and change it to a string, then ensure information has been entered,
        //if not, put error messages
        confirm();

        return myView;
    }

    private void initData(){
        bt_confirm = (Button)myView.findViewById(R.id.confirm);
        name = (EditText)myView.findViewById(R.id.name);
        description = (EditText)myView.findViewById(R.id.description);
        price = (EditText) myView.findViewById(R.id.price);
        location = (EditText) myView.findViewById(R.id.location);
        category = (Spinner) myView.findViewById(R.id.category);
    }

    private void confirm(){
        bt_confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String str_name = name.getText().toString();
                String str_description = description.getText().toString();
                String str_price = price.getText().toString();
                String str_location = location.getText().toString();
                String str_category = (String)category.getSelectedItem();
                //get entered information and convert it to string to check

                if(str_name.equals("")){
                    name.setHint("Please input your name");
                }

                if(str_description.equals("")){
                    description.setHint("Please input your description");
                }

                if(str_price.equals("")){
                    price.setHint("Input your price");
                }

                if(str_location.equals("")){
                    location.setHint("Please input your location");
                }

                //error handling to ensure information has been entered


                // Use bundle to save the data to pass to confirm activity
                Bundle bundle = new Bundle();
                bundle.putString("name", str_name);
                bundle.putString("description", str_description);
                bundle.putString("price", str_price);
                bundle.putString("location", str_location);
                bundle.putString("category", str_category);

                if(str_name.equals("")||str_description.equals("")||str_location.equals("")
                        ||str_price.equals("")){
                    return;
                }
                // Put bundle into intent

                Intent intent = new Intent(getActivity(), Confirm_Activity.class);
                intent.putExtras(bundle);
                startActivity(intent);

                //create bundle and pass it to confirm activity
            }
        });
    }


}