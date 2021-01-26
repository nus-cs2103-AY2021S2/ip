public class DukeException extends Throwable {
    private String msg;

    DukeException(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ErRoR: " + msg;
    }
}
