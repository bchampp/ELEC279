/* ELEC279
   Assignment 2
   Brent Champion
   20066282
*/

package com.example.brent.guessmaster;

//Imports
import java.util.Random;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class GuessMaster extends AppCompatActivity {
	
    //Instance Variables
	
	//For XML file
    String entName;
    int entityId = 0;
    private TextView entityName;
    private TextView ticketsum;
    private Button guessButton;
    private EditText userIn;
    private Button btnclearContent;
    private String user_input;
    private ImageView entityImage;
    String answer;
	//For GuessMaster
    int currentTickets;
    private int numOfEntities;
    private Entity[] entities;
    private int totalTicketNum = 0;
	
    //Create new instance of GuessMaster with onCreate Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set the content view to corresponding activity file activity_guess_activity
        setContentView(R.layout.activity_guess_activity);
        //Assign the components in the layout to the instance variables
		ticketsum = (TextView) findViewById(R.id.ticket);
		entityImage = (ImageView) findViewById((R.id.entityImage));
		entityName = (TextView) findViewById(R.id.entityName);
		userIn = (EditText) findViewById(R.id.guessinput);
        guessButton = (Button) findViewById(R.id.btnGuess);
        btnclearContent = (Button) findViewById(R.id.btnClear);
        
        //Initialize Entities
        Country usa = new Country("United States", new Date("July", 4, 1776), "Washington D.C.", 0.1);
		Person myCreator = new Person("Brent Champion", new Date("April", 7, 1999), "Male", 1);
		Politician jTrudeau = new Politician("Justin Trudeau", new Date("December", 25, 1971), "Male", "Liberal", 0.25);
        Singer cDion = new Singer("Celine Dion", new Date("March", 30, 1961), "Female", "La voix du bon Dieu", new Date("November", 6, 1981), 0.5);
		
        //Initialize GuessMaster object 
        final GuessMaster newGame = this;
		
        //Add the entities to the entity arrow in the GuessMaster Object 
        newGame.addEntity(jTrudeau);
        newGame.addEntity(cDion);
        newGame.addEntity(myCreator);
        newGame.addEntity(usa);
		
        //Change the entity being shown on click for btnClearContent 
        btnclearContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeEntity();
            }
        });
		
        //When Guess Button is pressed, generate new entity and play game
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Entity entity = entities[entityId];
                playGame(entity);
            }
        });
		
        //Call a new instance of the game, this will be the first
        newInstance();
    }//onCreate()
	
    //Start instance of the game
    public void newInstance() {
        entityId = genRandomEntityId();
        Entity entity = entities[entityId];
        entName = entity.getName();
        ImageSetter();
        welcomeToGame(entity);
        playGame(entity);
    }//newInstance()
	
    //Change the entity on display and redisplay it
    public void ChangeEntity() {
        userIn.getText().clear(); 
        entityId = genRandomEntityId();
        Entity entity = entities[entityId];
        entName = entity.getName();
        ImageSetter();
        playGame(entity);
    }//ChangeEntity()

    // Image Setter function which sets entity name to jgp image found in resources folder
    public void ImageSetter() {
        if (entName.equals("Justin Trudeau")) {
            entityImage.setImageResource(R.drawable.justintrudeau);
        } else if (entName.equals("Celine Dion")) {
            entityImage.setImageResource(R.drawable.celinedion);
        } else if (entName.equals("United States")) {
            entityImage.setImageResource(R.drawable.usaflag);
        } else if (entName.equals("Brent Champion")){
            entityImage.setImageResource(R.drawable.brentchampion);
        }
    }//ImageSetter()
	
    //Function to display a welcome message through an alert
    public void welcomeToGame(Entity entity) {
        AlertDialog.Builder welcomeAlert = new AlertDialog.Builder(GuessMaster.this);
        welcomeAlert.setTitle("Welcome to GuessMaster v3.0!");
        welcomeAlert.setMessage(entity.welcomeMessage());
        welcomeAlert.setCancelable(false);
        welcomeAlert.setNegativeButton("START GAME!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "The game is starting...Enjoy", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = welcomeAlert.create();
        dialog.show();
    }//welcomeToGame()
	
    //Guess master constructor used to initialize an array of entities
    public GuessMaster() {
        numOfEntities = 0;
        entities = new Entity[10];
    }//GuessMaster()
	
    //Continues game after user guesses correctly, creates new random entity and displays it
    public void continueGame() {
        entityId = genRandomEntityId();
        Entity entity = entities[entityId];
        entName = entity.getName();
        ImageSetter();
        entityName.setText(entName);
        userIn.getText().clear();
    }//continueGame()
	
    //Add each entity to the array of entities, calls clone() method from assignment 2
    public void addEntity(Entity entity) {
        entities[numOfEntities++] = entity.clone();
    }//addEntity()

	//Main Game function
    public void playGame (Entity entity){
        try {
            entityName.setText(entity.getName());
			
            //Recieve user input from app
            answer = userIn.getText().toString();
			
            //Format the User input
            answer = answer.replace("\n", "").replace("\r", ""); 
            answer = answer.replace("\n", "").replace("\r", "");
			
            //If the answer is not empty the play game
            if ((!answer.equals(""))) {
				
                Date date = new Date(answer); //Create new date object with user input

                if (date.precedes(entity.getBorn())) { //Call date precedes function
                    AlertDialog.Builder precedes = new AlertDialog.Builder(GuessMaster.this);
                    precedes.setTitle("Incorrect. ");
                    precedes.setMessage("Try a later date.");
                    precedes.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = precedes.create();
                    dialog.show();
                }

                else if (entity.getBorn().precedes(date)) { //call precedes function the other way (if entity is earlier)
                    AlertDialog.Builder after = new AlertDialog.Builder(GuessMaster.this);
                    after.setTitle("Incorrect.");
                    after.setMessage("Try an earlier date.");
                    after.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = after.create();
                    dialog.show();
                }

                else {
                    //Case where user guesses correctly, updates tickets and displays message
                    currentTickets = entity.getTicketNumber();
                    totalTicketNum += entity.getTicketNumber();
                    Integer temp = new Integer(totalTicketNum);
                    final String totalTicketNumString = temp.toString();
                    AlertDialog.Builder winAlert= new AlertDialog.Builder(GuessMaster.this);
                    winAlert.setTitle("You Won!");
                    winAlert.setMessage("BINGO! " + entity.closingMessage());
                    winAlert.setCancelable(false);
                    winAlert.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getBaseContext(), " you won: " + currentTickets, Toast.LENGTH_SHORT).show();
                            //Update the ticketsum variable to display tickets
                            ticketsum.setText("Total Tickets: " + totalTicketNumString);
                            //Continue playing game 
                            continueGame();

                        }
                    });
                    AlertDialog dialog = winAlert.create();
                    dialog.show();

                }
            }
        }
		
        //Catches exception where user inputs with wrong format. Displays alert message prompting them to use the correct formatting
        catch (Exception e) {
            AlertDialog.Builder after = new AlertDialog.Builder(GuessMaster.this);
            after.setTitle("Incorrect Date");
            after.setMessage("Please use the following format: mm/dd/yyyy");
            after.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog dialog = after.create();
            dialog.show();
        }
        userIn.getText().clear();
    }//playGame()
	
    //Generate random integer and return 
    public int genRandomEntityId () {
        Random randomNumber = new Random();
        return randomNumber.nextInt(numOfEntities);
    }//genRandomEntityId()
}
