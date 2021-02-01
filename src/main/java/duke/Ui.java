package duke;

import duke.task.Task;

/**
 *  The Ui class encapsulates methods to handle text displays to the CLI.
 */
public class Ui {
    private static final String PADDING = "  ";

    /**
     * Returns the chatbot's greeting message when a user starts the chatbot.
     */
    public String returnGreeting() {
        String greeting = "Hello! I'm Chandler, here to help you keep track of measly tasks"
                + " in your mundane human life. How may I help you today?";
        return greeting;
    }

    /**
     * Returns an error message in a string when a user inputs the list command with the wrong format.
     *
     * @return String containing error message of invalid list command.
     */
    public String listCmdError() {
        String errorMsg = "Sorry human, I do not understand your command."
                + "To access your list, enter 'list' with no additional descriptions.";
        return errorMsg;
    }

    /**
     * Returns an error message in a string when a user inputs the done command with too many parameters.
     *
     * @return String containing error message of invalid done command.
     */
    public String doneCmdTooManyArgsError() {
        String errorMsg = "Sorry human, please enter only one task for me to mark as complete."
                + "I am unable to process more than one task at one time.";
        return errorMsg;
    }

    /**
     * Returns an error message in a string when a user inputs the done command with no parameters.
     *
     * @return String containing error message of invalid done command.
     */
    public String doneCmdNoArgsError() {
        String errorMsg = "Sorry human, please enter a task number.";
        return errorMsg;
    }

    /**
     * Returns an error message in a string when a user inputs the done command with the wrong parameter.
     *
     * @return String containing error message of invalid done command.
     */
    public String doneCmdInvalidArgsError() {
        String errorMsg = "Sorry human, please enter the number of the task you want me to"
                + " mark as complete.";
        return errorMsg;
    }

    /**
     * Returns an error message in a string when a user inputs the event command with the wrong format.
     *
     * @return String containing error message of invalid event command.
     */
    public String eventFormatError() {
        String eErrorMsg = "Invalid format. Please enter as such:"
                + "\n"
                + "event <EVENT_NAME> /at <EVENT_TIME>";
        return eErrorMsg;
    }

    /**
     * Returns an error message in a string when a user inputs the deadline command with the wrong format.
     *
     * @return String containing error message of invalid deadline command.
     */
    public String deadlineFormatError() {
        String dErrorMsg = "Invalid format. Please enter as such:"
                + "\n"
                + "deadline <TASK_NAME> /by <DEADLINE_TIME>";
        return dErrorMsg;
    }

    /**
     * Returns an error message in a string when a user inputs the wrong date format.
     *
     * @return String containing error message of invalid date input.
     */
    public String dateFormatError() {
        String dateErrorMsg = "Invalid date format. Please enter as such:"
                + "\n"
                + "yyyy-MM-dd HHmm (e.g. 2019-10-15 1800)";
        return dateErrorMsg;
    }

    /**
     * Returns an error message in a string when a user inputs the delete command with too many parameters.
     *
     * @return String containing error message of invalid delete command.
     */
    public String deleteCmdTooManyArgsError() {
        String errorMsg = "Sorry human, please enter only one task for me to delete."
                + "\n"
                + "I am unable to process more than one task at one time.";
        return errorMsg;
    }

    /**
     * Returns error message from DukeException.
     *
     * @param e DukeException.
     */
    public String returnDukeExceptionMsg(DukeException e) {
        return e.toString();
    }

    /**
     * Returns deleted task message.
     *
     * @param task Task that has just been deleted.
     * @param taskList List of all tasks.
     */
    public String returnDeletedMsg(Task task, TaskList taskList) {
        return "Task has been deleted." + "\n"
                + "Just like you will be deleted someday too." + "\n"
                + PADDING + task + "\n"
                + "Now you have " + taskList.getSize()
                + (taskList.getSize() == 1 ? " task " : " tasks ") + "in your list." + "\n";
    }

    /**
     * Returns completed task message.
     *
     * @param task Task that has just been marked as done.
     */
    public String returnDoneMsg(Task task) {
        return "Well done human on completing " + task.getTaskName() + "!" + "\n"
                + "I have marked it as done." + "\n"
                + PADDING + task + "\n";
    }


    /**
     * Returns all tasks.
     *
     * @param taskList List of all tasks.
     */
    public String returnList(TaskList taskList) {
        String toReturn = "Here are the tasks in your list:" + "\n";
        for (int i = 1; i <= taskList.getSize(); i++) {
            toReturn += (i + ". " + taskList.getIndex(i - 1)) + "\n";
        }
        return toReturn;
    }

    /**
     * Prints added task to list message.
     *
     * @param task Task that has just been added to list.
     * @param taskList List of all tasks.
     */
    public String returnAddToListMsg(Task task, TaskList taskList) {
        return "Got it: I've added this task:" + "\n"
                + PADDING + task + "\n"
                + "Now you have " + taskList.getSize()
                + (taskList.getSize() == 1 ? " task " : " tasks ") + "in your list." + "\n";
    }

    /**
     * Returns goodbye message.
     */
    public String returnBye() {
        String farewell = "Bye! Hope you complete your tasks!";
        return farewell;
    }
}
