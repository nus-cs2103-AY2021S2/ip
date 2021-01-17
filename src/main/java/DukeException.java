public class DukeException extends Exception {
    String errorMessage;

    DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString() {
        String returnString = "\t  OOPS!!! " + errorMessage;
        return Duke.horizontalLine + returnString + Duke.horizontalLine + "\n";
    }
}
