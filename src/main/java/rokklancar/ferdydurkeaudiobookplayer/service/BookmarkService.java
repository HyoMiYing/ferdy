package rokklancar.ferdydurkeaudiobookplayer.service;

import rokklancar.ferdydurkeaudiobookplayer.persistence.dao.BookmarkRepository;
import rokklancar.ferdydurkeaudiobookplayer.persistence.model.Bookmark;
import rokklancar.ferdydurkeaudiobookplayer.persistence.model.User;
import rokklancar.ferdydurkeaudiobookplayer.web.dto.BookmarkDto;

import java.util.List;

public class BookmarkService {

    private BookmarkRepository bookmarkRepository;

    private UserService userService;

    BookmarkService (BookmarkRepository bookmarkRepository, UserService userService) {
        this.bookmarkRepository = bookmarkRepository;
        this.userService = userService;
    }

    public Bookmark addNewBookmark(BookmarkDto bookmarkDto) {
        final Bookmark bookmark = new Bookmark();
        bookmark.setChapter(bookmarkDto.getChapter());
        bookmark.setTimestamp(bookmarkDto.getTimestamp());
        bookmark.setNote(bookmarkDto.getNote());
        bookmark.setUser(userService.findUserByEmail(bookmarkDto.getUserEmail()));
        return bookmarkRepository.save(bookmark);
    }

    public List<Bookmark> findBookmarksByUser (User user) {
        return bookmarkRepository.findByUser(user);
    }
}
