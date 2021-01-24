package duke.data;

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

public class Data {

    private static final String PATHNAME = "./src/main/java/duke/data/data.txt";

    public static ArrayList<Task> initialiseList() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(PATHNAME);
        try {
            boolean isEmptyFile = f.createNewFile();
            if (!isEmptyFile) {
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    Task task = getTaskFromString(sc.nextLine());
                    tasks.add(task);
                }
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
        return tasks;
    }

    private static Task getTaskFromString(String taskString) throws DukeException {
        String[] taskArgs = taskString.split("::", 4);

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

    public static void updateDataFile(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(PATHNAME);
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
