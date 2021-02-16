package main.java;

public class EmptyDescriptionException extends DukeException {
    private static final String INDENTATION = "        ";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String SEPARATOR = INDENTATION + HORIZONTAL_LINE;

    @Override
    public String toString() {
        return SEPARATOR + "\nThe description of a task cannot be empty.\n" + SEPARATOR;
    }
}
