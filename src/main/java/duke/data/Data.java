package duke.data;

import duke.*;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.Duke.convertStringToDate;

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
                task = new Deadline(desc, convertStringToDate(taskArgs[3]));
                break;
            case EVENT:
                task = new Event(desc, convertStringToDate(taskArgs[3]));
                break;
            default:
                System.out.println("There was an error in getting the task object for " + taskString);
            }
            if (isDone) {
                assert task != null;
                task.markDone();
            }
            return task;
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        } catch (Exception e) {
            throw new DukeException("There seems to be something wrong with the menu file...");
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
