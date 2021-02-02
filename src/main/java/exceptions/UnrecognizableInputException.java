package main.java.exceptions;

public class UnrecognizableInputException extends Exception {
    public UnrecognizableInputException() {
        super("    Sorry, unrecognizable type of actions.\n    " +
                "Available actions are {todo, event, deadline, list, done, delete}");
    }
}
