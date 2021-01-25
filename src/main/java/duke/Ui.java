package duke;

import duke.task.Task;

public class Ui {
    private static final String TOP_BORDER = "╭------------------------------------------------------╮";
    private static final String BTM_BORDER = "╰|╱ ---------------------------------------------------╯";
    private static final String PADDING = "  ";

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

    public String listCmdError() {
        String errorMsg = "Sorry human, I do not understand your command."
                + "\n"
                + PADDING
                + "To access your list, enter 'list' with no additional descriptions.";
        return errorMsg;
    }

    public String doneCmdTooManyArgsError() {
        String errorMsg = "Sorry human, please enter only one task for me to mark as complete."
                + "\n"
                + PADDING
                + "I am unable to process more than one task at one time.";
        return errorMsg;
    }

    public String doneCmdNoArgsError() {
        String errorMsg = "Sorry human, please enter a task number.";
        return errorMsg;
    }

    public String doneCmdInvalidArgsError() {
        String errorMsg = "Sorry human, please enter the number of the task you want me to"
                + "\n"
                + PADDING
                + "mark as complete.";
        return errorMsg;
    }

    public String eventFormatError() {
        String eErrorMsg = "Invalid format. Please enter as such:"
                + "\n"
                + PADDING
                + "event <EVENT_NAME> /at <EVENT_TIME>";
        return eErrorMsg;
    }

    public String deadlineFormatError() {
        String dErrorMsg = "Invalid format. Please enter as such:"
                + "\n"
                + PADDING
                + "deadline <TASK_NAME> /by <DEADLINE_TIME>";
        return dErrorMsg;
    }

    public String dateFormatError() {
        String dateErrorMsg = "Invalid date format. Please enter as such:"
                + "\n"
                + PADDING
                + "yyyy-MM-dd HHmm (e.g. 2019-10-15 1800)";
        return dateErrorMsg;
    }

    public String deleteCmdTooManyArgsError() {
        String errorMsg = "Sorry human, please enter only one task for me to delete."
                + "\n"
                + PADDING
                + "I am unable to process more than one task at one time.";
        return errorMsg;
    }

    public void printDukeException(DukeException e) {
        System.out.println(e);
    }

    public void printDeletedMessage(Task task, TaskList taskList) {
        System.out.println(TOP_BORDER);
        System.out.println(PADDING + "Task has been deleted.");
        System.out.println(PADDING + "Just like you will be deleted someday too.");
        System.out.println(PADDING + PADDING + task);
        System.out.println(PADDING + "Now you have " + taskList.getSize()
                + (taskList.getSize() == 1 ? " task " : " tasks ") + "in your list.");
        System.out.println(BTM_BORDER);
    }

    public void printDoneMessage(Task task) {
        System.out.println(TOP_BORDER);
        System.out.println(PADDING + "Well done human on completing " + task.getTaskName() + "!");
        System.out.println(PADDING + "I have marked it as done.");
        System.out.println(PADDING + PADDING + task);
        System.out.println(BTM_BORDER);
    }


    public void printList(TaskList taskList) {
        System.out.println(TOP_BORDER);
        System.out.println(PADDING + "Here are the tasks in your list:");
        for (int i = 1; i <= taskList.getSize(); i++) {
            System.out.println(PADDING + i + ". " + taskList.getIndex(i - 1));
        }
        System.out.println(BTM_BORDER);
    }

    public void printAddToList(Task task, TaskList taskList) {
        System.out.println(TOP_BORDER);
        System.out.println(PADDING + "Got it: I've added this task:");
        System.out.println(PADDING + PADDING + task);
        System.out.println(PADDING + "Now you have " + taskList.getSize()
                + (taskList.getSize() == 1 ? " task " : " tasks ") + "in your list.");
        System.out.println(BTM_BORDER);
    }

    public void printBye() {
        String farewell = " ╭---------------------------------------╮\n"
                + " |  Bye! Hope you complete your tasks!   |\n"
                + " ╰|╱ ------------------------------------╯";
        System.out.println(farewell);
    }
}
