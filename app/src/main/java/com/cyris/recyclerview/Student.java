package com.cyris.recyclerview;

/**
 * Created by cyris on 1/4/17.
 */

public class Student {

    String firstName;
    String lastName;

    public Student(String firstName, String lastName)
    {
        this.firstName=firstName;
        this.lastName=lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
