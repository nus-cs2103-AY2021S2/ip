package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.EventTask;

/**
 * The EventCommand class encapsulates information and methods about a EventCommand.
 */
public class EventCommand implements Command {
    private String fullCmd;
    private String[] fullCmdStrArray;
    private Ui ui;

    /**
     * Create and initialize a Event Command.
     *
     * @param fullCmd The full user input in String form.
     * @param ui The ui object responsible for displaying event messages to the CLI.
     */
    public EventCommand(String fullCmd, Ui ui) {
        this.fullCmd = fullCmd;
        this.fullCmdStrArray = fullCmd.split(" ");;
        this.ui = ui;
    }

    /**
     * Processes the event command by adding a new event task to the list of tasks,
     * writing it into the saved data file of tasks and displaying a message on the CLI.
     *
     * @param storage The storage object that writes data to the saved data file of tasks.
     * @param taskList The list of tasks.
     * @throws DukeException if the format of the Event command is invalid.
     */
    @Override
    public String run(Storage storage, TaskList taskList) throws DukeException {
        if (fullCmdStrArray.length == 1) { // handle event without parameters
            throw new DukeException(ui.eventFormatError());
        }
        try {
            String eTaskDetails = fullCmd.substring(6); // remove "event "
            String[] eTaskDetailsArray = eTaskDetails.split(" /at ");
            String eTaskName = eTaskDetailsArray[0];
            String eTaskDate = eTaskDetailsArray[1];
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime ldt = LocalDateTime.parse(eTaskDate, dtf);
            EventTask newEventTask = new EventTask(eTaskName, ldt);
            taskList.add(newEventTask);
            storage.saveTaskList(taskList);
            return ui.returnAddToListMsg(newEventTask, taskList);
        } catch (ArrayIndexOutOfBoundsException e) { // handle wrong formats
            throw new DukeException(ui.eventFormatError());
        } catch (DateTimeParseException e) {
            throw new DukeException(ui.dateFormatError());
        }
    }
}
