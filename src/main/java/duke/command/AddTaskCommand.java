package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;
import main.java.duke.task.Task;

public class AddTaskCommand extends Command {
    private Task task;

    public AddTaskCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.speak(task.getAddMessage() + (task.getAddMessage() == null ? "" : " ") + "I've added:");
        System.out.println(task);
        ui.speak("You now have " + tasks.size() + " tasks at hand.");
    }
}
