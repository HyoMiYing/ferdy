package rokklancar.ferdydurkeaudiobookplayer.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rokklancar.ferdydurkeaudiobookplayer.persistence.model.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @Override
    void delete (Bookmark bookmark);
}
