package command;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import classes.DuckieException;
import classes.Storage;
import classes.TaskList;
import classes.Ui;



public class DeadlineCmd extends Command {
    private String cmd;

    /**
     * Constructor method.
     * @param cmd User input command.
     */
    public DeadlineCmd(String cmd) {
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

        if (!cmd.contains("/by")) {
            throw new DuckieException("oops! please specify deadline using '/by'");
        }

        String[] strD = cmd.trim().split("/by", 2);
        String[] description = strD[0].split(" ", 2);

        DateParser dateParser = new DateParser();
        Date date = dateParser.parse(strD[1]);
        Deadline tempD = new Deadline(description[1], date);
        lst.addTask(tempD);

        if (lst.getListSize() == 0) {
            return "there are no tasks in your list!";
        } else if (lst.getListSize() == 1) {
            return "ok! i've added this task: " + tempD.toString()
                    + "\n" + "you have " + lst.getListSize() + " task in your list! keep working!"
                    + "\n" + ui.customLine();
        } else {
            return "ok! i've added this task: " + tempD.toString()
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
