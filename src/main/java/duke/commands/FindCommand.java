package duke.commands;

import duke.ui.Ui;

import duke.storage.Storage;

import duke.tasks.TaskList;
import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Responsible for finding task.
 */
public class FindCommand extends Command {
    private String fullCommand;

    /**
     *
     *
     * @param fullCommand
     */
    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Prints a list of tasks that matches given word in command line.
     *
     * @param tasks
     * @param ui 
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchList = new TaskList(new ArrayList<Task>());
        String matchString = fullCommand.substring(4).trim();
        for (int i = 0; i < tasks.size(); i++) {
            Task temp = tasks.get(i);
            if (temp.getDescription().contains(matchString)) {
                matchList.add(temp);
            }
        }
        if (matchList.size() != 0) {
            ui.showMatchList(matchList);
        } else {
            ui.showNoMatch();
        }
    }

    /**
     * Returns if program should exit after this command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
