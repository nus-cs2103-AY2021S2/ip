package exceptions;

public class DukeTaskIndexOutOfRangeException extends Exception {
  /**
   * Add default generate serial verion uid
   */
  private static final long serialVersionUID = 1L;

  public DukeTaskIndexOutOfRangeException(String message) {
    super(message);
  }
}
