package ebusiness2project.nixerapplication;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class Category extends Fragment {

    ImageButton home;
    ImageButton tech;
    ImageButton cars;
    ImageButton music;
    ImageButton animals;

    //scrollable page where user can view all categories and click one
    //to display corresponding tasks



    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.category_layout, container, false); //show the relevant layout file
        home = (ImageButton) myView.findViewById(R.id.HomeImageButton);
        tech = (ImageButton) myView.findViewById(R.id.TechImageButton);
        cars = (ImageButton) myView.findViewById(R.id.CarsImageButton);
        music = (ImageButton) myView.findViewById(R.id.MusicImageButton);
        animals = (ImageButton) myView.findViewById(R.id.AnimalsImageButton);
        // declare the buttons and create variables for them

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loadHomeDiyPage = new Intent(getActivity(), HomeDiyPage.class);
                startActivity(loadHomeDiyPage);
                //when user clicks this button, the Home & DIY page with tasks will be displayed
            }
        });

        tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loadTechPage = new Intent(getActivity(), TechPage.class);
                startActivity(loadTechPage);
                //when user clicks this button, the technology page with tasks will be displayed
            }
        });

        cars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loadCarsPage = new Intent(getActivity(), CarsPage.class);
                startActivity(loadCarsPage);
                //when user clicks this button, the cars page with tasks will be displayed
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loadMusicPage = new Intent(getActivity(), MusicPage.class);
                startActivity(loadMusicPage);
                //when user clicks this button, the music page with tasks will be displayed
            }
        });

        animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loadAnimalsPage = new Intent(getActivity(), AnimalsPage.class);
                startActivity(loadAnimalsPage);
                //when user clicks this button, the animals page with tasks will be displayed
            }
        });

        return myView;


    }
}