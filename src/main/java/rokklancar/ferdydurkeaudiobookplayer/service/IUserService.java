package rokklancar.ferdydurkeaudiobookplayer.service;

import rokklancar.ferdydurkeaudiobookplayer.web.dto.UserDto;
import rokklancar.ferdydurkeaudiobookplayer.persistence.model.User;

import java.util.Optional;

public interface IUserService {

    User registerNewUserAccount(UserDto accountDto);

    void saveRegisteredUser(User user);

    void deleteUser(User user);

    User findUserByEmail(String email);

    Optional<User> getUserById(long id);
}
