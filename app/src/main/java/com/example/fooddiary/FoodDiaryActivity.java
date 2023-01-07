package com.example.fooddiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

/**
 * FoodDiaryActivity: Activity that controls activity_food_diary.xml.
 * @author JasmeanFernando
 */
public class FoodDiaryActivity extends AppCompatActivity {
    //activity variables
    User user = MainActivity.currentUser;
    String user_name = user.getName();

    public static ArrayList <Note> currentNotes = new ArrayList <Note> ();
    public static ArrayList <Log> previousLogs = new ArrayList <Log> ();

    /**
     * This method runs FoodDiaryActivity.
     * @param savedInstanceState Used to reload UI state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_diary); //loads .xml

        //.xml variables
        TextView title = findViewById(R.id.textview_title);
        MaterialButton newNote = findViewById(R.id.button_new_note);
        MaterialButton currentLog = findViewById(R.id.button_current_log);
        MaterialButton editProfile = findViewById(R.id.button_edit_profile);

        //initialize profile information
        String home_title = "Welcome back, " + user_name + "!";
        title.setText(home_title);

        //initialize NoteActivity
        newNote.setOnClickListener(view -> {
            Intent note = new Intent(FoodDiaryActivity.this, NoteActivity.class);
            startActivity(note);
        });

        //initialize CurrentLogActivity
        currentLog.setOnClickListener(view -> {
            Intent log = new Intent(FoodDiaryActivity.this, CurrentLogActivity.class);
            startActivity(log);
        });

        //initialize ProfileActivity
        editProfile.setOnClickListener(view -> {
            Intent profile = new Intent(FoodDiaryActivity.this, ProfileActivity.class);
            startActivity(profile);
        });
    }
}