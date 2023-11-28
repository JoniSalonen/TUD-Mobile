package com.example.passwordmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;


// REFERENCE: This Pattern lock code is from GeeksforGeeks tutorial
//@https://www.geeksforgeeks.org/how-to-generate-pattern-password-in-android/
public class InputPassword extends AppCompatActivity {
    PatternLockView mPatternLockView;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_password);

        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", 0);
        password = sharedPreferences.getString("password", "0");

        mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
        mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {
            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {
            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                // if drawn pattern is equal to created pattern you will navigate web page details
                if (password.equals(PatternLockUtils.patternToString(mPatternLockView, pattern))) {
                    Intent intent = new Intent(getApplicationContext(), WebPageActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // other wise you will get error wrong password
                    Toast.makeText(InputPassword.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                    mPatternLockView.clearPattern();
                }
            }

            @Override
            public void onCleared() {
            }
        });
    }
}
//REFERENCE completed

