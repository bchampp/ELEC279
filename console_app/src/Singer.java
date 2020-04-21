/* ELEC279 | Guess Master v2 */

public class Singer extends Person //Child Class of Person
{
    //Instance Variables
    private String debutAlbum;
    private Date albumRelease;

    //Constructors
    public Singer() {
        super(); //Initialize instance variables in Parent Class
        debutAlbum = "No Debut Album!";
        albumRelease = new Date(1, 1, 1000); //Default Date
    }//Singer()

    //Constructor with input parameters
    public Singer(String name, Date birthDate, String gender, String debutAlbum, Date albumRelease, double difficulty) {
        super(name, birthDate, gender, difficulty);
        if ((debutAlbum != null) && (albumRelease != new Date(1, 1, 1000))) //Check if empty
        {
            this.debutAlbum = debutAlbum;
            this.albumRelease = albumRelease;
        } else {
            System.out.println("Fatal Error");
            System.exit(0);
        }
    }//Singer

    //Clone Constructor
    public Singer(Singer entity) {
        super(entity); //Call parent class constructor
        debutAlbum = entity.debutAlbum;
        albumRelease = entity.albumRelease;
    }//Singer()

    //Accessors
    public String getDebutAlbum() {
        return debutAlbum;
    }//getDebutAlbum()

    public Date getAlbumRelease() {
        return albumRelease;
    }//getAlbumRelease()

    //Mutators
    public void setDebutAlbum(String debutAlbum) {
        this.debutAlbum = debutAlbum;
    }//setDebutAlbum()

    public void setAlbumRelease(Date albumRelease) {
        this.albumRelease = albumRelease;
    }//setAlbumRelease()

    public Singer clone() {
        return new Singer(this); //clone with this()
    }//clone()

    public String toString() {
        return "Name is: " + getName() + "\n" + "Born on: " + getBorn().toString() + "\n" + "Gender is: " + getGender() + "\n" + "Debut Album: " + debutAlbum + "\n" + "Album Release Date: " + albumRelease;
    }//toString()

    public String entityType() {
        return "Singer!";
    }//entityType()
}
