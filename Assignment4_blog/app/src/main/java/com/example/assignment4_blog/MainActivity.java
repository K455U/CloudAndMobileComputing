package com.example.assignment4_blog;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends Activity {
    private EditText userName, comment, searchText, searchDate;
    private TextView display;
    private Button submit, search;

    private List<String> entries = new ArrayList<>();
    private int entryNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.userName);
        comment = (EditText) findViewById(R.id.comment);
        searchText = (EditText) findViewById(R.id.searchText);
        searchDate = (EditText) findViewById(R.id.searchDate);
        display = (TextView) findViewById(R.id.display);
        submit = (Button) findViewById(R.id.submit);
        search = (Button) findViewById(R.id.search);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.getText().toString().isEmpty()) {
                    userName.setBackgroundColor(Color.RED);
                } else if (comment.getText().toString().isEmpty()) {
                    comment.setBackgroundColor(Color.RED);
                } else {
                    entryNumber++;
                    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                    String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                    String entry = "Entry " + entryNumber + "\nDate: " + currentDate + "\nTime: " + currentTime + "\nUser: " + userName.getText().toString() + "\nComment: " + comment.getText().toString() + "\n\n";
                    entries.add(0, entry);
                    display.setText(entry + display.getText().toString());
                    userName.setText("");
                    comment.setText("");
                    userName.setBackgroundColor(Color.WHITE);
                    comment.setBackgroundColor(Color.WHITE);
                }
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTextValue = searchText.getText().toString();
                String searchDateValue = searchDate.getText().toString();
                StringBuilder result = new StringBuilder();
                for (String entry : entries) {
                    if ((searchTextValue.isEmpty() || entry.contains(searchTextValue)) && (searchDateValue.isEmpty() || entry.contains(searchDateValue))) {
                        result.append(entry);
                    }
                }
                display.setText(result.toString());
            }
        });
    }
}