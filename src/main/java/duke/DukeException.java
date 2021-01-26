package duke;

public class DukeException extends Throwable {
    private String msg;

    public DukeException(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
