package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.DeadlineTask;
import duke.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The DeadlineCommand class encapsulates information and methods about a DeadlineCommand.
 */
public class DeadlineCommand implements Command {
    private String fullCmd;
    private String[] fullCmdStrArray;
    private Ui ui;

    public DeadlineCommand(String fullCmd, Ui ui) {
        this.fullCmd = fullCmd;
        this.fullCmdStrArray = fullCmd.split(" ");;
        this.ui = ui;
    }

    /**
     *  * Processes the deadline command by adding a new deadline task to the list of tasks,
     *  * writing it into the saved data file of tasks and displaying a message on the CLI.
     *
     * @param storage The storage object that writes data to the saved data file of tasks.
     * @param taskList The list of tasks.
     * @throws DukeException if the format of the Deadline command are invalid.
     */
    @Override
    public void run(Storage storage, TaskList taskList) throws DukeException {
        if (fullCmdStrArray.length == 1) { // handle deadline without parameters
            throw new DukeException(ui.deadlineFormatError());
        }
        try {
            String dTaskDetails = fullCmd.substring(9); // remove "deadline "
            String[] dTaskDetailsArray = dTaskDetails.split(" /by ");
            String dTaskName = dTaskDetailsArray[0];
            String dTaskDate = dTaskDetailsArray[1];
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime ldt = LocalDateTime.parse(dTaskDate, dtf);
            DeadlineTask newDeadlineTask = new DeadlineTask(dTaskName, ldt);

            taskList.add(newDeadlineTask);
            storage.saveTaskList(taskList);
            ui.printAddToList(newDeadlineTask, taskList);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ui.deadlineFormatError());
        } catch (DateTimeParseException e) {
            throw new DukeException(ui.dateFormatError());
        }
    }
}
