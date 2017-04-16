package ebusiness2project.nixerapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1800; //time the picture shows up for

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {



            @Override
            public void run() {

                Intent i = new Intent(SplashScreen.this, Main.class);
                startActivity(i);


                finish();
            }
        }, SPLASH_TIME_OUT);
        // after the splash time out, we create an intent and change to the login page
    }

}