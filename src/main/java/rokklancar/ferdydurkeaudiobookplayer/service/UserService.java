package rokklancar.ferdydurkeaudiobookplayer.service;

import rokklancar.ferdydurkeaudiobookplayer.web.dto.UserDto;
import rokklancar.ferdydurkeaudiobookplayer.persistence.dao.UserRepository;
import rokklancar.ferdydurkeaudiobookplayer.persistence.model.User;
import rokklancar.ferdydurkeaudiobookplayer.web.error.UserAlreadyExistsException;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UserService implements IUserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public User registerNewUserAccount(final UserDto accountDto) {
        if (emailExists(accountDto.getEmail())) {
            throw new UserAlreadyExistsException("There is an account with that email address: " + accountDto.getEmail());
        }
        final User user = new User();

        user.setEmail(accountDto.getEmail());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUserById(final long id) {
        return userRepository.findById(id);
    }

    @Override
    public void saveRegisteredUser(final User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(final User user) {
        userRepository.delete(user);
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }
}
