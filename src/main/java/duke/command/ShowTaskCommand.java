package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;
import main.java.duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ShowTaskCommand extends Command {

    public enum ShowTaskType {
        SHOW_TASK_ALL, SHOW_TASK_ONE
    }

    private LocalDate date;
    private ShowTaskType taskType;

    public ShowTaskCommand() {
        super();
        this.taskType = ShowTaskType.SHOW_TASK_ALL;
    }

    public ShowTaskCommand(LocalDate date) {
        super();
        this.taskType = ShowTaskType.SHOW_TASK_ONE;
        this.date = date;
    }

    public ShowTaskCommand(String date) {
        super();
        this.taskType = ShowTaskType.SHOW_TASK_ONE;

        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException dtEx) {
            System.out.println("Date/time must be in the yyyy-mm-dd format. Please try again!");
        }
    }

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
            System.out.println(tasks.toString());
        }
    }
}
