package main.java.exceptions;

public class EmptyTimeException extends Exception {
    public EmptyTimeException(String s) {
        super("OOPS!!! The time of a " + s + " cannot be empty.");
    }
}
