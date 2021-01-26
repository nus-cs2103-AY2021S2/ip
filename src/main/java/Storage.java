import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;

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

    private void addTodo(String[] parts, TaskList tasks) {
        Task todo = new Todo(parts[2]);
        if (parts[1].equals("X")) {
            todo.markDone();
        }
        tasks.add(todo);
    }

    private void addDeadline(String[] parts, TaskList tasks) throws DateTimeParseException {
        Task deadline = new Deadline(parts[2], parts[3]);
        if (parts[1].equals("X")) {
            deadline.markDone();
        }
        tasks.add(deadline);
    }

    private void addEvent(String[] parts, TaskList tasks) {
        Task event = new Event(parts[2], parts[3]);
        if (parts[1].equals("X")) {
            event.markDone();
        }
        tasks.add(event);
    }

    private void parseLine(String line, TaskList tasks, Ui ui) {
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
        } catch (UnknownCommandException exception) {
            ui.reply(ui.formatLine(exception.getMessage()));
        } catch (DateTimeParseException exception) {
            ui.reply(ui.formatLine("☹ Please provide dates in the \"dd/mm/yyyy hhmm\" or \"dd/mm/yyyy\" format"));
        }
    }

    /**
     * Reads and processes data from file.
     *
     * @param tasks List of tasks.
     * @param ui Ui object.
     * @throws IOException If an error occurs while reading from the file.
     */
    public void readFromFile(TaskList tasks, Ui ui) throws IOException {
        if (!this.file.createNewFile()) {
            Scanner fileSc = new Scanner(this.file);
            while (fileSc.hasNextLine()) {
                parseLine(fileSc.nextLine(), tasks, ui);
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
