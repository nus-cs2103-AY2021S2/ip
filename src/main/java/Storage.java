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
    String filePath;

    public Storage(String filePath) {
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
            file.mkdirs();
            file.createNewFile();
            System.out.println("     Welcome! New user :)");
            System.out.println("     What can I do for you?");
            System.out.println("    ____________________________________________________________");
        } else {
            scanFile();
            if (TaskList.tasks.size() == 0) {
                System.out.println("     You have no saved task!");
                System.out.println("     What can I do for you?");
                System.out.println("    ____________________________________________________________");
            } else {
                System.out.println("     You have " + TaskList.tasks.size() + " saved tasks!");
                System.out.println("     What can I do for you?");
                System.out.println("    ____________________________________________________________");
            }
        }
        return TaskList.tasks;
    }

    /**
     * Scan the file to retrieve saved tasks from the previous login.
     *
     * @throws DukeException         If an invalid command is given by the user. It also happens when there's lack of
     *                               information when task is created such as no description, date and time.
     * @throws FileNotFoundException If the attempt to open the file denoted by a specified pathname has failed.
     */
    public void scanFile() throws FileNotFoundException, DukeException {
        File file = new File(filePath);

        Scanner fileScanner = new Scanner(file);
        int i = 0;
        while (fileScanner.hasNextLine()) {
            String type = fileScanner.next();
            fileScanner.next();
            String done = fileScanner.next();
            fileScanner.next();
            if (type.equals("T")) {
                String desc = fileScanner.next() + fileScanner.nextLine();
                TaskList.tasks.add(new ToDo(desc));
                if (done.equals("1")) {
                    TaskList.tasks.get(i).markAsDone();
                }
            } else if (type.equals("D")) {
                String desc = fileScanner.next();
                while (true) {
                    String curr = fileScanner.next();
                    if (curr.equals("|")) {
                        break;
                    }
                    desc += " " + curr;
                }
                String by = fileScanner.next();
                String time = fileScanner.next();
                TaskList.tasks.add(new Deadline(desc, LocalDate.parse(by), LocalTime.parse(time)));
                if (done.equals("1")) {
                    TaskList.tasks.get(i).markAsDone();
                }
            } else {
                String desc = fileScanner.next();
                while (true) {
                    String curr = fileScanner.next();
                    if (curr.equals("|")) {
                        break;
                    }
                    desc += " " + curr;
                }
                String at = fileScanner.next();
                String time = fileScanner.next();
                TaskList.tasks.add(new Event(desc, LocalDate.parse(at), LocalTime.parse(time)));
                if (done.equals("1")) {
                    TaskList.tasks.get(i).markAsDone();
                }
            }
            i++;
        }
    }

    /**
     * Save current tasks in the tasklist to the file after the user logout so that it can be retrieve in the
     * next login.
     *
     * @throws IOException If the named file exists but is a rather than a regular file, does not exist but
     *                     cannot be created, or cannot be opened for any other reason.
     */
    public static void save() throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        for (int i = 0; i < TaskList.tasks.size(); i++) {
            Task task = TaskList.tasks.get(i);
            if (task instanceof ToDo) {
                int isDone = 0;
                if (task.isDone) {
                    isDone = 1;
                }
                fw.write("T | " + isDone + " | " + task.description);
            } else if (task instanceof Event) {
                int isDone = 0;
                if (task.isDone) {
                    isDone = 1;
                }
                fw.write("E | " + isDone + " | " + task.description + " | " + ((Event) task).at + " "
                        + ((Event) task).time);
            } else {
                int isDone = 0;
                if (task.isDone) {
                    isDone = 1;
                }
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
