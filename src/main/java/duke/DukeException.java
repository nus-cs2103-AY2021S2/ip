package main.java.duke;

public class DukeException extends Exception {
    // private final String err;

    public DukeException(String err) {
        super(err);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
