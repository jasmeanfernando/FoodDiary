package com.example.fooddiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

/**
 * ProfileActivity: Activity that controls activity_profile.xml.
 * @author JasmeanFernando
 */
public class ProfileActivity extends AppCompatActivity {
    //activity variables
    User user = MainActivity.currentUser;
    String user_name = user.getName();
    String user_email = user.getEmail();
    String user_birthday = user.getBirthday();
    String user_gender = user.getGender();

    /**
     * This method runs ProfileActivity.
     * @param savedInstanceState Used to reload UI state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile); //loads .xml

        //.xml variables
        TextView name = findViewById(R.id.textview_name);
        TextView email = findViewById(R.id.textview_email);
        TextView birthday = findViewById(R.id.textview_birthday);
        TextView gender = findViewById(R.id.textview_gender);
        MaterialButton returnToHomepage = findViewById(R.id.button_return);

        //initialize profile information
        name.setText(user_name);
        email.setText(user_email);
        birthday.setText(user_birthday);
        gender.setText(user_gender);

        //return to FoodDiaryActivity
        returnToHomepage.setOnClickListener(view -> {
            Intent homepage = new Intent(ProfileActivity.this, FoodDiaryActivity.class);
            startActivity(homepage);
        });
    }
}