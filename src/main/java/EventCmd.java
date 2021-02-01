package main.java;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EventCmd extends Command {
    private String cmd;

    public EventCmd(String cmd){
        this.cmd = cmd;
    }

    @Override
    public void execute(TaskList lst, Ui ui, Storage storage) throws DuckieException {
        //String[] cmdArr = cmd.trim().split(" ");
        if (!cmd.contains("/at")) {
            throw new DuckieException("oops! please specify the time of your event using '/at'");
        }

        //System.out.println(line);
        //ui.customLine();
        //String[] cmdArr = cmd.trim().split(" ");
        String[] strE = cmd.trim().split("/at", 2);

        String inputDate = strE[1].trim();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HHmm", Locale.ENGLISH);
        Date date;
        try {
            date = format.parse(inputDate);
        } catch (Exception e) {
            throw new DuckieException("please enter date in the format dd-mm-yyyy");
        }
        Event tempE = new Event(strE[0], date);
        lst.addTask(tempE);
        System.out.println("ok! i've added this task:");
        System.out.println(tempE.toString());

        if (lst.getListSize() == 0) {
            System.out.println("there are no tasks in your list!");
        } else if (lst.getListSize() == 1) {
            System.out.println("you have " + lst.getListSize() + " task in your list! keep working!");
        } else {
            System.out.println("you have " + lst.getListSize() + " tasks in your list! keep working!");
        }
        //System.out.println(line);
        //ui.customLine();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
