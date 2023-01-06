package com.example.fooddiary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.google.android.material.button.MaterialButton;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RegistrationActivity: Activity that controls activity_registration.xml.
 * @author JasmeanFernando
 */
public class RegistrationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    //activity variables
    String user_first_name;
    String user_last_name;
    String user_email;
    String user_password;
    String user_birthday;
    int user_gender;

    /**
     * This method runs RegistrationActivity.
     * @param savedInstanceState Used to reload UI state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration); //loads .xml

        //.xml variables
        EditText first_name = findViewById(R.id.edittext_firstname);
        EditText last_name = findViewById(R.id.edittext_lastname);
        EditText email = findViewById(R.id.edittext_email);
        EditText password = findViewById(R.id.edittext_password);
        MaterialButton birthday = findViewById(R.id.button_calendar);
        RadioGroup genders = findViewById(R.id.radiogroup_genders);
        MaterialButton sign_up = findViewById(R.id.button_register);

        //initialize AlertDialog
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        buildAlert(alert);

        //initialize birthday selection
        birthday.setOnClickListener(v -> datePickerDialog());

        //initialize the registration of a new user
        sign_up.setOnClickListener(view -> {
            user_first_name = String.valueOf(first_name.getText());
            user_last_name = String.valueOf(last_name.getText());
            user_email = String.valueOf(email.getText());
            user_password = String.valueOf(password.getText());

            /* 1) validate first and last name
             * case: must be all letters
             */
            if (!user_first_name.matches("[a-zA-Z]+") || !user_last_name.matches("[a-zA-Z]+")) {
                alert.setMessage("First and last name must only contain letters.");
                AlertDialog error = alert.create();
                error.show();
            }

            /* 2) validate email
             * case: must contain @.
             */
            validate_email(user_email);

            /* 3) validate password
             * case: must have 1 lowercase and uppercase letter, 1 number, 1 symbol, and be between 8-20 characters.
             */
            validate_password(user_password);

            /* 4) validate gender
             * case: must make a selection.
             */
            user_gender = genders.getCheckedRadioButtonId();
            if (user_gender == -1) {
                alert.setMessage("Please select a gender.");
                AlertDialog error = alert.create();
                error.show();
            }

            //5) register member to database
            if (user_first_name.matches("[a-zA-Z]+") && user_last_name.matches("[a-zA-Z]+") &&
                    validate_email(user_email) && validate_password(user_password) && user_gender != -1) {
                //obtain gender
                RadioButton selected_gender = findViewById(user_gender);
                User newUser = new User(user_first_name, user_last_name, user_email, user_password, user_birthday, selected_gender.getText().toString());

                //notify
                alert.setMessage("Successfully created account!");
                AlertDialog confirmation = alert.create();
                confirmation.show();

                //add to database
                MainActivity.registeredUsers.add(newUser);

                //return to MainActivity
                clearActivity();
            }
        });
    }

    /**
     * This method is used to build AlertDialog.
     * @param alert AlertDialog object
     */
    private void buildAlert(AlertDialog.Builder alert) {
        alert.setCancelable(true);
        alert.setPositiveButton( "Yes", (dialog, id) -> dialog.cancel());
        alert.setNegativeButton("No", (dialog, id) -> dialog.cancel());
    }

    /**
     * This method is used to create an instance of DatePickerDialog.
     */
    public void datePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    /**
     * This method is used to implement DatePickerDialog.
     * @param datePicker DatePicker object
     * @param year Year of birthday
     * @param month Month of birthday
     * @param dayOfMonth Day of birthday
     */
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        user_birthday = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
    }

    /**
     * This method checks if email is valid.
     * @param e Email of user
     * @return true if valid, false otherwise.
     */
    public boolean validate_email(String e) {
        //initialize and compile regex
        String regex =  "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);

        //compare regex to email
        Matcher matcher = pattern.matcher(e);
        if (!matcher.matches()) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            buildAlert(alert);

            alert.setMessage("Email must be in name@service.domain format.");
            AlertDialog error = alert.create();
            error.show();
            return false;
        }

        return true;
    }

    /**
     * This method checks if password is valid.
     * @param p Password of user
     * @return true if valid, false otherwise.
     */
    public boolean validate_password(String p) {
        //initialize and compile regex
        String regex =  "^(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[0-9])"+ "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(regex);

        //compare regex to password
        Matcher matcher = pattern.matcher(p);
        if (!matcher.matches()) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            buildAlert(alert);

            alert.setMessage("Password must be between 8-20 characters, have at least one uppercase and lowercase letter, one digit, and one symbol.");
            AlertDialog error = alert.create();
            error.show();
            return false;
        }

        return true;
    }

    /**
     * This method returns to MainActivity.
     */
    private void clearActivity() {
        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
        startActivity(intent);
    }
}