package managers.DDL;
import managers.DML.DataBaseManager;

import java.sql.SQLException;
import java.sql.Statement;

public class Migration {
    private Statement statement;
    public Migration() {
        try {
            this.statement = new DataBaseManager().getConnection().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ;
    }
    public void createUsersSequences(){
        String userSequences = "CREATE SEQUENCE IF NOT EXISTS user_seq START WITH 82 INCREMENT BY 15";
        try {
            statement.executeUpdate(userSequences);
            System.out.println("Sequences 'user_seq' created.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createUsersTables() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT PRIMARY KEY DEFAULT nextval('user_seq'), " +
                "login VARCHAR(255) NOT NULL, " +
                "password VARCHAR(255) NOT NULL, " +
                "user_name VARCHAR(255) NOT NULL" +
                ")";
        try {
            statement.executeUpdate(createUsersTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Table 'Users' created.");
    }
    public void createMusicBandSequences() {
        String musicBandSequences = "CREATE SEQUENCE IF NOT EXISTS music_band_seq START WITH 32 INCREMENT BY 45";
        try {
            statement.executeUpdate(musicBandSequences);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println("Sequences 'music_band_seq' created.");
    }
    public void createMusicBandTables() {
        String createUserMusicBandsTable = "CREATE TABLE IF NOT EXISTS music_band (" +
                "key_music_band BIGINT NOT NULL, " +
                "user_id BIGINT NOT NULL, " +
                "music_band_id BIGINT PRIMARY KEY DEFAULT nextval('music_band_seq')," +
                "music_band_name VARCHAR(255) NOT NULL, " +
                "coordinate_x FLOAT NOT NULL, " +
                "coordinate_y INT NOT NULL, " +
                "creation_date VARCHAR(255) NOT NULL, " +
                "number_of_participants INTEGER NOT NULL, " +
                "albums_count BIGINT NOT NULL, " +
                "establishment_date VARCHAR(255) NOT NULL, " +
                "genre VARCHAR(255) NOT NULL, " +
                "label VARCHAR(255) NOT NULL, " +
                "FOREIGN KEY (user_id) REFERENCES users(id)" +
                ")";
        try {
            statement.executeUpdate(createUserMusicBandsTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Table 'MusicBands' created.");
    }
}
