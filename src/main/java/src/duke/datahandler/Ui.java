package duke.datahandler;

import duke.enums.Command;

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
    public static String execute(Command command, TaskList taskList, String status) {
        String output = "";
        switch (command) {
        case BYE:
            output += "    Bye bye, catch you soon.";
            break;
        case EVENT:
        case TODO:
        case DEADLINE:
            output += "    Got it. I've added this task: \n";
            break;
        case DELETE:
            output += "    Noted. I've removed this task:\n";
            break;
        case DONE:
            output += "    Nice! I've marked this task as done:\n";
            break;
        case LIST:
            output += "    Here are the tasks in your list:\n";
            output += taskList.listOutTasks();
            break;
        case FIND:
            output += "    Here are the matching tasks in your list:";
            output += taskList.findContains(status);
            break;
        case SNOOZE:
            output += "    Task successfully snoozed, this is the new status:\n";
            break;
        case NONE:
            break;
        default:

        }
        if (!(command.equals(Command.LIST) || command.equals(Command.BYE) || command.equals(Command.FIND))) {
            output += "      " + status + "\n";
            output += "    Now you have " + taskList.numberOfTasks() + " tasks in the list.\n";
        }
        assert output != "";
        return output;
    }

}
