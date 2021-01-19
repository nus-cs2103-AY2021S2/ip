public class DukeException extends Exception{
    private final String msg;

    DukeException(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "OOPS!!! Error: " + this.msg;
    }
}
