package com.example.fooddiary;

import java.util.ArrayList;

/**
 * Log Class: Holds a valid log of notes.
 * @author JasmeanFernando
 */
public class Log {
    private ArrayList <Note> log;
    private int log_calories;

    /**
     * Constructor: Creates Log object.
     * @param log ArrayList containing notes.
     */
    public Log (ArrayList <Note> log) {
        this.log = log;
    }

    /**
     * Getter method that returns the total number of calories in a log.
     * @return
     */
    public int getLogCalories() {
        int total = 0;
        for (Note note: this.log) {
            total = total + note.getCalories();
        }
        this.log_calories = total;
        return this.log_calories;
    }
}