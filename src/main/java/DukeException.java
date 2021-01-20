public class DukeException extends Exception{
    private String exception;

    public DukeException(String exception) {
        super(exception);
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "Omo... I'm sorry..." + exception;
    }
}
