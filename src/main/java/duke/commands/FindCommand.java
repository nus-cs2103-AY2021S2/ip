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
     * Constructs a FindCommand with given full command line.
     *
     * @param fullCommand Full command line input.
     */
    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Prints a list of tasks that matches given word in command line.
     *
     * @param tasks TasksList to find from.
     * @param ui Ui for system outputs.
     * @param storage Storage for saving contents into file.
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
