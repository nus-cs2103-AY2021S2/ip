package Exceptions;

public class DukeException extends Exception {
    private final String info;

    public DukeException(String message) {
        super(message);
        this.info = "";
    }

    public DukeException(String message, String info) {
        super(message);
        this.info = info;
    }

    public String getInfo() {
        return this.info;
    }


}
