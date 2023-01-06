package com.example.fooddiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

/**
 * FoodDiaryActivity: Activity that controls activity_food_diary.xml.
 * @author JasmeanFernando
 */
public class FoodDiaryActivity extends AppCompatActivity {
    //activity variables
    public static ArrayList <String> currentNotes = new ArrayList <String> ();

    /**
     * This method runs FoodDiaryActivity.
     * @param savedInstanceState Used to reload UI state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_diary); //loads .xml

        //.xml variables
        MaterialButton newNote = findViewById(R.id.button_new_note);
        MaterialButton currentLog = findViewById(R.id.button_current_log);
        MaterialButton previousLogs = findViewById(R.id.button_previous_logs);
        MaterialButton editProfile = findViewById(R.id.button_edit_profile);

        //initialize ProfileActivity
        editProfile.setOnClickListener(view -> {
            Intent profile = new Intent(FoodDiaryActivity.this, ProfileActivity.class);
            startActivity(profile);
        });
    }
}