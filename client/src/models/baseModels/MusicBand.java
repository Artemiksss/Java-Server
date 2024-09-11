package models.baseModels;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.*;
public class MusicBand implements Comparable<MusicBand>, Serializable {
    private Long musicBandId;
    private String name;
    private Coordinates coordinates;
    private Date creationDate;
    private int numberOfParticipants;
    private long albumsCount;
    private Date establishmentDate;
    private MusicGenre genre;
    private Label label;
    private Long userId;

    public MusicBand(String name, Coordinates coordinates, Date creationDate, int numberOfParticipants, long albumsCount, Date establishmentDate, MusicGenre genre, Label label) {
        this.name = Objects.requireNonNull(name, "Field cannot be null");
        this.coordinates = Objects.requireNonNull(coordinates, "Field cannot be null");
        this.creationDate = Objects.requireNonNull(creationDate, "Error");
        this.numberOfParticipants = checkPositive(numberOfParticipants);
        this.albumsCount = checkPositive(albumsCount);
        this.establishmentDate = Objects.requireNonNull(establishmentDate, "Field cannot be null");
        this.genre = Objects.requireNonNull(genre, "Field cannot be null");
        this.label = Objects.requireNonNull(label, "Field cannot be null");
    }

    public MusicBand(Long userId, String name, Coordinates coordinates, Date creationDate, int numberOfParticipants, long albumsCount, Date establishmentDate, MusicGenre genre, Label label) {
        this.name = Objects.requireNonNull(name, "Field cannot be null");
        this.coordinates = Objects.requireNonNull(coordinates, "Field cannot be null");
        this.creationDate = Objects.requireNonNull(creationDate, "Error");
        this.numberOfParticipants = checkPositive(numberOfParticipants);
        this.albumsCount = checkPositive(albumsCount);
        this.establishmentDate = Objects.requireNonNull(establishmentDate, "Field cannot be null");
        this.genre = Objects.requireNonNull(genre, "Field cannot be null");
        this.label = Objects.requireNonNull(label, "Field cannot be null");
        this.userId = userId;
    }

    public MusicBand(Long userId, Long musicBandID,  String name, Coordinates coordinates, Date creationDate, int numberOfParticipants, long albumsCount, Date establishmentDate, MusicGenre genre, Label label) {
        this.musicBandId = musicBandID;
        this.name = Objects.requireNonNull(name, "Field cannot be null");
        this.coordinates = Objects.requireNonNull(coordinates, "Field cannot be null");
        this.creationDate = Objects.requireNonNull(creationDate, "Error");
        this.numberOfParticipants = checkPositive(numberOfParticipants);
        this.albumsCount = checkPositive(albumsCount);
        this.establishmentDate = Objects.requireNonNull(establishmentDate, "Field cannot be null");
        this.genre = Objects.requireNonNull(genre, "Field cannot be null");
        this.label = Objects.requireNonNull(label, "Field cannot be null");
        this.userId = userId;
    }
    public MusicBand(){}
    public static int checkPositive(int number) throws IllegalArgumentException {
        if (number > 0) {
            return number;
        } else {
            throw new IllegalArgumentException("Число не является положительным: " + number);
        }
    }


    public static long checkPositive(long number) throws IllegalArgumentException {
        if (number > 0) {
            return number;
        } else {
            throw new IllegalArgumentException("Число не является положительным: " + number);
        }
    }

    @Override
    public int compareTo(MusicBand other) { // сортирует коллекцию по умолчанию
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "MusicBand { userId: " + userId + " | " +
                "music_band_id: " + musicBandId + " | " +
                "name: " + name + " | " +
                "coordinates: " + coordinates + " | " +
                "creationDate: " + creationDate + " | " +
                "numberOfParticipants: " + numberOfParticipants + " | " +
                "albumsCount: " + albumsCount + " | " +
                "establishmentDate: " + establishmentDate + " | " +
                "genre: " + genre + " | " +
                "label: " + label + '}';
    }


