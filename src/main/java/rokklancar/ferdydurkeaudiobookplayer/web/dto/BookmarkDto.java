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
}
