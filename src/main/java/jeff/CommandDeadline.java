package jeff;

import java.time.format.DateTimeParseException;

public class CommandDeadline extends Command{

    CommandDeadline (String main, String supp) {
        super(main, supp);
    }
    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        try {
            String[] dateTime = supplementaryInfo.split("by ")[1].split(" ", 2);
            Task deadline = new Deadline(mainInfo, dateTime[0], dateTime[1]);
            tasks.addTask(deadline);
            return "Got it. I've added this task:\n" + deadline + tasks.queryNumTasks();
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new JeffException("please provide date & time as YYYY-MM-DD HH:MM");
        }
    }
}
