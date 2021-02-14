package command;

import java.io.IOException;

import classes.DuckieException;
import classes.Storage;
import classes.TaskList;
import classes.Ui;

public class ToDoCmd extends Command {

    private String cmd;

    /**
     * Constructor method.
     * @param cmd User input command.
     */
    public ToDoCmd (String cmd) {
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
        String[] cmdArr = cmd.trim().split(" ", 2);
        ToDo tempT = new ToDo(cmdArr[1]);
        lst.addTask(tempT);
        try {
            storage.saveTasks(lst.getTaskList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (lst.getListSize() == 0) {
            return "there are no tasks in your list!" + "\n" + ui.customLine();
        } else if (lst.getListSize() == 1) {
            return "ok! i've added this task: " + tempT.toString() + "\n" + "you have " + lst.getListSize()
                    + " task in your list! keep working!" + "\n" + ui.customLine();
        } else {
            return "ok! i've added this task: " + tempT.toString() + "\n" + "you have " + lst.getListSize()
                    + " tasks in your list! keep working!" + "\n" + ui.customLine();
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
