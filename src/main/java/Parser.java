import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Parse the input string into recognisable command.
     *
     * @param string input.
     * @return command.
     * @throws DukeException if command cannot be created.
     */
    public static Command getCommand(String string) throws DukeException {
        String action;
        String info;
        Command command;

        if (string.contains(" ")) {
            String[] str = string.split(" ", 2);
            action = str[0];
            info = str[1];
        } else {
            action = string;
            info = "";
        }

        switch (action) {
            case "todo":
                command = new TodoCommand(action, info);
                break;
            case "deadline":
                command = new DeadlineCommand(action, info);
                break;
            case "event":
                command = new EventCommand(action, info);
                break;
            case "done":
                command = new DoneCommand(action, info);
                break;
            case "delete":
                command = new DeleteCommand(action, info);
                break;
            case "bye":
                command = new ExitCommand(action, info);
                break;
            case "list":
                command = new ListCommand(action, info);
                break;
            case "find":
                command = new FindCommand(action, info);
                break;
            case "date":
                command = new DateCommand(action, info);
                break;
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means.");
        }

        return command;
    }

    /**
     * Parse the information string to get the task required.
     *
     * @param info details.
     * @return task.
     * @throws DukeException if there is no task.
     */
    public static String getTask(String info) throws DukeException {
        String task;

        if (info.equals("")) {
            throw new DukeException("OOPS!!! The description cannot be empty.");
        }

        if (info.contains(" /")) {
            String[] str = info.split(" /", 2);
            task = str[0];
        } else {
            task = info;
        }

        return task;
    }

    /**
     * Parse the information string to get the timing for event.
     *
     * @param info detail.
     * @return time at.
     * @throws DukeException if the time is wrong.
     */
    public static String getTimeAt(String info) throws DukeException {
        String time;

        if (info.contains(" /at ")) {
            String[] str = info.split(" /at ", 2);
            try {
                LocalDate date = LocalDate.parse(str[1]);
                time = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException e) {
                throw new DukeException("OOPS!!! The timing is not in the correct format.");
            }
        } else {
            throw new DukeException("OOPS!!! The timing cannot be empty.");
        }
        return time;
    }

    /**
     * Parse the information string to get the timing for deadline.
     *
     * @param info detail.
     * @return time by.
     * @throws DukeException if time is wrong.
     */
    public static String getTimeBy(String info) throws DukeException {
        String time;

        if (info.contains(" /by ")) {
            String[] str = info.split(" /by ", 2);
            try {
                LocalDate date = LocalDate.parse(str[1]);
                time = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException e) {
                throw new DukeException("OOPS!!! The timing is not in the correct format.");
            }
        } else {
            throw new DukeException("OOPS!!! The timing cannot be empty.");
        }

        return time;
    }

}
