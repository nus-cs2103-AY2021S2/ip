package duke;

/**
 * The UI class handles the printing of statements for the user to see.
 */
public class Ui {
    /**
     * Executes the command by printing out the relevant statements to the user
     * 
     * @param command  the command issued by the user
     * @param taskList the list of tasks that information may be needed from
     * @param status   the status of the newly added task
     */
    public static void execute(Command command, TaskList taskList, String status) {
        switch (command) {
            case BYE:
                System.out.println("    Bye bye, catch you soon.");
                break;
            case EVENT:
            case TODO:
            case DEADLINE:
                System.out.println("    Got it. I've added this task: ");
                break;
            case DELETE:
                System.out.println("    Noted. I've removed this task:");
                break;
            case DONE:
                System.out.println("    Nice! I've marked this task as done:");
                break;
            case LIST:
                System.out.println("    Here are the tasks in your list:");
                taskList.print();
                break;
            case FIND:
                System.out.println("    Here are the matching tasks in your list:");
                taskList.printContains(status);
                break;
            case NONE:
                break;

        }
        if (!(command.equals(Command.LIST) || command.equals(Command.BYE) || command.equals(Command.FIND))) {
            System.out.println("      " + status);
            System.out.println("    Now you have " + taskList.numberOfTasks() + " tasks in the list.\n");
        }

    }

    public static void greeting() {
        System.out.println("Hey yo, I'm Travis.\nI make you work. \n");
    }

    public static void exception(String exceptionMessage) throws TaskException {
        throw new TaskException(exceptionMessage);
    }
}
