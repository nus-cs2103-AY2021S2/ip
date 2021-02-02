package duke.command;

import java.util.HashMap;
import java.util.Map;

public enum CommandType {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    DELETE("delete");

    private final String label;
    private static final Map<String, CommandType> BY_LABEL = new HashMap<>();

    static {
        for (CommandType command : values()) {
            BY_LABEL.put(command.label, command);
        }
    }

    private CommandType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static CommandType valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}
