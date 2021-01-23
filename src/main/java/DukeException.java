public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public static void taskHandler(String input) throws DukeException {
        if (input.equals(""))
            throw new DukeException("☹ OOPS! The description of a task cannot be empty");
    }

    public static void listHandler(int totalTasks) throws DukeException {
        if (totalTasks == 0)
            throw new DukeException("☹ OOPS! It seems you have nothing on your list.");
    }

    public static void deleteHandler(int taskNum, int n) throws DukeException {
        if (taskNum <= 0 || taskNum > n)
            throw new DukeException("☹ OOPS! Invalid event number. Please specify which event would you like to delete?");
    }

    @Override
    public String toString() {
        return Output.addLine() + "\n    ☹ OOPS! " + this.getMessage() + "\n" + Output.addLine();
    }
}
