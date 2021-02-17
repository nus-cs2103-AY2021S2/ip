public class DescriptionException extends DukeException {
    /**
     * Fixed message for all DescriptionExceptions
     */
    static final String MESSAGE = "Description for tasks cannot contain '|'. "
        + "Please try again with a new description! :)";

    /**
     * Initializes a newly created DescriptionException object with the default MESSAGE.
     */
    public DescriptionException() {
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
