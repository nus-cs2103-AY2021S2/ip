package jeff.command;

import java.time.format.DateTimeParseException;

import jeff.JeffException;
import jeff.Storage;
import jeff.TaskList;
import jeff.task.Deadline;
import jeff.task.Task;

public class CommandDeadline extends Command {

    public CommandDeadline (String main, String supp) {
        super(main, supp);
    }
    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        try {
            String[] dateTime = getSupplementaryInfo().trim().split(" ", 2);
            Task deadline = new Deadline(getMainInfo(), dateTime[0], dateTime[1]);
            tasks.addTask(deadline);
            return "Got it. I've added this task:\n" + deadline + tasks.formatNumTasks();
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new JeffException("please provide date & time as YYYY-MM-DD HH:MM");
        }
    }
}
