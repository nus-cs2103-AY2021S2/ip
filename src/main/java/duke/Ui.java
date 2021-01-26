package duke;

import duke.task.Task;

/**
 *  The Ui class encapsulates methods to handle text displays to the CLI.
 */
public class Ui {
    private static final String TOP_BORDER = "╭------------------------------------------------------╮";
    private static final String BTM_BORDER = "╰|╱ ---------------------------------------------------╯";
    private static final String PADDING = "  ";

    /**
     * Prints the chatbot's greeting message when a user starts the chatbot.
     */
    public static void printGreeting() {
        String logo = "              .--.    .-.         .-.   \n"
                + "             : .-'    : :         : :   \n"
                + " .--.  .--.  : `;.--. : `-.  .--. : `-. \n"
                + "'  ..'' .; ; : :' '_.'' .; :' '_.'' .; :\n"
                + "`.__.'`.__,_;:_;`.__.'`.__.'`.__.'`.__.'";
        System.out.println(logo);
        String greeting = " ╭------------------------------------------------------------------╮\n"
                + " |  Hello! I'm cafebeb, here to help you keep track of measly tasks |\n"
                + " |  in your mundane human life. How may I help you today?           |\n"
                + " ╰|╱ ---------------------------------------------------------------╯\n";

        System.out.println(greeting);
    }

    /**
     * Returns an error message in a string when a user inputs the list command with the wrong format.
     *
     * @return String containing error message of invalid list command.
     */
    public String listCmdError() {
        String errorMsg = "Sorry human, I do not understand your command."
                + "\n"
                + PADDING
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
                + "\n"
                + PADDING
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
                + "\n"
                + PADDING
                + "mark as complete.";
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
                + PADDING
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
                + PADDING
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
                + PADDING
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
                + PADDING
                + "I am unable to process more than one task at one time.";
        return errorMsg;
    }

    /**
     * Prints error message from DukeException.
     *
     * @param e DukeException.
     */
    public void printDukeException(DukeException e) {
        System.out.println(e);
    }

    /**
     * Prints deleted task message.
     *
     * @param task Task that has just been deleted.
     * @param taskList List of all tasks.
     */
    public void printDeletedMessage(Task task, TaskList taskList) {
        System.out.println(TOP_BORDER);
        System.out.println(PADDING + "Task has been deleted.");
        System.out.println(PADDING + "Just like you will be deleted someday too.");
        System.out.println(PADDING + PADDING + task);
        System.out.println(PADDING + "Now you have " + taskList.getSize()
                + (taskList.getSize() == 1 ? " task " : " tasks ") + "in your list.");
        System.out.println(BTM_BORDER);
    }

    /**
     * Prints completed task message.
     *
     * @param task Task that has just been marked as done.
     */
    public void printDoneMessage(Task task) {
        System.out.println(TOP_BORDER);
        System.out.println(PADDING + "Well done human on completing " + task.getTaskName() + "!");
        System.out.println(PADDING + "I have marked it as done.");
        System.out.println(PADDING + PADDING + task);
        System.out.println(BTM_BORDER);
    }


    /**
     * Prints all tasks.
     *
     * @param taskList List of all tasks.
     */
    public void printList(TaskList taskList) {
        System.out.println(TOP_BORDER);
        System.out.println(PADDING + "Here are the tasks in your list:");
        for (int i = 1; i <= taskList.getSize(); i++) {
            System.out.println(PADDING + i + ". " + taskList.getIndex(i - 1));
        }
        System.out.println(BTM_BORDER);
    }

    /**
     * Prints added task to list message.
     *
     * @param task Task that has just been added to list.
     * @param taskList List of all tasks.
     */
    public void printAddToList(Task task, TaskList taskList) {
        System.out.println(TOP_BORDER);
        System.out.println(PADDING + "Got it: I've added this task:");
        System.out.println(PADDING + PADDING + task);
        System.out.println(PADDING + "Now you have " + taskList.getSize()
                + (taskList.getSize() == 1 ? " task " : " tasks ") + "in your list.");
        System.out.println(BTM_BORDER);
    }

    /**
     * Prints goodbye message.
     */
    public void printBye() {
        String farewell = " ╭---------------------------------------╮\n"
                + " |  Bye! Hope you complete your tasks!   |\n"
                + " ╰|╱ ------------------------------------╯";
        System.out.println(farewell);
    }
}
