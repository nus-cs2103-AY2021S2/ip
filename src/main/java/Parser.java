import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;

public class Parser {
    private ArrayList<String> validOperators;
    private static final String timeFormat = "yyyy-M-d H:mm";

    public Parser(){
        this.validOperators = initialiseOperators();
    }

    private ArrayList<String> initialiseOperators() {
        ArrayList<String> operators = new ArrayList<>();
        // add valid operators into list
        operators.add("bye");
        operators.add("list");
        operators.add("todo");
        operators.add("deadline");
        operators.add("event");
        operators.add("done");
        operators.add("delete");
        operators.add("find");
        return operators ;
    }

    /**
     * Parse the user input to determine what command to execute later.
     * @param userInput full text command input by user
     * @return TaskManager object that contains extracted information from user input.
     * @throws DukeException If operator or task details are not valid.
     */
    public TaskManager parseCommand(String userInput) throws DukeException {
        String operator = parseOperator(userInput);
        // split command text by its first space into 2 parts
        String[] taskDetail = userInput.split(" ", 2);

        TaskManager taskManager = new TaskManager(operator);
        // parse command details according to their operator
        switch (operator) {
        case "done":
            int taskNumberToComplete = parseDone(taskDetail);
            taskManager =  new TaskManager(operator, Integer.toString(taskNumberToComplete));
            break;
        case "delete":
            int taskNumberToDelete = parseDelete(taskDetail);
            taskManager = new TaskManager(operator, Integer.toString(taskNumberToDelete));
            break;
        case "todo":
            String description = parseAddToDo(taskDetail);
            taskManager = new TaskManager(operator, description);
            break;
        case "deadline":
            String[] detailsDeadline = parseAddDeadline(taskDetail);
            taskManager = new TaskManager(operator, detailsDeadline);
            break;
        case "event":
                String[] detailsEvent = parseAddEvent(taskDetail);
                taskManager = new TaskManager(operator, detailsEvent);
                break;
        case "list":
                taskManager = new TaskManager(operator);
                break;
        case "find":
            String keyword = parseFindTask(taskDetail);
            taskManager = new TaskManager(operator, keyword);
            break;
        }
        return taskManager;
    }

    /**
     * Parse the operator input by user.
     *
     * @param userInput full text command input by user.
     * @return Extracted operator.
     * @throws DukeException If operator is not valid.
     */
    private String parseOperator(String userInput) throws DukeException {
        // split command text by its first space into 2 parts
        String[] commandParts = userInput.split(" ", 2);
        // parse operator in the first word of a text command
        String operator = commandParts[0];
        if (!isValidOperator(operator)) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return operator;
    }


    private boolean isValidOperator(String operator){
        return validOperators.contains(operator.toLowerCase());
    }


    /**
     * Return the task number to be marked as done.
     *
     * @param taskDetail full text command input by user.
     * @return Task number.
     * @throws DukeException If task number is empty or a non-integer is entered.
     */
    private int parseDone(String[] taskDetail) throws DukeException {
        // parse task number
        if (taskDetail.length == 1) {
            throw new DukeException("OOPS!!! Please specify the task number.");
        } else {
            try {
                int taskNo = Integer.valueOf(taskDetail[1]);
                return taskNo;
            } catch (Exception e) {
                throw new DukeException("OOPS!!! The task cannot be found.");
            }
        }
    }

    /**
     * Return the task number to be deleted.
     *
     * @param taskDetail full text command input by user.
     * @return Task number.
     * @throws DukeException If task number is empty or a non-integer is entered.
     */
    private int parseDelete(String[] taskDetail) throws DukeException {
        // parse task number
        if (taskDetail.length == 1) {
            throw new DukeException("OOPS!!! Please specify the task number.");
        } else {
            try {
                int taskNo = Integer.valueOf(taskDetail[1]);
                return taskNo;
            } catch (Exception e) {
                throw new DukeException("OOPS!!! The task cannot be found.");
            }
        }
    }

    /**
     * Return the description of a ToDo task to be added specified by user.
     *
     * @param taskDetail full text command input by user.
     * @return Description of ToDo.
     * @throws DukeException If description is empty.
     */
    private String parseAddToDo(String[] taskDetail) throws DukeException {
        // parse description of ToDo
        if(taskDetail.length == 1 || taskDetail[1].isBlank()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else{
            String description = taskDetail[1];
            return description;
        }
    }

    /**
     * Return the description and due time of a Deadline task to be added specified by user.
     *
     * @param taskDetail full text command input by user.
     * @return List containing description and due time of Deadline.
     * @throws DukeException If description is empty or due time does not follow 'yyyy-M-d H:mm' format.
     */
    private String[] parseAddDeadline(String[] taskDetail) throws DukeException {
        // parse details of Deadline
        if(taskDetail.length == 1 || taskDetail[1].isBlank()) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else{
            // split details to description and time
            String[] details = taskDetail[1].split(" /by ", 2);
            if (details.length == 1 || !isValidTime(details[1])) {
                throw new DukeException("OOPS!! Please follow the correct data/time format: yyyy-MM-dd HH:mm");
            }
            String description = details[0];
            String by = details[1];
            return new String[] {description, by};
        }
    }

    /**
     * Return the description and time of an Event task to be added specified by user.
     *
     * @param taskDetail full text command input by user.
     * @return List containing description and time of event.
     * @throws DukeException If description is empty or time does not follow 'yyyy-M-d H:mm' format.
     */
    private String[] parseAddEvent(String[] taskDetail) throws DukeException {

        // parse details of Event
        if (taskDetail.length == 1 || taskDetail[1].isBlank()) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        } else{
            // split details to description and time
            String[] details = taskDetail[1].split(" /at ", 2);
            if (details.length == 1 || !isValidTime(details[1])) {
                throw new DukeException("OOPS!! Please follow the correct data/time format: yyyy-MM-dd HH:mm");
            }
            String description = details[0];
            String at = details[1];
            return new String[] {description, at};
        }
    }

    /**
     * Return the keyword specified by user.
     *
     * @param taskDetail full text command input by user.
     * @return Keyword to be used for finding matching tasks.
     * @throws DukeException If keyword is empty.
     */
    private String parseFindTask(String[] taskDetail) throws DukeException {
        // parse keyword of task
        if (taskDetail.length == 1 || taskDetail[1].isBlank()) {
            throw new DukeException("OOPS!!! There is no matching task.");
        } else{
            String keyword = taskDetail[1];
            return keyword;
        }
    }

    /**
     * Return if the time entered by user is valid.
     *
     * @param time Time of Deadline or Event.
     * @return True if the time follows "yyyy-M-d H:mm"; else, return false.
     * @throws DateTimeParseException If time is in invalid format.
     */
    private boolean isValidTime(String time) throws DateTimeParseException {
        try {
            // convert time from String to LocalDateTime
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-M-d H:mm", Locale.ENGLISH);
            inputFormat.parse(time);
        } catch (DateTimeParseException e){
            return false;
        }
        return true;
    }
}
