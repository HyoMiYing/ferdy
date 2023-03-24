package rokklancar.ferdydurkeaudiobookplayer.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rokklancar.ferdydurkeaudiobookplayer.persistence.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Override
    void delete(User user);
}
