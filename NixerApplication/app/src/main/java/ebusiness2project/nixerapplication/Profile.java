package ebusiness2project.nixerapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class Profile extends Fragment {

    //this page is the user's profile and will display the user's information

    TextView tvName;
    TextView tvEmail;
    TextView tvDOB;
    TextView tvProfile;
    View myView;
    //@Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MySQLiteHelper db = new MySQLiteHelper(getActivity());

        myView = inflater.inflate(R.layout.profile_layout, container, false);
        super.onCreate(savedInstanceState);
        tvName = (TextView) myView.findViewById(R.id.textView3);
        tvEmail = (TextView) myView.findViewById(R.id.textView8);
        tvDOB = (TextView) myView.findViewById(R.id.textView10);
        tvProfile = (TextView) myView.findViewById(R.id.tvProfile);
        //initializing the variables we will need to display user information

        User user = new User();
        int temp = db.returnUserID();
        user = db.getUser(temp);

        tvName.setText(user.getFirstName());
        tvEmail.setText(user.getEmail());
        tvDOB.setText(user.getDob());




        return myView;
    }

}