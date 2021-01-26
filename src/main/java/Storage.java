import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.nio.file.Paths;

public class Storage {
    File file;

    /**
     * Initialises a newly created Storage object
     * so that it represents a File
     * where the TaskList will be stored in.
     *
     * @param pathname a String representing the path
     *                 where the list will be stored.
     */
    public Storage(String pathname) {
        file = new File(Paths.get(pathname).toString());
    }

    /**
     * Creates a new file in the path specified
     * and return an empty TaskList if file does not exist.
     * Otherwise, reads from the existing file
     * and translates its contents into a TaskList.
     *
     *
     * @return a TaskList object according to the file specified.
     * @throws IOException if an I/O error occurred.
     * @throws UnknownCommandException if file contents contain unsupported Task object.
     */
    TaskList load() throws IOException, UnknownCommandException {
        if (file.exists()) {
            return new TaskList(this.convertFileToList());
        } else {
            this.createFile();
            return new TaskList();
        }
    }

    /**
     * Parses the contents of the TaskList in the specified file
     * and translates the file into a List<Task> object.
     *
     * @return a List<Task> containing the Task objects as specified in the file.
     * @throws FileNotFoundException if file specified by the path is not found.
     * @throws UnknownCommandException if the tag stored in the TaskList
     *                                      does not correspond to Todo, Deadline or Event.
     */
    List<Task> convertFileToList() throws FileNotFoundException, UnknownCommandException {
        Scanner sc = new Scanner(file);
        List<Task> list = new ArrayList<>();

        // Splice the lines in the file
        // and generate the appropriate Tasks
        while (sc.hasNext()) {
            String item = sc.nextLine();
            String[] split = item.split(" : ", 0);

            switch (split[0]) {
            case "T":
                list.add(new Todo(split[2], split[1].equals("1")));
                break;
            case "D":
                list.add(new Deadline(split[2], split[1].equals("1"), split[3]));
                break;
            case "E":
                list.add(new Event(split[2], split[1].equals("1"), split[3]));
                break;
            default:
                throw new UnknownCommandException();
            }
        }

        sc.close();
        
        return list;
    }

    /**
     * Creates a file according to the specified pathName.
     *
     * @throws IOException if an I/O error occurred.
     */
    void createFile() throws IOException {
        // Create directory and file
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Overwrites the file with the new updated TaskList.
     *
     * @param list a List<Task> object derived from TaskList
     *             with the updated Task objects.
     * @throws IOException if an I/O error occurred.
     */
    void writeToFile(List<Task> list) throws IOException {
        FileWriter fw = new FileWriter(file.getAbsolutePath());

        StringBuffer sb = new StringBuffer();
        for (Task task : list) {
            sb.append(this.translateTask(task));
        }

        fw.write(sb.toString());
        fw.close();
    }

    /**
     * Adds a Task to the bottom of the file containing the TaskList.
     *
     * @param task a Task object to be added to the TaskList.
     * @throws IOException if an I/O error occurred.
     */
    void appendToFile(Task task) throws IOException {
        // create a FileWriter in append mode
        FileWriter fw = new FileWriter(file.getAbsolutePath(), true);
        fw.write(this.translateTask(task));
        fw.close();
    }

    /**
     * Translates a Task object into String format to be stored into the file.
     *
     * @param task a Task object to be translated to String.
     * @return a String representation of the Task Object suitable to be stored in the file.
     */
    String translateTask(Task task) {
        int isDone = task.isDone ? 1 : 0;
        if (task instanceof Todo) {
            return String.format("T : %d : %s\n", isDone, task.name);
        } else if (task instanceof Deadline) {
            return String.format("D : %d : %s : %s\n", isDone, task.name, ((Deadline) task).time);
        } else {
            return String.format("E : %d : %s : %s\n", isDone, task.name, ((Event) task).time);
        }
    }
}
