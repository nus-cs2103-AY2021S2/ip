package exceptions;

public class DukeCommandNotFoundException extends Exception {
  /**
   * Add default generate serial verion uid
   */
  private static final long serialVersionUID = 1L;

  public DukeCommandNotFoundException(String message) {
    super(message);
  }
}
