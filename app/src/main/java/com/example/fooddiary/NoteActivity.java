package com.example.fooddiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

/**
 * ProfileActivity: Activity that controls activity_note.xml.
 * @author JasmeanFernando
 */
public class NoteActivity extends AppCompatActivity {
    /**
     * This method runs ProfileActivity.
     * @param savedInstanceState Used to reload UI state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note); //loads .xml

        //.xml variables
        EditText food_name = findViewById(R.id.edittext_food);
        EditText food_calories = findViewById(R.id.edittext_calories);
        EditText food_carbohydrates = findViewById(R.id.edittext_carbohydrates);
        EditText food_protein = findViewById(R.id.edittext_protein);
        EditText food_fat = findViewById(R.id.edittext_fat);
        MaterialButton addToLog = findViewById(R.id.button_add_log);
        MaterialButton returnToHomepage = findViewById(R.id.button_return);

        //create new note
        addToLog.setOnClickListener(view -> {
            //initialize note
            String item = food_name.getText().toString();
            int calories = Integer.parseInt(food_calories.getText().toString());
            int carbohydrates = Integer.parseInt(food_carbohydrates.getText().toString());
            int protein = Integer.parseInt(food_protein.getText().toString());
            int fat = Integer.parseInt(food_fat.getText().toString());
            Note newNote = new Note(item, calories, carbohydrates, protein, fat);

            //save note in FoodDiaryActivity
            FoodDiaryActivity.currentNotes.add(newNote);
        });

        //return to FoodDiaryActivity
        returnToHomepage.setOnClickListener(view -> {
            Intent homepage = new Intent(NoteActivity.this, FoodDiaryActivity.class);
            startActivity(homepage);
        });
    }
}