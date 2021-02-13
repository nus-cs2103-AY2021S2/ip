package command;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import classes.DuckieException;
import classes.Storage;
import classes.TaskList;
import classes.Ui;

public class EventCmd extends Command {
    private String cmd;

    /**
     * Constructor method.
     * @param cmd User input command.
     */
    public EventCmd(String cmd) {
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
        if (!cmd.contains("/at")) {
            throw new DuckieException("oops! please specify the time of your event using '/at'");
        }

        String[] strE = cmd.trim().split("/at", 2);

        String inputDate = strE[1].trim();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HHmm", Locale.ENGLISH);
        Date date;
        try {
            date = format.parse(inputDate);
        } catch (Exception e) {
            throw new DuckieException("please enter date in the format dd-mm-yyyy HHmm");
        }
        String[] description = strE[0].split(" ", 2);
        Event tempE = new Event(description[1], date);
        lst.addTask(tempE);
        try {
            storage.saveTasks(lst.getTaskList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (lst.getListSize() == 0) {
            return "there are no tasks in your list!";
        } else if (lst.getListSize() == 1) {
            return "ok! i've added this task: " + tempE.toString()
                    + "\n" + "you have " + lst.getListSize() + " task in your list! keep working!"
                    + "\n" + ui.customLine();
        } else {
            return "ok! i've added this task: " + tempE.toString()
                    + "\n" + "you have " + lst.getListSize() + " tasks in your list! keep working!"
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
