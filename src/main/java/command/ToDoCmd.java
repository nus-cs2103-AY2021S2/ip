package command;

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
     * @throws DuckieException if user enters commands besides accepted ones.
     * @return
     */
    @Override
    public String execute(TaskList lst, Ui ui, Storage storage) throws DuckieException {
        String[] cmdArr = cmd.trim().split(" ");
        //System.out.println(line);
        ui.customLine();
        ToDo tempT = new ToDo(cmdArr[1]);
        lst.addTask(tempT);
        System.out.println("ok! i've added this task:");
        System.out.println(tempT.toString());

        if (lst.getListSize() == 0) {
            System.out.println("there are no tasks in your list!");
        } else if (lst.getListSize() == 1) {
            System.out.println("you have " + lst.getListSize() + " task in your list! keep working!");
        } else {
            System.out.println("you have " + lst.getListSize() + " tasks in your list! keep working!");
        }
        //System.out.println(line);
        ui.customLine();
        return null;
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
