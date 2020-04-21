import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GuessMaster {
    private int numberOfCandidateEntities;
    private Entity[] entities;

    public GuessMaster() {
        this.numberOfCandidateEntities = 0;
        this.entities = new Entity[numberOfCandidateEntities];
    }

    public GuessMaster(int numberOfCandidateEntities) {
        this.numberOfCandidateEntities = numberOfCandidateEntities;
        this.entities = new Entity[numberOfCandidateEntities];
    }

    public void setGuessMaster(int numberOfCandidateEntities) {
        this.numberOfCandidateEntities = numberOfCandidateEntities;
        this.entities = new Entity[numberOfCandidateEntities];
    }

    public int getNumberOfCandidateEntities() {
        return numberOfCandidateEntities;
    }

    public Entity[] getEntity() {
        Entity[] newEntities = new Entity[numberOfCandidateEntities];
        for(int i= 0; i < entities.length; i++) {
            newEntities[i] = entities[i];
        }
        return newEntities;
    }

    public void addEntity(Entity entity) {
        Entity[] newEntities = Arrays.copyOf(entities, numberOfCandidateEntities + 1);
        this.entities = newEntities;
        this.entities[numberOfCandidateEntities] = entity;
        this.numberOfCandidateEntities++;
    }

    public void playGame(Entity entity) {
        System.out.println("~~ Hello, welcome to GuessMaster! ~~");
        System.out.println(String.format("Guess the birthday of %s", entity.getName()));
        System.out.println("Input in format month/day/year");
        Scanner keyboard = new Scanner(System.in);
        boolean playing = true;
        while(playing) {
            String input = keyboard.next();
            if(input.equals("quit"))
                System.exit(0);
            Date guess = new Date(input);
            if(entity.getBorn().equals(guess)) {
                System.out.println("BINGO. You got it!");
                playGame();
            } else if(entity.getBorn().precedes(guess)) {
                System.out.println("Incorrect. Try an earlier date.");
            } else if(guess.precedes(entity.getBorn())) {
                System.out.println("Incorrect. Try a later date.");
            }
        }
    }

    public void playGame(int entityInd) {
        playGame(entities[entityInd]);
    }

    public void playGame() {
        playGame(genRandomEntityInd());
    }

    int genRandomEntityInd() {
        Random rnd = new Random();
        return rnd.nextInt(numberOfCandidateEntities);
    }

    public static void main(String[] args) {
        Entity trudeau = new Entity("Justin Trudeau", new Date("December",25,1971));
        Entity dion = new Entity("Celine Dion", new Date("March", 30, 1968));
        Entity usa = new Entity("United States", new Date("July", 4, 1776));

        GuessMaster gm = new GuessMaster();
        gm.addEntity(trudeau);
        gm.addEntity(dion);
        gm.addEntity(usa);
        gm.playGame();
    }
}