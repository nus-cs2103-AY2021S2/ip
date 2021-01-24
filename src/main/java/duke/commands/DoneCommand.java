package duke.commands;

import duke.storage.Storage;

import duke.ui.Ui;

import duke.exceptions.*;

import duke.tasks.TaskList;

import java.io.IOException;

/**
 * Responsible for dealing with the completion of tasks.
 */
public class DoneCommand extends Command {
    private String[] checkCommands;

    /**
     * Constructs a DoneCommand with the given full command line.
     *
     * @param fullCommand Full command line input.
     */
    public DoneCommand(String fullCommand) {
        this.checkCommands = fullCommand.split(" ");
    }

    /**
     * Changes the completion status of indicated task in TaskList
     * given by command line and save changes into the save file.
     *
     * @param tasks TaskList containing the task.
     * @param ui Ui for system outputs.
     * @param storage Storage for saving contents into file.
     * @throws IOException If error happens while saving contents into file.
     * @throws DukeException If error happens while changing status of task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (checkCommands.length == 1 || !isNumber(checkCommands[1])) {
            throw new InvalidTaskSelectionException();
        }
        int num = Integer.parseInt(checkCommands[1]);
        if (num > 0 && num <= tasks.size()) {
            tasks.get(num - 1).markAsDone();
            ui.showDoneTask(tasks, num);

        } else {
            throw new TaskNotFoundException();
        }
        storage.save(tasks);
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

    private boolean isNumber(String checkString) {
        try {
            Integer.parseInt(checkString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
