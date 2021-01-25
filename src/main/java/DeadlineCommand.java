import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand implements Command {
    private String fullCmd;
    private String[] fullCmdStrArray;
    private Ui ui;

    TaskList taskList = Duke.taskList;

    public DeadlineCommand(String fullCmd, Ui ui) {
        this.fullCmd = fullCmd;
        this.fullCmdStrArray = fullCmd.split(" ");;
        this.ui = ui;
    }

    @Override
    public void run() throws DukeException {
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
            ui.printAddToList(newDeadlineTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ui.deadlineFormatError());
        } catch (DateTimeParseException e) {
            throw new DukeException(ui.dateFormatError());
        }
    }
}
