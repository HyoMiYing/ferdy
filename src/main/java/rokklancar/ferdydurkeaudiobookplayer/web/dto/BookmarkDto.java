package rokklancar.ferdydurkeaudiobookplayer.web.dto;

import jakarta.validation.constraints.NotEmpty;
import rokklancar.ferdydurkeaudiobookplayer.persistence.model.User;
import rokklancar.ferdydurkeaudiobookplayer.validation.ValidEmail;

public class BookmarkDto {

    @NotEmpty
    private String chapter;

    @NotEmpty
    private String timestamp;

    private String note;

    @NotEmpty
    @ValidEmail
    private String userEmail;

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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
