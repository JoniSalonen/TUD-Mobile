package com.example.passwordmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // REFERENCE: This Pattern lock code is from GeeksforGeeks tutorial
        //@ https://www.geeksforgeeks.org/how-to-generate-pattern-password-in-android/
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // loading is given
                SharedPreferences sharedPreferences = getSharedPreferences("PREFS", 0);
                String password = sharedPreferences.getString("password", "0");
                if (password.equals("0")) {
                    // Intent to navigate to Create Password Screen
                    Intent intent = new Intent(getApplicationContext(), CreatePassword.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Intent to navigate to Input Password Screen
                    Intent intent = new Intent(getApplicationContext(), InputPassword.class);
                    startActivity(intent);
                    finish();
                    //REFERENCE Completed
                }
            }
        }, 2000);
    }
}

