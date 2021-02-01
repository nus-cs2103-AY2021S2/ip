package main.java.command;

import main.java.classes.DuckieException;
import main.java.classes.Storage;
import main.java.classes.TaskList;
import main.java.classes.Ui;

public class DeleteCmd extends Command {

    private String cmd;

    public DeleteCmd (String cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute(TaskList lst, Ui ui, Storage storage) throws DuckieException {
        //System.out.println(line);
        String[] cmdArr = cmd.trim().split(" ");
        ui.customLine();
        System.out.println("ok! i've deleted this task: ");
        int deleteNum = Integer.parseInt(cmdArr[1]);
        System.out.println(lst.getTask(deleteNum - 1).toString());
        lst.deleteTask(deleteNum - 1);

        if (lst.getListSize() == 0) {
            System.out.println("yay! you are done!");
        } else if (lst.getListSize() == 1) {
            System.out.println("yay! you have " + lst.getListSize() + " task left to do!");
        } else {
            System.out.println("yay! you have " + lst.getListSize() + " tasks left to do!");
        }

    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
