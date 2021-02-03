import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final String ERROR_DESCRIPTION = "OOPS!!! The description cannot be empty.";
    private static final String ERROR_SEARCH_TERM = "OPPS!!! The search term for find cannot be empty.";

    /**
     * Returns a Command object based on the fullCommand given
     * @param fullCommand (command entered by the user)
     * @return Command comamnd
     * @throws DukeException
     * @throws DukeDeadlineException
     */
    public static Command parse(String fullCommand) throws DukeException , DukeDeadlineException {
        fullCommand = fullCommand.trim();
        int firstSpace = fullCommand.indexOf(" ");
        String keyword = firstSpace == -1 ? fullCommand : fullCommand.substring(0, firstSpace).toLowerCase();

        if (fullCommand.isEmpty()) {
            throw new DukeException("Please enter a command");
        }

        //Commands that does not need a description
        if (keyword.equalsIgnoreCase("bye")) {
            return new SaveCommand(keyword);
        } else if (keyword.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (keyword.equalsIgnoreCase("done") || keyword.equalsIgnoreCase("delete")) {
            int option = Integer.parseInt(fullCommand.substring(firstSpace + 1)) - 1;
            switch (keyword) {
            case "done" :
                return new DoneCommand(option);
            case "delete":
                return new DeleteCommand(option);
            }
        }

        //Commands that needs a description
        Task task = null;
        if (keyword.equalsIgnoreCase("find")) {
            checkDescription(firstSpace, ERROR_SEARCH_TERM);
            return new FindCommand(fullCommand.substring(firstSpace));
        } else if (keyword.equalsIgnoreCase("todo")) {
            checkDescription(firstSpace, ERROR_DESCRIPTION);
            task = new Todo(fullCommand.substring(firstSpace));
        } else if (keyword.equalsIgnoreCase("deadline") || keyword.equalsIgnoreCase("event")) {
            checkDescription(firstSpace, ERROR_DESCRIPTION);
            task = createTaskWithDeadline(fullCommand, keyword, firstSpace);
        } else {
            return null;
        }

        return new AddCommand(task);
    }

    private static void checkDescription(int firstSpace, String errorMessage) throws DukeException {
        if (firstSpace == -1) {
            throw new DukeException(errorMessage);
        }
    }

    private static Task createTaskWithDeadline(String fullCommand, String keyword, int firstSpace) throws DukeDeadlineException {
        Task t = null;
        int firstSlash = fullCommand.indexOf("/");

        if (firstSlash == -1) {
            throw new DukeDeadlineException("OOPS!!! The deadline of a task cannot be empty.");
        }

        int nextSpace = fullCommand.indexOf(" ", firstSlash) + 1;
        String taskDescription = fullCommand.substring(firstSpace, firstSlash);
        String errorMessage;
        switch (keyword) {
        case "deadline":
            errorMessage = "OOPS!!! Format of the deadline of a deadline task should be "
                    + "(Year-Month-Day time (24 hours)";
            LocalDateTime deadline = parseDate(fullCommand.substring(nextSpace), errorMessage);
            t = new Deadline(taskDescription, deadline);
            break;
        case "event":
            errorMessage = "OOPS!!! Format of the time period of a Event task should be "
                    + "(Year-Month-Day Time(24 hours)-Time(24 hours)";
            LocalDateTime[] deadlines = parseDates(fullCommand.substring(nextSpace), errorMessage);
            t = new Event(taskDescription, deadlines[0], deadlines[1]);
            break;
        }
        return t;
    }

    /**
     * Returns a array of LocalDateTime object
     * @param data String that contains the date
     * @param errorMessage  error message to display when error is occur during prasing to LocalDateTIme
     * @return LocalDateTime[].
     * @throws DukeDeadlineException
     */

    public static LocalDateTime[] parseDates(String data, String errorMessage) throws DukeDeadlineException {
        int firstSpace = data.indexOf(" ");
        String date = data.substring(0,firstSpace);
        data = data.substring(firstSpace + 1);
        String[] timePeriod = data.split("-");
        if (timePeriod.length != 2) {
            throw new DukeDeadlineException(errorMessage);
        }
        LocalDateTime[] deadline = new LocalDateTime[2];
        for (int i = 0; i < timePeriod.length; i++) {
            deadline[i] = parseDate(date + " " + timePeriod[i], errorMessage);
        }
        return deadline;
    }

    /**
     * Returns LocalDateTime object based on string given
     * @param date String that contains the date in the format of yyyy-M-d hhmm
     * @param errorMessage error message to display when error is occur during prasing to LocalDateTIme
     * @return LocalDateTime
     * @throws DukeDeadlineException
     */
    public static LocalDateTime parseDate(String date, String errorMessage) throws DukeDeadlineException{
        try {
            return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-M-d Hmm"));
        } catch (DateTimeParseException e) {
            throw new DukeDeadlineException(errorMessage);
        }
    }

    /**
     * Returns a Task Object based on the csvData provided
     * @param csvData (a row from the saved file)
     * @param ui (UI object for pririnting
     * @return
     */

    public static Task parseForText(String csvData) throws DukeDeadlineException {
        String errorMessage = "The deadline for this task is corrupted (Required : yyyy-M-d hhmm";
        String[] taskArr = csvData.split(",");
        Task task = null;
        try {
            switch (taskArr[0]) {
            case "T":
                task = new Todo(taskArr[2]);
                break;
            case "E":
                String[] deadlineArr = {taskArr[3], taskArr[4]};
                LocalDateTime[] deadlines = new LocalDateTime[2];
                for (int i = 0; i < deadlineArr.length; i++) {
                    deadlines[i] = Parser.parseDate(deadlineArr[i], errorMessage);
                }
                task = new Event(taskArr[2], deadlines[0], deadlines[1]);
                break;
            case "D":
                LocalDateTime deadline = Parser.parseDate(taskArr[3], errorMessage);
                task = new Deadline(taskArr[2], deadline);
            }
            if (taskArr[1] == "1") {
                task.markAsDone();
            }
        } catch (DukeDeadlineException e){
            throw e;
        }
        return task;
    }
}
