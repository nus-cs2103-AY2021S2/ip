package main.java;

public class InvalidCommandException extends DukeException {
    private static final String INDENTATION = "        ";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String SEPARATOR = INDENTATION + HORIZONTAL_LINE;

    @Override
    public String toString() {
        return SEPARATOR + "\nI'm sorry, but I don't know what that means :(\n" + SEPARATOR;
    }
}
