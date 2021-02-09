import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

/**
 * A class that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    static String filePath;
    static Scanner fileScanner;

    public Storage(String filePath) {
        assert filePath.length() > 0;
        this.filePath = filePath;
    }

    /**
     * Check if there's any saved tasks and returns a list of tasks saved in the previous login.
     *
     * @return List of saved tasks.
     * @throws DukeException If an invalid command is given by the user. It also happens when there's lack of
     *                       information when task is created such as no description, date and time.
     * @throws IOException   If the named file exists but is a rather than a regular file, does not exist but
     *                       cannot be created, or cannot be opened for any other reason
     */
    public List<Task> check() throws IOException, DukeException {
        File file = new File(filePath);
        if (!file.exists()) {
            File directory = new File("data");
            directory.mkdirs();
            file.createNewFile();
            System.out.println("     Welcome! New user :)");
            Duke.respond = "Welcome! New user :)\n" + "What can I do for you?";
        } else if (file.exists()) {
            scanFile();
            if (TaskList.tasks.size() == 0) {
                System.out.println("     You have no saved task!");
                Duke.respond = "You have no saved task!\n" + "What can I do for you?";
            } else {
                System.out.println("     You have " + TaskList.tasks.size() + " saved tasks!");
                Duke.respond = "You have " + TaskList.tasks.size() + " saved tasks!\n" + "What can I do for you?";
            }
        }
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        return TaskList.tasks;
    }


    /**
     * Scan the file to retrieve saved tasks from the previous login.
     *
     * @throws FileNotFoundException If the attempt to open the file denoted by a specified pathname has failed.
     */
    public void scanFile() throws FileNotFoundException {
        File file = new File(filePath);
        fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String type = fileScanner.next();
            fileScanner.next();
            String done = fileScanner.next();
            fileScanner.next();
            scanTask(type, done);
        }
    }

    /**
     * Scan details of task based on type given.
     */
    public void scanTask(String type, String done) {
        int index = TaskList.tasks.size();
        if (type.equals("T")) {
            TaskList.tasks.add(new ToDo(fileScanner.nextLine().substring(1)));
            if (done.equals("1")) {
                TaskList.tasks.get(index).markAsDone();
            }
            return;
        }
        String description = fileScanner.next();
        while (true) {
            String nextString = fileScanner.next();
            if (nextString.equals("|")) {
                break;
            }
            description += " " + nextString;
        }
        if (type.equals("D")) {
            TaskList.tasks.add(new Deadline(description, LocalDate.parse(fileScanner.next()),
                    LocalTime.parse(fileScanner.next())));
        } else if (type.equals("E")) {
            String at = fileScanner.next();
            String time = fileScanner.next();
            TaskList.tasks.add(new Event(description, LocalDate.parse(at), LocalTime.parse(time.substring(0, 5)),
                    LocalTime.parse(time.substring(6, 11))));
        }
        if (done.equals("1")) {
            TaskList.tasks.get(index).markAsDone();
        }
    }

    /**
     * Save current tasks in the taskList to the file after the user logout so that it can be retrieve in the
     * next login.
     *
     * @throws IOException If the named file exists but is a rather than a regular file, does not exist but
     *                     cannot be created, or cannot be opened for any other reason.
     */
    public static void save() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < TaskList.tasks.size(); i++) {
            int isDone = 0;
            Task task = TaskList.tasks.get(i);
            if (task.isDone) {
                isDone = 1;
            }
            if (task instanceof ToDo) {
                fw.write("T | " + isDone + " | " + task.description);
            } else if (task instanceof Event) {
                fw.write("E | " + isDone + " | " + task.description + " | " + ((Event) task).at + " "
                        + ((Event) task).start + "-" + ((Event) task).end);
            } else if (task instanceof Deadline) {
                fw.write("D | " + isDone + " | " + task.description + " | " + ((Deadline) task).by + " "
                        + ((Deadline) task).time);
            }
            if (i != TaskList.tasks.size() - 1) {
                fw.write("\n");
            }
        }
        fw.close();
    }
}
