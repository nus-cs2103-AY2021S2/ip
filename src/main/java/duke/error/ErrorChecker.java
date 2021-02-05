package duke.error;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.datetime.DateTimeConverter;
import duke.task.Task;

/**
 * Error checker to check for errors in user input.
 *
 * @author  Nicole Ang
 */
public class ErrorChecker {
    protected String input;
    protected ArrayList<Task> tasks;
    protected String errorMessage;

    private boolean isDone;
    private boolean isDelete;
    private boolean isFind;
    private boolean isTodo;
    private boolean isDeadline;
    private boolean isEvent;
    private boolean isValidType;

    /**
     * Constructs a new ErrorChecker object used for checking errors in user input.
     *
     * @param input User input.
     * @param tasks Task list.
     */
    public ErrorChecker(String input, ArrayList<Task> tasks) {
        this.input = input;
        this.tasks = tasks;

        isDone = input.startsWith("done");
        isDelete = input.startsWith("delete");
        isFind = input.startsWith("find");
        isTodo = input.startsWith("todo");
        isDeadline = input.startsWith("deadline");
        isEvent = input.startsWith("event");
        isValidType = isDone || isDelete || isFind || isTodo || isDeadline || isEvent;
    }

    /**
     * Returns error message.
     *
     * @return Error message string.
     */
    public String getMessage() {
        return errorMessage;
    }

    /**
     * Checks whether the input is valid and formatted correctly.
     * If input is invalid, user will be prompted to re-enter valid input in correct format.
     *
     * @return True if input is valid and formatted correctly, and false otherwise.
     */
    public boolean isValid() {
        String taskType = "";

        // if (!input.equals("help") && !input.startsWith("done") && !input.startsWith("delete")
        //        && !input.startsWith("find") && !input.startsWith("todo") && !input.startsWith("deadline")
        //        && !input.startsWith("event")) {
        //    errorMessage = new IllegalInputException("").toString();
        //    return false;
        // }

        if (!isValidType) {
            errorMessage = new IllegalInputException("").toString();
            return false;
        }

        try {
            if (isDone) {
                taskType = "done";
                input.substring(6);
                String taskToMark = input.substring(5);
                int taskToMarkInt = Integer.parseInt(taskToMark);
                tasks.get(taskToMarkInt - 1);
            } else if (isDelete) {
                taskType = "delete";
                input.substring(8);
                String taskToDelete = input.substring(7);
                int taskToDeleteInt = Integer.parseInt(taskToDelete);
                tasks.get(taskToDeleteInt - 1);
            } else if (isFind) {
                input.substring(6);
            } else if (isTodo) {
                taskType = "todo";
                input.substring(6);
            } else if (isDeadline) {
                taskType = "deadline";
                input.substring(10);

                String[] inputSplit = input.split("/");

                if (!inputSplit[1].startsWith("by")) {
                    throw new IllegalTaskException("", "deadline");
                }

                DateTimeConverter dateTimeConverter = new DateTimeConverter(inputSplit);
                dateTimeConverter.convertDate();
            } else if (isEvent) {
                taskType = "event";
                input.substring(7);

                String[] inputSplit = input.split("/");

                if (!inputSplit[1].startsWith("on")) {
                    throw new IllegalTaskException("", "event");
                }
                if (!inputSplit[2].startsWith("from") || !inputSplit[3].startsWith("to")) {
                    throw new IllegalTaskException("", "event");
                }
                DateTimeConverter dateTimeConverter = new DateTimeConverter(inputSplit);
                dateTimeConverter.convertDate();
                dateTimeConverter.convertTime("from");
                dateTimeConverter.convertTime("to");
            }
        } catch (StringIndexOutOfBoundsException ex) {
            if (isDone || isDelete) {
                errorMessage = new IllegalDoneDeleteException(ex.getMessage(), taskType).toString();
                return false;
            } else if (isFind) {
                errorMessage = new IllegalFindException(ex.getMessage()).toString();
            } else {
                errorMessage = new IllegalTaskException(ex.getMessage(), taskType).toString();
                return false;
            }
        } catch (IndexOutOfBoundsException ex) {
            errorMessage = new OutOfBoundsDoneDeleteException(ex.getMessage()).toString();
            return false;
        } catch (DateTimeParseException ex) {
            if (isDeadline) {
                errorMessage = "Oops, I don't understand that date format!\n"
                        + "Please re-enter with the format DD-MM-YYYY!";
            } else {
                errorMessage = "Oops, I don't understand that date format!\n"
                        + "Please re-enter with the format DD-MM-YYYY /from H AM/PM /to H AM/PM!";
            }
            return false;
        }

        return true;
    }
}
