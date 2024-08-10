package pe.marcolopez.apps.licenciapp.business.exceptions.web;

public class NotFoundException extends RuntimeException {

  public NotFoundException(String message) {
    super(message);
  }
}
