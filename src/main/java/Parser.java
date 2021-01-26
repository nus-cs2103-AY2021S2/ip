import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException , DukeDeadlineException{
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

        if (firstSpace == -1) {
            throw new DukeException("The description cannot be empty");
        }

        //Commands that needs a description
        Task task = null;
        if(keyword.equalsIgnoreCase("todo")) {
            task = new Todo(fullCommand.substring(firstSpace));
        } else {
            int firstSlash = fullCommand.indexOf("/");
            String taskDescription = fullCommand.substring(firstSpace, firstSlash);
            task = createTaskWithDeadline(fullCommand, keyword, taskDescription, firstSlash);
        }

        return new AddCommand(task);
    }

    private static Task createTaskWithDeadline(String fullCommand, String keyword, String taskDecription, int firstSlash) throws DukeDeadlineException {
        Task t = null;
        int nextSpace = fullCommand.indexOf(" ", firstSlash) + 1;
        if (firstSlash == -1) {
            throw new DukeDeadlineException("Deadline is missing");
        }

        switch (keyword) {
        case "deadline":
            String errorMessage = "Format of the deadline of a deadline task should be (Year-Month-Day time (24 hours)";
            LocalDateTime deadline = parseDate(fullCommand.substring(nextSpace), errorMessage);
            t = new Deadline(taskDecription, deadline);
        }
        return t;
    }

    public static LocalDateTime parseDate(String date, String errorMessage) throws DukeDeadlineException{
        try {
            return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-M-d Hmm"));
        } catch (DateTimeParseException e) {
            throw new DukeDeadlineException(errorMessage);
        }
    }

    public static Task parseForText(String csvData,Ui ui) {
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
        } catch (DukeDeadlineException e) {
            ui.showError(e.getMessage());
        }
        return task;
    }
}
