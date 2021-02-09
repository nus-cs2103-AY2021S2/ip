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
     */
    @Override
    public void execute(TaskList lst, Ui ui, Storage storage) throws DuckieException {
        String[] cmdArr = cmd.trim().split(" ");
        //System.out.println(line);
        ui.customLine();
        int taskNum = Integer.parseInt(cmdArr[1]);
        Task temp = lst.getTask(taskNum - 1);
        temp.markDone();
        System.out.println("cool! this task is marked as done:");
        System.out.println(temp.toString());
        //System.out.println(line);
        ui.customLine();

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
