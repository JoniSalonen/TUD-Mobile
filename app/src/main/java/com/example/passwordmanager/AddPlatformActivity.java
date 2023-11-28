package com.example.passwordmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddPlatformActivity extends AppCompatActivity {

    private static final int ADD_PLATFORM_REQUEST = 1;
    private EditText platformNameET;
    private EditText usernameET;
    private EditText passwordET;
    private Button saveDetailsBtn;


    public static final String EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_PLATFORM_NAME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_PLATFORM_NAME";
    public static final String EXTRA_USERNAME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_USERNAME";
    public static final String EXTRA_PASSWORD = "com.gtappdevelopers.gfgroomdatabase.EXTRA_PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_platform);


        platformNameET = findViewById(R.id.idEtPlatformName);
        usernameET = findViewById(R.id.idEtUsername);
        passwordET = findViewById(R.id.idEtPassword);
        saveDetailsBtn = findViewById(R.id.idBtnSaveDetails);



        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {

            platformNameET.setText(intent.getStringExtra(EXTRA_PLATFORM_NAME));
            usernameET.setText(intent.getStringExtra(EXTRA_USERNAME));
            passwordET.setText(intent.getStringExtra(EXTRA_PASSWORD));
        }

        saveDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String platformName = platformNameET.getText().toString();
                String username = usernameET.getText().toString();
                String password = passwordET.getText().toString();

                if (platformName.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(AddPlatformActivity.this, "Please enter the valid login details.", Toast.LENGTH_SHORT).show();
                    return;
                }

                savePlatform(platformName, username, password);
            }
        });
    }

    private void savePlatform(String platformName, String username, String password) {

        Intent data = new Intent();


        data.putExtra(EXTRA_PLATFORM_NAME, platformName);
        data.putExtra(EXTRA_USERNAME, username);
        data.putExtra(EXTRA_PASSWORD, password);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {

            data.putExtra(EXTRA_ID, id);
        }


        setResult(RESULT_OK, data);


        Toast.makeText(this, "Login details has been saved to Room Database. ", Toast.LENGTH_SHORT).show();
    }
}
