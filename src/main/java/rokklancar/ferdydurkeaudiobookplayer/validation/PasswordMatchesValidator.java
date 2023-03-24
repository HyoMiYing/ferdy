package rokklancar.ferdydurkeaudiobookplayer.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import rokklancar.ferdydurkeaudiobookplayer.web.dto.UserDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {}

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UserDto user = (UserDto) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
