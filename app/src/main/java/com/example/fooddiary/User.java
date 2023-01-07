package com.example.fooddiary;

/**
 * User Class: Holds a valid user.
 * @author JasmeanFernando
 */
public class User {
    private String firstName, lastName, email, password, gender;
    private final String birthday;

    /**
     * Constructor: Creates User object.
     * @param firstName First name of user.
     * @param lastName Last name of user.
     * @param email Email of user.
     * @param password Password of user.
     * @param birthday Birthday of user.
     * @param gender Gender of user.
     */
    public User (String firstName, String lastName, String email, String password, String birthday, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
    }

    /**
     * This method returns name of user.
     * @return first name and last name
     */
    public String getName() { return firstName + " " + lastName; };

    /**
     * This method returns email of user.
     * @return email
     */
    public String getEmail() { return email; };

    /**
     * This method returns password of user.
     * @return password
     */
    public String getPassword() { return password; };

    /**
     * This method returns birthday of user.
     * @return birthday
     */
    public String getBirthday() { return birthday; };

    /**
     * This method returns gender of user.
     * @return gender
     */
    public String getGender() { return gender; };
}
