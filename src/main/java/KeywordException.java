public class KeywordException extends DukeException {

    static final String MESSAGE = "Unidentified Keyword: Please try again using the correct keywords and order :)";

    public KeywordException() {
        super(MESSAGE);
    }

    @Override
    public String toString() {
        return super.toString() + "\n -----------------------------------------------------";

    }
}
