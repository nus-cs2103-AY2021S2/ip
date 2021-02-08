package duke;

import java.lang.Exception;

public class DukeException extends Exception {

	/**
	 * This is the constructor for Duke's custom exceptions
	 * @param message the error message to be displayed
	 */
	public DukeException(String message) {
		super(message);
	}
}