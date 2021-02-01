package duke.command;

import java.time.format.DateTimeParseException;

import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;

/**
 * The AddCommand class denotes an add command to the Duke chat bot.
 */
public class AddCommand extends Command {
    private final String type;

    /**
     * Constructs an AddCommand.
     * @param type              The type of task to be added into the list.
     * @param taskDescription   The description of the task.
     */
    public AddCommand(String type, String taskDescription) {
        super(taskDescription);
        this.type = type;
    }

    /**
     * Executing the command
     * @param taskList A list of recorded tasks.
     * @param ui       A user interface.
     * @param storage  A list of recorded user inputs data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (type.equals("deadline")) {
                storage.append(taskList.addTask("deadline", taskDescription, ui));
            } else if (type.equals("event")) {
                storage.append(taskList.addTask("event", taskDescription, ui));
            } else if (type.equals("todo")) {
                storage.append(taskList.addTask("todo", taskDescription, ui));
            }
        } catch (DateTimeParseException e) {
            System.out.println("     The date time format is wrong. "
                    + "It supposed to be yyyy-MM-dd or yyyy/MM/dd");
        } catch (Exception e) {
            System.out.println("     " + e.getMessage());
        }
    }

    /**
     * Do not exit the Duke program
     * @return   A signal that indicate a continuation of the Duke program.
     */
    public boolean isExit() {
        return false;
    }
}
