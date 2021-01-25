package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.DeadlineTask;

public class DeadlineCommand implements Command {
    private String fullCmd;
    private String[] fullCmdStrArray;
    private Ui ui;

    public DeadlineCommand(String fullCmd, Ui ui) {
        this.fullCmd = fullCmd;
        this.fullCmdStrArray = fullCmd.split(" ");;
        this.ui = ui;
    }

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
