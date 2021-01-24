/**
 * Enum for command, not in use.
 */
public enum CommandEnum {
    TODO ("todo"),
    DEADLINE ("deadline"),
    EVENT ("event"),
    DONE ("done"),
    DELETE ("delete"),
    LIST ("list"),
    BYE ("bye");

    private final String command;

    CommandEnum(String command) {
        this.command = command;
    }
}
