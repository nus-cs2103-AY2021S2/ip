package command;

import classes.DuckieException;
import classes.Storage;
import classes.TaskList;
import classes.Ui;

import java.io.IOException;

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
     * @throws DuckieException if user enters commands besides accepted ones.
     * @return
     */
    @Override
    public String execute(TaskList lst, Ui ui, Storage storage) throws DuckieException {
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
