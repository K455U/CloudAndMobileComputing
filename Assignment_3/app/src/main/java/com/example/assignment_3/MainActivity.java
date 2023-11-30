package com.example.assignment_3;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {

    EditText firstName, lastName, birthday, address, email, phone;
    int randomNumber;
    String dateTime;
    TextView randomLabel;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Update randomNumber and dateTime
        randomNumber = generateRandomNumber();
        dateTime = getCurrentDateTime();

        // Show the dateTime and randomNumber in a Toast
        Toast.makeText(this, "Date & Time: " + dateTime + "\nRandom Number: " +
                randomNumber, Toast.LENGTH_LONG).show();

        // Set the orientation to portrait

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        birthday = (EditText) findViewById(R.id.birthday);
        address = (EditText) findViewById(R.id.address);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        randomLabel = (TextView) findViewById(R.id.randomLabel);



        // Start the Handler to update the random number every second
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                randomNumber = generateRandomNumber();
                randomLabel.setText(String.valueOf(randomNumber));
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_main);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("randomNumber", randomNumber);
        outState.putString("dateTime", dateTime);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        randomNumber = savedInstanceState.getInt("randomNumber");
        dateTime = savedInstanceState.getString("dateTime");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop the Handler when the Activity is destroyed
        handler.removeCallbacksAndMessages(null);
    }

    private int generateRandomNumber() {
        // Generate a random number
        return (int)(Math.random() * 100);
    }

    private String getCurrentDateTime() {
        // Get the current date and time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault());
        return sdf.format(new Date());
    }
}