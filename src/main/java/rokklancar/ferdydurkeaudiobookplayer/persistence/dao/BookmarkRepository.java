package rokklancar.ferdydurkeaudiobookplayer.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rokklancar.ferdydurkeaudiobookplayer.persistence.model.Bookmark;
import rokklancar.ferdydurkeaudiobookplayer.persistence.model.User;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findByUser(User user);

    @Override
    void delete (Bookmark bookmark);
}
