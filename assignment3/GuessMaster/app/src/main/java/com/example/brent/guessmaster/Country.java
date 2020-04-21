package com.example.brent.guessmaster;

/* ELEC279
   Assignment 2
   Brent Champion
   20066282
*/

public class Country extends Entity //child class of Entity
{
    private String capitalCity;

    //Constructors
    public Country() {
        super(); //call parent class constructor
        capitalCity = "No Capital City"; //Empty Constructor
    }//Country()

    public Country(String name, Date birthDate, String countCapital, double difficulty) {
        super(name, birthDate, difficulty); //call parent class constructor with input parameters
        if (countCapital != null) //check if empty
        {
            capitalCity = countCapital;
        } else {
            System.out.println("Fatal Error");
            System.exit(0);
        }
    }//Country()

    //Copy Constructor
    public Country(Country clone) {
        super(clone);
        capitalCity = clone.capitalCity;
    }//Country()

    //Accessors
    public String getCapital() {
        return capitalCity;
    }//getCapital()

    //Mutators
    public void setCapital(String capital) {
        this.capitalCity = capital;
    }//setCapital()

    //Methods
    public String entityType() {
        return "Country!";
    }//entityType()

    public String toString() {
        return "Name is: " + getName() + "\n" + "Born on: " + getBorn().toString() + "\n" + "Capital City is: " + capitalCity;
    }//toString()

    public Country clone() {
        return new Country(this); //call this() which returns all of the instance variables of the class
    }//clone()

}
