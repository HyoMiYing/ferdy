package rokklancar.ferdydurkeaudiobookplayer.service;

import rokklancar.ferdydurkeaudiobookplayer.persistence.dao.BookmarkRepository;

public class BookmarkService {

    private BookmarkRepository bookmarkRepository;

    BookmarkService (BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }
}
