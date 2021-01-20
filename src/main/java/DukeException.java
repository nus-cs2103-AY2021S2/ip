package main.java;

public class DukeException extends Exception {
    protected String errorMessage = "☹ OOPS!!! ";

    DukeException(String s) {
        super(s);
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

}
