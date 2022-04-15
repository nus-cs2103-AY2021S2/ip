package jeff.command;

import java.time.format.DateTimeParseException;

import jeff.JeffException;
import jeff.Storage;
import jeff.TaskList;
import jeff.task.Deadline;
import jeff.task.Event;
import jeff.task.Task;

public class CommandReschedule extends Command {

    public CommandReschedule(String main, String supp) {
        super(main, supp);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        String[] dateTime = getSupplementaryInfo().trim().split(" ", 2);
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(getMainInfo()) - 1;
        } catch (NumberFormatException e) {
            throw new JeffException("indicate task number as an integer");
        }
        if (taskIndex > tasks.getNumTasks() - 1) {
            throw new JeffException("task number does not exist");
        }
        Task oldTask = tasks.getTask(taskIndex);
        Task newTask;
        try {
            if (oldTask instanceof Deadline) {
                newTask = new Deadline(oldTask.getName(), dateTime[0], dateTime[1]);
            } else if (oldTask instanceof Event) {
                newTask = new Event(oldTask.getName(), dateTime[0], dateTime[1]);
            } else {
                throw new JeffException("can only reschedule deadlines or events");
            }
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new JeffException("please provide date & time as YYYY-MM-DD HH:MM");
        }
        if (oldTask.getSymbol().equals("X")) {
            newTask.setDone();
        }
        tasks.setTask(taskIndex, newTask);
        return "I've rescheduled this task:\n" + newTask;
    }
}
