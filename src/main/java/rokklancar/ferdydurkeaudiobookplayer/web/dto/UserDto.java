package rokklancar.ferdydurkeaudiobookplayer.web.dto;


import org.hibernate.validator.constraints.NotEmpty;
import rokklancar.ferdydurkeaudiobookplayer.validation.PasswordMatches;
import rokklancar.ferdydurkeaudiobookplayer.validation.ValidEmail;

@PasswordMatches
public class UserDto {
    @ValidEmail
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
    private String matchingPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
