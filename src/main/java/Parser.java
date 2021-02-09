import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A class that deals with making sense of the user command.
 */
public class Parser {
    /**
     * Parses the user inputs into commands that the program understands and perform actions accordingly.
     *
     * @param command User inputs.
     * @return Formatted date eg. changes 1/1/2011 to 01/01/2011.
     * @throws DukeException If an invalid command is given by the user. It also happens when there's lack of
     *                       information when task is created such as no description, date and time.
     * @throws IOException   If the named file exists but is a rather than a regular file, does not exist but
     *                       cannot be created, or cannot be opened for any other reason.
     */
    public static void parse(String command) throws DukeException, IOException {
        Ui ui = new Ui();
        if (command.equals("bye")) {
            ui.bye();
        } else if (command.equals("list")) {
            list();
        } else if (command.startsWith("done")) {
            done(command);
        } else if (command.startsWith("find")) {
            find(command);
        } else if (command.startsWith("todo")) {
            addToDo(command);
        } else if (command.startsWith("event")) {
            addEvent(command);
        } else if (command.startsWith("delete")) {
            delete(command);
        } else if (command.startsWith("deadline")) {
            addDeadline(command);
        } else {
            Duke.respond = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                    "Our program has terminated, please restart to continue using our service";
            throw (new DukeException("\n    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + "    ____________________________________________________________"));
        }
    }

    /**
     * List all the tasks in TaskList.
     */
    public static void list() {
        TaskList.list();
    }

    /**
     * Initiates find in TaskList.
     *
     * @param command User command.
     * @throws DukeException If an invalid command is given by the user. It also happens when index of task
     *                       to be deleted is not provided.
     */
    public static void find(String command) throws DukeException {
        if (command.length() == 4) {
            throw new DukeException("Keyword for search is missing!");
        }
        TaskList.find(command.substring(5));
    }

    /**
     * Initiates done in TaskList.
     *
     * @param command User command.
     * @throws DukeException If an invalid command is given by the user. It also happens when index of task
     *                       to be deleted is not provided.
     */
    public static void done(String command) throws DukeException {
        if (command.length() == 4) {
            throw new DukeException("Index for task done is missing!");
        }
        TaskList.done(Integer.parseInt(command.substring(5)));
    }

    /**
     * Initiates delete in TaskList.
     *
     * @param command User command.
     * @throws DukeException If an invalid command is given by the user. It also happens when index of task
     *                       to be deleted is not provided.
     */
    public static void delete(String command) throws DukeException {
        if (command.length() == 7) {
            throw new DukeException("Index for task to be deleted is missing!");
        }
        TaskList.delete(Integer.parseInt(command.substring(7)));
    }

    /**
     * Add toDo into TaskList.
     *
     * @param command User command.
     * @throws DukeException If an invalid command is given by the user. It also happens when there's lack of
     *                       information when task is created such as no description.
     */
    public static void addToDo(String command) throws DukeException {
        ToDo todo = new ToDo(getDescription(command, "T"));
        if (TaskList.isDuplicate(todo)) {
            throw new DukeException("This task is duplicated!");
        }
        TaskList.addTask(todo);
    }

    /**
     * Add event into TaskList.
     *
     * @param command User command.
     * @throws DukeException If an invalid command is given by the user. It also happens when there's lack of
     *                       information when task is created such as no description, date and time.
     */
    public static void addEvent(String command) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String eventDescription = Parser.getDescription(command, "E");
        String date = Parser.getDate(command, "E", eventDescription.length() + 11);
        int currentIndexOfString = eventDescription.length() + date.length();
        if (command.length() < currentIndexOfString + 16) {
            throw new DukeException("startTime need to be included!");
        } else if (command.length() < currentIndexOfString + 21) {
            throw new DukeException("endTime need to be included!");
        }
        String startTime = command.substring(currentIndexOfString + 12, currentIndexOfString + 16);
        String endTime = command.substring(currentIndexOfString + 17, currentIndexOfString + 21);
        String localStart = startTime.substring(0, 2) + ":" + startTime.substring(2, 4);
        String localEnd = endTime.substring(0, 2) + ":" + endTime.substring(2, 4);
        Event event = new Event(eventDescription, LocalDate.parse(format(date), formatter),
                LocalTime.parse(localStart), LocalTime.parse(localEnd));
        if (TaskList.isDuplicate(event)) {
            throw new DukeException("This task is duplicated!");
        }
        TaskList.addTask(event);
    }

    /**
     * Add Deadline into TaskList.
     *
     * @param command User command.
     * @throws DukeException If an invalid command is given by the user. It also happens when there's lack of
     *                       information when task is created such as no description, date and time.
     */
    public static void addDeadline(String command) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String deadlineDescription = Parser.getDescription(command, "D");
        String date = Parser.getDate(command, "D", deadlineDescription.length() + 14);
        int currentIndexOfString = deadlineDescription.length() + date.length();
        if (command.length() < currentIndexOfString + 15) {
            throw new DukeException("Time need to be included!");
        }
        String time = command.substring(currentIndexOfString + 15);
        String localTime = time.substring(0, 2) + ":" + time.substring(2, 4);
        Deadline deadline = new Deadline(deadlineDescription,
                LocalDate.parse(format(date), formatter), LocalTime.parse(localTime));
        if (TaskList.isDuplicate(deadline)) {
            throw new DukeException("This task is duplicated!");
        }
        TaskList.addTask(deadline);
    }

    /**
     * Returns date from user command.
     *
     * @param command User command.
     * @param type    Command type.
     * @param start   First index of date.
     * @return Return date from user command.
     */
    public static String getDate(String command, String type, int start) throws DukeException {
        String date = "";
        if (type.equals("E")) {
            for (int i = start; i < command.length(); i++) {
                if (command.charAt(i) == ' ') {
                    break;
                }
                date += command.charAt(i);
            }
        } else if (type.equals("D")) {
            for (int i = start; i < command.length(); i++) {
                if (command.charAt(i) == ' ') {
                    break;
                }
                date += command.charAt(i);
            }
        }
        if (date.length() == 0) {
            throw new DukeException("Date cannot be empty");
        }
        return date;
    }

    /**
     * Returns the description of task from user command.
     *
     * @param command User command.
     * @param type    Command type.
     * @return Description of task from user command.
     */
    public static String getDescription(String command, String type) throws DukeException {
        String description = "";
        switch (type) {
            case "T":
                if (command.length() == 4) {
                    throw new DukeException("Description cannot be empty");
                }
                description = command.substring(5);
                break;
            case "E":
                if (command.length() == 5) {
                    throw new DukeException("Description cannot be empty");
                }
                for (int i = 6; i < command.length(); i++) {
                    if (command.charAt(i) == ' ' && command.charAt(i + 1) == '/') {
                        break;
                    }
                    description += command.charAt(i);
                }
                break;
            case "D":
                if (command.length() == 8) {
                    throw new DukeException("Description cannot be empty");
                }
                for (int i = 9; i < command.length(); i++) {
                    if (command.charAt(i) == ' ' && command.charAt(i + 1) == '/') {
                        break;
                    }
                    description += command.charAt(i);
                }
                break;
        }
        return description;
    }

    /**
     * Returns a formatted date of the given date string.
     *
     * @param date Date.
     * @return Formatted date eg. changes 1/1/2011 to 01/01/2011.
     */
    public static String format(String date) {
        if (date.charAt(1) == '/') {
            date = "0" + date;
        }
        if (date.charAt(4) == '/') {
            date = date.substring(0, 3) + "0" + date.substring(3);
        }
        return date;
    }
}
