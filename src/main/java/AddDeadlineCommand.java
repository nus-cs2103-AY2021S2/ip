import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    private Deadline deadline;

    public AddDeadlineCommand(String details) throws DukeException {
        try {
            if (details.isEmpty()) {
                throw new DukeException("You can't create an empty deadline!");
            }
            String[] detailsArr = details.split(" /by ", 2);
            if (detailsArr.length != 2) {
                throw new DukeException("You can't add a deadline without a datetime!");
            }
            LocalDateTime date = Parser.parseDateTimeFromInput(detailsArr[1]);
            this.deadline = new Deadline(detailsArr[0], date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please follow the datetime format of dd/mm/yyyy hhmm.");
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(deadline);
        ui.printAddTaskReport(deadline, tasks);
        storage.saveTasksToFile(tasks);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
