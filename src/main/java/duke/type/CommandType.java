package duke.type;

/**
 * Encapsulates a CommandType enum class that represents the type of command.
 * The class includes seven types: todo, clear, deadline, event, bye, list, delete, done and find.
 */
public enum CommandType {
    BYE("bye"),
    CLEAR("clear"),
    DEADLINE("deadline"),
    DELETE("delete"),
    DONE("done"),
    EVENT("event"),
    FIND("find"),
    LIST("list"),
    TODO("todo");

    private final String type;


    /**
     * Constructs a CommandType object with a String representation of the type.
     *
     * @param type String representation of the type.
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
