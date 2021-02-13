package command;

import classes.DuckieException;
import classes.Storage;
import classes.Task;
import classes.TaskList;
import classes.Ui;

public class DoneCmd extends Command {

    private String cmd;

    /**
     * Constructor method.
     * @param cmd User input command.
     */
    public DoneCmd (String cmd) {
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
        String[] cmdArr = cmd.trim().split(" ");

        int taskNum = Integer.parseInt(cmdArr[1]);
        Task temp = lst.getTask(taskNum - 1);
        temp.markDone();

        return "cool! this task is marked as done: " + temp.toString() + ui.customLine();
    }

    /**
     * Method to determine whether to exit program.
     * @return Returns false, program continues.
     */
    @Override
    public boolean isEnd() {
        return false;
    }
}
