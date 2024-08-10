package pe.marcolopez.apps.licenciapp.commons.dto.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pe.marcolopez.apps.licenciapp.commons.dto.constraint.EmailValid;

import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<EmailValid, String> {

  String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
      + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return patternMatches(value, regexPattern);
  }

  private boolean patternMatches(String emailAddress, String regexPattern) {
    return Pattern.compile(regexPattern)
        .matcher(emailAddress)
        .matches();
  }
}
