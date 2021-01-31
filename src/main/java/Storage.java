import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Class which deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads tasks from a file.
     *
     * @return An ArrayList of Task Objects containing the tasks read from the file.
     */
    public ArrayList<Task> readTasksFromFile() throws FileNotFoundException {
        File f = new File(this.filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] elements = line.replace(" ", "").split("\\|", -2);
            if (elements[0].equals("D")) {
                boolean isDone = convertToBool(elements[1]);
                String description = elements[2];
                LocalDate by = LocalDate.parse(elements[3]);
                Task task = new Deadline(description, by);
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            } else if (elements[0].equals("E")) {
                boolean isDone = convertToBool(elements[1]);
                String description = elements[2];
                LocalDate at = LocalDate.parse(elements[3]);
                Task task = new Event(description, at);
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            } else if (elements[0].equals("T")) {
                boolean isDone = convertToBool(elements[1]);
                String description = elements[2];
                Task task = new ToDo(description);
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            }


        }
        return tasks;
    }

    /**
     * Writes tasks to a file.
     *
     * @param tasks An ArrayList of Task objects containing the tasks to be written to the file.
     */
    public void writeTasksToFile(ArrayList<Task> tasks) throws IOException {

        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileWriter fw = new FileWriter(this.filePath);

        String fileString = "";

        for (Task task : tasks) {
            fileString += task.fileString() + "\n";
        }
        fw.write(fileString);
        fw.close();
    }

    /**
     * Convert the String of a number into a boolean.
     *
     * @param numString String having value "1" or "0".
     * */
    public Boolean convertToBool(String numString) {
        if (numString.equals("1")) {
            return true;
        } else {
            return false;
        }

    }

}
