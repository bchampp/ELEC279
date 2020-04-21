/* ELEC279 | Guess Master v2 */

package com.example.brent.guessmaster;

public class Politician extends Person //Child Class of Person
{
    private String party;

    //Constructors
    public Politician() {
        super();
        party = "no party";
    } //Politician()

    //Constructor with input parameters
    public Politician(String name, Date birthDate, String entGender, String polParty, double difficulty) {
        super(name, birthDate, entGender, difficulty);
        if (polParty != null) {
            party = polParty;
        } else {
            System.out.println("Fatal Error");
            System.exit(0);
        }
    }//Politician()

    //Clone Constructor
    public Politician(Politician entity) {
        super(entity); //Call instance variables in parent class
        party = entity.party;
    }//Politician()

    //Mutators
    public String getParty() {
        return party;
    }//getParty()

    public void setParty(String party) {
        this.party = party;
    }//setParty()

    public Politician clone() {
        return new Politician(this);
    }//clone()

    public String toString() {
        return "Name is: " + getName() + "\n" + "Born on: " + getBorn().toString() + "\n" + "Gender is: " + getGender() + "\n" + "Political Party is: " + party;
    }//toString()

    public String entityType() {
        return "Politican!";
    }//entityType()
}

