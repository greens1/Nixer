package ebusiness2project.nixerapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MusicPage extends AppCompatActivity {

    //this page displays all tasks related to Music

    MySQLiteHelper db;
    private List<Task> myTasks = new ArrayList<Task>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_page);

        ListView listView = (ListView) findViewById(R.id.ListView);
        db = new MySQLiteHelper(this);// set up database helper

        List<Task> listTasks = db.getListOfTasksByCategory("Music and Education");// get a list of tasks that have the category music and education

        //making sure there is something in the list
        if (listTasks.size() != 0) {
            //if there is tasks in the list display them on the screen
            ArrayAdapter<Task> listAdapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1, listTasks);
            listView.setAdapter(listAdapter);
        }else{
            //if there isnt any tasks then give the user a message saying that
            Toast.makeText(this, "There are no Nixers about Music and Education", Toast.LENGTH_LONG).show();
        }
    }
}

