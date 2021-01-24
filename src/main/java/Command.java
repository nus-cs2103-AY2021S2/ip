public enum Command {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    DELETE("delete");

    private final String label;

    private Command(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Command valueOfLabel(String label) {
        for (Command c : values()) {
            if (c.label.equals(label)) {
                return c;
            }
        }
        return null;
    }
}
