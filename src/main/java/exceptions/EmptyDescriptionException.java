package main.java.exceptions;

public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String s) {
        super("OOPS!!! The description of a " + s + " cannot be empty.");
    }
}
