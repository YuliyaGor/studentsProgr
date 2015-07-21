package entity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dell on 20.07.2015.
 */
public class User {
    private int id;
    private String login;
    private String password;
    private List<Role> roles = new LinkedList();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
