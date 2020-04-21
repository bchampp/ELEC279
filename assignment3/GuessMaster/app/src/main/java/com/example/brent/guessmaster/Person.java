package com.example.brent.guessmaster;

/* ELEC279
   Assignment 2
   Brent Champion
   20066282
*/

public class Person extends Entity //child class of Entity
{
    private String gender;

    //Constructors
    public Person() {
        super(); //call constructor from parent class
        gender = "no gender";
    }//Person()

    //Constructor with input parameters
    public Person(String name, Date birthDate, String entgender, double difficulty) {
        super(name, birthDate, difficulty);
        if (entgender != null) {
            gender = entgender;
        } else {
            System.out.println("Fatal Error");
            System.exit(0);
        }
    }//Person()

    //Clone Constructors
    public Person(Person entity) {
        super(entity); //call parent class constructors
        gender = entity.gender;
    }//Person()

    //Accessors
    public String getGender() {
        return gender;
    }//getGender()

    //Mutators
    public void setGender(String gender) {
        this.gender = gender;
    }//setGender()

    public Person clone() {
        return new Person(this);
    }//clone()

    public String toString() {
        return "Name is: " + getName() + "\n" + "Born on: " + getBorn().toString() + "\n" + "Gender is: " + gender;
    }//toString()

    public String entityType() {
        return "Person!";
    }//entityType()

}
