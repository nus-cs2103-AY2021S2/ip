import java.util.HashMap;
import java.util.Map;

public enum Command {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    DELETE("delete");

    private final String label;
    private static final Map<String, Command> BY_LABEL = new HashMap<>();

    static {
        for (Command command : values()) {
            BY_LABEL.put(command.label, command);
        }
    }

    private Command(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Command valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}
