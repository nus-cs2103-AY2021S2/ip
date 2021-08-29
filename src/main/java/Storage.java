import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the reading and writing of information to an external file.
 */
public class Storage {
    private String filepath;
    private ArrayList<Task> tasks;
    private Parser parser;

    /**
     * Create and initialize a Storage object.
     *
     * @param filepath The path of the file to be written to and read from.
     */
    Storage(String filepath) {
        this.filepath = filepath;
        tasks = new ArrayList<>();
        parser = new Parser();
    }

    /**
     * Reads the existing txt file and stores the text into Task objects.
     * If the file does not exist, it creates a new file.
     *
     * @return the list of tasks that contains the task objects.
     */
    public ArrayList<Task> load() {
        assert tasks != null : "tasks cannot be null";
        try {
            File myFile = new File("duke.txt");
            myFile.createNewFile();
            decideTaskType(myFile);
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Decides based on the text file, what type of Task should be stored.
     *
     * @param myFile The file to read.
     * @throws FileNotFoundException If file is not found.
     */
    public void decideTaskType(File myFile) throws FileNotFoundException {
        Scanner myReader = new Scanner(myFile);
        while (myReader.hasNextLine()) {
            String type = myReader.nextLine();
            if (type.contains("[T]")) {
                loadTodo(type);
            } else if (type.contains("[D]")) {
                loadDeadline(type);
            } else if (type.contains("[E]")) {
                loadEvent(type);
            }
        }
        myReader.close();
    }

    /**
     * Saves the current information into a txt file.
     *
     * @throws FileNotFoundException If a file duke.txt is not found.
     */
    public void save() throws FileNotFoundException {
        PrintWriter myWriter = new PrintWriter("duke.txt");
        for (Task t : TaskList.getTasklist()) {
            myWriter.println(t.toString());
        }
        myWriter.flush();
        myWriter.close();
    }

    /**
     * Loads a Todo object from the file.
     *
     * @param type A String that determines what type of Task is read.
     */
    private void loadTodo(String type) {
        String description = parser.parseTodoDescription(type);
        Task toAdd = new Todo(description);
        if (type.contains("\u2713")) {
            toAdd.markAsDone();
        }
        tasks.add(toAdd);
    }

    /**
     * Loads a Deadline object from the file.
     *
     * @param type A String that determines what type of Task is read.
     */
    private void loadDeadline(String type) {
        String description = parser.parseDeadlineEventDescription(type);
        String dateString = parser.parseDateTimeDeadline(type);
        LocalDate date = LocalDate.parse(dateString,
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Task toAdd = new Deadline(description, date);
        if (type.contains("\u2713")) {
            toAdd.markAsDone();
        }
        tasks.add(toAdd);
    }

    /**
     * Loads an Event object from the file.
     *
     * @param type A String that determines what type of Task is read.
     */
    private void loadEvent(String type) {
        String description = parser.parseDeadlineEventDescription(type);
        String timeString = parser.parseDateTimeEvent(type);
        LocalDate time = LocalDate.parse(timeString,
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Task toAdd = new Event(description, time);
        if (type.contains("\u2713")) {
            toAdd.markAsDone();
        }
        tasks.add(toAdd);
    }
}
