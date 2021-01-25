import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventCommand implements Command {
    private String fullCmd;
    private String[] fullCmdStrArray;
    private Ui ui;

    public EventCommand(String fullCmd, Ui ui) {
        this.fullCmd = fullCmd;
        this.fullCmdStrArray = fullCmd.split(" ");;
        this.ui = ui;
    }

    @Override
    public void run(Storage storage, TaskList taskList) throws DukeException {
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
            ui.printAddToList(newEventTask, taskList);
        } catch (ArrayIndexOutOfBoundsException e) { // handle wrong formats
            throw new DukeException(ui.eventFormatError());
        } catch (DateTimeParseException e) {
            throw new DukeException(ui.dateFormatError());
        }
    }
}
