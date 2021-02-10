import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {
    private final File file;

    /**
     * Constructor for the Storage class.
     *
     * @param dirName Name of the directory the data file is stored in.
     * @param fileName Name of the data file.
     */
    public Storage(String dirName, String fileName) {
        File dir = new File(dirName);
        if (!dir.exists()) {
            dir.mkdir();
        }

        this.file = new File(dirName + "/" + fileName);
    }

    private void markDoneAndAdd(Task task, String donePart, TaskList tasks) {
        if (donePart.equals("X")) {
            task.markDone();
        }
        tasks.add(task);
    }

    private void addTodo(String[] parts, TaskList tasks) {
        assert parts.length == 4 : "There should be 4 parts to this line of data";
        Task todo = new Todo(parts[3], parts[2]);
        markDoneAndAdd(todo, parts[1], tasks);
    }

    private void addDeadline(String[] parts, TaskList tasks) throws DateTimeParseException {
        assert parts.length == 5 : "There should be 5 parts to this line of data";
        Task deadline = new Deadline(parts[3], parts[4], parts[2]);
        markDoneAndAdd(deadline, parts[1], tasks);
    }

    private void addEvent(String[] parts, TaskList tasks) {
        assert parts.length == 5 : "There should be 5 parts to this line of data";
        Task event = new Event(parts[3], parts[4], parts[2]);
        markDoneAndAdd(event, parts[1], tasks);
    }

    private void parseLine(String line, TaskList tasks) {
        String[] parts = Arrays.stream(line.split("\\|"))
                .map(String::trim)
                .toArray(String[]::new);

        try {
            switch (parts[0]) {
            case Command.TODO:
                addTodo(parts, tasks);
                break;
            case Command.EVENT:
                addEvent(parts, tasks);
                break;
            case Command.DEADLINE:
                addDeadline(parts, tasks);
                break;
            default:
                throw new UnknownCommandException();
            }
        } catch (UnknownCommandException | DateTimeParseException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Reads and processes data from file.
     *
     * @param tasks List of tasks.
     * @throws IOException If an error occurs while reading from the file.
     */
    public void readFromFile(TaskList tasks) throws IOException {
        if (!this.file.createNewFile()) {
            Scanner fileSc = new Scanner(this.file);
            while (fileSc.hasNextLine()) {
                parseLine(fileSc.nextLine(), tasks);
            }
            fileSc.close();
        }
    }

    /**
     * Writes task data to data file.
     *
     * @param task Task to write to file.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void writeToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(this.file, true);
        fw.write(task.toFileString());
        fw.close();
    }

    /**
     * Updates task data in data file.
     *
     * @param tasks List of tasks.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void updateFile(TaskList tasks) throws IOException {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            buffer.append(tasks.getTaskAt(i).toFileString());
        }

        FileWriter fw = new FileWriter(file);
        fw.write(buffer.toString());
        fw.close();
    }
}
