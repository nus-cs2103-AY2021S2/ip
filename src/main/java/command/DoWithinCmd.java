package command;

import java.io.IOException;
import java.util.Date;

import classes.DuckieException;
import classes.Storage;
import classes.TaskList;
import classes.Ui;

public class DoWithinCmd extends Command {
    private String cmd;

    public DoWithinCmd(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String execute(TaskList lst, Ui ui, Storage storage) throws DuckieException {
        cmd = cmd.trim();
        if (cmd.equals("doWithin")) {
            throw new DuckieException("oops! doWithin cannot be empty!");
        } else if (!cmd.contains("/from") || !cmd.contains("/to")) {
            throw new DuckieException("oops! please specify the time of your "
                   + "do within task using '/from' and '/to'");
        }

        String[] strFrom = cmd.split("/from", 2);
        String[] dateStr1 = strFrom[1].trim().split(" ");
        Date date1 = DateParserDw.parse(dateStr1[0]);

        String[] strTo = cmd.split("/to", 2);
        Date date2 = DateParserDw.parse(strTo[1]);

        String[] description = strFrom[0].split(" ", 2);
        DoWithin tempDW = new DoWithin(description[1], date1, date2);
        lst.addTask(tempDW);
        try {
            storage.saveTasks(lst.getTaskList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (lst.getListSize() == 0) {
            return "there are no tasks in your list!";
        } else if (lst.getListSize() == 1) {
            return "ok! i've added this task: " + tempDW.toString()
                    + "\n" + "you have " + lst.getListSize() + " task in your list! keep working!"
                    + "\n" + ui.customLine();
        } else {
            return "ok! i've added this task: " + tempDW.toString()
                    + "\n" + "you have " + lst.getListSize() + " tasks in your list! keep working!"
                    + "\n" + ui.customLine();
        }
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}

