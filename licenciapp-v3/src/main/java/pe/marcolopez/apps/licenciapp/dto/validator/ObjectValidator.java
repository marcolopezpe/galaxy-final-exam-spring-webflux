package pe.marcolopez.apps.licenciapp.dto.validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pe.marcolopez.apps.licenciapp.exceptions.web.ObjectValidationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class ObjectValidator {

  private final Validator validator;

  public <T> void validate(T object) {
    Set<ConstraintViolation<T>> errors = validator.validate(object);
    Map<String, List<String>> errorDetails = new HashMap<>();

    if (!errors.isEmpty()) {
      log.info("### Errors: {}", errors);
      for (ConstraintViolation<T> violation : errors) {
        String propertyPath = violation.getPropertyPath().toString();
        String message = violation.getMessage();

        errorDetails.computeIfAbsent(propertyPath, k -> new ArrayList<>()).add(message);
      }

      throw new ObjectValidationException(errorDetails.toString());
    }
  }
}
