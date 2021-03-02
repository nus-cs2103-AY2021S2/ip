package command;

import java.io.IOException;

import classes.DuckieException;
import classes.Storage;
import classes.TaskList;
import classes.Ui;

public class FindCmd extends Command {
    private String cmd;

    public FindCmd(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String execute(TaskList lst, Ui ui, Storage storage) throws DuckieException {
        cmd = cmd.trim();
        if (cmd.equals("find")) {
            throw new DuckieException("oops! find cannot be empty!");
        }
        String[] cmdArr = cmd.split(" ");
        String output = "";
        int taskSize = lst.getListSize();
        for (int i = 1; i < taskSize + 1; i++) {
            if (lst.getTask(i - 1).getDescription().contains(cmdArr[1])) {
                output = output + "\n" + String.format("%s. %s", i, lst.getTask(i - 1).toString());
            }
        }
        try {
            storage.saveTasks(lst.getTaskList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ui.findMessage() + output + "\n" + ui.customLine();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
