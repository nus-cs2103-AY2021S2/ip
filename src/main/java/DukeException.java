import java.util.ArrayList;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public static void validInputHandler(ArrayList<String> validInputs, String input) throws DukeException {
        if (!validInputs.contains(input))
            throw new DukeException("☹ OOPS! I'm sorry, but I don't know what that means :-(");
    }

    public static void taskHandler(String input) throws DukeException {
        if (input.equals(""))
            throw new DukeException("☹ OOPS! The description of a task cannot be empty");
    }

    public static void dateHandler(int input) throws DukeException {
        if (input < 2)
            throw new DukeException("☹ OOPS! Please specify a date/time using /by or /at!");
    }

    public static void doneHandler(int taskNum, int totalTasks) throws DukeException {
        if (taskNum <= 0 || taskNum > totalTasks)
            throw new DukeException("☹ OOPS! Invalid event number. Please specify which event would you like to mark done?");
    }

    public static void listHandler(int totalTasks) throws DukeException {
        if (totalTasks == 0)
            throw new DukeException("☹ OOPS! It seems you have nothing on your list.");
    }

    public static void deleteHandler(int taskNum, int n) throws DukeException {
        if (taskNum <= 0 || taskNum > n)
            throw new DukeException("☹ OOPS! Invalid event number. Please specify which event would you like to delete?");
    }
}
