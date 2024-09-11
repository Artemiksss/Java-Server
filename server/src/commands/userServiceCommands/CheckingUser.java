package commands.userServiceCommands;

import managers.DML.DataBaseManager;
import commands.baseCommands.Command;
import models.users.PasswordHashing;
import models.users.User;

import java.io.Serial;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckingUser extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 111L;

    public String execute(User user, DataBaseManager dataBaseManager) {
        String checkUserSQL = "SELECT * FROM Users WHERE login = ? AND password = ?";
        try (PreparedStatement pstmt = dataBaseManager.getConnection().prepareStatement(checkUserSQL)) {
            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, PasswordHashing.hashPassword(user.getPassword()));
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Long id = dataBaseManager.getUserId(user.getLogin(), user.getPassword());
                    return "OK" + "Your ID: " + id;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error checking user";
        }
        return "Incorrect username or password.";
    }
}
