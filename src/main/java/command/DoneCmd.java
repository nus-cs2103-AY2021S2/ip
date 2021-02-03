package main.java.command;

import main.java.classes.DuckieException;
import main.java.classes.Storage;
import main.java.classes.Task;
import main.java.classes.TaskList;
import main.java.classes.Ui;

public class DoneCmd extends Command {

    private String cmd;

    public DoneCmd (String cmd) {
        this.cmd = cmd;
    }

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

    @Override
    public boolean isEnd() {
        return false;
    }
}
