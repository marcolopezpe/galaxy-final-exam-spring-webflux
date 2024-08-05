package pe.marcolopez.apps.licenciapp.dto.constraint;

import static java.lang.annotation.ElementType.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotEmpty;
import pe.marcolopez.apps.licenciapp.dto.validator.EmailValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NotEmpty
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface EmailValid {

  String message() default "Email is invalid";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
