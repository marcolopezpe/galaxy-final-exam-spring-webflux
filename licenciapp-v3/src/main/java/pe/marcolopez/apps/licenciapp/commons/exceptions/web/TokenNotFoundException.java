package pe.marcolopez.apps.licenciapp.commons.exceptions.web;

public class TokenNotFoundException extends RuntimeException {

  public TokenNotFoundException(String message) {
    super(message);
  }
}
