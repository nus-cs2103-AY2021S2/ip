package duke;

import java.time.LocalDate;

public class TimedTask /* <T extends TimedTask> */ extends Task{
    LocalDate date;

    TimedTask() {
    }
    /*
    static <T> T fileReader(String line, String regex) {
        TimedTask<T> t = new T<>();
        if (line.charAt(5) == 'X') {
            t.isDone = true;
        } else {
            t.isDone = false;
        }
        String[] lines = line.substring(7).trim().split(regex);
        t.task = lines[0].substring(0, lines[0].length() - 2).trim();
        t.date = lines[1].substring(0, lines[1].length() - 1);
        return t;
    }
    */

}
