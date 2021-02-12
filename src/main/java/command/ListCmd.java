package command;

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
        //System.out.println(line);
        ui.customLine();

        if (lst.getListSize() == 0) {
            System.out.println("there are no tasks in your list!");
        } else if (lst.getListSize() == 1) {
            System.out.println("get to work! this is the task in your list: ");
        } else {
            System.out.println("get to work! these are the tasks in your list: ");
        }
        for (int i = 0; i < lst.getListSize(); i++) {
            System.out.println(i + 1 + "." + lst.getTask(i).toString());
        }
        
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
