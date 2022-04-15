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

    private boolean isBye;
    private boolean isHelp;
    private boolean isList;
    private boolean isDone;
    private boolean isDelete;
    private boolean isFind;
    private boolean isUndo;
    private boolean isRedo;
    private boolean isTodo;
    private boolean isDeadline;
    private boolean isEvent;
    private boolean isNewTask;
    private boolean isValidType;

    private String taskType = "";

    /**
     * Constructs a new ErrorChecker object used for checking errors in user input.
     *
     * @param input User input.
     * @param tasks Task list.
     */
    public ErrorChecker(String input, ArrayList<Task> tasks) {
        this.input = input;
        this.tasks = tasks;

        isBye = input.equals("bye");
        isHelp = input.equals("help");
        isList = input.equals("list");
        isDone = input.startsWith("done");
        isDelete = input.startsWith("delete");
        isFind = input.startsWith("find");
        isUndo = input.equals("undo");
        isRedo = input.equals("redo");
        isTodo = input.startsWith("todo");
        isDeadline = input.startsWith("deadline");
        isEvent = input.startsWith("event");
        isNewTask = isTodo || isDeadline || isEvent;
        isValidType = isBye || isHelp || isList || isDone || isDelete
                || isFind || isUndo || isRedo || isNewTask;
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
        if (!isValidType) {
            errorMessage = new IllegalInputException("").toString();
            return false;
        }

        try {
            checkInputFormat();
        } catch (StringIndexOutOfBoundsException ex) {
            if (isDone || isDelete) {
                errorMessage = new IllegalDoneDeleteException(ex.getMessage(), taskType).toString();
                return false;
            } else if (isFind) {
                errorMessage = new IllegalFindException(ex.getMessage()).toString();
            } else if (isNewTask) {
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
            } else if (isEvent) {
                errorMessage = "Oops, I don't understand that date format!\n"
                        + "Please re-enter with the format DD-MM-YYYY /from H AM/PM /to H AM/PM!";
            }
            return false;
        }

        return true;
    }

    private void checkInputFormat() {
        if (isDone) {
            taskType = "done";
            checkDone();
        } else if (isDelete) {
            taskType = "delete";
            checkDelete();
        } else if (isFind) {
            input.substring(6); // Checking that search keyword was specified
        } else if (isTodo) {
            taskType = "todo";
            input.substring(6); // Checking that task description was specified
        } else if (isDeadline) {
            taskType = "deadline";
            checkDeadline();
        } else if (isEvent) {
            taskType = "event";
            checkEvent();
        }
    }

    private void checkDone() {
        input.substring(6); // Checking that task number was specified

        // Checking that task exists in list
        String taskToMark = input.substring(5);
        int taskToMarkInt = Integer.parseInt(taskToMark);
        tasks.get(taskToMarkInt - 1);
    }

    private void checkDelete() {
        input.substring(8); // Checking that task number was specified

        // Checking that task exists in list
        String taskToDelete = input.substring(7);
        int taskToDeleteInt = Integer.parseInt(taskToDelete);
        tasks.get(taskToDeleteInt - 1);
    }

    private void checkDeadline() {
        input.substring(10); // Checking that task description was specified

        String[] inputSplit = input.split("/");
        // Checking that format of deadline task input is correct
        if (inputSplit.length < 2) {
            throw (new IllegalTaskException("", "deadline"));
        }
        if (!inputSplit[1].startsWith("by")) {
            throw new IllegalTaskException("", "deadline");
        }
        // Checking that format of date in input is correct
        DateTimeConverter dateTimeConverter = new DateTimeConverter(inputSplit);
        dateTimeConverter.convertDate();
    }

    private void checkEvent() {
        input.substring(7); // Checking that task description was specified

        String[] inputSplit = input.split("/");
        // Checking that format of deadline task input is correct
        if (inputSplit.length < 4) {
            throw (new IllegalTaskException("", "deadline"));
        }
        if (!inputSplit[1].startsWith("on")) {
            throw new IllegalTaskException("", "event");
        }
        if (!inputSplit[2].startsWith("from") || !inputSplit[3].startsWith("to")) {
            throw new IllegalTaskException("", "event");
        }
        // Checking that format of date and times in input is correct
        DateTimeConverter dateTimeConverter = new DateTimeConverter(inputSplit);
        dateTimeConverter.convertDate();
        dateTimeConverter.convertTime("from");
        dateTimeConverter.convertTime("to");
    }
}
