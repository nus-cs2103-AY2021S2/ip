package command;

import java.io.IOException;

import classes.DuckieException;
import classes.Storage;
import classes.TaskList;
import classes.Ui;

public class ListCmd extends Command {
    private String cmd;

    /**
     * Constructor method.
     * @param cmd User input command.
     */
    public ListCmd (String cmd) {
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
        cmd = cmd.trim();
        if (!cmd.equals("list")) {
            throw new DuckieException("please enter 'list' only!");
        }

        String output = "";

        for (int i = 0; i < lst.getListSize(); i++) {
            int count = i + 1;
            String cur = count + "." + lst.getTask(i).toString();
            output = output + "\n" + cur;
        }
        try {
            storage.saveTasks(lst.getTaskList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (lst.getListSize() == 0) {
            return "there are no tasks in your list!"
                    + "\n" + ui.customLine();
        } else if (lst.getListSize() == 1) {
            return "get to work! this is the task in your list: " + output
                    + "\n" + ui.customLine();
        } else {
            return "get to work! these are the tasks in your list: " + output
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
