import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DukeStorage {
    private File file;

    private DukeStorage(File file) {
        this.file = file;
    }

    /**
     * Hard coded creation of java.io.File at path "./data/duke.txt"
     *
     * @param filePath Path of the file to store tasks info from Duke
     * @return DukeStorage object to handle all storage changes
     */
    public static DukeStorage createStorage(String filePath) {
        // Hard coded check for valid directory
        if (!new File("./data").exists()) {
            new File("./data").mkdir();
        }

        File file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Storage file cannot be created. List created will not be saved.");
        } finally {
            return new DukeStorage(file);
        }
    }

    /**
     * Reads and creates the tasks from the file to store in the list. List will not change if file is not found/created.
     *
     * @return Appended list of tasks.
     */
    public void loadTaskList(List<Task> tasks) {
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                boolean isDone = reader.nextInt() == 1;
                String command = reader.nextLine();
                Task newTask = Duke.dispatchTaskCreation(command);  // Cyclic dependency!
                if (isDone) {
                    newTask.markDone();
                }
                tasks.add(newTask);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates the boolean value of a task in this instance's file, which is the first character of line taskNumber.
     *
     * @param taskNumber Refers to the line of the task in the file.
     * @throws DukeException if the file cannot be read or written on.
     */
    public void updateTaskDone(int taskNumber) throws DukeException {
        try {
            Scanner reader = new Scanner(file);
            StringBuffer buffer = new StringBuffer();
            int lineNum = 0;
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (lineNum == taskNumber) {
                    line = line.replaceFirst("0", "1");
                }
                buffer.append(line + "\n");
                lineNum++;
            }
            reader.close();

            FileWriter writer = new FileWriter(file);
            writer.append(buffer);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
