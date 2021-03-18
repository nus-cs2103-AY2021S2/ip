package duke;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Storage class for Duke.
 */
public class Storage {
    private static final String FILE_NAME = ".data.txt";

    /**
     * Writes file with tasks on list and saves.
     *
     * @param tasks string format of all tasks on list.
     */
    public void save(String tasks) throws DukeException {
        try {
            String path = Duke.getPath();
            FileWriter fw = new FileWriter(path + FILE_NAME);
            fw.write(tasks);
            fw.close();
        } catch (Exception e) {
            throw new DukeSaveFileException();
        }
    }

    /**
     * Loads tasks stored in drive to task list.
     */
    public TaskList load() {
        try {
            TaskList tl = new TaskList();
            String path = Duke.getPath();
            File f = new File(path + FILE_NAME);
            f.createNewFile();
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String taskInfo = sc.nextLine();
                Task t = loadTask(taskInfo);
                tl.add(t);
            }
            return tl;
        } catch (Exception e) {
            return new TaskList();
        }
    }

    private Task loadTask(String taskInfo) throws DukeWrongFormatException {

        /* Split task info into 3 - 4 parts (array)
        *  first part is task type
        *  second part is if task is finished
        *  third part is task name
        *  fourth part (if applicable) is time */
        String[] taskArray = taskInfo.split(" \\| ");
        String taskName = taskArray[2];
        Task t = null;
        switch (taskArray[0]) {
        case "T":
            t = new ToDo(taskName);
            break;
        case "E":
            String[] timeArray = taskArray[3].split("-");
            LocalDateTime ldtStart = Parser.parseFileDate(timeArray[0]);
            LocalDateTime ldtEnd = Parser.parseFileDate(timeArray[1]);
            t = new Event(taskName, ldtStart, ldtEnd);
            break;
        case "D":
            LocalDateTime deadline = Parser.parseFileDate(taskArray[3]);
            t = new Deadline(taskName, deadline);
            break;
        default:
            throw new DukeWrongFormatException("loaded file");
        }
        assert t != null : "Task should not be null!";
        if (taskArray[1].equals("X")) {
            t.finishTask();
        }
        return t;
    }
}
