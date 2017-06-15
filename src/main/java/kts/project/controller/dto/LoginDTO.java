package kts.project.controller.dto;

/**
 * Created by Korisnik on 6/14/2017.
 */
public class LoginDTO {
    private String username;
    private String password;

    @Override
    public String toString() {
        return "loginDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public LoginDTO() {
    }

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
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

    public void setPassword(String password) {
        this.password = password;
    }
}
