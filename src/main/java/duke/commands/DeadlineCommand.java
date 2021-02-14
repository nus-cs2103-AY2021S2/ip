package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.Deadline;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private TaskList taskList;
    private String taskDescription;
    private LocalDate dueDate;

    public DeadlineCommand(TaskList taskList, String taskDescription, LocalDate dueDate) {
        super(taskList);
        this.taskDescription = taskDescription;
        this.dueDate = dueDate;
    }

    public TaskList execute() {
        LocalDate dueDate = this.dueDate;
        String taskDescription = this.taskDescription;
        Deadline deadline = new Deadline(taskDescription, dueDate);
        TaskList tasks = this.getTaskList();
        tasks = tasks.add(deadline);
        return tasks;
    }

    public String toString() {
        String message = "Got it. I've added this task:\n";
        Deadline deadline = new Deadline(this.taskDescription, this.dueDate);
        message += deadline.toString() + "\n";
        message += "Now you have " + this.getTaskList().size() + " tasks in the list.\n";
        return message;
    }
}