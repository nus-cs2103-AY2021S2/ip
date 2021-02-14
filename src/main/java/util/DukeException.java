package util;

public class DukeException extends Exception {
    private final String msg;

    public DukeException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
