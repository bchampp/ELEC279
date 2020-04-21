/* ELEC279 | Guess Master v2 */

import java.util.Random;
import java.util.Scanner;

public class GuessMaster {

    //Instance Variables
    private Entity[] entities;
    int totalPoints = 0;
    private int numberOfCandidateEntities;

    //Constructor
    public GuessMaster() {
        numberOfCandidateEntities = 0;
        entities = new Entity[100];
    }//GuessMaster()

    public void addEntity(Entity entity) {
        entities[numberOfCandidateEntities++] = entity.clone();
    }//addEntity()

    //Initial playgame which displays the welcome message
    public void playGame(int entityId) {
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        Entity entity = entities[entityId];

        System.out.println(entity.welcomeMessage());
        playGame(entity);
    }//playGame()

    public void playGame(Entity entity) {
        System.out.printf("\nGuess %s's birthday\n", entity.getName());
        System.out.println("(mm/dd/yyyy)");

        Scanner keyboard = new Scanner(System.in);

        while (true) {
            String input = keyboard.nextLine();
            input = input.replace("\n", "").replace("\r", "");

            if (input.equals("quit")) {
                exitGame();
            }

            if (input.equals("restart")) {
                restart();
            }

            Date date = new Date(input);

            if (date.precedes(entity.getBorn())) {
                delayConsole(1000);
                System.out.println("\nIncorrect. Try a later date.");
            } else if (entity.getBorn().precedes(date)) {
                delayConsole(1000);
                System.out.println("\nIncorrect. Try an earlier date.");
            } else {
                int tickets = entity.getTicketNumber();
                totalPoints += tickets;
                delayConsole(1000);
                System.out.println("~~~~~~~~~~~~~~BINGO!!~~~~~~~~~~~~~~\n");
                System.out.println("You just won " + tickets + " tickets this round!");
                System.out.println("Your total number of tickets is " + totalPoints + "!");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                System.out.println(entity.closingMessage());
                break;
            }
        }
    }//playGame()

    public void playGame() {
        Random randomNum = new Random();
        playGame(randomNum.nextInt(numberOfCandidateEntities)); //pass random entity index to initialize game with
        //I designed the game like this so that every time the game is played a random entity is chosen
    }//playGame()

    public void restart() {
        delayConsole(500);
        System.out.println("\n¯\\_(ツ)_/¯\n\n");
        delayConsole(1000);
        typeWriter("Let's try that again...\n", 125);
        playGame();
    }//restart()

    public void newGame() {
        System.out.println("Would you like to play again?");
        Scanner keyboard = new Scanner(System.in); //Can reuse variable name 'keyboard' here because it is local to this method
        String newGame = keyboard.next();

        //Create flags to exit game for various input cases
        if (newGame.equalsIgnoreCase("N")) {
            exitGame();
        }

        if (newGame.equalsIgnoreCase("quit")) {
            exitGame();
        }

        if (newGame.equalsIgnoreCase("No")) {
            exitGame();
        }

        //initialize a new game
        typeWriter("\nOk, let's play again!\n", 100);
        playGame();
    }//newGame()

    public void exitGame() {
        System.out.println("Thank you for playing the game!");
        System.exit(0); //Exit out of the program with exit code 0
    }//exitGame()

    public static void delayConsole(long milliSeconds) {
        try {
            Thread.sleep(milliSeconds); //Let the console 'sleep' for input amount of time
        } catch (InterruptedException exception) { //Without this catch it could prevent noticing the interrupt
            Thread.currentThread().interrupt(); //Sets the interrupt flag of the thread
        }
        //All of this is done to keep 'state' in the program.
    }//delayConsole()

    public static void typeWriter(String input, long milliSeconds) {
        for (int i = 0; i < input.length(); i++) { //Loop through the string
            System.out.print(input.charAt(i)); //Print each character in string individually
            delayConsole(milliSeconds); //Call delayConsole after each character prints
        }
    }//typeWriter

    public static void main(String[] args) {
        GuessMaster newGame = new GuessMaster();

        Politician trudeau = new Politician("Justin Trudeau", new Date("December", 25, 1971), "Male", "Liberal", 0.25);
        Singer dion = new Singer("Celine Dion", new Date("March", 30, 1961), "Female", "La voix du don Dieu", new Date("November", 6, 1981), 0.5);
        Person myCreator = new Person("myCreator", new Date("September", 1, 2000), "Female", 1);
        Country usa = new Country("United States", new Date("July", 4, 1776), "Washington D.C.", 0.1);
        newGame.addEntity(trudeau);
        newGame.addEntity(dion);
        newGame.addEntity(myCreator);
        newGame.addEntity(usa);

        //Start the game!
        delayConsole(1000);
        typeWriter("\nhello there...\n", 100);
        delayConsole(300);
        typeWriter("\n\t\t\t ~~Welcome to GuessMaster 2.0~~\n\n", 100);
        delayConsole(800);
        System.out.print("If you would like to exit the game, type 'quit' at any time!\n");
        delayConsole(2000);
        System.out.print("If you would like to start again, type 'restart' at any time!\n\n");
        delayConsole(2000);
        newGame.playGame();
    }

}
