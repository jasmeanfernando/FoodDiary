package com.example.fooddiary;

import java.util.ArrayList;

/**
 * Log Class: Holds a valid log of notes.
 * @author JasmeanFernando
 */
public class Log {
    private ArrayList <Note> log = new ArrayList <Note> ();
    private int log_calories;

    /**
     * Constructor: Creates Log object.
     * @param log ArrayList containing notes.
     */
    public Log (ArrayList <Note> log) {
        this.log = log;
    }

    /**
     * Method that calculates the total number of calories in a log.
     */
    public void calculate_log() {
        int total = 0;
        for (Note note: log) {
            total = total + note.getCalories();
        }
        this.log_calories = total;
    }

    /**
     * Getter method that returns the total number of calories in a log.
     * @return
     */
    public int getLogCalories() { return log_calories; }
}
