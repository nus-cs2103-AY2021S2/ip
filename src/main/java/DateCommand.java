import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateCommand extends Command {

    public DateCommand(String info) {
        super(info);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String time;

        if (info.equals("")) {
            throw new DukeException("OOPS!!! The description cannot be empty.");
        } else {
            try {
                LocalDate date = LocalDate.parse(info);
                assert date != null;
                time = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException e) {
                throw new DukeException("OOPS!!! The timing is not in the correct format.");
            }
            ui.showDate(time, tasks.searchDateTask(time));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
