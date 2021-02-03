package duke.commands;

/**
 * AddCommandType contains command type for adding command types.
 */
public enum SpecificCommandType {
    TODO("todo", 2), DEADLINE("deadline", 9), EVENT("event", 6), FIND("find", 5);

    private final String name;
    private final int addCommandPostfix;

    SpecificCommandType(String name, int addCommandPostfix) {
        this.name = name;
        this.addCommandPostfix = addCommandPostfix;
    }

    /**
     * Returns string representation of AddCommandType.
     * @return string representation of the individual AddCommandType.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns postfix index for the AddCommandType.
     * @return postfix index of AddCommandType.
     */
    public int getPostfix() {
        return addCommandPostfix;
    }
}
