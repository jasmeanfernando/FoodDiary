package com.example.fooddiary;

/**
 * Note Class: Holds a valid user.
 * @author JasmeanFernando
 */
public class Note {
    private final String food_item;
    private int calories, carbohydrates, protein, fat;

    /**
     * Constructor: Creates Note object.
     * @param food_item Name of food item.
     * @param calories Total calories of food item.
     * @param carbohydrates Amount of carbohydrates in food item.
     * @param protein Amount of protein in food item.
     * @param fat Amount of fat in food item.
     */
    public Note (String food_item, int calories, int carbohydrates, int protein, int fat) {
        this.food_item = food_item;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.fat = fat;
    }

    /**
     * This method concatenates Note object into a String.
     * @return
     */
    @Override
    public String toString() {
        return food_item +
                ": Calories " + calories +
                "g, Carbs " + carbohydrates +
                "g, Protein " + protein +
                "g, Fat " + fat;
    }

    /**
     * Getter method that returns number of calories.
     * @return
     */
    public int getCalories() { return calories; }
}
