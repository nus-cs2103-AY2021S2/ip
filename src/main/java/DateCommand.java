import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateCommand extends Command {
    public DateCommand(String fullCommand, String action) {
        super(fullCommand, action);
    }

    @Override
    public void execute(TaskList tasks) throws DukeException {
        Ui.printLine();
        String[] inputs = this.fullCommand.split(" ");
        if (inputs.length == 1) {
            throw new DukeException("OOPS! Please give me the date that you want to check in YYYY-MM-DD format.");
        }
        LocalDate d = LocalDate.parse(inputs[1]);
        List<Task> toPrint = tasks.print(d);
        for (int i = 1; i < toPrint.size() + 1; i++) {
            Ui.print(Aligner.align(i + "." + toPrint.get(i - 1).toString()));
        }
        Ui.printLine();
    }
}
