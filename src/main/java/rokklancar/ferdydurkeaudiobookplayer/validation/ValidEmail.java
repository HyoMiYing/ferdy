package rokklancar.ferdydurkeaudiobookplayer.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import rokklancar.ferdydurkeaudiobookplayer.validation.EmailValidator;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {

    String message() default "Neveljaven email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
