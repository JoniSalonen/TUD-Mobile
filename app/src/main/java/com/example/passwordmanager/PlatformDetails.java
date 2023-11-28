package com.example.passwordmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlatformDetails extends AppCompatActivity {

    private static final int ADD_PLATFORM_REQUEST = 1;
    Button btnUpdate;
    Button btnDelete;
    TextView tvPlatform;
    TextView tvUsername;
    TextView tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platform_details);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        tvPlatform = findViewById(R.id.tvPlatformNa);
        tvUsername = findViewById(R.id.tvUName);
        tvPassword = findViewById(R.id.tvPasswd);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlatformDetails.this, AddPlatformActivity.class);
                startActivityForResult(intent, ADD_PLATFORM_REQUEST);

            }
        });

    }


}