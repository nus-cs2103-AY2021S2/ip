package command;

import java.io.IOException;

import classes.DuckieException;
import classes.Storage;
import classes.TaskList;
import classes.Ui;

public class DeleteCmd extends Command {

    private String cmd;

    /**
     * Constructor method.
     * @param cmd User input command.
     */
    public DeleteCmd (String cmd) {
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
        assert !cmdArr[1].contains(" ");
        int deleteNum = Integer.parseInt(cmdArr[1]);
        String output = lst.getTask(deleteNum - 1).toString();
        lst.deleteTask(deleteNum - 1);

        try {
            storage.saveTasks(lst.getTaskList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (lst.getListSize() == 0) {
            return "yay! you are done!";
        } else if (lst.getListSize() == 1) {
            return "ok! i've deleted this task: " + output
                    + "\n" + "yay! you have " + lst.getListSize() + " task left to do!"
                    + "\n" + ui.customLine();
        } else {
            return "ok! i've deleted this task: " + output
                    + "\n" + "yay! you have " + lst.getListSize() + " tasks left to do!"
                    + "\n" + ui.customLine();
        }
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
