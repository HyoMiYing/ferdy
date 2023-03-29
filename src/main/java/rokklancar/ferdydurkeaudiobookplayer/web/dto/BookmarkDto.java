package rokklancar.ferdydurkeaudiobookplayer.web.dto;

import jakarta.validation.constraints.NotEmpty;
import rokklancar.ferdydurkeaudiobookplayer.persistence.model.User;

public class BookmarkDto {

    @NotEmpty
    private String chapter;

    @NotEmpty
    private String timestamp;

    private String note;

    @NotEmpty
    private User user;

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
