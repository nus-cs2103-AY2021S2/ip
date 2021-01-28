import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with storing and retrieving tasks from a saved file in the hard drive.
 */
public class Storage {
    private final File file;

    /**
     * Constructor to create a saved file in the hard drive if it does not already exist.
     * @param path The path where the saved file is located.
     * @throws IOException If error occur when creating the new file.
     * @throws DukeException If error occur when creating the folder/file.
     */
    public Storage(String path) throws IOException, DukeException {
        String[] splits = path.split("/");
        File dir = new File(splits[0]);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                throw new DukeException("Error while creating ./data folder.");
            }
        }

        File savedTasks = new File(path);
        if (!savedTasks.exists()) {
            if (!savedTasks.createNewFile()) {
                throw new DukeException("Error while creating savedTask.txt file.");
            }
        }

        this.file = savedTasks;
    }

    /**
     * Loads in the data from the saved file.
     * @return An ArrayList of Tasks retrieved from the saved file.
     * @throws FileNotFoundException If the saved file cannot be found.
     * @throws DukeException If the saved file contains data that cannot be read.
     */
    public ArrayList<Task> load() throws FileNotFoundException, DukeException {
        Scanner sc = new Scanner(this.file);
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            String current = sc.nextLine();
            String[] splits = current.split(" ", 3);
            switch (splits[0]) {
            case "T": {
                Task toAdd = new Todo(splits[2], (splits[1].equals("1")));
                tasks.add(toAdd);
                break;
            }
            case "E": {
                Task toAdd = new Event(splits[2], LocalDate.parse(sc.nextLine()), splits[1].equals("1"));
                tasks.add(toAdd);
                break;
            }
            case "D": {
                Task toAdd = new Deadline(splits[2], LocalDate.parse(sc.nextLine()), splits[1].equals("1"));
                tasks.add(toAdd);
                break;
            }
            default: {
                throw new DukeException("Error occurred when reading data from storage file.\n"
                        + "Therefore, creating a new task list.");
            }
            }
        }
        return tasks;
    }

    /**
     * Updates the saved file in hard drive according to what is in the current TaskList.
     * @param taskList Contains the tasks at hand.
     * @throws IOException If a FileWriter cannot be created.
     */
    public void update(TaskList taskList) throws IOException {
        StringBuilder textToAdd = new StringBuilder();
        FileWriter fw = new FileWriter(this.file);
        for (Task curr : taskList.getTasks()) {
            if (curr instanceof Todo) {
                textToAdd.append("T ")
                        .append(curr.isDone() ? "1 " : "0 ")
                        .append(curr.getName())
                        .append(System.lineSeparator());
            } else if (curr instanceof Deadline) {
                textToAdd.append("D ")
                        .append(curr.isDone() ? "1 " : "0 ")
                        .append(curr.getName())
                        .append(System.lineSeparator())
                        .append(((Deadline) curr).getBy())
                        .append(System.lineSeparator());
            } else if (curr instanceof Event) {
                textToAdd.append("E ")
                        .append(curr.isDone() ? "1 " : "0 ")
                        .append(curr.getName())
                        .append(System.lineSeparator())
                        .append(((Event) curr).getAt())
                        .append(System.lineSeparator());
            }
        }
        fw.write(textToAdd.toString());
        fw.close();
    }
}
