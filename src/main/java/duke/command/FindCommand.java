package duke.command;

import java.util.ArrayList;

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
    public ArrayList<String> execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<String> returnMsg = new ArrayList<>();

        TaskList findTasks = tasks.findAll(this.input);
        if (findTasks.size() > 0) {
            returnMsg.add(ui.speak("Here are the matching tasks in your list:"));
            returnMsg.add(findTasks.toString());
        } else {
            returnMsg.add(ui.speak("We did not find any matching tasks with the criteria: " + this.input + " :("));
        }
        return returnMsg;
    }
}
