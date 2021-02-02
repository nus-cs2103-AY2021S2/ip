package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private String input;

    /**
     * Creates a command for 'finding task'
     * @param input The input criteria for the task, for example: if the input criteria is 'book', you are finding
     *               all tasks with book
     */
    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList findTasks = tasks.findAll(this.input);
        if (findTasks.size() > 0) {
            ui.speak("Here are the matching tasks in your list:");
            System.out.println(findTasks.toString());
        } else {
            ui.speak("We did not find any matching tasks with the criteria: " + this.input + " :(");
        }
    }
}
