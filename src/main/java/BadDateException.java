/**
 * An exception class used when the user inputs an invalid date or a date with improper format
 */
public class BadDateException extends Exception {
    /**
     * @return the exception message to ask user to format his input date correctly
     */
    @Override
    public String getMessage() {
        return "Please format your dead as yyyy-mm-dd";
    }
}