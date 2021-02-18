package duke;

/**
 * Class DukeException represents an exception resulted due to wrong format of command
 * entered into Danh"s Duke
 */
class DukeException extends Exception {

    /**
     * Returns a Duke exception with specified error message.
     *
     * @param errorMessage the error message of this Duke's exception.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
