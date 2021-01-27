package Commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Commands {
    public static String ind = "    ";
    public static String line = ind + "____________________________________________________________\n" + ind;


    private void goodbye(Storage storage) {
        storage.saveFile();
        System.out.println(line + "Bye. Kobe saved your list.\n" + ind
                + "Kobe hopes to see you again soon!\n" + line);
    }

    private void showList(TaskList tasks, Ui ui) {
        System.out.print(line + "Here are the tasks in your list:\n");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.print(ind + (i+1) + ". " + tasks.get(i).toString() + "\n");
        }
        ui.showLine();
    }


}