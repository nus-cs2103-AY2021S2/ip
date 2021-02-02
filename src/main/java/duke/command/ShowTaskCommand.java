package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class ShowTaskCommand extends Command {

    public enum ShowTaskType {
        SHOW_TASK_ALL, SHOW_TASK_ONE
    }

    private LocalDate date;
    private ShowTaskType taskType;

    /**
     * Creates a command for listing tasks
     */
    public ShowTaskCommand() {
        super();
        this.taskType = ShowTaskType.SHOW_TASK_ALL;
    }

    /**
     * Overloads ShowTaskCommand() method with specified date
     * @param date show tasks with specific date
     */
    public ShowTaskCommand(LocalDate date) {
        super();
        this.taskType = ShowTaskType.SHOW_TASK_ONE;
        this.date = date;
    }

    /**
     * Overloads ShowTaskCommand() method with date in string
     * @param date show tasks with specific date in string, format: yyyy-mm-dd
     */
    public ShowTaskCommand(String date) {
        super();
        this.taskType = ShowTaskType.SHOW_TASK_ONE;

        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException dtEx) {
            System.out.println("Date/time must be in the yyyy-mm-dd format. Please try again!");
        }
    }

    /**
     * Execute action to list tasks
     * @param tasks list of tasks
     * @param ui UI required for conversation
     * @param storage Storage required for .txt file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            ui.speak("You currently have no tasks! Use todo, deadline or event.");
        } else if (this.taskType == ShowTaskType.SHOW_TASK_ALL) {
            ui.speak("Here you go! Your list of items:");
            System.out.println(tasks.toString());
        } else {
            List<Task> tempTask = tasks.getByDate(date);
            ui.speak("Here you go! Your list of items:");
            System.out.println(tempTask.toString());
        }
    }
}
