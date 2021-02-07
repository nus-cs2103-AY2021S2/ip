package duke.commands;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskHandler;
import duke.exceptions.ChatBotException;
import duke.tasks.Task;

/**
 * Represents a command line to view the schedule of the day
 */
public class ScheduleCommand extends ChatBotCommand {
    private String date;
    /**
     * Constructor for the ScheduleCommand class.
     * @param date LocalDate for the date of the schedule.
     */
    public ScheduleCommand(String date) {
        this.date = date;
    }

    /**
     * Returns list of tasks.
     *
     * @param th TaskHandler.
     * @param storage Storage to write and read file.
     * @return Command response.
     * @throws ChatBotException if encounters error.
     */
    public String runTask(TaskHandler th, Storage storage) throws ChatBotException {
        ArrayList<Task> tasks = th.findTaskOfDay(this.date);
        StringBuilder output = new StringBuilder("Here is the schedule of the day: \n");
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            output.append(num)
                    .append(": ")
                    .append(tasks.get(i))
                    .append("\n");
        }

        if (tasks.size() == 0) {
            output.append("--- There is no task on the day ---");
        }
        return output.toString();
    }
}
