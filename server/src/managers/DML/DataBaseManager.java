package managers.DML;

import models.baseModels.Coordinates;
import models.baseModels.Label;
import models.baseModels.MusicBand;
import models.baseModels.MusicGenre;
import models.users.PasswordHashing;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TreeMap;

public class DataBaseManager {
    public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
    static private Connection connection;

    public DataBaseManager() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Ошибка подключения к базе данных");
            e.printStackTrace();
        }
    }

    public void insertUser(String login, String password, String name) {
        String insertUserSQL = "INSERT INTO Users (login, password, user_name) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, PasswordHashing.hashPassword(password));
            preparedStatement.setString(3, name);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("------ Database: User inserted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long insertUserMusicBands(Long userId, MusicBand musicBand, Long key) {
        String insertUserMusicBandsSQL = "INSERT INTO music_band (key_music_band, user_id, music_band_name, coordinate_x, coordinate_y, creation_date, number_of_participants, albums_count, establishment_date, genre, label) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertUserMusicBandsSQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, key);
            preparedStatement.setLong(2, userId);
            preparedStatement.setString(3, musicBand.getName());
            preparedStatement.setFloat(4, musicBand.getCoordinates().getX());
            preparedStatement.setInt(5, musicBand.getCoordinates().getY());
            preparedStatement.setString(6, musicBand.getCreationDate());
            preparedStatement.setInt(7, musicBand.getNumberOfParticipants());
            preparedStatement.setLong(8, musicBand.getAlbumsCount());
            preparedStatement.setString(9, musicBand.getEstablishmentDate());
            preparedStatement.setString(10, musicBand.getGenre().getGenre());
            preparedStatement.setLong(11, musicBand.getLabel().getBands());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    long generatedId = generatedKeys.getLong(3);
                    System.out.println("User music bands inserted successfully with ID: " + generatedId);
                    return generatedId;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Connection getConnection() {
        return connection;
    }

    public Long getUserId(String login, String password) {
        String query = "SELECT id FROM users WHERE login = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, PasswordHashing.hashPassword(password));

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1L;
    }
//rename
    public TreeMap<Long, MusicBand> updateCollection() {
        String selectAllMusicBandsSQL = "SELECT * FROM music_band";
        TreeMap<Long, MusicBand> musicBandMap = new TreeMap<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectAllMusicBandsSQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long key = resultSet.getLong("key_music_band");
                Long userId = resultSet.getLong("user_id");;
                long musicBandId = resultSet.getLong("music_band_id");
                String musicBandName = resultSet.getString("music_band_name");
                float coordinateX = resultSet.getFloat("coordinate_x");
                int coordinateY = resultSet.getInt("coordinate_y");
                String creationDate = resultSet.getString("creation_date");
                int numberOfParticipants = resultSet.getInt("number_of_participants");
                long albumsCount = resultSet.getLong("albums_count");
                String establishmentDate = resultSet.getString("establishment_date");
                String genre = resultSet.getString("genre");
                String label = resultSet.getString("label");

                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                MusicBand musicBand = new MusicBand(userId, musicBandId, musicBandName, new Coordinates(coordinateX, coordinateY), formatter.parse(creationDate), numberOfParticipants, albumsCount, formatter.parse(establishmentDate), MusicGenre.convert(genre), new Label(Long.parseLong(label)));
                musicBandMap.put(key, musicBand);
            }
            return musicBandMap;
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void deleteAllMusicBandsByUserId(Long userId) {
        String deleteSQL = "DELETE FROM music_band WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setLong(1, userId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted " + rowsAffected + " records where user_id = " + userId);
            } else {
                System.out.println("No records found with user_id = " + userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMusicBandLower(Long userId, Long key) {
        String deleteSQL = "DELETE FROM music_band WHERE user_id = ? AND key_music_band < ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, key);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted " + rowsAffected + " records where user_id = " + userId + "and key > " + key);
            } else {
                System.out.println("No records found with user_id = " + userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMusicBandGreater(Long userId, Long albumsCount) {
        String deleteSQL = "DELETE FROM music_band WHERE user_id = ? AND albums_count > ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, albumsCount);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted " + rowsAffected + " records where user_id = " + userId + "and music_band_id > " + albumsCount);
            } else {
                System.out.println("No records found with user_id = " + userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMusicBandsByUserId(Long userId, Long key) {
        String deleteSQL = "DELETE FROM music_band WHERE user_id = ? AND key_music_band = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, key);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted " + rowsAffected + " records where user_id = " + userId + "and key > " + key);
            } else {
                System.out.println("No records found with user_id = " + userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateUserMusicBandsId(Long musicBandId, MusicBand musicBand, Long userId) {
        String updateUserMusicBands = "UPDATE music_band SET music_band_name = ?, coordinate_x = ?, coordinate_y = ?, creation_date = ?, number_of_participants = ?, albums_count = ?, establishment_date = ?, genre = ?, label = ? WHERE music_band_id = ? AND user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateUserMusicBands)) {
            preparedStatement.setString(1, musicBand.getName());
            preparedStatement.setFloat(2, musicBand.getCoordinates().getX());
            preparedStatement.setInt(3, musicBand.getCoordinates().getY());
            preparedStatement.setString(4, musicBand.getCreationDate());
            preparedStatement.setInt(5, musicBand.getNumberOfParticipants());
            preparedStatement.setLong(6, musicBand.getAlbumsCount());
            preparedStatement.setString(7, musicBand.getEstablishmentDate());
            preparedStatement.setString(8, musicBand.getGenre().getGenre());
            preparedStatement.setLong(9, musicBand.getLabel().getBands());
            preparedStatement.setLong(10, musicBandId);
            preparedStatement.setLong(11, userId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateUserMusicBandsKey(Long key, MusicBand musicBand, Long user_id) {
        String updateUserMusicBands = "UPDATE music_band SET music_band_name = ?, coordinate_x = ?, coordinate_y = ?, creation_date = ?, number_of_participants = ?, albums_count = ?, establishment_date = ?, genre = ?, label = ?, music_band_id = ? WHERE key_music_band = ? AND user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateUserMusicBands)) {
            preparedStatement.setString(1, musicBand.getName());
            preparedStatement.setFloat(2, musicBand.getCoordinates().getX());
            preparedStatement.setInt(3, musicBand.getCoordinates().getY());
            preparedStatement.setString(4, musicBand.getCreationDate());
            preparedStatement.setInt(5, musicBand.getNumberOfParticipants());
            preparedStatement.setLong(6, musicBand.getAlbumsCount());
            preparedStatement.setString(7, musicBand.getEstablishmentDate());
            preparedStatement.setString(8, musicBand.getGenre().getGenre());
            preparedStatement.setLong(9, musicBand.getLabel().getBands());
            preparedStatement.setLong(10, musicBand.getMusicBandId());
            preparedStatement.setLong(11, key);
            preparedStatement.setLong(12, user_id);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteUser(Long userId) {
        deleteAllMusicBandsByUserId(userId);
        String deleteSQL = "DELETE FROM users WHERE id = ? ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setLong(1, userId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted " + rowsAffected + " records where user_id = " + userId);
            } else {
                System.out.println("No records found with user_id = " + userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkingAvailabilityGroup(Long userId, Long groupId) {
        String deleteSQL = "SELECT * FROM music_band WHERE music_band_id = ? AND user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setLong(1, groupId);
            preparedStatement.setLong(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkingKeyAndUser(Long userId, Long key) {
        String check = "SELECT * FROM music_band WHERE key_music_band = ? AND user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(check)) {
            preparedStatement.setLong(1, key);
            preparedStatement.setLong(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
