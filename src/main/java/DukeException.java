public class DukeException extends Exception {
    String errorMessage;

    DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString() {
        return "\t  OOPS!!! " + errorMessage;
    }
}
