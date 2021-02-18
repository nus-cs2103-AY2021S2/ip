public class UnknownInputError extends DukeException {

  protected String msg;

  public UnknownInputError(String msg) {
    super(msg);
  }
}
