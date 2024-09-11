package network.specialRequests;

import commands.baseCommands.*;
import commands.specialCommands.ExecuteScript;
import exceptions.ConsoleOutputErrorException;
import exceptions.GenreException;
import exceptions.ParameterException;
import models.baseModels.Coordinates;
import models.baseModels.Label;
import models.baseModels.MusicBand;
import models.baseModels.MusicGenre;
import models.users.User;
import network.managers.ClientManager;
import network.request.Request;
import network.request.SendRequest;
import network.response.ServerResponse;
import parameters.Parameters;
import parser.Parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.security.Key;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScriptExecutionRequests {
    public static void executeScript(InetAddress serverAddress, int serverPort, Command command, User user) {
        String fileName = ((ExecuteScript) command).getFileName();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String newCommand;
            while ((newCommand = br.readLine()) != null) {
                Command finalCommand = new ClientManager(new Parser().parsing(newCommand), user).handler(serverAddress, serverPort);
                new SendRequest().sending(new Request(finalCommand, user), serverAddress, serverPort);
                new ServerResponse().response();
            }
        } catch (IOException | ConsoleOutputErrorException | NullPointerException | ClassNotFoundException e) {
            e.getMessage();
        } catch (ParameterException e) {
            throw new RuntimeException(e);
        }
    }

    public static MusicBand parseFile(InetAddress serverAddress, int serverPort, Command command, User user) throws IOException, ParseException {
        String fileName = ((ExecuteScript) command).getFileName();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().toLowerCase().startsWith("insert")) {
                    String[] parts = line.trim().split(" ");
                    String key = parts[1];
                    MusicBand musicBand = readMusicBand(reader);
                    Command newCommand = new InsertNewElement(key);
                    newCommand.setParameter(new Parameters(musicBand, key));
                    send(new Request(newCommand, user), serverAddress, serverPort);
                } else if (line.trim().toLowerCase().startsWith("update")) {
                    String[] parts = line.trim().split(" ");
                    String id = parts[1];
                    MusicBand musicBand = readMusicBand(reader);
                    Command newCommand = new Update(id);
                    Parameters param = new Parameters(musicBand);
                    param.setMusicBandId(id);
                    newCommand.setParameter(param);
                    send(new Request(newCommand, user), serverAddress, serverPort);
                } else if (line.trim().toLowerCase().startsWith("remove_key")) {
                    String[] parts = line.trim().split(" ");
                    String key = parts[1];
                    Command newCommand = new RemoveKey(key);
                    Parameters param = new Parameters();
                    param.setKey(key);
                    newCommand.setParameter(param);
                    send(new Request(newCommand, user), serverAddress, serverPort);
                } else if (line.trim().equalsIgnoreCase("remove_greater")) {
                    String countAlbum = reader.readLine();
                    Command newCommand = new RemoveGreater();
                    newCommand.setParameter(new Parameters(countAlbum));
                    send(new Request(newCommand, user), serverAddress, serverPort);
                } else if (line.trim().toLowerCase().startsWith("remove_lower_key")) {
                    String[] parts = line.trim().split(" ");
                    String key = parts[1];
                    Command newCommand = new RemoveLowerKey(null);
                    Parameters param = new Parameters();
                    param.setKey(key);
                    newCommand.setParameter(param);
                    send(new Request(newCommand, user), serverAddress, serverPort);
                } else if (line.trim().toLowerCase().startsWith("replace_if_greater")) {
                    String[] parts = line.trim().split(" ");
                    String key = parts[1];
                    MusicBand musicBand = readMusicBand(reader);
                    Command newCommand = new ReplaceIfGreater(key);
                    newCommand.setParameter(new Parameters(musicBand, key));
                    send(new Request(newCommand, user), serverAddress, serverPort);
                } else if (line.trim().toLowerCase().startsWith("count_less_than_genre")) {
                    String[] parts = line.trim().split(" ");
                    String genre = parts[1];
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
                    } catch (NullPointerException e) {
                        throw new GenreException("Genre reading error.");
                    }
                    Command newCommand = new CountLessThanGenre(newGenre);
                    newCommand.setParameter(new Parameters(newGenre));
                    send(new Request(newCommand, user), serverAddress, serverPort);
                } else if (line.trim().toLowerCase().startsWith("filter_starts_with_name")) {
                    String[] parts = line.trim().split(" ");
                    String name = parts[1];
                    Command newCommand = new FilterStartsWithName(name);
                    newCommand.setParameter(new Parameters(name));
                    send(new Request(newCommand, user), serverAddress, serverPort);
                } else {
                    Command finalCommand = new ClientManager(new Parser().parsing(line), user).handler(serverAddress, serverPort);
                    send(new Request(finalCommand, user), serverAddress, serverPort);
                }
            }
        } catch (ConsoleOutputErrorException | ClassNotFoundException |
                 FileNotFoundException | NumberFormatException e) {
            System.out.println("An error occurred while reading data from a file.");
        } catch (GenreException e){
            System.out.println(e.getMessage());
        } catch (ParameterException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static MusicBand readMusicBand(BufferedReader reader) throws IOException, ParseException {
        String name = reader.readLine();
        Float x = Float.parseFloat(reader.readLine());
        int y = Integer.parseInt(reader.readLine());
        Date creationDate = new SimpleDateFormat("dd-MM-yyyy").parse(reader.readLine());
        int numberOfParticipants = Integer.parseInt(reader.readLine());
        long albumsCount = Long.parseLong(reader.readLine());
        Date establishmentDate = new SimpleDateFormat("dd-MM-yyyy").parse(reader.readLine());
        MusicGenre genre = MusicGenre.valueOf(reader.readLine());
        Long labelSales = Long.parseLong(reader.readLine());
        Coordinates coordinates = new Coordinates(x, y);
        Label label = new Label(labelSales);
        return new MusicBand(name, coordinates, creationDate, numberOfParticipants, albumsCount, establishmentDate, genre, label);
    }

    public static void send(Request request, InetAddress serverAddress, int serverPort) throws IOException, ClassNotFoundException {
        new SendRequest().sending(request, serverAddress, serverPort);
        new ServerResponse().response();
    }
}

