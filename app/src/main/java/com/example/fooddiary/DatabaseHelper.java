package com.example.fooddiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * databaseHelper: Class that implements use of SQLite database.
 * @author JasmeanFernando
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    //class variables
    Context context;
    private static final String DATABASE_NAME = "FoodDiary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "registered_accounts";
    private static final String COLUMN_F_NAME = "first_name";
    private static final String COLUMN_L_NAME = "last_name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_BDAY = "bday";
    private static final String COLUMN_GENDER = "gender";

    /**
     * Constructor: Creates DatabaseHelper object.
     * @param context
     */
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This method runs DatabaseHelper.
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create table
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_F_NAME + " TEXT, " +
                COLUMN_L_NAME + " TEXT, " +
                COLUMN_EMAIL + " VARCHAR, " +
                COLUMN_PASSWORD + " VARCHAR, " +
                COLUMN_BDAY + " DATE, " +
                COLUMN_GENDER + "TEXT);";

        //execute table
        sqLiteDatabase.execSQL(query);
    }

    /**
     * This method helps run DatabaseHelper.
     * @author JasmeanFernando
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long addAccount(String firstName, String lastName, String email, String password, String birthday, String gender) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_F_NAME, firstName);
        cv.put(COLUMN_L_NAME, firstName);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PASSWORD, password);
        cv.put(COLUMN_BDAY, birthday);
        cv.put(COLUMN_GENDER, gender);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, cv);
        return result;
    }
}
