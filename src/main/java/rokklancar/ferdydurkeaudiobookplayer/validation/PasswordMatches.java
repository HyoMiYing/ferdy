package rokklancar.ferdydurkeaudiobookplayer.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validateBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {
    String message() default "Gesli se ne ujemata";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
