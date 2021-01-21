
public class EmptyException extends DukeException {
	
	static final String message = ":( OOPS! the description of a task cannot be empty.";

	public EmptyException() {
		super(message);
	}
}