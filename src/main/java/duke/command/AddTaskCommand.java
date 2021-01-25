package main.java.duke;

import main.java.duke.task.Task;

public class AddTaskCommand extends Command {
    private Task task;

    AddTaskCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.speak(task.addMessage + (task.addMessage == null ? "" : " ") + "I've added:");
        System.out.println(task);
        ui.speak("You now have " + tasks.size() + " tasks at hand.");
    }
}
