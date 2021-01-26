package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

public class FindCommand extends Command {
    private String input;

    /**
     * Creates a command for 'finding task'
     * @param input: The input criteria for the task, for example: if the input criteria is 'book', you are finding
     *               all tasks with book
     */
    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList findTasks = tasks.findAll(this.input);
        ui.speak("Here are the matching tasks in your list:");
        System.out.println(findTasks.toString());
    }
}
