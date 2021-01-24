public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        Output output = new Output();
        return output.addLine() + "\n    ☹ OOPS! " + this.getMessage() + "\n" + output.addLine();
    }
}
