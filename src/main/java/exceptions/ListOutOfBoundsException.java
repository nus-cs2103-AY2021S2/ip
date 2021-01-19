package main.java.exceptions;

public class ListOutOfBoundsException extends Exception {
    public ListOutOfBoundsException(int size) {
        super("OOPS!!! The list currently only has " + size + " elements.");
    }
}
