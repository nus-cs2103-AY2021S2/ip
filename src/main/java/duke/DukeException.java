package duke;

public class DukeException extends Exception {

    public DukeException() {
        super();
    }

    public DukeException(String message) {
        super(message);
    }

    /**
     * Prints an error message
     *
     * @param message This is the error message
     */
    public void printError(String message) {
        System.out.println("\n---------------------------------------");
        System.out.println(message);
        System.out.println("---------------------------------------");
    }
}
