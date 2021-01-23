package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    private static final DateTimeFormatter PRINT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    static File file;
    static Scanner sc;
    static FileWriter writer;

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
                    LocalDateTime.from(PRINT_FORMAT.parse(command.substring(position + 3))));
            if (command.charAt(4) != '0') {
                result.markAsDone();
            }
            return result;
        } else {
            int position = command.indexOf("@@@");
            Event result = new Event(command.substring(8, position - 1),
                    LocalDateTime.from(PRINT_FORMAT.parse(command.substring(position + 3))));
            if (command.charAt(4) != '0') {
                result.markAsDone();
            }
            return result;
        }
    }

    public static String saveData(Task task) {
        if (task.getSaveType() == "T") {
            return task.getSaveType() + " | " + (task.getStatus() ? "1" : "0")
                    + " | " + task.getDescription() + "\n";
        } else {
            return task.getSaveType() + " | " + (task.getStatus() ? "1" : "0")
                    + " | " + task.getDescription() + " @@@" + task.getSaveTime() + "\n";
        }
    }

    public static void init() throws IOException {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        file = new File("data" + File.separatorChar + "data.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        sc = new Scanner(file);
    }

    public static TaskList loadToList() {
        TaskList list = new TaskList();
        while (Objects.requireNonNull(sc).hasNextLine()) {
            list.addJob(loadData(sc.nextLine()));
        }
        return list;
    }

    public static void writeToData(TaskList list) {
        String saveData = "";
        for (int i = 0; i < list.getSize(); i++) {
            saveData = saveData.concat(saveData(list.getJob(i)));
        }
        try {
            writer = new FileWriter(file);
            writer.write(saveData);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