    public MusicBand createNewMusicBand(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter new element:");

        String name = "";
        while (name.isEmpty()) {
            System.out.println("----- Enter name: ");
            name = in.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty. Please enter a valid name.");
            }
        }

        Float x = 0.0f;
        Integer y = 0;
        boolean validCoordinates = false;
        while (!validCoordinates) {
            System.out.println("----- Enter Coordinates (x y): ");
            try {
                x = in.nextFloat();
                y = in.nextInt();
                in.nextLine();
                validCoordinates = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input format. Coordinates must be numbers.");
                in.nextLine();
            }
        }
        var coordinates = new Coordinates(x, y);

        int numberOfParticipants = 0;
        while (numberOfParticipants <= 0) {
            System.out.println("----- Enter numberOfParticipants: ");
            try {
                numberOfParticipants = in.nextInt();
                if (numberOfParticipants <= 0) {
                    System.out.println("Number of participants must be greater than 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input format. Please enter a valid integer.");
                in.nextLine();
            }
        }

        Date creationDate = null;
        while (creationDate == null) {
            try {
                Scanner console = new Scanner(System.in);
                System.out.println("----- Enter establishmentDate (dd-MM-yyyy): ");
                String creationDateNew = console.nextLine();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                creationDate = formatter.parse(creationDateNew);
            } catch (RuntimeException | ParseException e) {
                System.out.println("Invalid date format. Please enter the date in the format: EEE MMM dd HH:mm:ss zzz yyyy");
            }
        }

        long albumsCount = 0;
        while (albumsCount <= 0) {
            System.out.println("----- Enter albumsCount: ");
            try {
                albumsCount = in.nextLong();
                if (albumsCount <= 0) {
                    System.out.println("Albums count must be greater than 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input format. Please enter a valid integer.");
                in.nextLine(); // clear buffer
            }
        }

        Date establishmentDate = null;
        while (establishmentDate == null) {
            try {
                Scanner console = new Scanner(System.in);
                System.out.println("----- Enter establishmentDate (dd-MM-yyyy): ");
                String establishmentDateString = console.nextLine();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                establishmentDate = formatter.parse(establishmentDateString);
            } catch (DateTimeParseException | ParseException e) {
                System.out.println("Invalid date format. Please enter the date in the format: EEE MMM dd HH:mm:ss zzz yyyy");
            }
        }



        String genre = "";
        while (true) {
            Scanner consoleOne = new Scanner(System.in);
            System.out.println("Choose a genre" + "\n" +
                    "ROCK" + "\n" +
                    "BLUES" + "\n" +
                    "POP" + "\n" +
                    "POST_ROCK" + "\n" +
                    "BRIT_POP");
            System.out.println("----- Enter genre: ");
            genre = consoleOne.nextLine();
            if (MusicGenre.convert(genre) != null){
                break;
            }
            System.out.println("Error format genre! Please enter a valid genre.");
            if (genre.isEmpty()) {
                System.out.println("Genre cannot be empty. Please enter a valid genre.");
            }
        }

        long bands = 0;
        while (bands <= 0) {
            Scanner consoleTwo = new Scanner(System.in);
            System.out.println("----- Enter label: ");
            try {
                bands = consoleTwo.nextLong();
                if (bands <= 0) {
                    System.out.println("Number of bands in the label must be greater than 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input format. Please enter a valid integer.");
                in.nextLine();
            }
        }

        return new MusicBand(name, coordinates, creationDate, numberOfParticipants, albumsCount, establishmentDate, MusicGenre.convert(genre), new Label(bands));
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getCreationDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return format.format(creationDate);
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public long getAlbumsCount() {
        return albumsCount;
    }

    public String getEstablishmentDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return format.format(establishmentDate);
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public Label getLabel() {
        return label;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setMusicBandId(Long musicBandId) {
        this.musicBandId = musicBandId;
    }

    public Long getMusicBandId() {
        return musicBandId;
    }
}