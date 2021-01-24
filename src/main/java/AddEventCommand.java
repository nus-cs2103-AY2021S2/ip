import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {
    private Event event;

    public AddEventCommand(String details) throws DukeException {
        try {
            if (details.isEmpty()) {
                throw new DukeException("You can't create an empty event!");
            }
            String[] detailsArr = details.split(" /at ", 2);
            if (detailsArr.length != 2) {
                throw new DukeException("You can't add an event without a datetime!");
            }
            LocalDateTime date = Parser.parseDateTimeFromInput(detailsArr[1]);
            this.event = new Event(detailsArr[0], date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please follow the datetime format of dd/mm/yyyy hhmm.");
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(event);
        ui.printAddTaskReport(event, tasks);
        storage.saveTasksToFile(tasks);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}