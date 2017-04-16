package ebusiness2project.nixerapplication;

import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class TaskView extends Fragment {

    private List<Task> myTasks = new ArrayList<Task>();
    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.taskview, container, false);
        super.onCreate(savedInstanceState);
        MySQLiteHelper db = new MySQLiteHelper(getActivity());

        List<Task> listTasks = db.getAllTasks();
        if (listTasks != null) {
            ArrayAdapter<Task> adapter = new ArrayAdapter<Task>(getActivity(), android.R.layout.simple_list_item_1, listTasks);
            ListView lv = (ListView) myView.findViewById(R.id.ListView);
            lv.setAdapter(adapter);
        }

        if (listTasks == null) {
            // to do
        }
        return myView;

    }

}
