package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Storage {
    private final String PATH_NAME = "C:/users/chian/Desktop/CS2103/ip/data/";
    private final String FILE_NAME = "data.txt";

    /**
     * Writes file with tasks on list and saves.
     *
     * @param tasks string format of all tasks on list.
     */
    public void save(String tasks) {
        try {
            FileWriter fw = new FileWriter(PATH_NAME + FILE_NAME);
            fw.write(tasks);
            fw.close();
        } catch (IOException e) {
            Ui.printMessage("Unable to save file!");
        }
    }

    /**
     * Loads tasks stored in drive to task list.
     */
    public TaskList load() {
        try {
            TaskList tl = new TaskList();
            File f = new File(PATH_NAME + FILE_NAME);
            f.createNewFile();
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String strTask = sc.nextLine();
                String[] taskArray = strTask.split(" \\| ");
                Task t = null;
                switch (taskArray[0]) {
                case "T":
                    t = new ToDo(taskArray[2]);
                    break;
                case "E":
                    String[] timeArray = taskArray[3].split("-");
                    LocalDateTime ldtStart = Parser.parseFileDate(timeArray[0]);
                    LocalDateTime ldtEnd = Parser.parseFileDate(timeArray[1]);
                    t = new Event(taskArray[2], ldtStart, ldtEnd);
                    break;
                case "D":
                    LocalDateTime ldt = Parser.parseFileDate(taskArray[3]);
                    t = new Deadline(taskArray[2], ldt);
                    break;
                default:
                    throw new DukeWrongFormatException("loaded file");
                }
                assert t != null : "Task should not be null!";
                if (taskArray[1].equals("X")) {
                        t.finishTask();
                }
                tl.add(t);
            }
            return tl;
        } catch (Exception e) {
            return new TaskList();
        }
    }
}
