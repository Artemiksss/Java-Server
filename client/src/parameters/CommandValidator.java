package parameters;

import exceptions.ParameterException;
import models.baseModels.MusicBand;
import parser.ManualInput;

import java.util.InputMismatchException;

public class CommandValidator {
    public static Parameters Validation(TypeParameter typeParameter, String parameter) throws ParameterException {
        if (typeParameter == TypeParameter.KEY) {
            return inputKey(parameter);
        }
        if (typeParameter == TypeParameter.GENRE) {
            return inputGenre(parameter);
        }
        if (typeParameter == TypeParameter.NONE) {
            return null;
        }
        if (typeParameter == TypeParameter.NAME) {
            return inputName(parameter);
        }
        if (typeParameter == TypeParameter.MUSIC_BAND) {
            return inputMusicBand(parameter);
        }
        if (typeParameter == TypeParameter.KEY_AND_MUSIC_BAND) {
            return inputMusicBandAndKey(parameter);
        }
        if (typeParameter == TypeParameter.ID_AND_MUSIC_BAND) {
            return inputMusicBandAndId(parameter);
        }
        if (typeParameter == TypeParameter.NUMBER_OF_ALBUMS) {
            return inputNumberAlbums();
        }
        return null;
    }


    private static Parameters inputKey(String key) throws ParameterException {
        try {
            Long.parseLong(key);
        } catch (NumberFormatException e) {
            System.out.println("Invalid key. Please enter a valid integer key.");
            throw new ParameterException("Error reading the parameter!");
        }
        Parameters parameters = new Parameters();
        parameters.setKey(key);
        return parameters;
    }

    private static Parameters inputGenre(String genre) {
        try {
            String newGenre = null;
            String[] genres = {"POP", "BLUES", "ROCK", "POST_ROCK", "BRIT_POP"};
            int flag = 0;
            try {
                for (String a : genres) {
                    if (a.equals(genre)) {
                        newGenre = genre;
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    throw new NullPointerException();
                }
            } catch (InputMismatchException e) {
                System.out.println("The input error must be of type String.");
            } catch (NullPointerException e) {
                System.out.println("The genre was entered incorrectly.");
            }
            return new Parameters(newGenre);
        } catch (InputMismatchException e) {
            System.out.println("The genre was entered incorrectly!");
        }
        return null;
    }

    private static Parameters inputName(String name) {
        return new Parameters(name);
    }

    private static Parameters inputMusicBand(String key) throws ParameterException {
        try {
            Long.parseLong(key);
        } catch (NumberFormatException e) {
            System.out.println("Invalid key. Please enter a valid integer key.");
            throw new ParameterException("Error reading the parameter!");
        }
        MusicBand musicBand = new MusicBand().createNewMusicBand();
        return new Parameters(musicBand, key);
    }

    private static Parameters inputMusicBandAndKey(String key) throws ParameterException {
        try {
            Long.parseLong(key);
        } catch (NumberFormatException e) {
            System.out.println("Invalid key. Please enter a valid integer key.");
            throw new ParameterException("Error reading the parameter!");
        }
        MusicBand musicBand = new MusicBand().createNewMusicBand();
        return new Parameters(musicBand, key);
    }

    private static Parameters inputMusicBandAndId(String id) throws ParameterException {
        try {
            Long.parseLong(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid key. Please enter a valid integer key.");
            throw new ParameterException("Error reading the parameter!");
        }
        MusicBand musicBand = new MusicBand().createNewMusicBand();
        Parameters parameter = new Parameters(musicBand);
        parameter.setMusicBandId(id);
        return parameter;
    }

    public static Parameters inputNumberAlbums() throws ParameterException {
        String album = null;
        int flag = 0;
        while (flag == 0) {
            System.out.println("Enter number of albums: ");
            String currentKey = new ManualInput().nextLine();
            try {
                Integer.parseInt(currentKey);
                album = currentKey;
                flag = 1;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
                flag = 0;
                throw new ParameterException("Error reading the parameter!");
            }

        }
        return new Parameters(album);
    }
}
