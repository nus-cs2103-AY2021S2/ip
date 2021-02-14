import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * When the user inputs a Deadline task, the DeadlineCommand is returned
 */
public class DeadlineCommand extends Command {
    private String command;

    public DeadlineCommand(String command) {
        this.command = command;
    }

    @Override
    public String executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        String input = command.trim();
        if (input.equalsIgnoreCase("deadline")) {
            throw new DukeException("Could you please specify your task? :)");
        }

        String[] strArray = input.split(" ", 2);
        if (!strArray[1].contains("/by")) {
            throw new DukeException("Uh oh! Please specify a timing using /by.");
        }

        String cmd = strArray[0];
        String cmdTask = strArray[1];
        String[] tempStrArray = cmdTask.split("/by", 2);
        String inputDate = tempStrArray[1].trim();

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HHmm", Locale.ENGLISH);
        Date date;
        try {
            date = format.parse(inputDate);
        } catch (Exception e) {
            throw new DukeException("Uh oh! Please enter a timing in the format dd-mm-yyyy HHmm");
        }
        Deadline tempTask = new Deadline(tempStrArray[0], date);
        taskList.add(tempTask);
        return ui.showTaskAdded(tempTask);
    }

    public boolean isRunning() {
        return true;
    }
}