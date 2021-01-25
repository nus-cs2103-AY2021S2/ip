package duke.storage;

import duke.*;
import duke.command.CommandType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static final String splitter = " /&/ ";
    private static ArrayList<Task> tasks;
    private static String pathName;

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static void initialisePath(String newPathName) {
        pathName = newPathName;
    }

    public static void initialiseList() throws DukeException {
        tasks = new ArrayList<>();
        File f = new File(pathName);
        try {
            boolean isEmptyFile = f.createNewFile();
            if (!isEmptyFile) {
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    Task task = convertStringToTask(sc.nextLine());
                    tasks.add(task);
                }
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    private static Task convertStringToTask(String taskString) throws DukeException {
        String[] taskArgs = taskString.split(splitter, 4);
        try {
            CommandType type = CommandType.valueOf(taskArgs[0]);
            boolean isDone = Integer.parseInt(taskArgs[1]) == 1;
            String desc = taskArgs[2];

            Task task = null;

            switch (type) {
            case TODO:
                task = new Todo(desc);
                break;
            case DEADLINE:
                task = new Deadline(desc, LocalDateTime.parse(taskArgs[3]));
                break;
            case EVENT:
                task = new Event(desc, LocalDateTime.parse(taskArgs[3]));
                break;
            }
            if (isDone) {
                assert task != null;
                task.markDone();
            }
            return task;
        } catch (Exception e) {
            throw new DukeException("There seems to be something wrong with the menu and we can't start up...");
        }
    }

    public static void updateDataFile() throws DukeException {
        try {
            FileWriter fw = new FileWriter(pathName);
            StringBuilder sb = new StringBuilder();
            for (Task task : tasks) {
                sb.append(task.getFormattedString());
            }
            fw.write(sb.toString());
            fw.close();
        } catch (Exception e) {
            throw new DukeException("Oh no! I can't seem to update your menu...");
        }
    }
}
