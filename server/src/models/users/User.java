package models.users;

import java.io.Serial;
import java.io.Serializable;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1234L;
    private static Access access;

    private String name = null;
    private String login;
    private String password;
    private Long id;

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
        access = Access.DENIED;
    }
    public User(String login, String password) {
        this.login = login;
        this.password = password;
        access = Access.DENIED;
    }
    public User(Long id) {
        this.id = id;
    }

    public static void setAccess(Access access) {
        User.access = access;
    }

    public static Access getAccess() {
        return access;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
