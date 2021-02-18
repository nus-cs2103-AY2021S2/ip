public class KeywordException extends DukeException {

    /**
     * Fixed message for all KeywordExceptions
     */
    static final String MESSAGE = "Unidentified Keyword: Please try again using the correct keywords and order :)";

    /**
     * Initializes a newly created KeywordException object with the default MESSAGE.
     */
    public KeywordException() {
        super(MESSAGE);
    }

    /**
     * Converts this object to a string that represents the error message
     * @return A string representing the error message
     */
    @Override
    public String toString() {
        return super.toString();

    }
}
