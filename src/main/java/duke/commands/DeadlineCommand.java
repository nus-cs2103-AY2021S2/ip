package duke.commands;

import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private String task;
    private LocalDateTime dateTime;

    public DeadlineCommand(TaskList taskList, Ui ui, Storage storage, String task, LocalDateTime dateTime) {
        super(taskList, ui, storage);
        this.task = task;
        this.dateTime = dateTime;
    }

    @Override
    public void execute() {
        Deadline d = new Deadline(this.task, this.dateTime);
        System.out.println("Got it. I've added this task:\n" + d);
        this.taskList.addTask(d);
    }
}
