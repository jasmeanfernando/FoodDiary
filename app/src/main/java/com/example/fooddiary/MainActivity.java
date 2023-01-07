package com.example.fooddiary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MainActivity: Activity that controls activity_main.xml.
 * @author JasmeanFernando
 */
public class MainActivity extends AppCompatActivity {
    //activity variables
    public static ArrayList <User> registeredUsers = new ArrayList<>();
    public static User currentUser;
    String user_email;
    String user_password;

    /**
     * This method runs MainActivity.
     * @param savedInstanceState Used to reload UI state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //loads .xml

        //.xml variables
        EditText email = findViewById(R.id.edittext_email);
        EditText password = findViewById(R.id.edittext_password);
        TextView forgotPassword = findViewById(R.id.textview_forgotpassword);
        MaterialButton login = findViewById(R.id.button_login);
        MaterialButton register = findViewById(R.id.button_register);

        //initialize AlertDialog
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        buildAlert(alert);

        //initialize FoodDiaryActivity
        login.setOnClickListener(view -> {
            user_email = String.valueOf(email.getText());
            user_password = String.valueOf(password.getText());

            //check if email and password are valid
            validate_email(user_email);
            validate_password(user_password);

            //check if user exists in the database
            if (validate_user(user_email, user_password)) {
                Intent returningUser = new Intent(MainActivity.this, FoodDiaryActivity.class);
                startActivity(returningUser);
            }
            else {
                alert.setMessage("User does not exist.");
                AlertDialog error = alert.create();
                error.show();
            }
        });

        //initialize RegistrationActivity
        register.setOnClickListener(view -> {
            Intent newUser = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(newUser);
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
     * This method checks if user is in the database.
     * @param e Email of user
     * @param p Password of user
     * @return true if valid, false otherwise.
     */
    public boolean validate_user(String e, String p) {
        for (User user: registeredUsers) {
            if (user.getEmail().equalsIgnoreCase(e) && user.getPassword().equalsIgnoreCase(p)) {
                currentUser = user;
                return true;
            }
        }

        return false;
    }
}