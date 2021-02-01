package main.java;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DeadlineCmd extends Command {
    private String cmd;

    public DeadlineCmd(String cmd){
        this.cmd = cmd;
    }

    @Override
    public void execute(TaskList lst, Ui ui, Storage storage) throws DuckieException {
        //String[] cmdArr = cmd.trim().split(" ");
        if (!cmd.contains("/by")) {
            throw new DuckieException("oops! please specify deadline using '/by'");
        }

        //ui.customLine();
        //System.out.println(line);
        String[] strD = cmd.trim().split("/by", 2);
        String inputDate = strD[1].trim();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HHmm", Locale.ENGLISH);
        Date date;
        try {
            date = format.parse(inputDate);
        } catch (Exception e) {
            throw new DuckieException("please enter date in the format dd-mm-yyyy HHmm");
        }
        Deadline tempD = new Deadline(strD[0], date);

        lst.addTask(tempD);
        System.out.println("ok! i've added this task:");
        System.out.println(tempD.toString());

        if (lst.getListSize() == 0) {
            System.out.println("there are no tasks in your list!");
        } else if (lst.getListSize() == 1) {
            System.out.println("you have " + lst.getListSize() + " task in your list! keep working!");
        } else {
            System.out.println("you have " + lst.getListSize() + " tasks in your list! keep working!");
        }
        //ui.customLine();
       // System.out.println(line);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
