package command;

import java.io.IOException;

import classes.DuckieException;
import classes.Storage;
import classes.TaskList;
import classes.Ui;

public class ByeCmd extends Command {

    private String cmd;

    /**
     * Constructor method.
     * @param cmd User input command.
     */
    public ByeCmd (String cmd) {
        this.cmd = cmd;
    }

    /**
     * Execute method.
     * @param lst a TaskList object containing Task Objects.
     * @param ui a Ui object.
     * @param storage a storage object.
     * @return System response to user input.
     * @throws DuckieException if user enters commands besides accepted ones.
     */
    @Override
    public String execute(TaskList lst, Ui ui, Storage storage) throws DuckieException {
        cmd = cmd.trim();
        if (!cmd.equals("bye")) {
            throw new DuckieException("please enter 'bye' only!");
        }
        try {
            storage.saveTasks(lst.getTaskList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ui.endMessage();
    }

    /**
     * Method to determine whether to exit program.
     * @return Returns true to exit program upon 'Bye' command.
     */
    @Override
    public boolean isEnd() {
        return true;
    }
}
