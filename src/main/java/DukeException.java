package main.java;

public class DukeException extends Exception {
    private final String err;

    public DukeException(String err) {
        this.err = err;
    }

    @Override
    public String toString() {
        return err;
    }
}
