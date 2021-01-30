package duke.type;

/**
 * Encapsulate a CommandType enum class that represents the type of command.
 * The class includes seven types: todo, deadline, event, bye, list, delete, done and find.
 */
public enum CommandType {
	TODO("todo"),
	DEADLINE("deadline"),
	EVENT("event"),
	BYE("bye"),
	DONE("done"),
	LIST("list"),
	DELETE("delete");

	private String type;

	/**
	 * Construct a CommandType object with a String representation of the type.
	 * @param type
	 */
	CommandType(String type) {
		this.type = type;
	}

	/**
	 * Gets the CommandType object from a string representation of the type.
	 * Returns null if no such object exists.
	 *
	 * @param type a string representing the type.
	 * @return an CommandType object of the specified type and null if no such object exists.
	 */
	public static CommandType valueOfType(String type) {
		for (CommandType t : values()) {
			if (t.type.equals(type)) {
				return t;
			}
		}
		return null;
	}
}
