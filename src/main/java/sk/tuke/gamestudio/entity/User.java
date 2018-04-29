package sk.tuke.gamestudio.entity;

import sk.tuke.gamestudio.server.controller.BattleshipsBrehuvControllerUser;

import javax.persistence.*;

@Entity
@Table(name = "player")
@NamedQueries({
        @NamedQuery(name = "User.login",
                query = "SELECT u FROM User u WHERE u.username=:username AND u.password=:password"),
        @NamedQuery(name = "User.find",
                query = "SELECT u FROM User u WHERE u.username=:username"),
})
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String username;

    private String password;

    @Transient
    private String verifiedPasswd;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwd) {
        this.password = passwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", passwd='" + password + '\'' +
                '}';
    }


    public String userLogged() {
        StringBuilder sb = new StringBuilder();
        try {
            if (BattleshipsBrehuvControllerUser.isLogged()) {
                sb.append("<div id=\"logged\" class=\"logged\"></div>");
                return sb.toString();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }


    public String userRegistered() {
        StringBuilder sb = new StringBuilder();
        try {
            if (BattleshipsBrehuvControllerUser.isLogged()) {
                sb.append("<div id=\"registered\" class=\"registered\"></div>");
                return sb.toString();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }
}
