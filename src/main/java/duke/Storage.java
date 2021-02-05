package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * A class that handles all I/O related matter.
 */
public class Storage {

    // Output date and time in this manner.
    private static final DateTimeFormatter FORMAT_PRINT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    private static File file;
    private static Scanner sc;

    /**
     * Converts data that saved in data.txt to Task object.
     *
     * @param command Strings in the format of my own design.
     * @return Tasks to be stored in the TaskList.
     */
    public static Task loadData(String command) {

        if (command.charAt(0) == 'T') {
            Todo result = new Todo(command.substring(8));
            if (command.charAt(4) != '0') {
                result.markAsDone();
            }
            return result;
        } else if (command.charAt(0) == 'D') {
            int position = command.indexOf("@@@");
            Deadline result = new Deadline(command.substring(8, position - 1),
                    LocalDateTime.from(FORMAT_PRINT.parse(command.substring(position + 3))));
            if (command.charAt(4) != '0') {
                result.markAsDone();
            }
            return result;
        } else {
            int position = command.indexOf("@@@");
            Event result = new Event(command.substring(8, position - 1),
                    LocalDateTime.from(FORMAT_PRINT.parse(command.substring(position + 3))));
            if (command.charAt(4) != '0') {
                result.markAsDone();
            }
            return result;
        }
    }

    /**
     * Converts Task to string in a designed manner.
     *
     * @param task Task that need to be saved.
     * @return Output string that need to be written into save data.
     */
    public static String saveData(Task task) {
        if (task.getSaveType() == "T") {
            return task.getSaveType() + " | " + (task.getStatus() ? "1" : "0")
                    + " | " + task.getDescription() + "\n";
        } else {
            return task.getSaveType() + " | " + (task.getStatus() ? "1" : "0")
                    + " | " + task.getDescription() + " @@@" + task.getSaveTime() + "\n";
        }
    }

    /**
     * Initialize the save data file and scanner of that file.
     *
     * @throws IOException When unable to create a file due to any security reason.
     */
    public static void init() throws IOException {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        file = new File("data" + File.separatorChar + "data.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        assert file.exists() : "Cannot save data.";
        sc = new Scanner(file);
    }

    /**
     * Loads the entire save data to a TaskList by calling multiple loadData.
     *
     * @return TaskList that presents the initial state of Duke robot.
     */
    public static TaskList loadToList() {
        TaskList list = new TaskList();
        while (Objects.requireNonNull(sc).hasNextLine()) {
            list.addJob(loadData(sc.nextLine()));
        }
        return list;
    }

    /**
     * Initialises the writer and writes to save data file by calling multiple saveData.
     *
     * @param list Data need to be converted and written.
     */
    public static void writeToData(TaskList list) {
        String saveData = "";
        for (int i = 0; i < list.getSize(); i++) {
            saveData = saveData.concat(saveData(list.getJob(i)));
        }
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(saveData);
            writer.close();
        } catch (IOException e) {
            System.out.println("Unable to save file.");
        }
    }
}
