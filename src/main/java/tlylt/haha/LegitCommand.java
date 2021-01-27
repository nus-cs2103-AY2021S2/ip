package tlylt.haha;

/**
 * Representation of valid commands.
 */
public enum LegitCommand {
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo"),
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    BYE("bye");
    private final String rep;
    private String detail = "";

    /**
     * Constructs a valid command.
     *
     * @param rep String representation of a valid command.
     */
    LegitCommand(String rep) {
        this.rep = rep;
    }

    /**
     * Setter for command detail. This will include information like
     * the task number for "done" command.
     *
     * @param detail Details regarding this command.
     */
    void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * Returns command details for further processing.
     *
     * @return String details such as task number.
     */
    String getDetail() {
        return detail;
    }

    /**
     * Getter for command representation.
     *
     * @return String representation of command.
     */
    String getRep() {
        return rep;
    }

}
