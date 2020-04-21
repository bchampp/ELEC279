package com.example.brent.guessmaster;

/* ELEC279
   Assignment 2
   Brent Champion
   20066282
*/

public abstract class Entity { //Base Abstract Class

    //Instance Variables
    private double difficulty;
    private String name;
    private Date born;

    //Constructors
    public Entity() {
        name = "No Name";
        born = new Date(1, 1, 1000);
        difficulty = 0;
    }//Entity()

    //Constructor with input parameters
    public Entity(String name, Date birthDate, double difficulty) {
        this.name = name;
        this.born = new Date(birthDate); //no privacy leak
        this.difficulty = difficulty;
    }//Entity()

    //Copy Constructor
    public Entity(Entity clone) {
        this.name = clone.name;
        this.born = new Date(clone.born); //avoiding privacy leak!
        this.difficulty = clone.difficulty;
    }//Entity()


    //Accessors
    public int getTicketNumber() {
        double ticketNum;
        ticketNum = difficulty * 100;
        return (int) ticketNum; //Typecast to be an int
    }//getTicketNumber()

    public String getName() {
        return new String(name);
    }//getName()

    public Date getBorn() {
        return new Date(born);
    }//getBorn

    public double getDifficulty() {
        return difficulty;
    }//getDifficulty

    //Methods
    public String toString() {
        return "Name is: " + name + "\n" + "Born on: " + born.toString() + "\n";
    }//toString()

    //Abstract methods, no method definitions, only protocols
    public abstract String entityType();

    public abstract Entity clone();

    public String welcomeMessage() {
        return "Welcome! Let's start the game! This entity is a " + entityType();

    }//welcomeMessage()

    public String closingMessage() {
        return "Congratulations! The detailed information of the entity you guessed is: " + "\n" + toString();
    }//closingMessage()
}
