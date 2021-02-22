package duke;

/**
 * DukeException is an exception made specially for Duke.
 */
@SuppressWarnings("checkstyle:SummaryJavadoc")
class DukeException extends Exception {
    DukeException(String message) {
        super(message);
    }
}
