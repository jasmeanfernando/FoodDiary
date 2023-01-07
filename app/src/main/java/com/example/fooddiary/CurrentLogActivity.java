package com.example.fooddiary;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * CurrentLogActivity: Activity that controls activity_current_log.xml.
 * @author JasmeanFernando
 */
public class CurrentLogActivity extends AppCompatActivity {
    /**
     * This method runs CurrentLogActivity.
     * @param savedInstanceState Used to reload UI state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_log); //loads .xml

        //.xml variables
        TextView log_date = findViewById(R.id.textview_log_date);
        ListView log_items = findViewById(R.id.listview_log);
        TextView log_calories = findViewById(R.id.textview_log_calories);
        MaterialButton completeLog = findViewById(R.id.button_complete_log);
        MaterialButton clearLog = findViewById(R.id.button_clear_log);
        MaterialButton returnToHomepage = findViewById(R.id.button_return);

        //initialize date
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        log_date.setText(date);

        //initialize current log
        //1) get all notes from FoodDiaryActivity
        ArrayList <Note> all_notes = FoodDiaryActivity.currentNotes;
        Log current_log = new Log (all_notes);

        //2) get note details
        ArrayList <String> current_log_details = new ArrayList<>();
        for (Note note: all_notes) {
            current_log_details.add(note.toString());
        }

        //3) finalize the adapters
        ArrayAdapter <String> log_items_adapter;
        log_items_adapter = new ArrayAdapter<> (this, android.R.layout.simple_list_item_1, current_log_details);
        log_items.setAdapter(log_items_adapter);

        //initialize current log's total calories
        current_log.calculate_log();
        log_calories.setText(current_log.getLogCalories());

        //save current log
        completeLog.setOnClickListener(view -> {
            //save log to previousLogs in FoodDiaryActivity
            FoodDiaryActivity.previousLogs.add(current_log);

            //clear log in FoodDiaryActivity
            FoodDiaryActivity.currentNotes.clear();

            //clear page
            Intent clear = new Intent(CurrentLogActivity.this, CurrentLogActivity.class);
            startActivity(clear);
        });

        //clear current log
        clearLog.setOnClickListener(view -> {
            //clear log in FoodDiaryActivity
            FoodDiaryActivity.currentNotes.clear();

            //clear log
            Intent clear = new Intent(CurrentLogActivity.this, CurrentLogActivity.class);
            startActivity(clear);
        });

        //return to FoodDiaryActivity
        returnToHomepage.setOnClickListener(view -> {
            Intent homepage = new Intent(CurrentLogActivity.this, FoodDiaryActivity.class);
            startActivity(homepage);
        });
    }
}